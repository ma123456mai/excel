package com.easyexcel.controller;

import com.easyexcel.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Mr丶s
 * @date 2020/6/6 1:58 下午
 * @description
 */
@RestController
@RequestMapping("excel")
public class ExcelController {


    @Autowired
    private ExcelService excelService;


    /**
     * 导出
     */
    @GetMapping("write")
    public void writeExcel(HttpServletResponse response) {
        excelService.writeExcel(response);
    }


    /**
     * 导入
     */
    @PostMapping("read")
    public void readExcel(@RequestParam("excelFile") MultipartFile excelFile) {
        excelService.readExcel(excelFile);
    }


}
