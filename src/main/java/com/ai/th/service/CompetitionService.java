package com.ai.th.service;

import com.ai.th.pojo.Competition;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface CompetitionService extends IService<Competition> {
    Page<Competition> findPage(Page<Competition> page,String ename);
}
