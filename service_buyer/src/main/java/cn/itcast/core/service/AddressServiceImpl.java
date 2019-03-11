package cn.itcast.core.service;

import cn.itcast.core.dao.address.AddressDao;
import cn.itcast.core.dao.address.AreasDao;
import cn.itcast.core.dao.address.CitiesDao;
import cn.itcast.core.dao.address.ProvincesDao;
import cn.itcast.core.pojo.address.*;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressDao addressDao;

    @Autowired
    private ProvincesDao provincesDao;

    @Autowired
    private CitiesDao citiesDao;

    @Autowired
    private AreasDao areasDao;


    @Override
    public List<Address> findListByLoginUser(String userName) {
        AddressQuery query = new AddressQuery();
        AddressQuery.Criteria criteria = query.createCriteria();
        criteria.andUserIdEqualTo(userName);
        List<Address> addresses = addressDao.selectByExample(query);
        return addresses;
    }

    /**
     * 添加用户地址  更改dao添加后会返回id
     * @param address
     */
    @Override
    public void add(Address address) {
        address.setIsDefault("0");
        addressDao.insertSelective(address);
    }

    @Override
    public Address findOne(Long id) {
        Address address = addressDao.selectByPrimaryKey(id);
        return address;
    }

    @Override
    public void update(Address address) {
        addressDao.updateByPrimaryKeySelective(address);
    }

    @Override
    public void dele(Long id,String userName) {
        if(id!=null){
            AddressQuery query = new AddressQuery();
            AddressQuery.Criteria criteria = query.createCriteria();
            criteria.andUserIdEqualTo(userName);
            criteria.andIdEqualTo(id);
            addressDao.deleteByExample(query);
            }
    }

    @Override
    public void updateStatus(Long id, String userName) {
        if(id!=null) {
            List<Address> addresses = findListByLoginUser(userName);
            for (Address address : addresses) {
                address.setIsDefault("0");
            }
            Address address = findOne(id);
            address.setIsDefault("1");
            addressDao.updateByPrimaryKeySelective(address);
        }
    }

    @Override
    public List<Provinces> findByParentId(String parentId) {
            List<Provinces> provinces = provincesDao.selectByExample(null);
            return provinces;

    }

    @Override
    public List<Cities> findByParentId2(String parentId) {
        CitiesQuery query = new CitiesQuery();
        CitiesQuery.Criteria criteria = query.createCriteria();
        criteria.andProvinceidEqualTo(parentId);
        List<Cities> cities = citiesDao.selectByExample(query);
        return cities;
    }

    @Override
    public List<Areas> findByParentId3(String parentId) {
        AreasQuery query = new AreasQuery();
        AreasQuery.Criteria criteria = query.createCriteria();
        criteria.andCityidEqualTo(parentId);
        List<Areas> areas = areasDao.selectByExample(query);
        return areas;
    }
}
