package com.ai.th.controller;


import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.ai.th.common.Constant;
import com.ai.th.common.Result;
import com.ai.th.pojo.Student;
import com.ai.th.pojo.dto.UserDto;
import com.ai.th.service.StudentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/student")
public class StudentController {

    @Resource
    private StudentService studentService;

    /**
     * 登录业务
     *
     * @param userDto
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody UserDto userDto) {
        String account = userDto.getAccount();
        String password = userDto.getPassword();
        if ((StrUtil.isBlank(account) || StrUtil.isBlank(password))) {
            return Result.error(Constant.CODE_401, "权限不足");
        } else {
            UserDto dto = studentService.login(userDto);
            return Result.success(dto);
        }
    }

    /**
     * 注册业务
     *
     * @param userDto
     * @return
     */
    @PostMapping("/register")
    public Result register(@RequestBody UserDto userDto) {
        String account = userDto.getAccount();
        String password = userDto.getPassword();
        if ((StrUtil.isBlank(account) || StrUtil.isBlank(password))) {
            return Result.error(Constant.CODE_401, "权限不足");
        } else {
            return Result.success(studentService.register(userDto));
        }
    }

    /**
     * 导出数据
     *
     * @param response
     * @throws IOException
     */
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException {
        //从数据库查询所有数据
        List<Student> list = studentService.list();
        //通过工具类创建writer 写出到磁盘
        //在内存操作 写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);
        //自定义标题别名
        writer.addHeaderAlias("account", "用户名");
        writer.addHeaderAlias("password", "密码");
        writer.addHeaderAlias("sname", "姓名");
        writer.addHeaderAlias("sex", "性别");
        writer.addHeaderAlias("birthday", "出生日期");
        writer.addHeaderAlias("college", "所属高校");
        writer.addHeaderAlias("address", "地址");
        writer.addHeaderAlias("email", "邮箱");
        writer.addHeaderAlias("tel", "联系电话");
        //一次性写出到list内的对象excel，使用默认样式，强制输出标题
        writer.write(list, true);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("用户信息", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;fileName=" + fileName + ".xlsx");

        ServletOutputStream outputStream = response.getOutputStream();
        writer.flush(outputStream, true);
        outputStream.close();
        writer.close();
    }

    /**
     * 查询所有
     *
     * @return
     */
    @GetMapping("/account/{account}")
    public Result findAccount(@PathVariable String account) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", account);
        return Result.success(studentService.getOne(queryWrapper));
    }

    /**
     * 查询所有
     *
     * @return
     */
    @GetMapping("/find")
    public Result findAll() {
        List<Student> studentList = studentService.findAll();
        for (Student student : studentList) {
            System.out.println(student);
        }
        return Result.success(studentList);
    }

    @GetMapping("/role/{menurole}")
    public Result findCompanysByRole(@PathVariable String menurole) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("menurole", menurole);
        List<Student> list = studentService.list(queryWrapper);
        return Result.success(list);

    }


    /**
     * 新增用户数据
     *
     * @param student
     * @return
     */
    @PostMapping("/insert")
    public Result insert(@RequestBody Student student) {
        return Result.success(studentService.insert(student));
    }

    /**
     * 更新用户数据
     *
     * @param student
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody Student student) {
        return Result.success(studentService.update(student));
    }

    /**
     * 删除用户数据
     *
     * @param sid
     * @return
     */
    @DeleteMapping("/delete/{sid}")
    public Result removeById(@PathVariable Integer sid) {
        return Result.success(studentService.removeById(sid));
    }

    /**
     * 实现分页
     *
     * @param pageNum
     * @param pageSize
     * @param account
     * @return
     */
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String account) {


        return Result.success(studentService.findPage(new Page<>(pageNum, pageSize), account));
    }
}
