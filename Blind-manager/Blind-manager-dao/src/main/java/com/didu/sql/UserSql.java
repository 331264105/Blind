package com.didu.sql;

import com.didu.Utils.Page;
import com.didu.domain.Login;
import com.didu.domain.Reduser;
import com.didu.domain.User;
import com.didu.domain.Userpicture;
import org.apache.ibatis.jdbc.SQL;

/**
 * Created by Administrator on 2017/12/9.
 */
public class UserSql {
    public String queryReduser(Reduser reduser) {
        {
            String sql = new SQL() {
                {
                    SELECT("*");
                    FROM("reduser");
                    if (reduser.getIdentity() != null && reduser.getIdentity().length() > 0) {
                        WHERE("identity=#{identity}");
                    }
                    if (reduser.getPhone() != null && reduser.getPhone().length() > 0) {
                        WHERE("phone=#{phone}");
                    }
                    if (reduser.getUid()!=null&&reduser.getUid().length() > 0) {
                        WHERE("uid=#{uid}");
                    }
                    ORDER_BY("id desc");
                }
            }.toString();
            return sql;
        }
    }
    public String queryReduserByother(Reduser reduser,Page page) {
        {
            String sql = new SQL() {
                {
                    SELECT("*");
                    FROM("reduser");
                    if (reduser.getIdentity() != null && reduser.getIdentity().length() > 0) {
                        WHERE("identity=#{arg0.identity}");
                    }
                    if (reduser.getPhone() != null && reduser.getPhone().length() > 0) {
                        WHERE("phone=#{arg0.phone}");
                    }
                    if (reduser.getUid()!=null&&reduser.getUid().length() > 0) {
                        WHERE("uid=#{arg0.uid}");
                    }
                    if (reduser.getAddress()!=null&&reduser.getAddress().length() > 0) {
                        WHERE("address=#{arg0.address}");
                    }
                    ORDER_BY("id desc limit #{arg1.page},#{arg1.rows}");
                }
            }.toString();
            return sql;
        }
    }
    public String queryLogin(Login login) {
        {
            String sql = new SQL() {
                {
                    SELECT("*");
                    FROM("login");
                    if (login.getUid()!=null&&login.getUid().length() > 0) {
                        WHERE("uid=#{uid}");
                    }
                    if (login.getPhone()!=null&&login.getPhone().length()>0) {
                        WHERE("phone=#{phone}");
                    }
                    ORDER_BY("id desc");
                }
            }.toString();
            return sql;
        }
    }
    public String updateReduser(Reduser reduser){
        {
            String sql = new SQL(){
                {
                    UPDATE("reduser");
                    if (reduser.getCity()!=null&&reduser.getCity().length()>0){
                        SET("city=#{city}");
                    }
                    if (reduser.getPhone()!=null&&reduser.getPhone().length()>0){
                        SET("phone=#{phone}");
                    }
                    if (reduser.getUsername()!=null&&reduser.getUsername().length()>0){
                        SET("username=#{username}");
                    }
                    if (reduser.getRemark()!=null&&reduser.getRemark().length()>0){
                        SET("remark=#{remark}");
                    }
                    if (reduser.getWechat()!=null&&reduser.getWechat().length()>0){
                        SET("wechat=#{wechat}");
                    }
                  WHERE("uid=#{uid}");
                }
            }.toString();
            return sql;
        }
    }
    public String updateUserpicture(Userpicture userpicture){
        {
            String sql = new SQL(){
                {
                    UPDATE("userpicture");
                    if (userpicture.getUrl()!=null&&userpicture.getUrl().length()>0){
                        SET("url=#{url}");
                    }
                    if (userpicture.getStatus()!=null&&userpicture.getStatus().length()>0){
                        SET("status=#{status}");
                    }
                    WHERE("id=#{id}");
                }
            }.toString();
            return sql;
        }
    }
    public String queryUser(User user) {
        {
            String sql = new SQL() {
                {
                    SELECT("*");
                    FROM("user");
                    if (user.getUid()!=null&&user.getUid().length() > 0) {
                        WHERE("uid=#{uid}");
                    }
                    if (user.getPhone()!=null&&user.getPhone().length() > 0) {
                        WHERE("phone=#{phone}");
                    }
                    ORDER_BY("num desc");
                }
            }.toString();
            return sql;
        }
    }
    public String queryUserByother(User user,Page page) {
        {
            String sql = new SQL() {
                {
                    SELECT("*");
                    FROM("user");
                    if (user.getSex()!=null&&user.getSex().length() > 0) {
                        WHERE("sex=#{arg0.sex}");
                    }
                    if (user.getStatus()!=null&&user.getStatus().length()>0){
                        WHERE("status=#{arg0.status}");
                    }
                    if (user.getUsername()!=null&&user.getUsername().length()>0){
                        WHERE("username=#{arg0.username}");
                    }
                    if (user.getCity()!=null&&user.getCity().length()>0){
                        WHERE("city like  CONCAT('%', #{arg0.city},'%')");
                    }
                    if (user.getEducation()!=null&&user.getEducation().length()>0){
                        WHERE("education=#{arg0.education}");
                    }
                    if (user.getStature()!=null&&user.getStature().length()>0){
                        WHERE("stature=#{arg0.stature}");
                    }
                    if (user.getUid()!=null&&user.getUid().length() > 0) {
                        WHERE("uid=#{arg0.uid}");
                    }
                    ORDER_BY("num desc limit #{arg1.page},#{arg1.rows}");
                }
            }.toString();
            return sql;
        }
    }
    public String updateUser(User user){
        {
            String sql = new SQL() {
                {
                    UPDATE("user");
                    if (user.getRemark()!=null&&user.getRemark().length()>0){
                        SET("remark=#{remark}");
                    }
                    if (user.getCity()!=null&&user.getCity().length()>0){
                        SET("city=#{city}");
                    }
                    if (user.getStatus()!=null&&user.getStatus().length()>0){
                        SET("status=#{status}");
                    }
                    if (user.getBirthdata()!=null&&user.getBirthdata().length()>0){
                        SET("birthdata=#{birthdata}");
                    }
                    if (user.getEducation()!=null&&user.getEducation().length()>0){
                        SET("education=#{education}");
                    }
                    if (user.getIncome()!=null&&user.getIncome().length()>0){
                        SET("income=#{income}");
                    }
                    if (user.getIntroduce()!=null&&user.getIntroduce().length()>0){
                        SET("introduce=#{introduce}");
                    }
                    if (user.getPhone()!=null&&user.getPhone().length()>0){
                        SET("phone=#{phone}");
                    }
                    if (user.getProfession()!=null&&user.getProfession().length()>0){
                        SET("profession=#{profession}");
                    }
                    if (user.getUsername()!=null&&user.getUsername().length()>0){
                        SET("username=#{username}");
                    }
                    if (user.getWechat()!=null&&user.getWechat().length()>0){
                        SET("wechat=#{wechat}");
                    }
                    if (user.getMarital()!=null&&user.getMarital().length()>0){
                        SET("marital=#{marital}");
                    }
                    WHERE("uid=#{uid}");
                }
            }.toString();
            return sql;
        }
    }
    public String queryUserpic(Userpicture userpicture) {
        {
            String sql = new SQL() {
                {
                    SELECT("*");
                    FROM("userpicture");
                    if (userpicture.getUid()!=null&& userpicture.getUid().length() > 0) {
                        WHERE("uid=#{uid}");
                    }
                    if (userpicture.getStatus()!=null && userpicture.getStatus().length()>0){
                        WHERE("status=#{status}");
                    }
                    ORDER_BY("id desc");
                }
            }.toString();
            return sql;
        }
    }
}
