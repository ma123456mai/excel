package com.easyexcel.pojo.param.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Mr丶s
 * @date 2020/6/6 2:22 下午
 * @description
 */
@Data
public class Dict implements Serializable {

    /**
     * 字典类型,1-常规数据,2-功能数据,3-系统数据,4-其他
     */
    private String type;

    /**
     * 功能简称,方便按功能归类,知道该数据是哪个功能的数据
     */
    private String funcAlias;

    /**
     * 数据key
     */
    private String key;

    /**
     * 数据value
     */
    private String value;


    /**
     * 忽略这个字段
     */
    private String ignore;
}
