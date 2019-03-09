package cn.itcast.core.service;

import cn.itcast.core.pojo.entity.PageResult;
import cn.itcast.core.pojo.seckill.SeckillOrder;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface SeckillOrderService {

    /**
     * 保存秒杀支付
     */
    public void saveOrderFromRedisToDb(String userName, Long out_trade_no, String transactionId);

    /**
     * 查询订单
     */
    public PageResult search(Integer page, Integer rows,SeckillOrder seckillOrder);


}
