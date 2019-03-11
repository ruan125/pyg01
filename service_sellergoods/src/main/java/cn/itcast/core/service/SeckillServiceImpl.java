package cn.itcast.core.service;

import cn.itcast.core.common.Constants;
import cn.itcast.core.dao.seckill.SeckillGoodsDao;
import cn.itcast.core.pojo.seckill.SeckillGoods;
import cn.itcast.core.pojo.seckill.SeckillGoodsQuery;
import cn.itcast.core.pojo.seckill.SeckillOrder;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class SeckillServiceImpl implements SeckillService {

    @Autowired
    private SeckillGoodsDao seckillGoodsDao;

    @Autowired
    private RedisTemplate redisTemplate;

    /*@Autowired
    private IdWorker idWorker;*/

    /*@Override
    public List<SeckillGoods> findList() {
        SeckillGoodsQuery query = new SeckillGoodsQuery();
        SeckillGoodsQuery.Criteria criteria = query.createCriteria();
        criteria.andStatusEqualTo("1");//审核通过
        criteria.andStockCountGreaterThan(0);//剩余库存大于0
        criteria.andStartTimeLessThanOrEqualTo(new Date());//开始时间小于等于当前时间
        criteria.andEndTimeGreaterThan(new Date());//结束时间大于当前时间
        return seckillGoodsDao.selectByExample(query);
    }*/

    @Override
    public List<SeckillGoods> findList() {
        //获取秒杀商品列表
        List<SeckillGoods> seckillGoodsList = redisTemplate.boundHashOps(Constants.REDIS_SECKILL).values();
        //判断
        if (seckillGoodsList == null || seckillGoodsList.size() == 0) {
            SeckillGoodsQuery query = new SeckillGoodsQuery();
            SeckillGoodsQuery.Criteria criteria = query.createCriteria();
            criteria.andStatusEqualTo("1");//审核通过
            criteria.andStockCountGreaterThan(0);//剩余库存大于0
            /*criteria.andStartTimeLessThanOrEqualTo(new Date());//开始时间小于等于当前时间
            criteria.andEndTimeGreaterThan(new Date());//结束时间大于当前时间*/
            //将查到的数据放到集合中
            seckillGoodsList = seckillGoodsDao.selectByExample(null);
            //将商品集合装到缓存
            for (SeckillGoods seckillGoods : seckillGoodsList) {
                redisTemplate.boundHashOps(Constants.REDIS_SECKILL).put(seckillGoods.getId(), seckillGoods);
            }
        }
        return seckillGoodsList;
    }

    //详情页面显示
    @Override
    public SeckillGoods findOneFromRedis(Long id) {
        SeckillGoods seckillGoods = (SeckillGoods) redisTemplate.boundHashOps(Constants.REDIS_SECKILL).get(id);
        return seckillGoods;
    }

    //提交订单
    @Override
    public void submitOrder(Long seckillId, String userName) {
        //1. 从缓存中获取商品
        SeckillGoods seckillGoods = (SeckillGoods) redisTemplate.boundHashOps(Constants.REDIS_SECKILL).get(seckillId);
        //判断
        if (seckillGoods == null) {
            throw new RuntimeException("商品不存在!");
        }
        if (seckillGoods.getStockCount() <= 0) {
            throw new RuntimeException("商品已被抢完!");
        }
        //减少库存量,并放回缓存
        seckillGoods.setStockCount(seckillGoods.getStockCount() - 1);
        redisTemplate.boundHashOps(Constants.REDIS_SECKILL).put(seckillId, seckillGoods);
        //如果已经被抢完,同步到数据库
        if (seckillGoods.getStockCount() == 0) {
            seckillGoodsDao.updateByPrimaryKey(seckillGoods);
            redisTemplate.boundHashOps(Constants.REDIS_SECKILL).delete(seckillId);
        }
        //保存订单
        /*long orderId = idWorker.nextId();*/
        SeckillOrder seckillOrder = new SeckillOrder();
         /*seckillOrder.setId(orderId);*/
        seckillOrder.setId(11111L);
        seckillOrder.setCreateTime(new Date());
        seckillOrder.setMoney(seckillGoods.getCostPrice());
        seckillOrder.setSeckillId(seckillId);
        seckillOrder.setSellerId(seckillGoods.getSellerId());
        seckillOrder.setUserId(userName);//设置用户ID
        seckillOrder.setStatus("0");//状态
        redisTemplate.boundHashOps("seckillOrder").put(userName, seckillOrder);
    }


}
