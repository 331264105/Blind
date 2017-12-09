package com.didu.domain;

/**
 * Created by Administrator on 2017/12/9.
 */
public class Login {
    private int id;
    private String phone;
    private String passwork;
    private int referrer;
    private int uid;
    private int daytime;

    public Login(int id, String phone, String passwork, int referrer, int uid, int daytime) {
        this.id = id;
        this.phone = phone;
        this.passwork = passwork;
        this.referrer = referrer;
        this.uid = uid;
        this.daytime = daytime;
    }

    public Login(String phone, String passwork, int referrer, int uid, int daytime) {
        this.phone = phone;
        this.passwork = passwork;
        this.referrer = referrer;
        this.uid = uid;
        this.daytime = daytime;
    }

    public Login() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPasswork() {
        return passwork;
    }

    public void setPasswork(String passwork) {
        this.passwork = passwork;
    }

    public int getReferrer() {
        return referrer;
    }

    public void setReferrer(int referrer) {
        this.referrer = referrer;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getDaytime() {
        return daytime;
    }

    public void setDaytime(int daytime) {
        this.daytime = daytime;
    }

    @Override
    public String toString() {
        return "Login{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", passwork='" + passwork + '\'' +
                ", referrer=" + referrer +
                ", uid=" + uid +
                ", daytime=" + daytime +
                '}';
    }
}
