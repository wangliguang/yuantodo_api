package com.guang.yuantodo.requestbody;

import com.guang.yuantodo.enums.TodoTypeEnum;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Setter
@Getter
public class RequestBodyUpdateTodo {

    private TodoTypeEnum type;

    private String content;

    private Integer done;

    @NotNull(message = "t_id必传")
    Integer t_id;

    private Date planTime;


    private Double sort;

}
