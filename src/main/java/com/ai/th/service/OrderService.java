package com.ai.th.service;

import com.ai.th.pojo.Orders;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface OrderService extends IService<Orders> {

    int insert(Orders orders);

    List<Orders> findPage(Integer pageNum, Integer pageSize, String name);

    Integer selectTotal(String name);
}
