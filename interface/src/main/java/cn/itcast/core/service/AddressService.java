package cn.itcast.core.service;

import cn.itcast.core.pojo.address.Address;
import cn.itcast.core.pojo.address.Areas;
import cn.itcast.core.pojo.address.Cities;
import cn.itcast.core.pojo.address.Provinces;

import java.util.List;

public interface AddressService {

    public List<Address> findListByLoginUser(String userName);

    /**
     * 用户添加地址
     * @param address
     */
    public void add(Address address);

    public Address findOne(Long id);

    public void update(Address address);

    public void dele(Long id,String userName);

    public void updateStatus(Long id,String userName);

    public List<Provinces> findByParentId(String parentId);

    public List<Cities> findByParentId2(String parentId);

    public List<Areas> findByParentId3(String parentId);
}
