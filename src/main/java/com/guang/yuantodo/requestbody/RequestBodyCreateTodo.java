package com.guang.yuantodo.requestbody;

import com.guang.yuantodo.enums.TodoTypeEnum;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class RequestBodyCreateTodo {

    @NotNull(message = "type必传")
    private TodoTypeEnum type;

    @NotNull(message = "content必传")
    private String content;

    @NotNull(message = "sort必传")
    private Double sort;
}
