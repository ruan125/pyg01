package cn.itcast.core.controller;

import cn.itcast.core.pojo.address.Address;
import cn.itcast.core.pojo.address.Areas;
import cn.itcast.core.pojo.address.Cities;
import cn.itcast.core.pojo.address.Provinces;
import cn.itcast.core.pojo.entity.Result;
import cn.itcast.core.service.AddressService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Reference
    private AddressService AddressService;

    @RequestMapping("/findListByLoginUser")
    public List<Address> findListByLoginUser() {
        //1. 获取当前用户的登录名
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        //2. 根据用户名获取这个人收货地址列表
        List<Address> addressList = AddressService.findListByLoginUser(userName);
        return addressList;
    }

    @RequestMapping("/add")
    public Result add(@RequestBody Address address){
        try {
            //获取当前登录用户的用户名
            String userName = SecurityContextHolder.getContext().getAuthentication().getName();
            address.setUserId(userName);
            AddressService.add(address);
            return new Result(true, "添加成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "添加失败!");
        }
    }

    @RequestMapping("/findOne")
    public Address findOne(Long id){
        Address one = AddressService.findOne(id);
        return one;
    }

    @RequestMapping("/update")
    public Result update(@RequestBody Address address){
        try {
        //获取当前登录用户的用户名
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        address.setUserId(userName);
        AddressService.update(address);
            return new Result(true, "修改成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败!");
        }
    }

    @RequestMapping("/delete")
   public Result dele(Long id){
       try {
           //获取当前登录用户的用户名
           String userName = SecurityContextHolder.getContext().getAuthentication().getName();
            AddressService.dele(id,userName);
           return new Result(true, "删除成功!");
       } catch (Exception e) {
           e.printStackTrace();
           return new Result(false, "删除失败!");
       }
   }

   @RequestMapping("/updateStatus")
   public Result updateStatus(Long id){
       try { //获取当前登录用户的用户名
           String userName = SecurityContextHolder.getContext().getAuthentication().getName();
           AddressService.updateStatus(id,userName);
           return new Result(true, "设置成功!");
       } catch (Exception e) {
           e.printStackTrace();
           return new Result(false, "设置失败!");
       }
   }

   @RequestMapping("/findByParentId")
    public List<Provinces> findByParentId(String  parentId){
       List<Provinces> provinces = AddressService.findByParentId(parentId);
       return provinces;
   }
    @RequestMapping("/findByParentId2")
    public List<Cities> findByParentId2(String parentId2){
        List<Cities> cities = AddressService.findByParentId2(parentId2);
        return cities;
    }
    @RequestMapping("/findByParentId3")
    public List<Areas> findByParentId3(String parentId3){
        List<Areas> areas = AddressService.findByParentId3(parentId3);
        return areas;
    }
}
