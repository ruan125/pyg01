package cn.itcast.core.controller;

import cn.itcast.core.pojo.entity.PageResult;
import cn.itcast.core.pojo.entity.Result;
import cn.itcast.core.pojo.good.Goods;
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

    @RequestMapping("/search")
    public PageResult search(Integer page, Integer rows, @RequestBody SeckillGoods seckillGoods) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        seckillGoods.setSellerId(userName);
        PageResult pageResult = seckillGoodsService.findPage(page, rows, seckillGoods);
        return pageResult;
    }

    /**
     * 修改商品状态
     *
     * @param ids    商品id数组
     * @param status 状态码为1审核通过, 状态码为2驳回
     * @return
     */
    @RequestMapping("/updateStatus")
    public Result updateStatus(Long[] Ids, String status) {
        try {
            //1. 根据商品id改变数据库中商品的上架状态
            seckillGoodsService.updateStatus(Ids, status);
            return new Result(true, "修改成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败!");
        }
    }
}
