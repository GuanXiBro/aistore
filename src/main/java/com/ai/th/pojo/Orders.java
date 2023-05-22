package com.ai.th.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String name;
    private String number;
    private String time;
    private String state;
    private Integer sid;
    //支付时间
    private String paymentTime;
    //支付宝订单号
    private String alipayNo;

    private BigDecimal total;
}
