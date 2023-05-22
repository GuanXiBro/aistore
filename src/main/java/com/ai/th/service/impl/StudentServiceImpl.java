package com.ai.th.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ai.th.common.Constant;
import com.ai.th.exception.ServiceException;
import com.ai.th.mapper.MenuMapper;
import com.ai.th.mapper.RoleMapper;
import com.ai.th.mapper.RoleMenuMapper;
import com.ai.th.mapper.StudentMapper;
import com.ai.th.pojo.Menu;
import com.ai.th.pojo.RoleMenu;
import com.ai.th.pojo.Student;
import com.ai.th.pojo.dto.UserDto;
import com.ai.th.service.MenuService;
import com.ai.th.service.StudentService;
import com.ai.th.utils.JwtUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Resource
    private StudentMapper studentMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Resource
    private MenuService menuService;

    @Override
    public List<Student> findAll() {
        return studentMapper.findAll();
    }

    @Override
    public int insert(Student student) {
        return studentMapper.insert(student);
    }

    @Override
    public int update(Student student) {
        return studentMapper.update(student);
    }

    @Override
    public Integer removeById(Integer sid) {
        return studentMapper.removeById(sid);
    }

    @Override
    public Integer selectTotal(String account) {
        return studentMapper.selectTotal(account);
    }

    @Override
    public UserDto login(UserDto userDto) {
        Student one = getUserInfo(userDto);
        if (one != null) {
            BeanUtil.copyProperties(one, userDto, true);
            //设置token
            try {
                String token = JwtUtils.genTok(one.getSid().toString(), one.getPassword());
                userDto.setToken(token);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String menurole = one.getMenurole();
            List<Menu> roleMenus = getRoleMenus(menurole);
            userDto.setMenus(roleMenus);
            return userDto;
        } else {
            throw new ServiceException(Constant.CODE_500, "用户名或密码错误");
        }
    }

    @Override
    public Student register(UserDto userDto) {
        Student one = getUserInfo(userDto);
        if (one == null) {
            one = new Student();
            BeanUtil.copyProperties(userDto, one, true);
            insert(one);
        } else {
            throw new ServiceException(Constant.CODE_500, "用户已存在");
        }
        return one;
    }

    @Override
    public Student findByAccount(String account) {
        return studentMapper.findByAccount(account);
    }

    @Override
    public Page<Student> findPage(Page<Student> page, String account) {
        return studentMapper.findPage(page,account);
    }

    private Student getUserInfo(UserDto userDto) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper();
        queryWrapper.eq("account", userDto.getAccount()).
                eq("password", userDto.getPassword());
        Student one = getOne(queryWrapper);
        return one;
    }

    /**
     * 获取当前角色的菜单列表
     *
     * @param menurole
     * @return
     */
    private List<Menu> getRoleMenus(String menurole) {
        Integer roleKey = roleMapper.selectByRoleKey(menurole);
        //当前用户角色所有的菜单id集合
        List<Integer> menuIds = roleMenuMapper.selectByRoleId(roleKey);
        //查出系统所有的菜单
        List<Menu> menus = menuService.findMenu("");
        //new  一个最后筛选完成的list
        List<Menu> roleMenus = new ArrayList<>();
        // 再去筛选当前用户的菜单
        for (Menu menu : menus) {
            if (menuIds.contains(menu.getId())) {
                roleMenus.add(menu);
            }
            List<Menu> children = menu.getChildren();
            //移除当前集合里面不在menuids中的元素
            children.removeIf(child -> !menuIds.contains(child.getId()));
        }
        return roleMenus;
    }
}
