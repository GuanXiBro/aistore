package com.ai.th.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.ai.th.mapper.RoleMapper;
import com.ai.th.mapper.RoleMenuMapper;
import com.ai.th.pojo.Menu;
import com.ai.th.pojo.Role;
import com.ai.th.pojo.RoleMenu;
import com.ai.th.service.MenuService;
import com.ai.th.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Resource
    private RoleMapper roleMapper;
    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Resource
    private MenuService menuService;

    @Transactional
    @Override
    public void setRoleMenu(Integer rid, List<Integer> menuIds) {
        //先删除有当前角色所有的绑定关系
        roleMenuMapper.deleteByRoleId(rid);
        List<Integer> menuIdCopy = CollUtil.newArrayList(menuIds);
        //再把前端传过来的菜单id绑定到当前的这个角色id上去
        for (Integer mid : menuIds) {
            Menu menu = menuService.getById(mid);
            if (menu.getPid() != null && !menuIdCopy.contains(menu.getPid())) {//二级菜单 并且传过来的menuId里面没有它的父级ID
                //那么我们就得补上这个父级ID
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setRid(rid);
                roleMenu.setMid(mid);
                roleMenuMapper.insert(roleMenu);
                menuIdCopy.add(menu.getPid());
            }
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRid(rid);
            roleMenu.setMid(mid);
            roleMenuMapper.insert(roleMenu);
        }
    }

    @Override
    public List<Integer> getRoleMenu(Integer rid) {
        return roleMenuMapper.selectByRoleId(rid);
    }

    @Override
    public int update(Role role) {
        return roleMapper.update(role);
    }

    @Override
    public int insert(Role role) {
        return roleMapper.insert(role);
    }
}
