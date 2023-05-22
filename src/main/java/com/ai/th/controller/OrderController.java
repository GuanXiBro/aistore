package com.ai.th.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.extra.tokenizer.TokenizerUtil;
import com.ai.th.common.Result;
import com.ai.th.exception.ServiceException;
import com.ai.th.pojo.Competition;
import com.ai.th.pojo.Orders;
import com.ai.th.pojo.Student;
import com.ai.th.service.CompetitionService;
import com.ai.th.service.OrderService;
import com.ai.th.utils.JwtUtils;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    private OrderService orderService;

    @Resource
    private CompetitionService competitionService;


    /**
     * 查询所有管理员
     *
     * @return
     */
    @GetMapping("/find")
    public Result findAll() {
        return Result.success(orderService.list());
    }


    /**
     * 删除管理员
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public Result removeById(@PathVariable Integer id) {
        return Result.success(orderService.removeById(id));
    }

    @PostMapping("/{eid}/{num}")
    public Result insert(@PathVariable Integer eid, @PathVariable Integer num){
        Competition competition = competitionService.getById(eid);
        if(competition == null){
            throw new ServiceException("-1","未找到竞赛");
        }
        Student currentUser = JwtUtils.getCurrentUser();
        Integer sid = currentUser.getSid();
        Orders orders = new Orders();
        orders.setName(competition.getEname());
        orders.setNumber(DateUtil.format(new Date(),"yyyyMMdd") + System.currentTimeMillis());
        orders.setTime(DateUtil.now());
        orders.setSid(sid);
        orders.setTotal(competition.getBuymoney().multiply(BigDecimal.valueOf(num)));
        orderService.insert(orders);
        return Result.success();
    }

    @PostMapping("/insert")
    public Result createOrder(@RequestBody Orders orders){
        return Result.success(orderService.insert(orders));
    }
    @GetMapping("/page")
    public Map<String, Object> findPage(@RequestParam Integer pageNum,
                                        @RequestParam Integer pageSize,
                                        @RequestParam String name) {
        pageNum = (pageNum - 1) * pageSize;
        List<Orders> data = orderService.findPage(pageNum, pageSize, name);
        Integer total = orderService.selectTotal(name);
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("total", total);
        return res;
    }
}
