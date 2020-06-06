package com.easyexcel.response;


import com.easyexcel.consts.ExcelConsts;

/**
 * @author Mr丶s
 * @date 2020/6/6 1:29 下午
 * @description 业务响应信息
 */

public enum ResponseEnum {

    /**
     * 通用业务段
     */
    RESULT_SUCCESS(200, "成功"),
    RESULT_ERROR(500, "服务器内部错误"),
    RESULT_PARAMETER_NULL(601, "参数为空"),
    RESULT_DATA_NULL(602, "操作数据不存在"),
    RESULT_DATA_REPETITION(603, "数据重复"),
    RESULT_MUST_REQUIRE_PARAMETER_NULL(604, "必填参数不能为空"),
    RESULT_RULE_TYPE_NOT_REPEAT(605, "规则类型不能重复"),
    RESULT_EXCEL_EXPORT_ERROR(606, "excel导出失败"),
    RESULT_EXCEL_DATA_SIZE_OVERFLOW(607, "一次最多只能导入" + ExcelConsts.MAX_DATA_SIZE + "条"),
    RESULT_FILE_IS_NULL(608, "文件不能为空"),
    RESULT_FILE_NOT_SUPPORT_TYPE(609, "文件格式错误"),





    ;


    private Integer code;
    private String message;

    ResponseEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}