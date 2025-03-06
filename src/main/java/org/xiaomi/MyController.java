package org.xiaomi;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MyController {

    // 处理POST请求，路径为 /api/create
    @RequestMapping("/greeting")
    public String greeting() {
        return "Hello, World!";
    }

}