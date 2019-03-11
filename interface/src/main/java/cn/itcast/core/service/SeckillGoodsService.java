package cn.itcast.core.service;

import cn.itcast.core.pojo.entity.PageResult;
import cn.itcast.core.pojo.seckill.SeckillGoods;

import java.util.List;

public interface SeckillGoodsService {

    //秒杀商品申请
    public void add(SeckillGoods seckillGoods);

    //分页展示
    public PageResult findPage(Integer page, Integer rows, SeckillGoods seckillGoods);

    //改变商品的状态
    public void updateStatus(Long[] Ids, String status);


}
