package com.ai.th.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Student {
    @TableId(value = "sid",type = IdType.AUTO)
    private Integer sid;
    private String account;
    @JsonIgnore//忽略用户密码数据
    private String password;
    private String sname;
    private String sex;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private String college;
    private String address;
    private String email;
    private String tel;
    private String menurole;
    @TableField(exist = false)
    private List<Competition> competitions;
}
