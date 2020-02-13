package com.wxy.excel.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: 王鑫垚
 * @Description:
 * @Date: Create in 22:56 2020/2/12
 */
public interface ExcelService {

    boolean getExcel(MultipartFile file) throws Exception;
}
