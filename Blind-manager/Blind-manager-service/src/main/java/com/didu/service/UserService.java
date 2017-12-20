package com.didu.service;

import com.didu.Utils.Page;
import com.didu.domain.*;

import java.util.List;

/**
 * Created by Administrator on 2017/12/9.
 */
public interface UserService {
    boolean addLogin(Login login);
    List<Login> queryLogin(Login login);
    boolean updateLoginUid(Login login);

    boolean addReduser(Reduser reduser);
    List<Reduser> queryReduser(Reduser reduser);
    boolean updateReduser(Reduser reduser);
    boolean updateRedpicture(Redpicture redpicture);
    List<Redpicture> queryRedpicture(Redpicture redpicture);
    List<Reduser> queryReduserByother(Reduser reduser,Page page);
    Redpicture queryRedIpic(Redpicture redpicture);
    //通过id查看
    List<Userpicture> queryUserpicture(Userpicture userpicture);
    //我的查看所有图片
    List<Userpicture> queryUserAllPic(String uid);
    //通过uid和status查看
    List<Userpicture> queryUserpic(Userpicture userpicture);
    boolean updateUserpicture(Userpicture userpicture);

    boolean upRedpicture(Redpicture redpicture);

    List<User> queryUser(User user);
    boolean queryUserByphone(User user);
    boolean addUser(User user);
    boolean updateUser(User user);
    boolean addUserpicture(Userpicture userpicture);
    boolean updateUserpictureByuid(Userpicture userpicture);
    List<User> queryUserByother(User user,Page page);

}
