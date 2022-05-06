package com.guang.yuantodo.entity;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 王立广
 * @since 2022-05-06
 */
public class Todos implements Serializable {

    private static final long serialVersionUID = 1L;

      private String tId;

    private String phone;

    private String type;

    private String check;

    private String content;

    private String curDate;


    public String gettId() {
        return tId;
    }

    public void settId(String tId) {
        this.tId = tId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCurDate() {
        return curDate;
    }

    public void setCurDate(String curDate) {
        this.curDate = curDate;
    }

    @Override
    public String toString() {
        return "Todos{" +
        "tId=" + tId +
        ", phone=" + phone +
        ", type=" + type +
        ", check=" + check +
        ", content=" + content +
        ", curDate=" + curDate +
        "}";
    }
}
