package cn.itcast.core.controller;

import cn.itcast.core.service.zhexianService;
import com.alibaba.dubbo.config.annotation.Reference;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rest")
public class echarsController {
    @Reference
    private zhexianService echartsService;

    @RequestMapping("/userChart")
    public  Map<String,Object> userChart() throws JsonProcessingException {
        return echartsService.userChart();
//        String s = new ObjectMapper().writeValueAsString(stringListMap);
//        return s;
    }

    @RequestMapping("/echartslist")
    public Map<String,List<String>> echartslist(){
        return echartsService.echartslist();
    }
}
