package com.changgou.service;

import com.changgou.dao.BrandMapper;
import com.changgou.goods.pojo.Brand;
import com.changgou.service.impl.BrandService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;
 @Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandMapper brandMapper;

    /**
     * 查询所有
     */
    @Override
    public List<Brand> findAll() {
        return brandMapper.selectAll();
    }

     /**
      * 根据id查询数据
      * @return
      */
     @Override
     public Brand findById(Integer id) {
         return brandMapper.selectByPrimaryKey(id);
     }

     /**
      * 添加品牌
      * @param brand
      */
     @Override
     public void add(Brand brand) {
       brandMapper.insertSelective(brand);

     }

     /**
      * 修改品牌
      * @param brand
      */
     @Override
     public void update(Brand brand) {
        brandMapper.updateByPrimaryKeySelective(brand);
     }

     /**
      * 根据品牌id删除品牌
      * @param id
      */
     @Override
     public void delete(Integer id) {
       brandMapper.deleteByPrimaryKey(id);
     }

     /**
      * 多条件实现查询
      */
     @Override
     public List<Brand> findList(Brand brand){
         //自定义条件搜索对象
         Example example = new Example(Brand.class);
         //条件构造器
         Example.Criteria criteria = example.createCriteria();
         if (brand != null){
             //根据名称实现模糊搜索
             if (!StringUtils.isEmpty(brand.getName())){
               criteria.andLike("name","%"+brand.getName()+"%");
             }
         }
         //根据首字母搜索
         if (!StringUtils.isEmpty(brand.getLetter())){
             criteria.andEqualTo("letter",brand.getLetter());
         }
         return brandMapper.selectByExample(example);
     }

     /**
      * 分页查询
      * @param page
      * @param size
      * @return
      */
     @Override
     public PageInfo<Brand> findPage(Integer page, Integer size) {
         //注意：PageHelper.startPage(page,size)分页实现后面的查询只能跟集合查询
         PageHelper.startPage(page,size);
         //查询集合
         List<Brand> brands = brandMapper.selectAll();
         //封装pageinfo
         return new PageInfo<Brand>(brands);
     }
 }
