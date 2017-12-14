package com.didu.web;

import com.didu.Utils.CreateUid;
import com.didu.domain.*;
import com.didu.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.crypto.hash.Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
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
    //登陆
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
    //注册红娘
    @RequestMapping("/addReduser")
    @ResponseBody
    public String addReduser(Reduser reduser,Login login) throws IOException {
        User user = new User();
        user.setPhone(reduser.getPhone());
        List<User> ll = userService.queryUser(user);
        if (ll.size()>0){
            return "1";
        }
        List<Reduser> redusers = userService.queryReduser(reduser);
        String phone = reduser.getPhone();
        if (redusers.size()>0){
            return  "0";
        }else {
            boolean b = userService.addReduser(reduser);
            if (b) {
                Reduser ru = new Reduser();
                ru.setPhone(phone);
                List<Reduser> redusers1 = userService.queryReduser(ru);
                Reduser reduser1 = redusers1.get(0);
                String i = CreateUid.CreateUid(reduser1.getId());
                String s = String.valueOf(i);
                String sub = s.substring(0, 6);
                login.setDaytime(sub);
                login.setUid(i);
                reduser1.setUid(i);
                userService.addLogin(login);
                boolean bbb=userService.updateReduid(reduser1);
                if (bbb){
                    return i;
                }else{
                    return "0";
                }
            }
            return "0";
        }
    }
    //注册用户
    @RequestMapping("/addUser")
    @ResponseBody
    public String addUser(User user,Login login)throws IOException{
        Reduser reduser = new Reduser();
        reduser.setPhone(user.getPhone());
        List<Reduser> ll = userService.queryReduser(reduser);
        if (ll.size()>0){
            return "1";
        }
        boolean bb = userService.queryUserByphone(user);
        String phone = user.getPhone();
        if (bb){
            return "0";
        }else {
            user.setStatus("1");
            boolean b = userService.addUser(user);
            if (b) {
                User ru=new User();
                ru.setPhone(phone);
                List<User> redusers1 = userService.queryUser(ru);
                User reduser1 = redusers1.get(0);
                String i = CreateUid.CreateUid(reduser1.getId());
                String s = String.valueOf(i);
                String sub = s.substring(0, 6);
                login.setDaytime(sub);
                login.setUid(i);
                reduser1.setUid(i);
                userService.addLogin(login);
                boolean a = userService.updateUseruid(reduser1);
                if (a){
                    return i;
                }else{
                    return "0";
                }
            }
            return "0";
        }
    }
    //用户上传图片
    @RequestMapping("/addUserpic")
    @ResponseBody
    public boolean addUserpic(Userpicture userpicture,@RequestParam(name = "files", required = false) CommonsMultipartFile[] files,@ModelAttribute(value = "dir") File dir) throws IOException {
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
                    userpicture.setUrl("user/"+file1.getName());
                    return userService.addUserpicture(userpicture);
                }
            }
        }
        return false;
    }
    //查看用户信息
    @RequestMapping("/queryUser")
    @ResponseBody
    public List<User> queryUser(User user){
       return userService.queryUser(user);
    }
    //查看红娘信息
    @RequestMapping("/queryReduser")
    @ResponseBody
    public List<Reduser> queryReduser(Reduser reduser){
        return userService.queryReduser(reduser);
    }
    //修改用户
    @RequestMapping("/updateUser")
    @ResponseBody
    public boolean updateUser(User user){
        return userService.updateUser(user);
    }
    //修改红娘信息
    @RequestMapping("/updateReduser")
    @ResponseBody
    public boolean updateReduser(Reduser reduser){
        return userService.updateReduser(reduser);
    }
    //修改红娘图片
   /* @RequestMapping("/updateRedUserpic")
    @ResponseBody
    public boolean updateRedUserpic(Reduser reduser,@RequestParam(name = "files", required = false) CommonsMultipartFile[] files,@ModelAttribute(value = "dir") File dir) throws IOException {
        List<Reduser> redusers = userService.queryReduser(reduser);
        if (redusers.size()>0) {
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
                        String url = redusers.get(0).getUrl();
                        String[] splits = url.split("\\/");
                        String realurl = dir + "/"+splits[1];
                        File file = new File(realurl);
                        file.delete();
                        reduser.setUrl("user/"+file1.getName());
                        boolean b = userService.updateReduser(reduser);
                        if (b){
                            return true;
                        }else{
                            return false;
                        }
                    }
                }
            }
            return false;
        }else {
            return false;
        }
    }*/
    //修改用户图片
    @RequestMapping("/updateUserpic")
    @ResponseBody
    public boolean updaetUserpic(Userpicture userpicture,@RequestParam(name = "files", required = false) CommonsMultipartFile[] files,@ModelAttribute(value = "dir") File dir) throws IOException {
        List<Userpicture> userpictures = userService.queryUserpicture(userpicture);
        if (userpictures.size()>0) {
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
                        String url = userpictures.get(0).getUrl();
                        String[] splits = url.split("\\/");
                        String realurl = dir + "/"+splits[1];
                        File file = new File(realurl);
                        file.delete();
                        userpicture.setUrl("user/"+file1.getName());
                        boolean b = userService.updateUserpicture(userpicture);
                        if (b){
                            return true;
                        }else{
                            return false;
                        }
                    }
                }
            }
            return false;
        }else {
            return false;
        }
    }
    //上传红娘头像
    @RequestMapping("/upRedTou")
    @ResponseBody
    public boolean upRedTou(Redpicture redpicture,@RequestParam(name = "files", required = false) CommonsMultipartFile[] files,@ModelAttribute(value = "dir") File dir) throws IOException {
        boolean b = false;
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
                    redpicture.setUrl("user/"+file1.getName());
                    redpicture.setStatus("1");
                    b = userService.upRedpicture(redpicture);
                }
            }
        }
        if (b){
            return true;
        }else{
            return false;
        }
    }
    //上传红娘的营业执照图片
    @RequestMapping("/upRedPic")
    @ResponseBody
    public boolean upRedPic(Redpicture redpicture,@RequestParam(name = "files", required = false) CommonsMultipartFile[] files,@ModelAttribute(value = "dir") File dir) throws IOException {
        boolean b = false;
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
                    redpicture.setUrl("user/"+file1.getName());
                    b = userService.upRedpicture(redpicture);
                    }
                }
            }
        if (b){
            return true;
        }else{
            return false;
        }
    }
    //修改用户头像
    @RequestMapping("/updateTou")
    @ResponseBody
    public boolean updateTou(Userpicture userpicture){
        userpicture.setStatus("");
        boolean a = userService.updateUserpictureByuid(userpicture);
        if (a){
            userpicture.setStatus("1");
            return userService.updateUserpicture(userpicture);
        }else {
            return false;
        }
    }
    //查看用户图片
    @RequestMapping("/queryUserpic")
    @ResponseBody
    public List<Userpicture> queryUserpic(Userpicture userpicture){
        return userService.queryUserpic(userpicture);
    }
    //查看红娘图片
    @RequestMapping("/queryRedpic")
    @ResponseBody
    public List<Redpicture> queryUserpic(Redpicture redpicture){
        return userService.queryRedpicture(redpicture);
    }
    @RequestMapping("/queryUserByother")
    @ResponseBody
    public HashMap<String,Object> queryUserByother(User user){
        HashMap<String,Object> map=new HashMap<>();
        List<User> users = userService.queryUserByother(user);
        Collections.shuffle(users);
        return map;
    }
}
