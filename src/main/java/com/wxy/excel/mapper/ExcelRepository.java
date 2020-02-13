package com.wxy.excel.mapper;
import com.wxy.excel.entity.Excel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: 王鑫垚
 * @Description:
 * @Date: Create in 23:04 2020/2/12
 */
@Repository
public interface ExcelRepository extends JpaRepository<Excel,String> {
}
