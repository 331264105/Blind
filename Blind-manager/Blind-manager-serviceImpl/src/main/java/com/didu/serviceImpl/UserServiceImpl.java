package com.didu.serviceImpl;

import com.didu.dao.UserDao;
import com.didu.domain.Login;
import com.didu.domain.Reduser;
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
}
