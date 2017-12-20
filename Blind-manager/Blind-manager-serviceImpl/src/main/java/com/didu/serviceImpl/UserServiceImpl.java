package com.didu.serviceImpl;

import com.didu.Utils.Page;
import com.didu.dao.UserDao;
import com.didu.domain.*;
import com.didu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2017/12/9.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public boolean addLogin(Login login) {
        return userDao.addLogin(login)>0?true:false;
    }

    @Override
    public List<Login> queryLogin(Login login) {
        return userDao.queryLogin(login);
    }

    @Override
    public boolean updateLoginUid(Login login) {
        return userDao.updateLoginUid(login)>0?true:false;
    }

    @Override
    public boolean addReduser(Reduser reduser) {
        return userDao.addReduser(reduser)>0?true:false;
    }

    @Override
    public List<Reduser> queryReduser(Reduser reduser) {
        return userDao.queryReduser(reduser);
    }


    @Override
    public boolean updateReduser(Reduser reduser) {
        return userDao.updateReduser(reduser) >0?true:false;
    }

    @Override
    public boolean updateRedpicture(Redpicture redpicture) {
        return userDao.updateRedpicture(redpicture)>0?true:false;
    }

    @Override
    public List<Redpicture> queryRedpicture(Redpicture redpicture) {
        return userDao.queryRedpicture(redpicture);
    }

    @Override
    public List<Reduser> queryReduserByother(Reduser reduser,Page page) {
        return userDao.queryReduserByother(reduser,page);
    }

    @Override
    public Redpicture queryRedIpic(Redpicture redpicture) {
        return userDao.queryRedIpic(redpicture);
    }

    @Override
    public List<Userpicture> queryUserpicture(Userpicture userpicture) {
        return userDao.queryUserpicture(userpicture);
    }

    @Override
    public List<Userpicture> queryUserAllPic(String uid) {
        return userDao.queryUserAllPic(uid);
    }

    @Override
    public List<Userpicture> queryUserpic(Userpicture userpicture) {
        return userDao.queryUserpic(userpicture);
    }

    @Override
    public boolean updateUserpicture(Userpicture userpicture) {
        return userDao.updateUserpicture(userpicture)>0?true:false;
    }

    @Override
    public boolean upRedpicture(Redpicture redpicture) {
        return userDao.upRedpicture(redpicture)>0?true:false;
    }

    @Override
    public List<User> queryUser(User user) {
        return userDao.queryUser(user);
    }


    @Override
    public boolean queryUserByphone(User user) {
        return userDao.queryUserByphone(user)>0?true:false;
    }

    @Override
    public boolean addUser(User user) {
        return userDao.addUser(user)>0?true:false;
    }


    @Override
    public boolean updateUser(User user) {
        return userDao.updateUser(user)>0?true:false;
    }

    @Override
    public boolean addUserpicture(Userpicture userpicture) {
        return userDao.addUserpicture(userpicture)>0?true:false;
    }

    @Override
    public boolean updateUserpictureByuid(Userpicture userpicture) {
        return userDao.updateUserpictureByuid(userpicture)>0?true:false;
    }

    @Override
    public List<User> queryUserByother(User user,Page page) {
        return userDao.queryUserByother(user,page);
    }
}
