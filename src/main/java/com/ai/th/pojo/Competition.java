package com.ai.th.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Competition {
    @TableId(value = "eid",type = IdType.AUTO)
    private Integer eid;
    private String ename;
    private String context;
    private Integer sponsor;
    private BigDecimal buymoney;
    private String money;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date Rstime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date Rctime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date Cstime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date Cftime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date Resulttime;
    @TableField(exist = false)
    private String company;
}
