package com.didu.web;

import com.didu.Utils.CreateUid;
import com.didu.Utils.Page;
import com.didu.domain.*;
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
    //动态路径
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
        Userpicture up = new Userpicture();
        Redpicture rp = new Redpicture();
        User u = new User();
        List<Login> logins = userService.queryLogin(login);
        if (logins.size()>0){
            Login login1 = logins.get(0);
            if (login.getPhone().equals(login1.getPhone())&&login.getPassword().equals(login1.getPassword())){
                ru.setUid(login1.getUid());
                u.setUid(login1.getUid());
                List<User> users = userService.queryUser(u);
                if (users.size()>0){
                    //审核通过
                    if ("2".equals(users.get(0).getStatus())) {
                        up.setUid(login1.getUid());
                        up.setStatus("1");
                        List<Userpicture> ups = userService.queryUserpic(up);
                        users.get(0).setUserpicture(ups.get(0));
                        map.put("success", true);
                        map.put("user", users.get(0));
                        return map;
                    //审核中，待审核
                    }else if("1".equals(users.get(0).getStatus())){
                        map.put("success",false);
                        map.put("user",2);
                        return map;
                    //审核未通过
                    }else{
                        map.put("success",false);
                        map.put("user",3);
                        return map;
                    }
                }else {
                    List<Reduser> redusers = userService.queryReduser(ru);
                    //审核通过
                    if ("3".equals(redusers.get(0).getStatus())) {
                        rp.setUid(login1.getUid());
                        rp.setStatus("1");
                        List<Redpicture> redpictures = userService.queryRedpicture(rp);
                        redusers.get(0).setRedpicture(redpictures.get(0));
                        map.put("success", true);
                        map.put("user", redusers.get(0));
                        return map;
                    //待审核
                    }else if ("1".equals(redusers.get(0).getStatus())){
                        map.put("success", false);
                        map.put("user",2);
                        return map;
                    //审核未通过
                    }else {
                        map.put("success", false);
                        map.put("user",3);
                        return map;
                    }
                }
            //密码错误
            }else {
                map.put("success",false);
                map.put("user",0);
                return map;
            }
        //用户不存在
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
        String phone = login.getPhone();
        if (redusers.size()>0){
            return  "0";
        }else {
            boolean b = userService.addLogin(login);
            if (b) {
                Login log = new Login();
                log.setPhone(phone);
                List<Login> logins = userService.queryLogin(login);
                Login login1 = logins.get(0);
                String i = CreateUid.CreateUid(login1.getId());
                String sub = i.substring(0, 6);
                login1.setDaytime(sub);
                login1.setUid(i);
                reduser.setUid(i);
                reduser.setStatus("1");
                System.err.println(reduser);
                userService.addReduser(reduser);
                boolean bbb=userService.updateLoginUid(login1);
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
        String phone = login.getPhone();
        if (bb){
            return "0";
        }else {
            boolean b = userService.addLogin(login);
            if (b) {
                Login log = new Login();
                log.setPhone(phone);
                List<Login> logins = userService.queryLogin(log);
                Login login1 = logins.get(0);
                String i = CreateUid.CreateUid(login1.getId());
                String s = String.valueOf(i);
                String sub = s.substring(0, 6);
                login1.setDaytime(sub);
                login1.setUid(i);
                user.setStatus("1");
                user.setNum(0);
                user.setUid(i);
                userService.addUser(user);
                boolean a = userService.updateLoginUid(login1);
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
    //后台查看用户信息
    @RequestMapping("/bacqueryUsers")
    @ResponseBody
    public List<User> bacqueryUsers(User user,Page page){
        page.setRows(10);
        page.setPage((page.getPage() - 1) * 10);
        List<User> userList = userService.queryUserByother(user,page);
        Userpicture upic = new Userpicture();
        for (User u:userList) {
            upic.setUid(u.getUid());
            upic.setStatus("1");
            List<Userpicture> userpictures = userService.queryUserpic(upic);
            u.setUserpicture(userpictures.get(0));
        }
        return userList;
    }
    //前端查看用户信息
    @RequestMapping("/queryUsers")
    @ResponseBody
    public List<User> queryUsers(User user,Page page){
        page.setRows(10);
        page.setPage((page.getPage() - 1) * 10);
        List<User> userList = userService.queryUserByother(user,page);
        Userpicture upic = new Userpicture();
        for (User u:userList) {
            String[] area = u.getCity().split(" ");
            u.setCity(area[2]);
            String[] year = u.getBirthdata().split("-");
            u.setBirthdata(year[0]);
            upic.setUid(u.getUid());
            upic.setStatus("1");
            List<Userpicture> userpictures = userService.queryUserpic(upic);
            u.setUserpicture(userpictures.get(0));
        }
        return userList;
    }
    //前端查看红娘信息
    @RequestMapping("/queryReduser")
    @ResponseBody
    public List<Reduser> bacqueryReduser(Reduser reduser,Page page){
        page.setRows(10);
        page.setPage((page.getPage() - 1)*10);
        List<Reduser> redusers = userService.queryReduserByother(reduser,page);
        Redpicture rp=new Redpicture();
        for (Reduser ru:redusers){
            String[] area = ru.getCity().split(" ");
            ru.setCity(area[2]);
            String[] year = ru.getBirthdata().split("-");
            ru.setBirthdata(year[0]);
            rp.setUid(ru.getUid());
            rp.setStatus("1");
            Redpicture rpic = userService.queryRedIpic(rp);
            ru.setRedpicture(rpic);
        }
        return redusers;
    }
    //后台查看红娘信息
    @RequestMapping("/bacqueryReduser")
    @ResponseBody
    public List<Reduser> queryReduser(Reduser reduser,Page page){
        page.setRows(10);
        page.setPage((page.getPage() - 1)*10);
        List<Reduser> redusers = userService.queryReduserByother(reduser,page);
        Redpicture rp=new Redpicture();
        for (Reduser ru:redusers){
            rp.setUid(ru.getUid());
            rp.setStatus("1");
            Redpicture rpic = userService.queryRedIpic(rp);
            ru.setRedpicture(rpic);
        }
        return redusers;
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
    @RequestMapping("/updateRedUserpic")
    @ResponseBody
    public boolean updateRedUserpic(Redpicture redpicture,@RequestParam(name = "files", required = false) CommonsMultipartFile[] files,@ModelAttribute(value = "dir") File dir) throws IOException {
        List<Redpicture> redpictureList = userService.queryRedpicture(redpicture);
        if (redpictureList.size()>0) {
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
                        String url = redpictureList.get(0).getUrl();
                        String[] splits = url.split("\\/");
                        String realurl = dir + "/"+splits[1];
                        File file = new File(realurl);
                        file.delete();
                        redpicture.setUrl("user/"+file1.getName());
                        boolean b = userService.updateRedpicture(redpicture);
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
        User u = new User();
        u.setUid(userpicture.getUid());
        List<User> users = userService.queryUser(u);
        int i = users.get(0).getNum();
        u.setNum(i+1);
        boolean b = userService.updateUser(u);
        userpicture.setStatus(" ");
        return userService.queryUserpic(userpicture);
    }
    //查看红娘图片
    @RequestMapping("/queryRedpic")
    @ResponseBody
    public List<Redpicture> queryUserpic(Redpicture redpicture){
        return userService.queryRedpicture(redpicture);
    }

    //查看单个用户的所有图片
    @RequestMapping("/queryUserAllPic")
    @ResponseBody
    public List<Userpicture> queryUserAllPic(String uid){
        return userService.queryUserAllPic(uid);
    }
    //查看用户信息
    @RequestMapping("queryOneUserByUid")
    @ResponseBody
    public User queryOneUser(String uid){
        User u = new User();
        Userpicture up = new Userpicture();
        u.setUid(uid);
        List<User> users = userService.queryUser(u);
        if (users.size()>0) {
            up.setStatus("1");
            up.setUid(uid);
            List<Userpicture> ups = userService.queryUserpic(up);
            if (ups.size()>0){
                users.get(0).setUserpicture(ups.get(0));
                return users.get(0);
            }else{
                return users.get(0);
            }
        }else {
            return users.get(0);
        }
    }
    //查看红娘信息
    @RequestMapping("queryOneRedUserByUid")
    @ResponseBody
    public Reduser queryOneRedUserByUid(String uid){
        Reduser ru = new Reduser();
        Redpicture rp = new Redpicture();
        ru.setUid(uid);
        List<Reduser> redusers = userService.queryReduser(ru);
        if (redusers.size()>0) {
            rp.setStatus("1");
            rp.setUid(uid);
            List<Redpicture> redpictures = userService.queryRedpicture(rp);
            if (redpictures.size()>0){
                redusers.get(0).setRedpicture(redpictures.get(0));
                return redusers.get(0);
            }else{
                return redusers.get(0);
            }
        }else {
            return redusers.get(0);
        }
    }
}
