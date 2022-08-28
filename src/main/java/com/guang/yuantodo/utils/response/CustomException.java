package com.guang.yuantodo.utils.response;

import org.springframework.validation.BindingResult;

public class CustomException extends Exception {

    private CustomHttpStatus customHttpStatus;

    public CustomException(CustomHttpStatus customHttpStatus) {
        super();
        this.customHttpStatus = customHttpStatus;
    }

    public CustomHttpStatus getCustomHttpStatus() {
        return customHttpStatus;
    }
}
