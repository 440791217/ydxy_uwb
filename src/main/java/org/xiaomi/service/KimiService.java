package org.xiaomi.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.xiaomi.entity.ChatRequest;
import org.xiaomi.entity.ChatResponse;
import org.xiaomi.entity.Message;

import java.util.Arrays;
import java.util.List;

@Service
public class KimiService {

    @Value("${kimi.api-key}")
    private String apiKey;

    @Value("${kimi.url}")
    private String kimiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public String chatWithKimi(String userMessage) {
        // 构造请求的消息格式
        List<Message> messages = Arrays.asList(
                new Message("system", "你是 Kimi，由 Moonshot AI 提供的人工智能助手。"),
                new Message("user", userMessage)
        );

        // 构造请求体
        ChatRequest request = new ChatRequest("moonshot-v1-8k", messages, 0.3);

        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);

        // 发送请求
        HttpEntity<ChatRequest> entity = new HttpEntity<>(request, headers);
        try {
            ResponseEntity<ChatResponse> response = restTemplate.exchange(
                    kimiUrl + "/chat/completions", HttpMethod.POST, entity, ChatResponse.class);

            if (response.getBody() != null && response.getBody().getChoices().size() > 0) {
                return response.getBody().getChoices().get(0).getMessage().getContent();
            } else {
                return "Kimi API 返回了空响应";
            }
        } catch (Exception e) {
            return "请求 Kimi API 失败: " + e.getMessage();
        }
    }
}
