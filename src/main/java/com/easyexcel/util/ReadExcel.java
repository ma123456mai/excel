package com.easyexcel.util;

import com.alibaba.excel.EasyExcel;
import com.easyexcel.consts.ExcelConsts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Mr丶s
 * @date 2020/6/3 2:57 下午
 * @description
 */
@Slf4j
public class ReadExcel {

    /**
     * 是否为Excel文件
     *
     * @param file 表单文件
     * @return 文件格式是否正确
     */
    public static boolean isExcelFile(MultipartFile file) throws IOException {
        String filename = file.getOriginalFilename();
        String header = GeneralUtils.getFileHeader(file.getInputStream());
        return (filename.endsWith(ExcelConsts.EXCEL_VERSION_XLSX) || filename.endsWith(ExcelConsts.EXCEL_VERSION_XLS)) &&
                (header.contains(ExcelConsts.XLS_HEADER) || header.contains(ExcelConsts.XLSX_HEADER));
    }


    /**
     * 读excel文件信息
     *
     * @param excelFile 文件
     * @param data      实体
     */
    public static void readExcel(MultipartFile excelFile, Class data) {
        try {
            EasyExcel.read(excelFile.getInputStream(), data, new ReadExcelListener()).sheet(0).doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
