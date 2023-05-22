package com.ai.th.controller;

import com.ai.th.common.Result;
import com.ai.th.pojo.Menu;
import com.ai.th.service.MenuService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Resource
    private MenuService menuService;


    /**
     * 查询所有管理员
     *
     * @return
     */
    @GetMapping("/find")
    public Result findAll(@RequestParam(defaultValue = "") String name) {

        return Result.success(menuService.findMenu(name));
    }

    /**
     * 新增管理员
     *
     * @return
     */
    @PostMapping("/insert")
    public Result insert(@RequestBody Menu menu) {
        return Result.success(menuService.insert(menu));
    }

    /**
     * 更新管理员
     *
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody Menu menu) {
        return Result.success(menuService.update(menu));
    }

    /**
     * 删除管理员
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public Result removeById(@PathVariable Integer id) {
        return Result.success(menuService.removeById(id));
    }

    /**
     * 实现分页
     *
     * @param pageNum
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public Map<String, Object> findPage(@RequestParam Integer pageNum,
                                        @RequestParam Integer pageSize,
                                        @RequestParam String name) {
        pageNum = (pageNum - 1) * pageSize;
        List<Menu> data = menuService.findPage(pageNum, pageSize, name);
        Integer total = menuService.selectTotal(name);
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("total", total);
        return res;
    }
}
