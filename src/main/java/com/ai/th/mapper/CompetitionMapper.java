package com.ai.th.mapper;

import com.ai.th.pojo.Competition;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompetitionMapper extends BaseMapper<Competition> {
    List<Competition> findAll();

    int insert(Competition competition);

    int update(Competition competition);

    Integer removeById(@Param("eid") Integer eid);

    List<Competition> selectPage(Integer pageNum, Integer pageSize,String ename);

    Integer selectTotal(String ename);


    Page<Competition> findPage(Page<Competition> page, @Param("ename") String ename);

}
