package com.elon.springbootdemo.model;
import java.io.Serializable;

/**
 * 通用的响应模型。
 * @author elon
 * @version 2018年11月4日
 */
public class ResponseModel <T> implements Serializable {

    private static final long serialVersionUID = 773236882872100177L;

    /**
     * 错误码。1是成功，0是失败。可定义其它错误码
     */
    private int retCode = 1;
    
    /**
     * 错误信息
     */
    private String errorMsg = "";
    
    /**
     * 操作结果
     */
    private T result = null;
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static ResponseModel success(Object result) {
        ResponseModel rm = new ResponseModel();
        rm.setResult(result);
        return rm;
    }
    
    @SuppressWarnings("rawtypes")
    public static ResponseModel error(String errorInfo) {
        ResponseModel rm = new ResponseModel();
        rm.setErrorMsg(errorInfo);
        return rm;
    }

    public int getRetCode() {
        return retCode;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
