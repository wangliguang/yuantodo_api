package com.guang.yuantodo.requestbody;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Getter
@Setter
public class RequestBodyTodo {

    private static final long serialVersionUID = 1L;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "请设置开始时间")
    private Date beginDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "请设置截止时间")
    private Date endDate;
}

