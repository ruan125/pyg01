package cn.itcast.core.controller;

import cn.itcast.core.pojo.ad.Content;
import cn.itcast.core.service.ContentService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/content")
public class ContentController {

    @Reference
    private ContentService contentService;
    //首页广告展示
    @RequestMapping("/findByCategoryId")
    public List<Content> findByCategoryId(Long categoryId) {
        System.out.println(categoryId);
        return contentService.findByCategoryIdFromRedis(categoryId);
    }
    //楼层广告展示
    @RequestMapping("findByFloorId")
    public List<Content> findByFloorId(long floorId){
        System.out.println(floorId);
    //前端请求携带分类ID，根据分类ID 查询对应广告，先查询redis，如果有直接返回，如果没有去数据库content查询
        return contentService.findByFloorIdFromRedis(floorId);
    }
}
