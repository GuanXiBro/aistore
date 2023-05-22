package com.ai.th.mapper;

import com.ai.th.pojo.RoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    @Delete("delete from rolemenu where rid = #{rid}")
    int deleteByRoleId(@Param("rid") Integer rid);

    @Select("select mid from rolemenu where rid = #{rid}")
    List<Integer> selectByRoleId(@Param("rid") Integer rid);
}
