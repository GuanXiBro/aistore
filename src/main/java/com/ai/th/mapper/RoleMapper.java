package com.ai.th.mapper;


import com.ai.th.pojo.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper extends BaseMapper<Role> {
    List<Role> findAll();

    int insert(Role role);

    int update(Role role);

    Integer removeById(@Param("id") Integer id);

    List<Role> findPage(Integer pageNum, Integer pageSize, String name);

    Integer selectTotal(String name);

    @Select("select id from airole where rolekey = #{rolekey}")
    Integer selectByRoleKey(@Param("rolekey") String rolekey);
}
