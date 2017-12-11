package com.didu.serviceImpl;

import com.didu.dao.UserDao;
import com.didu.domain.Login;
import com.didu.domain.Reduser;
import com.didu.domain.User;
import com.didu.domain.Userpicture;
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
    public boolean addReduser(Reduser reduser) {
        return userDao.addReduser(reduser)>0?true:false;
    }

    @Override
    public List<Reduser> queryReduser(Reduser reduser) {
        return userDao.queryReduser(reduser);
    }

    @Override
    public boolean updateReduid(Reduser reduser) {
        return userDao.updateReduid(reduser) >0?true:false;
    }

    @Override
    public boolean updateReduser(Reduser reduser) {
        return userDao.updateReduser(reduser) >0?true:false;
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
    public boolean updateUseruid(User user) {
        return userDao.updateUserid(user)>0?true:false;
    }

    @Override
    public boolean addUserpicture(Userpicture userpicture) {
        return userDao.addUserpicture(userpicture)>0?true:false;
    }
}
