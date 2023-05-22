package com.ai.th.mapper;

import com.ai.th.pojo.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//表示是一个mybatis类
@Repository
public interface StudentMapper extends BaseMapper<Student>{
    List<Student> findAll();

    int insert(Student student);

    int update(Student student);

    Integer removeById(@Param("sid") Integer sid);

    Page<Student> findPage(Page<Student> page, @Param("account") String account);

    Integer selectTotal(String account);

    Student findByAccount(String account);
}
