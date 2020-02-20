package com.wxy.excel.mapper;

import com.wxy.excel.entity.Excel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: 王鑫垚
 * @Description:
 * @Date: Create in 21:03 2020/2/20
 */
@Mapper
public interface UserMapper {

    @Select("select * from excel")
    List<Excel> getAllUser();

}
