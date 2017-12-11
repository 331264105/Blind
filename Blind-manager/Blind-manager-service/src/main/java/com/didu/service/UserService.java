package com.didu.service;

import com.didu.domain.Login;
import com.didu.domain.Reduser;
import com.didu.domain.User;
import com.didu.domain.Userpicture;

import java.util.List;

/**
 * Created by Administrator on 2017/12/9.
 */
public interface UserService {
    boolean addLogin(Login login);
    List<Login> queryLogin(Login login);
    boolean addReduser(Reduser reduser);
    List<Reduser> queryReduser(Reduser reduser);
    boolean updateReduid(Reduser reduser);
    boolean updateReduser(Reduser reduser);

    List<User> queryUser(User user);
    boolean queryUserByphone(User user);
    boolean addUser(User user);
    boolean updateUseruid(User user);
    boolean addUserpicture(Userpicture userpicture);
}
