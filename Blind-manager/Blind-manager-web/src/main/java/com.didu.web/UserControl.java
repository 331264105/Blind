package com.didu.web;

import com.didu.Utils.CreateUid;
import com.didu.domain.Login;
import com.didu.domain.Reduser;
import com.didu.domain.User;
import com.didu.domain.Userpicture;
import com.didu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/12/9.
 */
@Controller
public class UserControl {
    @Autowired
    private UserService userService;
    //TODO[俞海]  动态路径
    @RequestMapping("{jn}")
    public String dt(@PathVariable String jn,HttpServletRequest request){
        System.out.print(jn);
        return jn;
    }
    //图片路径
    @ModelAttribute("dir")
    public File pre(HttpServletRequest request) {
        File dir = new File(new File(request.getServletContext().getRealPath("/")), "user");
        // 验证文件夹是否存在，不存在就新建
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir;
    }
    @RequestMapping("/loginReduser")
    @ResponseBody
    public HashMap<String,Object> queryLogin(Login login){
        HashMap<String,Object> map=new HashMap<>();
        Reduser ru= new Reduser();
        User u = new User();
        List<Login> logins = userService.queryLogin(login);
        if (logins.size()>0){
            Login login1 = logins.get(0);
            if (login.getPhone().equals(login1.getPhone())&&login.getPassword().equals(login1.getPassword())){
                ru.setUid(login1.getUid());
                u.setUid(login1.getUid());
                List<User> users = userService.queryUser(u);
                if (users.size()>0){
                    map.put("success",true);
                    map.put("user",users.get(0));
                }else {
                    List<Reduser> redusers = userService.queryReduser(ru);
                    map.put("success", true);
                    map.put("user", redusers.get(0));
                    return map;
                }
                map.put("success",false);
                map.put("user",1);
                return map;
            }else {
                map.put("success",false);
                map.put("user",0);
                return map;
            }
        }else{
            map.put("success",false);
            map.put("user",1);
            return map;
        }
    }
    @RequestMapping("/addReduser")
    @ResponseBody
    public boolean addReduser(Reduser reduser,Login login,@RequestParam(name = "files", required = false) CommonsMultipartFile[] files,@ModelAttribute(value = "dir") File dir) throws IOException {
        List<Reduser> redusers = userService.queryReduser(reduser);
        String phone = reduser.getPhone();
        if (redusers.size()>0){
            return false;
        }else {
            for (int i = 0; i < files.length; i++) {
                if (!files[i].isEmpty() && files[i].getSize() > 0) {
                    // 获取上传的文件的名称
                    String fileName = files[i].getOriginalFilename();
                    if (fileName.endsWith(".jpg") || fileName.endsWith(".png")) {
                        // 设置文件存储位置--->当前项目的平级目录下
                        // 限制文件名称最长位50，若超出截取后面部分
                        if (fileName.length() > 50) {
                            fileName = fileName.substring(fileName.length() - 51);
                        }
                        File file1 = new File(dir,System.currentTimeMillis() +"_"+ fileName);
                        files[i].transferTo(file1);
                        reduser.setUrl("user/"+file1.getName());
                    }
                }
            }
            System.err.println("<<<-----【"+reduser+"】-----【"+login+"】----->>>");
            boolean b = userService.addReduser(reduser);
            if (b) {
                Reduser ru = new Reduser();
                ru.setPhone(phone);
                List<Reduser> redusers1 = userService.queryReduser(ru);
                Reduser reduser1 = redusers1.get(0);
                int i = CreateUid.CreateUid(reduser1.getId());
                String s = String.valueOf(i);
                String sub = s.substring(0, 6);
                login.setDaytime(sub);
                login.setUid(i);
                reduser1.setUid(i);
                userService.addLogin(login);
                return userService.updateReduid(reduser1);
            }
            return false;
        }
    }
    @RequestMapping("/addUser")
    @ResponseBody
    public boolean addUser(User user,Login login,Userpicture userpicture,@RequestParam(name = "files", required = false) CommonsMultipartFile[] files,@ModelAttribute(value = "dir") File dir)throws IOException{
        System.err.println("<<<-----【"+user+"】-----【"+login+"】----->>>");
        boolean bb = userService.queryUserByphone(user);
        String phone = user.getPhone();
        if (bb){
            return false;
        }else {
            boolean b = userService.addUser(user);
            if (b) {
                User ru=new User();
                ru.setPhone(phone);
                List<User> redusers1 = userService.queryUser(ru);
                User reduser1 = redusers1.get(0);
                int i = CreateUid.CreateUid(reduser1.getId());
                for (int j = 0; j < files.length; j++) {
                    if (!files[j].isEmpty() && files[j].getSize() > 0) {
                        // 获取上传的文件的名称
                        String fileName = files[j].getOriginalFilename();
                        if (fileName.endsWith(".jpg") || fileName.endsWith(".png")) {
                            // 设置文件存储位置--->当前项目的平级目录下
                            // 限制文件名称最长位50，若超出截取后面部分
                            if (fileName.length() > 50) {
                                fileName = fileName.substring(fileName.length() - 51);
                            }
                            File file1 = new File(dir,System.currentTimeMillis() +"_"+ fileName);
                            files[j].transferTo(file1);
                            if (j==0){
                                userpicture.setStatus("1");
                            }
                            userpicture.setUrl("user/"+file1.getName());
                            userpicture.setUid(i);
                            userService.addUserpicture(userpicture);
                        }
                    }
                }
                String s = String.valueOf(i);
                String sub = s.substring(0, 6);
                login.setDaytime(sub);
                login.setUid(i);
                reduser1.setUid(i);
                userService.addLogin(login);
                return userService.updateUseruid(reduser1);
            }
            return false;
        }
    }
}
