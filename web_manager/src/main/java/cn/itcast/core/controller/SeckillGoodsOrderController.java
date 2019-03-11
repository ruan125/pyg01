package cn.itcast.core.controller;

import cn.itcast.core.pojo.entity.PageResult;
import cn.itcast.core.pojo.seckill.SeckillOrder;
import cn.itcast.core.service.SeckillOrderService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/seckillOrder")
public class SeckillGoodsOrderController {

    @Reference
    private SeckillOrderService seckillOrderService;

    @RequestMapping("/search")
    public PageResult search(Integer page, Integer rows, @RequestBody SeckillOrder seckillOrder){
        String usrName = SecurityContextHolder.getContext().getAuthentication().getName();
        seckillOrder.setSellerId(usrName);
        PageResult pageResult = seckillOrderService.search(page, rows, seckillOrder);
        return pageResult;
    }

}
