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
    private String uid;
    private String address;
    private String introduce;
    private String workname;
    private String birthdata;

    public Reduser(int id, String redname, String city, String phone, String identity, String wechat, String remark, String status, String uid, String address, String introduce, String workname, String birthdata) {
        this.id = id;
        this.redname = redname;
        this.city = city;
        this.phone = phone;
        this.identity = identity;
        this.wechat = wechat;
        this.remark = remark;
        this.status = status;
        this.uid = uid;
        this.address = address;
        this.introduce = introduce;
        this.workname = workname;
        this.birthdata = birthdata;
    }

    public Reduser(String redname, String city, String phone, String identity, String wechat, String remark, String status, String uid, String address, String introduce, String workname, String birthdata) {
        this.redname = redname;
        this.city = city;
        this.phone = phone;
        this.identity = identity;
        this.wechat = wechat;
        this.remark = remark;
        this.status = status;
        this.uid = uid;
        this.address = address;
        this.introduce = introduce;
        this.workname = workname;
        this.birthdata = birthdata;
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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getWorkname() {
        return workname;
    }

    public void setWorkname(String workname) {
        this.workname = workname;
    }

    public String getBirthdata() {
        return birthdata;
    }

    public void setBirthdata(String birthdata) {
        this.birthdata = birthdata;
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
                ", uid='" + uid + '\'' +
                ", address='" + address + '\'' +
                ", introduce='" + introduce + '\'' +
                ", workname='" + workname + '\'' +
                ", birthdata='" + birthdata + '\'' +
                '}';
    }
}
