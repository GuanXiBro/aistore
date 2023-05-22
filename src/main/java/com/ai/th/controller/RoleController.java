package com.ai.th.controller;

import com.ai.th.common.Result;
import com.ai.th.pojo.Role;
import com.ai.th.pojo.Student;
import com.ai.th.service.RoleService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Resource
    private RoleService roleService;


    /**
     * 查询所有管理员
     *
     * @return
     */
    @GetMapping("/find")
    public Result findAll() {
        return Result.success(roleService.list());
    }

    @PostMapping("/update")
    public Result update(@RequestBody Role role){
        return Result.success(roleService.update(role));
    }

    /**
     * 删除管理员
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public Result removeById(@PathVariable Integer id) {
        return Result.success(roleService.removeById(id));
    }

    @PostMapping("/insert")
    public Result insert(@RequestBody Role role){
        return Result.success(roleService.insert(role));
    }

    /**
     * 绑定角色和菜单的关系
     * @param rid
     * @param menuIds
     * @return
     */
    @PostMapping("/roleMenu/{rid}")
    public Result roleMenu(@PathVariable Integer rid ,@RequestBody List<Integer> menuIds) {
        roleService.setRoleMenu(rid,menuIds);
        return Result.success();
    }
    @GetMapping("/roleMenu/{rid}")
    public Result getRoleMenu(@PathVariable Integer rid) {
        return Result.success(roleService.getRoleMenu(rid));
    }
}
