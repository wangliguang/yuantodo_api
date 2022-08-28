package com.guang.yuantodo.utils.response;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResultData<String> exception(CustomException e) {
        return ResultData.fail(e.getCustomHttpStatus());
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        FieldError filedError = e.getBindingResult().getFieldError();
        return ResultData.fail(CustomHttpStatus.RC402.getCode(),filedError.getDefaultMessage());
    }

}
