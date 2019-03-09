package cn.itcast.core.controller;

import cn.itcast.core.pojo.entity.Result;
import cn.itcast.core.service.GoodsService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * 数据库导出
 */
@RestController
@RequestMapping("/exp")
public class ExportController {

    @Reference
    private GoodsService goodsService;

    @RequestMapping("/export")
    public Result export() throws IOException {
        goodsService.findAll();
        return new Result(true,"导出成功!");
    }
}
