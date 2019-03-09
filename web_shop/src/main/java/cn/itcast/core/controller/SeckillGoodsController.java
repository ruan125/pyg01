package cn.itcast.core.controller;

import cn.itcast.core.pojo.entity.PageResult;
import cn.itcast.core.pojo.entity.Result;
import cn.itcast.core.pojo.seckill.SeckillGoods;
import cn.itcast.core.service.SeckillGoodsService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/seckillGoods")
public class SeckillGoodsController {

    @Reference
    private SeckillGoodsService seckillGoodsService;



    @RequestMapping("/add")
    public Result add(@RequestBody SeckillGoods seckillGoods) {
        try {
            seckillGoodsService.add(seckillGoods);
            return new Result(true, "添加成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "添加失败!");
        }
    }

    @RequestMapping("/findPage")
    public PageResult findPage(Integer page, Integer rows, SeckillGoods seckillGoods) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        seckillGoods.setSellerId(userName);
        PageResult pageResult = seckillGoodsService.findPage(page, rows, seckillGoods);
        return pageResult;
    }

    @RequestMapping("/search")
    public PageResult search(Integer page, Integer rows, @RequestBody SeckillGoods seckillGoods) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        seckillGoods.setSellerId(userName);
        PageResult pageResult = seckillGoodsService.findPage(page, rows, seckillGoods);
        return pageResult;
    }

}
