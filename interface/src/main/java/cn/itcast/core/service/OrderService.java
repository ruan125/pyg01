package cn.itcast.core.service;

import cn.itcast.core.pojo.entity.PageResult;
import cn.itcast.core.pojo.order.Order;

import java.util.List;
import java.util.Map;

public interface OrderService {

    /**
     * 保存订单
     * 涉及到三张表, payLog支付日志, order订单表, orderItem订单详情表
     * @param order  页面提交的订单对象数据, 这个对象不直接保存到数据库中, 只是需要页面提交过来的
     *                 收货人地址, 收货人姓名, 收货人电话, 支付方式等信息.
     */
    public void add(Order order);

    /**
     * 根据支付单号修改, 支付状态为已支付
     * @param out_trade_no 支付单号
     */
    public void  updatePayLogAndOrderStatus(String out_trade_no);


    public PageResult findPage(Integer page, Integer rows ,Order order);



    /**
     * 根据用户名 和订单状态查询查询订单集合
     * @param userName  用户名
     * @param status    订单状态
     * @return
     */
    public PageResult userSeletOrder(String userName, String status,Integer page,Integer rows);
}
