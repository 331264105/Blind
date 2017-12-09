package com.didu.service;

import com.didu.domain.Login;
import com.didu.domain.Reduser;

import java.util.List;

/**
 * Created by Administrator on 2017/12/9.
 */
public interface UserService {
    boolean addLogin(Login login);
    boolean addReduser(Reduser reduser);
    List<Reduser> queryReduser(Reduser reduser);
    boolean updateReduid(Reduser reduser);
    boolean updateReduser(Reduser reduser);
}
