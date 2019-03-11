package cn.itcast.core.service;


import cn.itcast.core.pojo.entity.PageResult;
import cn.itcast.core.pojo.order.Order;
import cn.itcast.core.pojo.user.User;

import java.util.List;

public interface UserService {

    public void sendCode(String phone);

    public boolean checkSmsCode(String phone, String smsCode);

    public  void  add(User user);

    /**
     *根据登录名查询个人信息
     * @param userName
     * @return
     */
    public  User selectUser(String userName);

    /**
     * 更改用户信息
     * @param user
     */
    public void update(User user);

    public PageResult search(String userName, Integer page, Integer rows, Order order);

    public List<Order> findAll(String userName);

}