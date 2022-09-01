package com.guang.yuantodo.utils.response;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResultData<String> exception(CustomException e) {
        return ResultData.fail(e.getCustomHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultData<String> exception(Exception e) {
        return ResultData.fail(CustomHttpStatus.RC500.getCode(), e.getMessage());
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
