package com.easyexcel.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Mr丶s
 * @date 2020/6/6 1:35 下午
 * @description
 */
public interface ExcelService {


    /**
     * 导出
     */
    void writeExcel(HttpServletResponse response);


    /**
     * 导入
     */
    void readExcel(MultipartFile excelFile);

}
