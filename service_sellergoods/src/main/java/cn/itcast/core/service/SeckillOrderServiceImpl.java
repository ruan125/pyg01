package cn.itcast.core.service;

import cn.itcast.core.common.Constants;
import cn.itcast.core.dao.seckill.SeckillOrderDao;
import cn.itcast.core.pojo.entity.PageResult;
import cn.itcast.core.pojo.seckill.SeckillOrder;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Date;

@Service
public class SeckillOrderServiceImpl implements SeckillOrderService {

    @Autowired
    private SeckillOrderDao seckillOrderDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void saveOrderFromRedisToDb(String userName, Long out_trade_no, String transactionId) {
        //根据用户id获取redis中的支付日志.
        SeckillOrder seckillOrder = (SeckillOrder) redisTemplate.boundHashOps(Constants.REDIS_SECKILL).get(userName);
        //判断
        if (seckillOrder == null) {
            throw new RuntimeException("订单不存在!");
        }
        //判断单号是否一致
        if (seckillOrder.getId() == out_trade_no.longValue()){
            throw new RuntimeException("订单号不一致!");
        }
        seckillOrder.setTransactionId(transactionId);
        seckillOrder.setPayTime(new Date());
        seckillOrder.setStatus("1");
        seckillOrderDao.insert(seckillOrder);//保存到数据库
        redisTemplate.boundHashOps(Constants.REDIS_SECKILL).delete(userName);
    }

    @Override
    public PageResult search(Integer page, Integer rows, SeckillOrder seckillOrder) {
        PageHelper.startPage(page,rows);
        Page<SeckillOrder> seckillOrderList = (Page<SeckillOrder>) seckillOrderDao.selectByExample(null);
        return new PageResult(seckillOrderList.getTotal(),seckillOrderList.getResult());
    }


}
