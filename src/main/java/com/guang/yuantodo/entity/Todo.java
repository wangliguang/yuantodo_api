package com.guang.yuantodo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.guang.yuantodo.enums.TodoTypeEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.beanvalidation.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

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
@SuperBuilder(toBuilder = true)
public class Todo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer tId;

    private Integer uId;

    private TodoTypeEnum type;

    private Integer sort;

    private Integer done;

    @NotNull(message = "content为必填项")
    private String content;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "update_time",fill = FieldFill.UPDATE)
    private Date updateTime;

    @TableLogic(delval = "1", value = "0")
    private String deleted;

    @Override
    public String toString() {
        return "Todo{" +
                "tId=" + tId +
                ", uId=" + uId +
                ", type='" + type + '\'' +
                ", check='" + done + '\'' +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", deleted='" + deleted + '\'' +
                '}';
    }
}
