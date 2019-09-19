package com.capgemini.cn.demo.userSystem.mapper;

import com.capgemini.cn.demo.userSystem.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hasaker
 * @since 2019/9/8 10:43
 */
@Repository
@Mapper
public interface MenuMapper {

    Menu getMenuByMenuId(@Param("menuId") Long menuId);

    Menu getMenuByMenuName(@Param("menuName") String menuName);

    Integer addMenu(@Param("menu") Menu menu);

    List<Menu> getAllMenus();
}
