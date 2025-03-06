package org.xiaomi;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.xiaomi.entity.KimiMessage;
import org.xiaomi.entity.Response;
import org.xiaomi.service.KimiMessageService;
import org.xiaomi.service.KimiService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kimi")
public class KimiController {

    private final KimiService kimiService;
    @Autowired
    private KimiMessageService kimiMessageService;

    public KimiController(KimiService kimiService) {
        this.kimiService = kimiService;
    }

    @PostMapping("/chat")
    public JSONObject chat(@RequestBody Map<String, String> request) {
        String userMessage = request.get("message"); // 确保从 JSON 体里获取 message
        String message;
        if (userMessage == null || userMessage.isEmpty()) {
            return Response.getResult(0,"提问不能为空");
//            throw new IllegalArgumentException("message 不能为空！");
        }
        KimiMessage kimiMessage;
        List<KimiMessage> kimiMessageList=kimiMessageService.getByQuestion(userMessage);
        String response;
        if(kimiMessageList.isEmpty()){
            // 创建一个表示当前时间的 Date 对象
            // 创建一个 SimpleDateFormat 对象，指定日期格式
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            // 获取当前本地时间
            Date currentDate = new Date();
            // 将日期对象格式化为指定格式的字符串
            String formattedDate = sdf.format(currentDate);

            response = kimiService.chatWithKimi(userMessage);
            kimiMessage=new KimiMessage();
            kimiMessage.setAnswer(response);
            kimiMessage.setQuestion(userMessage);
            kimiMessage.setTimestamp(formattedDate);
            kimiMessageService.insertMessage(kimiMessage);
            message="新建的答案";
        }else{
            kimiMessage=kimiMessageList.get(0);
            response=kimiMessage.getAnswer();
            message="数据库中的答案";
        }

        JSONObject object=new JSONObject();
        object.put("response", response);
        return Response.getResult(0,message, object);
    }

    @PostMapping("/poll")
    public JSONObject poll(@RequestBody Map<String, String> request){
//        sessionId: sessionId.value,
        String sessionId=request.get("sessionId");
        String userName=request.get("userName");
        String startTime=request.get("startTime"),endTime=request.get("endTime");
        List<KimiMessage> kimiMessageList;
        if(userName.isEmpty()){
            return Response.getResult(1,"请输入用户名.");
        }else if(startTime.isEmpty()){
            return Response.getResult(1,"请输入起始时间.");
        }else if(endTime.isEmpty()){
            return Response.getResult(1,"请输入结束时间.");
        }else{

        }
        kimiMessageList=kimiMessageService.getByTime(startTime,endTime);
        JSONObject object=new JSONObject();
        object.put("list",kimiMessageList);
        return Response.getResult(0,"",object);
    }
}
