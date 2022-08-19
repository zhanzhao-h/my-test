package com.test.demo.controller;

import com.test.demo.model.ResponseContainer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/api/v1/demo/test")
@RestController
public class TestController {
    /**
     * 测试 / 探针接口
     * @author zhan.zhao
     */
    @GetMapping("/keepalive")
    public ResponseContainer<Long> keepalive() {


        return ResponseContainer.success();
    }
}
