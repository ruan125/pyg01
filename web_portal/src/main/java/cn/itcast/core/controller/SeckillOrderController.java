package cn.itcast.core.controller;

import cn.itcast.core.pojo.entity.Result;
import cn.itcast.core.service.SeckillService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seckillOrder")
public class SeckillOrderController {

    @Reference
    private SeckillService seckillService;

    @RequestMapping("/submitOrder")
    public Result submitOrder(Long seckillId) {
        //获取当前用户名
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        if ("anonymousUser".equals(userName)) {
            return new Result(false, "请登录!");
        }
        try {
            seckillService.submitOrder(seckillId, userName);
            return new Result(true, "提交成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "提交失败!");
        }
    }
}
