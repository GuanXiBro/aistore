package com.ai.th.service;


import com.ai.th.common.Result;
import com.ai.th.pojo.Student;
import com.ai.th.pojo.dto.UserDto;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentService extends IService<Student> {
    List<Student> findAll();

    int insert(Student student);

    int update(Student student);

    Integer removeById(@Param("sid") Integer sid);

//    List<Student> findPage(Integer pageNum, Integer pageSize,String account);

    Integer selectTotal(String account);

    UserDto login(UserDto userDto);

    Student register(UserDto userDto);

    Student findByAccount(String account);

    Page<Student> findPage(Page<Student> page,String account);
}
