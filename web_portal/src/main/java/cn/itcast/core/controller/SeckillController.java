package cn.itcast.core.controller;

import cn.itcast.core.pojo.entity.Result;
import cn.itcast.core.pojo.seckill.SeckillGoods;
import cn.itcast.core.service.SeckillService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/seckillGoods")
public class SeckillController {

    @Reference
    private SeckillService seckillService;

    @RequestMapping("/findList")
    public List<SeckillGoods> findList() {
        return seckillService.findList();
    }

    @RequestMapping("/findOneFromRedis")
    public SeckillGoods findOneFromRedis(Long id){
        return seckillService.findOneFromRedis(id);
    }


}
