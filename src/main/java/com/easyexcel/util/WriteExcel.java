package com.easyexcel.util;

import com.alibaba.excel.EasyExcel;
import com.easyexcel.exception.ExcelException;
import com.easyexcel.response.ResponseEnum;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author Mr丶s
 * @date 2020/6/6 1:27 下午
 * @description 导出excel
 */
@Slf4j
public class WriteExcel {

    /**
     * 数据导出
     *
     * @param cla      导出的模版类
     * @param data     导出数据
     * @param fileName 导出文件名称
     * @param response response
     */
    public static void simpleWrite(Class cla, List data, String fileName, HttpServletResponse response) {
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            // 这里需要设置不关闭流
            EasyExcel.write(response.getOutputStream(), cla).autoCloseStream(Boolean.FALSE).sheet("模板")
                    .doWrite(data);
        } catch (Exception e) {
            throw new ExcelException(ResponseEnum.RESULT_EXCEL_EXPORT_ERROR);
        }
    }


}
