package com.ai.th.pojo;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Menu {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String name;
    private String discription;
    private String url;
    @TableField(exist = false)
    private List<Menu> children;
    @TableField(insertStrategy = FieldStrategy.IGNORED)
    private Integer pid;
    private String pagepath;
}
