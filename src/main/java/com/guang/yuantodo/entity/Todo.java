package com.guang.yuantodo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.guang.yuantodo.enums.TodoTypeEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 * 
 * </p>
 *
 * @author 王立广
 * @since 2022-05-07
 */
@TableName("todos")
@Setter
@Getter
@NoArgsConstructor
public class Todo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer tId;

    private Integer uId;

    private TodoTypeEnum type;

    private String check;

    private String content;

    @TableField(fill = FieldFill.INSERT)
    private LocalDate createTime;

    @TableField(fill = FieldFill.UPDATE)
    private LocalDate updateTime;

    @TableLogic(delval = "1", value = "0")
    private String deleted;

    @Override
    public String toString() {
        return "Todo{" +
                "tId=" + tId +
                ", uId=" + uId +
                ", type='" + type + '\'' +
                ", check='" + check + '\'' +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", deleted='" + deleted + '\'' +
                '}';
    }
}
