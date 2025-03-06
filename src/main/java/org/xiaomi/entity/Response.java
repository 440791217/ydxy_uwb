package org.xiaomi.entity;

import com.alibaba.fastjson.JSONObject;

public class Response {
//    int code;
//    String msg;


    static public JSONObject getResult(int rc,String msg,JSONObject data){
        JSONObject object=new JSONObject();
        object.put("code",200);
        object.put("rc",rc);
        object.put("message",msg);
        object.put("data",data);
        return object;
    }

    static public JSONObject getResult(int rc,String msg){
        return getResult(rc,msg,new JSONObject());
    }
}
