package com.didu.service;

import com.didu.domain.*;

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
    List<Redpicture> queryRedpicture(Redpicture redpicture);
    //通过id查看
    List<Userpicture> queryUserpicture(Userpicture userpicture);
    //通过uid和status查看
    List<Userpicture> queryUserpic(Userpicture userpicture);
    boolean updateUserpicture(Userpicture userpicture);

    boolean upRedpicture(Redpicture redpicture);

    List<User> queryUser(User user);
    boolean queryUserByphone(User user);
    boolean addUser(User user);
    boolean updateUseruid(User user);
    boolean updateUser(User user);
    boolean addUserpicture(Userpicture userpicture);
    boolean updateUserpictureByuid(Userpicture userpicture);
    List<User> queryUserByother(User user);

}
