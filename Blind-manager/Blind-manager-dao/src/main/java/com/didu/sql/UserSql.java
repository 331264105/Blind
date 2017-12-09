package com.didu.sql;

import com.didu.domain.Reduser;
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
                    if (reduser.getUid() > 0) {
                        WHERE("uid=#{uid}");
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
                    if (reduser.getIdentity()!=null&&reduser.getIdentity().length()>0){
                        SET("identity=#{identity}");
                    }
                    if (reduser.getCity()!=null&&reduser.getCity().length()>0){
                        SET("city=#{city}");
                    }
                    if (reduser.getPhone()!=null&&reduser.getPhone().length()>0){
                        SET("phone=#{phone}");
                    }
                    if (reduser.getRedname()!=null&&reduser.getRedname().length()>0){
                        SET("redname=#{redname}");
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
}
