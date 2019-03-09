package cn.itcast.core.controller;

import cn.itcast.core.common.importExcel;
import cn.itcast.core.pojo.entity.Result;
import cn.itcast.core.service.BrandService;
import cn.itcast.core.service.SpecService;
import cn.itcast.core.service.TemplateService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/impotr")
public class importController {

    @Reference
    private BrandService brandService;


    @RequestMapping("/importList1Excel")
    public Result importList1Excel() throws Exception{
        try {
            brandService.insertDB();
            return new Result(true,"导入成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"导入失败!");
        }
    }

}
