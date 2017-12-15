package com.didu.dao;

import com.didu.Utils.Page;
import com.didu.domain.*;
import com.didu.sql.UserSql;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by Administrator on 2017/12/9.
 */
public interface UserDao {
    @Insert("insert into login(phone,password,referrer,uid,daytime) values(#{phone},#{password},#{referrer},#{uid},#{daytime})")
    int addLogin(Login login);
    @Insert("insert into reduser(username,phone,identity,city,wechat,remark,status,address,introduce,workname,birthdata) values(#{username},#{phone},#{identity},#{city},#{wechat},#{remark},#{status},#{address},#{introduce},#{workname},#{birthdata})")
    int addReduser(Reduser reduser);
    @Insert("insert into user(username,phone,sex,stature,birthdata,education,city,census,income,marital,wechat,introduce,remark,status,uid,profession,smoke,children,drink,race,num) values(#{username},#{phone},#{sex},#{stature},#{birthdata},#{education},#{city},#{census},#{income},#{marital},#{wechat},#{introduce},#{remark},#{status},#{uid},#{profession},#{smoke},#{children},#{drink},#{race},#{num})")
    int addUser(User user);
    @Insert("insert into userpicture(url,uid,status) values(#{url},#{uid},#{status})")
    int addUserpicture(Userpicture userpicture);
    @SelectProvider(type = UserSql.class,method = "queryReduser")
    List<Reduser> queryReduser(Reduser reduser);
    @SelectProvider(type = UserSql.class,method = "queryReduserByother")
    List<Reduser> queryReduserByother(Reduser reduser,Page page);
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
    @UpdateProvider(type = UserSql.class,method = "updateUser")
    int updateUser(User user);
    @Select("select * from userpicture where id=#{id}")
    List<Userpicture> queryUserpicture(Userpicture userpicture);
    @UpdateProvider(type = UserSql.class,method = "updateUserpicture")
    int updateUserpicture(Userpicture userpicture);
    @Insert("insert into redpicture (url,uid,status) values (#{url},#{uid},#{status})")
    int upRedpicture(Redpicture redpicture);
    @Update("update userpicture set status=#{status} where uid=#{uid}")
    int updateUserpictureByuid(Userpicture userpicture);
    @Select("select * from redpicture where uid=#{uid}")
    List<Redpicture> queryRedpicture(Redpicture redpicture);
    @SelectProvider(type = UserSql.class,method = "queryUserpic")
    List<Userpicture> queryUserpic(Userpicture userpicture);
    @SelectProvider(type = UserSql.class,method = "queryUserByother")
    List<User> queryUserByother(User user,Page page);
    @Update("update redpicture set url=#{url} where id=#{id}")
    int updateRedpicture(Redpicture redpicture);
    @Select("select * from redpicture where uid=#{uid} and status=#{status}")
    Redpicture queryRedIpic(Redpicture redpicture);
}
