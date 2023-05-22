package com.ai.th.mapper;

import com.ai.th.pojo.Menu;
import com.ai.th.pojo.Orders;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper extends BaseMapper<Orders> {
    List<Orders> findAll();

    int insert(Orders orders);

    Integer removeById(@Param("id") Integer id);

    List<Orders> findPage(Integer pageNum, Integer pageSize, String name);

    Integer selectTotal(String name);

    @Update("update orders set state = #{state}, payment_time = #{payment_time}, alipay_no = #{alipay_no} where number = #{number}")
    int updateState(@Param("number") String tradeNo,
                    @Param("state") String state,
                    @Param("payment_time") String gmtPayment,
                    @Param("alipay_no") String alipayTradeNo);

}
