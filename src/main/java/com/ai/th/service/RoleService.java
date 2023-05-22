package com.ai.th.service;

import com.ai.th.pojo.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface RoleService extends IService<Role> {


    void setRoleMenu(Integer rid, List<Integer> menuIds);

    List<Integer> getRoleMenu(Integer rid);

    int update(Role role);

    int insert(Role role);
}
