package cn.itcast.core.controller;

import cn.itcast.core.pojo.entity.Result;
import cn.itcast.core.pojo.user.User;
import cn.itcast.core.service.UserService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 登录用户显示登录名
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Reference
    private UserService userService;

    /**
     * 回显登录名
     * @return
     */
    @RequestMapping("/name")
    public String showName(){
        //1. 获取当前登录用户名称
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        return userName;
    }

    /**
     * 个人信息
     * @return
     */
    @RequestMapping("/findUser")
    public User  selectUser(){
        //1. 获取当前登录用户名称
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.selectUser(userName);
        return user;
    }

    @RequestMapping("/updateUser")
    public Result update(@RequestBody User user){
        try {
            //1. 获取当前登录用户名称
            String userName = SecurityContextHolder.getContext().getAuthentication().getName();
            user.setUsername(userName);
            userService.update(user);
            return  new Result(true,"更改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"更改失败");
        }
    }
}
