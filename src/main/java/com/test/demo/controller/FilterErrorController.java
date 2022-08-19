package com.test.demo.controller;

import com.test.demo.exception.BusinessErrorEnum;
import com.test.demo.model.ResponseContainer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/api/v1/filter/")
@RestController
public class FilterErrorController {

    /**
     *拦截器 token验证错误统一返回格式处理接口
     * @author zhan.zhao
     */
    @GetMapping("/token_error")
    public ResponseEntity<ResponseContainer<Long>> filterError() {
        return ResponseEntity.status(401).body(ResponseContainer.error(BusinessErrorEnum.TOKEN_INVALID.getErrorCode(),null,BusinessErrorEnum.TOKEN_INVALID.getMessage()));
    }
}
