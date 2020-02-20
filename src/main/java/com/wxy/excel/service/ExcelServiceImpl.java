package com.wxy.excel.service;
import com.wxy.excel.mapper.ExcelRepository;
import com.wxy.excel.entity.Excel;
import com.wxy.excel.mapper.UserMapper;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: 王鑫垚
 * @Description:
 * @Date: Create in 22:57 2020/2/12
 */

@Service
public class ExcelServiceImpl implements ExcelService{
    @Autowired
    private ExcelRepository excelRepository;
    @Autowired
    private UserMapper userMapper;
    @Override
    public boolean getExcel(MultipartFile file) throws Exception {
        List<Excel> list = new ArrayList<Excel>();
        //1.得到上传的表
        Workbook workbook2 = WorkbookFactory.create(file.getInputStream());
        //2.获取test工作表 注意test就是excel下面的sheet名称
        Sheet sheet2 = workbook2.getSheet("test");
        //3.获取表的总行数
        int num = sheet2.getLastRowNum();
        //4.获取表总列数
        int col = sheet2.getRow(0).getLastCellNum();
        //5.遍历excel每一行
        for (int j = 0; j <= num; j++) {
            Row row1 = sheet2.getRow(j);
            // 如果单元格中有数字或者其他格式的数据，则调用setCellType()转换为string类型
            Cell cell1 = row1.getCell(0);
            cell1.setCellType(CellType.STRING);
            //获取表中第i行，第2列的单元格
            Cell cell2 = row1.getCell(1);
            cell2.setCellType(CellType.STRING);
            //获取excel表的第i行，第3列的单元格
            Cell cell3 = row1.getCell(2);
            cell3.setCellType(CellType.STRING);
            Cell cell4 = row1.getCell(3);
            cell4.setCellType(CellType.STRING);
            Cell cell5 = row1.getCell(4);
            cell5.setCellType(CellType.STRING);
            //这里new 一个对象，用来装填从页面上传的Excel数据，字段根据上传的excel决定
            Excel excel= new Excel();
            excel.setId(cell1.getStringCellValue());
            excel.setUsername(cell2.getStringCellValue());
            excel.setEmail(cell3.getStringCellValue());
            excel.setPassword(cell4.getStringCellValue());
            excel.setRole(cell5.getStringCellValue());
            list.add(excel);
            System.out.println("excel"+excel);
        }
        excelRepository.saveAll(list);
        return true;
    }

    @Override
    public void exportExcel(HttpServletResponse response) throws IOException {
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("test");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow(0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        // 创建一个居中格式
        style.setAlignment(HorizontalAlignment.CENTER);
        /*此处根据情况自己自定义样式*/

        HSSFCell cell = row.createCell(0);
        cell.setCellValue("ID");
        cell.setCellStyle(style);
        cell = row.createCell(1);
        cell.setCellValue("姓名");
        cell.setCellStyle(style);
        cell = row.createCell(2);
        cell.setCellValue("邮箱");
        cell.setCellStyle(style);
        cell = row.createCell(3);
        cell.setCellValue("密码");
        cell.setCellStyle(style);
        cell = row.createCell(4);
        cell.setCellValue("角色");
        cell.setCellStyle(style);

        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，
        List<Excel> list = userMapper.getAllUser();

        for (int i = 0; i < list.size(); i++) {
            row = sheet.createRow(i + 1);
            Excel excel = list.get(i);
            // 创建单元格，并设置值
            row.createCell(0).setCellValue(excel.getId());
            row.createCell(1).setCellValue(excel.getUsername());
            row.createCell(2).setCellValue(excel.getEmail());
            row.createCell(3).setCellValue(excel.getPassword());
            row.createCell(4).setCellValue(excel.getRole());
        }
        //第六步,输出Excel文件
        OutputStream output = response.getOutputStream();
        response.reset();
        //设置日期格式
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        // 获取当前系统时间
        String fileName = df.format(new Date());
        //设置导出文件表头（即文件名）
        response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");
        //设置返回内容类型
        response.setContentType("application/msexcel");
        wb.write(output);
        output.close();
    }
}
