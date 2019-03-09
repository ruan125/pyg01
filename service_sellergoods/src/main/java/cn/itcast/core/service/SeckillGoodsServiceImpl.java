package cn.itcast.core.service;

import cn.itcast.core.common.IdWorker;
import cn.itcast.core.dao.seckill.SeckillGoodsDao;
import cn.itcast.core.pojo.entity.PageResult;
import cn.itcast.core.pojo.entity.Result;
import cn.itcast.core.pojo.good.Goods;
import cn.itcast.core.pojo.good.GoodsQuery;
import cn.itcast.core.pojo.seckill.SeckillGoods;
import cn.itcast.core.pojo.seckill.SeckillGoodsQuery;
import com.alibaba.dubbo.config.annotation.Service;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class SeckillGoodsServiceImpl implements SeckillGoodsService {

    @Autowired
    private SeckillGoodsDao seckillGoodsDao;


    @Override
    public void add(SeckillGoods seckillGoods) {
        //获取当前的商家用户名
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        seckillGoods.setSellerId(userName);
//        seckillGoods.setGoodsId();

        seckillGoods.setId(seckillGoods.getId());
        seckillGoods.setTitle(seckillGoods.getTitle());
        seckillGoods.setPrice(seckillGoods.getPrice());
        seckillGoods.setCostPrice(seckillGoods.getCostPrice());
        seckillGoods.setStatus("0");
        seckillGoodsDao.insert(seckillGoods);

    }

    @Override
    public PageResult findPage(Integer page, Integer rows, SeckillGoods seckillGoods) {
        PageHelper.startPage(page, rows);
        //创建查询对象
        SeckillGoodsQuery query = new SeckillGoodsQuery();
        //创建where条件对象
        SeckillGoodsQuery.Criteria criteria = query.createCriteria();
        if (seckillGoods != null) {
            if (seckillGoods.getStatus() != null && "".equals(seckillGoods.getStatus())) {
                criteria.andStatusEqualTo(seckillGoods.getStatus());
            }
            if (seckillGoods.getTitle() != null && !"".equals(seckillGoods.getTitle())) {
                criteria.andTitleLike("%" + seckillGoods.getTitle() + "%");
            }
            //如果不是管理员用户, 根据用户名查询, 如果是管理员用户查询所有数据
            if (!"admin".equals(seckillGoods.getSellerId()) && !"wc".equals(seckillGoods.getSellerId())
                    && !"".equals(seckillGoods.getSellerId()) && seckillGoods.getSellerId() != null) {
                criteria.andSellerIdEqualTo(seckillGoods.getSellerId());
            }
        }
        Page<SeckillGoods> seckillGoodsList = (Page<SeckillGoods>) seckillGoodsDao.selectByExample(null);
        return new PageResult(seckillGoodsList.getTotal(), seckillGoodsList.getResult());
    }

    @Override
    public void updateStatus(Long[] Ids, String status) {
        //根据商品id集合,改变商品状态
        if (Ids != null) {
            for (long id : Ids) {
                SeckillGoods seckillGoods = new SeckillGoods();
                seckillGoods.setId(id);
                //修改秒杀状态
                seckillGoods.setStatus(status);
                //根据秒杀的商品id修改
                SeckillGoodsQuery query = new SeckillGoodsQuery();
                SeckillGoodsQuery.Criteria criteria = query.createCriteria();
                criteria.andIdEqualTo(id);
                seckillGoodsDao.updateByExampleSelective(seckillGoods, query);
            }
        }
    }


}




