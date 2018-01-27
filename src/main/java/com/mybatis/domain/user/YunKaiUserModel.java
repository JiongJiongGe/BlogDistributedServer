package com.mybatis.domain.user;

import java.io.Serializable;

/**
 * 用户model
 *
 * Created by yunkai on 2017/5/24.
 */
public class YunKaiUserModel implements Serializable{

    private static final long serialVersionUID = -731158379505299844L;

    private Integer id;

    private String userName; //用户名称

    private String userPhone;//用户联系方式

    private Integer isDel;   //是否删除 0、未删除;1、已删除

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
}
