package com.didu.domain;

/**
 * Created by Administrator on 2017/12/9.
 */
public class Login {
    private int id;
    private String phone;
    private String password;
    private String referrer;
    private String uid;
    private String daytime;

    public Login(int id, String phone, String password, String referrer, String uid, String daytime) {
        this.id = id;
        this.phone = phone;
        this.password = password;
        this.referrer = referrer;
        this.uid = uid;
        this.daytime = daytime;
    }

    public Login(String phone, String password, String referrer, String uid, String daytime) {
        this.phone = phone;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getReferrer() {
        return referrer;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDaytime() {
        return daytime;
    }

    public void setDaytime(String daytime) {
        this.daytime = daytime;
    }

    @Override
    public String toString() {
        return "Login{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", referrer='" + referrer + '\'' +
                ", uid='" + uid + '\'' +
                ", daytime='" + daytime + '\'' +
                '}';
    }
}
