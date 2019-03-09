package cn.itcast.core.service;

import cn.itcast.core.pojo.entity.PageResult;
import cn.itcast.core.pojo.good.Brand;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface BrandService {

    public List<Brand> findAll();

    public PageResult findPage(Brand brand ,Integer page, Integer rows);

    public void add(Brand brand);

    public Brand findOne(Long id);

    public void update(Brand brand);

    public void delete(Long[] ids);

    public List<Map> selectOptionList();

    //导入到数据库
    public void insertDB() throws Exception;
}
