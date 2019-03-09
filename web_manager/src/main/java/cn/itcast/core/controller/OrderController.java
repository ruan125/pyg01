package cn.itcast.core.controller;

import cn.itcast.core.pojo.entity.PageResult;
import cn.itcast.core.pojo.order.Order;
import cn.itcast.core.service.OrderService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 品牌管理
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Reference
    private OrderService orderService;



    /**
     * 高级查询(分页, 高级查询)
     * @param page  当前页
     * @param rows  每页展示多少条数据
     * @param order 需要查询的条件品牌对象
     * @return
     */
    @RequestMapping("/search")
    public PageResult search(Integer page, Integer rows, @RequestBody Order order) {
        PageResult pageResult = orderService.findPage( page, rows,order);
        return pageResult;
    }

    /**
     * 查询品牌所有数据, 返回, 给模板中select2下拉框使用, 数据格式是select2下拉框规定的
     * 例如: $scope.brandList={data:[{id:1,text:'联想'},{id:2,text:'华为'},{id:3,text:'小米'}]}
     */
}
