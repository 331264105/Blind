package com.didu.dao;

import com.didu.domain.Login;
import com.didu.domain.Reduser;
import com.didu.domain.User;
import com.didu.domain.Userpicture;
import com.didu.sql.UserSql;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Administrator on 2017/12/9.
 */
public interface UserDao {
    @Insert("insert into login(phone,password,referrer,uid,daytime) values(#{phone},#{password},#{referrer},#{uid},#{daytime})")
    int addLogin(Login login);
    @Insert("insert into reduser(redname,phone,identity,city,wechat,remark,status,url) values(#{redname},#{phone},#{identity},#{city},#{wechat},#{remark},#{status},#{url})")
    int addReduser(Reduser reduser);
    @Insert("insert into user(username,phone,sex,stature,birthdata,education,city,census,income,marital,wechat,introduce,remark,status,uid,profession,smoke,children,drink,race) values(#{username},#{phone},#{sex},#{stature},#{birthdata},#{education},#{city},#{census},#{income},#{marital},#{wechat},#{introduce},#{remark},#{status},#{uid},#{profession},#{smoke},#{children},#{drink},#{race})")
    int addUser(User user);
    @Insert("insert into userpicture(url,uid,status) values(#{url},#{uid},#{status})")
    int addUserpicture(Userpicture userpicture);
    @SelectProvider(type = UserSql.class,method = "queryReduser")
    List<Reduser> queryReduser(Reduser reduser);
    @UpdateProvider(type = UserSql.class,method = "updateReduser")
    int updateReduser(Reduser reduser);
    @Update("update reduser set uid=#{uid} where phone=#{phone}")
    int updateReduid(Reduser reduser);
    @SelectProvider(type = UserSql.class,method = "queryLogin")
    List<Login> queryLogin(Login login);
    @Select("select count(1) from user where phone=#{phone}")
    int queryUserByphone(User user);
    @SelectProvider(type = UserSql.class,method = "queryUser")
    List<User> queryUser(User user);
    @Update("update user set uid=#{uid} where phone=#{phone}")
    int updateUserid(User user);
}
