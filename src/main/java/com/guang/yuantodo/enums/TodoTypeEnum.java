package com.guang.yuantodo.enums;

public enum TodoTypeEnum {

    IM_UR("重要紧急"),
    IM_noUR("重要不紧急"),
    noIM_UR("不重要紧急"),
    noIM_noUR("不重要不紧急");

    private String desc;
    private TodoTypeEnum(String desc) {
        this.desc = desc;
    }
}
