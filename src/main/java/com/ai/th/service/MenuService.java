package com.ai.th.service;

import com.ai.th.pojo.Menu;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuService extends IService<Menu> {
    List<Menu> findAll();

    int insert(Menu menu);

    int update(Menu menu);

    Integer removeById(@Param("id") Integer id);

    List<Menu> findPage(Integer pageNum, Integer pageSize, String name);

    Integer selectTotal(String name);

    List<Menu> findMenu(String name);
}
