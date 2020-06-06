package com.easyexcel.pojo.param;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 数据字典表
 * </p>
 *
 * @author Mr丶s
 * @since 2020-05-11
 */
@Data
public class DictReadParam implements Serializable {


    /**
     * 字典类型,1-常规数据,2-功能数据,3-系统数据,4-其他
     */
    @ExcelProperty("字典类型")
    private String type;

    /**
     * 功能简称,方便按功能归类,知道该数据是哪个功能的数据
     */
    @ExcelProperty("功能简称")
    private String funcAlias;

    /**
     * 数据key
     */
    @ExcelProperty("数据key")
    private String key;

    /**
     * 数据value
     */
    @ExcelProperty("数据value")
    private String value;


}
