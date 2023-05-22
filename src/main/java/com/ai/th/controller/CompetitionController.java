package com.ai.th.controller;


import com.ai.th.common.Result;
import com.ai.th.mapper.CompetitionMapper;
import com.ai.th.pojo.Competition;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/competition")
public class CompetitionController {

    @Resource
    private CompetitionMapper competitionMapper;

    /**
     * 查询所有
     *
     * @return
     */
    @GetMapping("/find")
    public Result findAll() {
        List<Competition> competitionList = competitionMapper.findAll();
        for (Competition competition : competitionList) {
            System.out.println(competition);
        }
        return Result.success(competitionList);
    }


    /**
     * 新增用户数据
     *
     * @param competition
     * @return
     */
    @PostMapping("/insert")
    public Result insert(@RequestBody Competition competition) {
        return Result.success(competitionMapper.insert(competition));
    }

    /**
     * 更新用户数据
     *
     * @param competition
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody Competition competition) {
        return Result.success(competitionMapper.update(competition));
    }

    /**
     * 删除用户数据
     *
     * @param eid
     * @return
     */
    @DeleteMapping("/delete/{eid}")
    public Result removeById(@PathVariable Integer eid) {
        return Result.success(competitionMapper.removeById(eid));
    }

    /**
     * 实现分页
     *
     * @param pageNum
     * @param pageSize
     * @param ename
     * @return
     */
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam String ename) {
        Page<Competition> page = competitionMapper.findPage(new Page<>(pageNum, pageSize), ename);
        return Result.success(page);
    }
}
