package com.ai.th.pojo;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("rolemenu")
@Data
public class RoleMenu {
    private Integer rid;
    private Integer mid;
}
