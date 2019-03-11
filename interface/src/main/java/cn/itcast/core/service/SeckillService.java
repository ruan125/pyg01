package cn.itcast.core.service;

import cn.itcast.core.pojo.seckill.SeckillGoods;

import java.util.List;

public interface SeckillService {

    //秒杀商品审核

    //查询所有正在秒杀的商品
    public List<SeckillGoods> findList();

    //根据id从缓存中获取实体
    public SeckillGoods findOneFromRedis(Long id);

    //提交订单
    public void submitOrder(Long seckillId,String userName);

}
