package com.easyexcel.consts;

/**
 * @author Mr丶s
 * @date 2020/6/6 1:37 下午
 * @description excel常量
 */
public class ExcelConsts {


    /**
     * 开始读取的行的index
     */
    public static final int START_READ_ROW_INDEX = 2;

    /**
     * 最大支持的数据条数
     */
    public static final int MAX_DATA_SIZE = 500;
    /**
     * response的contentType
     */
    public static final String CONTENT_TYPE = "application/vnd.ms-excel;charset=utf-8";
    /**
     * excel后缀
     */
    public static final String EXCEL_VERSION_XLSX = ".xlsx";
    public static final String EXCEL_VERSION_XLS = ".xls";
    public static final String EXCEL_VERSION_XLSM = ".xlsm";
    /**
     * response的header s参数
     */
    public static final String HEADER_S = "content-Disposition";
    /**
     * response的header s1参数
     */
    public static final String RESPONSE_HEADER = "attachment;filename=";

    /**
     * 日期转换格式
     */
    public static final String DATE_PATTERN = "yyyyMMddHHmmss";

    /**
     * xls格式Excel文件头信息
     */
    public static final String XLS_HEADER = "D0CF11E0";
    /**
     * xlsx格式Excel文件头信息
     */
    public static final String XLSX_HEADER = "504B0304";


}
