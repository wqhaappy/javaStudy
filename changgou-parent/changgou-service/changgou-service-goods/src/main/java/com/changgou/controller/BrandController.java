package com.changgou.controller;

import com.changgou.goods.pojo.Brand;
import com.changgou.service.impl.BrandService;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import javafx.geometry.Pos;
import org.apache.logging.log4j.message.ReusableMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/brand")
@CrossOrigin//跨域访问数据
public class BrandController {
    @Autowired
    private BrandService brandService;
    /**
     * 查询所有品牌
     * @return
     */
    @GetMapping
    public Result<List<Brand>> findAll(){
        List<Brand> brands = brandService.findAll();
        return new Result<List<Brand>>(true, StatusCode.OK,"查询品牌集合成功",brands);
    }

    /**
     * 根据id查询数据
     */
    @GetMapping(value = "/{id}")
    public Result<Brand>  findById(@PathVariable(value = "id")Integer id){
        Brand brand = brandService.findById(id);
        return new Result<Brand>(true,StatusCode.OK,"根据id查询Brand成功",brand);
    }

    /**
     * 增加品牌
     */
    @PostMapping
    public Result add(@RequestBody Brand brand){
        brandService.add(brand);
     return new Result(true,StatusCode.OK,"增加品牌成功");
    }

    /**
     * 修改品牌
     */
    @PutMapping(value = "/{id}")
    public Result update(@PathVariable(value = "id")Integer id,@RequestBody Brand brand){
        brand.setId(id);
        brandService.update(brand);
        return new Result(true,StatusCode.OK,"修改品牌成功");
    }

    /**
     * 根据品牌id删除品牌信息
     */
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable(value = "id")Integer id){
        brandService.delete(id);
        return new Result(true,StatusCode.OK,"删除品牌成功");
    }
    /**
     * 根据多条件查询数据
     */
    @PostMapping(value="/search")
    public Result<List<Brand>> findList(@RequestBody Brand brand){
       List<Brand> list= brandService.findList(brand);
       return new Result<List<Brand>>(true,StatusCode.OK,"查询品牌成功",list);
    }

    /**
     * 分页查询实现
     */
    @GetMapping(value = "/search/{page}/{size}")
    public Result<PageInfo<Brand>> findPage(@PathVariable(value = "page")Integer page,
                                    @PathVariable(value = "size")Integer size){
      PageInfo<Brand> pageInfo = brandService.findPage(page,size);
      return new Result<PageInfo<Brand>>(true,StatusCode.OK,"分页查询成功",pageInfo);
    }

}
