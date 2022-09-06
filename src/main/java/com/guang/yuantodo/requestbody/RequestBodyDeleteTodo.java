package com.guang.yuantodo.requestbody;

import com.guang.yuantodo.enums.TodoTypeEnum;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class RequestBodyDeleteTodo {

    @NotNull(message = "tId 必传")
    private Integer tId;

}

