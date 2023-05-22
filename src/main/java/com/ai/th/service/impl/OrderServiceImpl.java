package com.ai.th.service.impl;

import com.ai.th.mapper.OrderMapper;
import com.ai.th.pojo.Orders;
import com.ai.th.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Orders> implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Override
    public int insert(Orders orders) {
        return orderMapper.insert(orders);
    }

    @Override
    public List<Orders> findPage(Integer pageNum, Integer pageSize, String name) {
        return orderMapper.findPage(pageNum, pageSize, name);
    }

    @Override
    public Integer selectTotal(String name) {
        return orderMapper.selectTotal(name);
    }
}
