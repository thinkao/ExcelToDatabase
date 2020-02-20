package com.wxy.excel.service;

import com.wxy.excel.entity.Excel;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author: 王鑫垚
 * @Description:
 * @Date: Create in 22:56 2020/2/12
 */
public interface ExcelService {

    boolean getExcel(MultipartFile file) throws Exception;

    void exportExcel(HttpServletResponse response) throws IOException;
}
