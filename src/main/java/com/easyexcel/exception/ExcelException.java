package com.easyexcel.exception;


import com.easyexcel.response.ResponseEnum;

/**
 * @author heyongjiu
 * @date 2019-05-26 22:45
 * @description 通用异常
 */

public class ExcelException extends RuntimeException {

    /**
     * 业务错误码
     */
    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * 通用异常
     *
     * @param responseEnum 错误信息类
     */
    public ExcelException(ResponseEnum responseEnum) {
        super(responseEnum.getMessage());
        this.code = responseEnum.getCode();
    }

    /**
     * 通用异常
     *
     * @param code 错误码
     * @param msg  错误信息
     */
    public ExcelException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }
}
