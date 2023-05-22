package com.ai.th.pojo.dto;

import com.ai.th.pojo.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {
    private String account;
    private String password;
    private String token;
    private List<Menu> menus;
}
