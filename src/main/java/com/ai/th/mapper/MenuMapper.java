package com.ai.th.mapper;

import com.ai.th.pojo.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuMapper extends BaseMapper<Menu> {
    List<Menu> findAll();

    int insert(Menu menu);

    int update(Menu menu);

    Integer removeById(@Param("id") Integer id);

    List<Menu> findPage(Integer pageNum, Integer pageSize, String name);

    Integer selectTotal(String name);
}
