package com.didu.dao;

import com.didu.domain.Login;
import com.didu.domain.Reduser;
import com.didu.sql.UserSql;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;

/**
 * Created by Administrator on 2017/12/9.
 */
public interface UserDao {
    @Insert("insert into login(phone,password,referrer,uid,daytime) values(#{phone},#{password},#{referrer},#{uid},#{daytime})")
    int addLogin(Login login);
    @Insert("insert into reduser(redname,phone,identity,city,wechat,remark,status) values(#{redname},#{phone},#{identity},#{city},#{wechat},#{remark},#{status})")
    int addReduser(Reduser reduser);
    @SelectProvider(type = UserSql.class,method = "queryReduser")
    List<Reduser> queryReduser(Reduser reduser);
    @UpdateProvider(type = UserSql.class,method = "updateReduser")
    int updateReduser(Reduser reduser);
    @Update("update reduser set uid=#{uid} where phone=#{phone}")
    int updateReduid(Reduser reduser);
}
