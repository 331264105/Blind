package com.didu.web;

import com.didu.Utils.CreateUid;
import com.didu.domain.Login;
import com.didu.domain.Reduser;
import com.didu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2017/12/9.
 */
@Controller
public class UserControl {
    @Autowired
    private UserService userService;
    //动态路径
    @RequestMapping("{jn}")
    public String dt(@PathVariable String jn,HttpServletRequest request){
        System.out.print(jn);
        return jn;
    }
  /*  @RequestMapping("/addUser")
    @ResponseBody
    public boolean addUser(User user){
        return false;
    }*/
    @RequestMapping("/addReduser")
    @ResponseBody
    public boolean addUser(Reduser reduser,Login login){
        List<Reduser> redusers = userService.queryReduser(reduser);
        String phone = reduser.getPhone();
        if (redusers.size()>0){
            return false;
        }else {
            boolean b = userService.addReduser(reduser);
            if (b) {
                Reduser ru = new Reduser();
                ru.setPhone(phone);
                List<Reduser> redusers1 = userService.queryReduser(ru);
                Reduser reduser1 = redusers1.get(0);
                int i = CreateUid.CreateUid(reduser1.getId());
                String s = String.valueOf(i);
                String sub = s.substring(0, 5);
                int i1 = Integer.parseInt(sub);
                login.setDaytime(i1);
                login.setUid(i);
                reduser1.setUid(i);
                userService.addLogin(login);
                return userService.updateReduid(reduser1);
            }
            return false;
        }
    }
}
