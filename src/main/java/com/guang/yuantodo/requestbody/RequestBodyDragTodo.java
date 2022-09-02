package com.guang.yuantodo.requestbody;

import com.guang.yuantodo.enums.TodoTypeEnum;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class RequestBodyDragTodo {

    @NotNull(message = "sourceTodoId 必传")
    private Integer sourceTodoId;

    @NotNull(message = "sourceTodoType 必传")
    private TodoTypeEnum sourceTodoType;

    @NotNull(message = "sourceTodoSort 必传")
    private Double sourceTodoSort;


    @NotNull(message = "destTodoId 必传")
    private Integer destTodoId;

    @NotNull(message = "destTodoType 必传")
    private TodoTypeEnum destTodoType;

    @NotNull(message = "destTodoSort 必传")
    private Double destTodoSort;
}

