package cn.itcast.core.service;

import cn.itcast.core.dao.good.GoodsDao;
import cn.itcast.core.dao.template.TypeTemplateDao;
import cn.itcast.core.pojo.entity.Obj;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@SuppressWarnings("all")
public class echarsServiceImpl implements zhexianService{
    @Autowired
    private TypeTemplateDao typeTemplateDao;

    @Autowired
    private GoodsDao goodsDao;

    @Override
    public Map<String, Object> userChart() {
        Map<String,Object> map=new HashMap<>();

        //定义变量
        int mobileNum=0;
        int tvNum=0;
        int comNum=0;
        int cloNum=0;
        List<String> ids = goodsDao.selectTypeTemplateIds();
        //遍历id
        for (String id : ids) {
            if ("35".equals(id)){
                mobileNum++;
            }
            if ("37".equals(id)){
                tvNum++;
            }
            if ("38".equals(id)){
                comNum++;
            }

            if ("40".equals(id)){
                cloNum++;
            }

        }
        List<Integer> nums=new ArrayList<>();
        nums.add(mobileNum);
        nums.add(tvNum);
        nums.add(comNum);
        nums.add(cloNum);

        List<String> nameList = typeTemplateDao.selectNames();

        List<Obj> objList=new ArrayList<>();
        for (int i = 0; i < nameList.size(); i++) {
            Obj obj = new Obj();
            String s = nameList.get(i);
            obj.setName(s);
            obj.setValue(nums.get(i)+"");
            objList.add(obj);
        }
        map.put("category",objList);
        map.put("name",nameList);

        return map;
    }

    @Override
    public Map<String, List<String>> echartslist() {
        Map<String,List<String>> map=new HashMap<>();
        List<String> ids = goodsDao.selectTypeTemplateIds();
        //定义变量
        int mobileNum=0;
        int tvNum=0;
        int comNum=0;
        int cloNum=0;
        //遍历id
        for (String id : ids) {
            if ("35".equals(id)){
                mobileNum++;
            }
            if ("37".equals(id)){
                tvNum++;
            }
            if ("38".equals(id)){
                comNum++;
            }

            if ("40".equals(id)){
                cloNum++;
            }

        }
        List<String> nums=new ArrayList<>();
        nums.add(mobileNum+"");
        nums.add(tvNum+"");
        nums.add(comNum+"");
        nums.add(cloNum+"");
        map.put("data",nums);

        List<String> nameList = typeTemplateDao.selectNames();
        map.put("categories",nameList);

        return map;
    }
}

