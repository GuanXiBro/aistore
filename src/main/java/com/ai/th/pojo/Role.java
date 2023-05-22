package com.ai.th.pojo;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("airole")
public class Role {
    private Integer id;
    private String discription;
    private String name;
    private String rolekey;
}
