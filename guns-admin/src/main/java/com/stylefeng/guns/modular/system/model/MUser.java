package com.stylefeng.guns.modular.system.model;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author stylefeng123
 * @since 2018-06-16
 */
@TableName("m_user")
public class MUser extends Model<MUser> {

    private static final long serialVersionUID = 1L;

    private String id;
    @TableField("login_name")
    private String loginName;
    @TableField("user_pass")
    private String userPass;
    /**
     * 用户姓名
     */
    @TableField("user_name")
    private String userName;
    private String mobile;
    private String token;
    @TableField("create_time")
    private Date createTime;
    private String sex;
    /**
     * 登录时间
     */
    @TableField("login_time")
    private Date loginTime;
    /**
     * 上次登录时间
     */
    @TableField("last_login_time")
    private Date lastLoginTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "MUser{" +
        "id=" + id +
        ", loginName=" + loginName +
        ", userPass=" + userPass +
        ", userName=" + userName +
        ", mobile=" + mobile +
        ", token=" + token +
        ", createTime=" + createTime +
        ", sex=" + sex +
        ", loginTime=" + loginTime +
        ", lastLoginTime=" + lastLoginTime +
        "}";
    }
}
