package org.xiaomi;


import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.xiaomi.entity.Response;
import org.xiaomi.entity.User;
import org.xiaomi.service.UserService;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/findAll")
    public List<User> findAll() {
        return userService.findAll();
    }

    @RequestMapping("/insertUser")
    public JSONObject insertUser(@RequestBody Map<String, Object> requestBody) {
        String name = Objects.toString(requestBody.get("name"));
        String passwd = Objects.toString(requestBody.get("passwd"));
//        @RequestBody
//        @RequestParam("name") String name, @RequestParam("passwd") String passwd
        int rc=0;
        String msg="";
        if(userService.isNameDuplicate(name)){
            msg="用户名存在或用户名已经被注册！";
            rc=1;
        }else{
            User user = new User();
            user.setName(name);
            user.setPasswd(passwd);
            userService.insertUser(user);
            msg="注册成功！";
        }

        return Response.getResult(rc,msg);
    }
    @PostMapping("/login")
    public JSONObject login(@RequestBody Map<String, Object> requestBody) {
        String name = Objects.toString(requestBody.get("name"));
        String passwd = Objects.toString(requestBody.get("passwd"));
        System.out.println("login"+JSONObject.toJSONString(requestBody));
        System.out.println("name"+name);
        int rc=0;
        String msg="";
        boolean status=userService.validateUser(name, passwd);
        if(status){

        }else{
            rc=1;
            msg="用户或密码错误！";
        }
        JSONObject object=new JSONObject();
        object.put("name",name);
        object.put("passwd",passwd);
        return Response.getResult(rc,msg,object);
    }
}