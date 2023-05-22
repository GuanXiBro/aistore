package com.ai.th.service.impl;

import com.ai.th.mapper.CompetitionMapper;
import com.ai.th.pojo.Competition;
import com.ai.th.service.CompetitionService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CompetitionServiceImpl extends ServiceImpl<CompetitionMapper, Competition> implements CompetitionService {

    @Resource
    private CompetitionMapper competitionMapper;
    @Override
    public Page<Competition> findPage(Page<Competition> page, String ename) {
        return competitionMapper.findPage(page,ename);
    }
}
