package com.didu.domain;

/**
 * Created by Administrator on 2017/12/9.
 */
public class Reduser {
    private int id;
    private String redname;
    private String city;
    private String phone;
    private String identity;
    private String wechat;
    private String remark;
    private String status;
    private int uid;

    public Reduser(int id, String redname, String city, String phone, String identity, String wechat, String remark, String status, int uid) {
        this.id = id;
        this.redname = redname;
        this.city = city;
        this.phone = phone;
        this.identity = identity;
        this.wechat = wechat;
        this.remark = remark;
        this.status = status;
        this.uid = uid;
    }

    public Reduser(String redname, String city, String phone, String identity, String wechat, String remark, String status, int uid) {
        this.redname = redname;
        this.city = city;
        this.phone = phone;
        this.identity = identity;
        this.wechat = wechat;
        this.remark = remark;
        this.status = status;
        this.uid = uid;
    }

    public Reduser() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRedname() {
        return redname;
    }

    public void setRedname(String redname) {
        this.redname = redname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "Reduser{" +
                "id=" + id +
                ", redname='" + redname + '\'' +
                ", city='" + city + '\'' +
                ", phone='" + phone + '\'' +
                ", identity='" + identity + '\'' +
                ", wechat='" + wechat + '\'' +
                ", remark='" + remark + '\'' +
                ", status='" + status + '\'' +
                ", uid=" + uid +
                '}';
    }
}
