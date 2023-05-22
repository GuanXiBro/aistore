package com.ai.th.service.impl;

import cn.hutool.core.util.StrUtil;
import com.ai.th.common.Result;
import com.ai.th.mapper.MenuMapper;
import com.ai.th.pojo.Menu;
import com.ai.th.service.MenuService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
    @Autowired
    private MenuMapper menuMapper;
    @Override
    public List<Menu> findAll() {
        return menuMapper.findAll();
    }

    @Override
    public int insert(Menu menu) {
        return menuMapper.insert(menu);
    }

    @Override
    public int update(Menu menu) {
        return menuMapper.update(menu);
    }

    @Override
    public Integer removeById(Integer id) {
        return menuMapper.removeById(id);
    }

    @Override
    public List<Menu> findPage(Integer pageNum, Integer pageSize, String name) {
        return menuMapper.findPage(pageNum,pageSize,name);
    }

    @Override
    public Integer selectTotal(String name) {
        return menuMapper.selectTotal(name);
    }

    @Override
    public List<Menu> findMenu(String name) {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        if(StrUtil.isNotBlank(name)){
            queryWrapper.like("name",name);
        }
        queryWrapper.orderByAsc("id");
        List<Menu> list = menuMapper.selectList(queryWrapper);
        List<Menu> parentNode= list.stream().filter(menu -> menu.getPid() == null).collect(Collectors.toList());
        for (Menu menu : parentNode) {
            menu.setChildren(list.stream().filter(m -> menu.getId().equals(m.getPid())).collect(Collectors.toList()));
        }
        return parentNode;
    }
}
