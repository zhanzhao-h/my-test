package com.test.demo.controller;

import com.test.demo.common.token.Token;
import com.test.demo.common.util.ValidateUtil;
import com.test.demo.exception.BusinessErrorEnum;
import com.test.demo.model.dto.AccountTokenDTO;
import com.test.demo.model.ResponseContainer;
import com.test.demo.service.EchoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * 说明：
 *
 * token这里不太没明白题目的意思：
 * 我第一印象以为是要做token验证,但是题目中说明的是token是调用方传进来的并不是由这个服务生成。
 * 我理解的意思是: 该服务储存调用方的token并记录最后近一次访问时间.
 * 以此为基础：若仅做临时记录可以使用redis做缓存存储。如果需要持久存储并做它用，则使用数据库建一个访问表。
 * 此处使用数据库实现存储token.
 *
 */
@Slf4j
@RequestMapping("/api/echo")
@RestController
public class EchoController {

    private EchoService echoService;

    @Autowired
    public EchoController(EchoService echoService){
        this.echoService = echoService;
    }

    /**
     *  储存token
     * @author zhan.zhao
     */
    @PostMapping("/set_token")
    @Token
    public ResponseEntity<ResponseContainer<Void>> storeToken(@RequestBody AccountTokenDTO accountTokenDTO) {
        //验证必须字段值是否为空
        if (!Objects.equals(ValidateUtil.validateParam(accountTokenDTO), "")){
            return ResponseEntity.status(500).body(ResponseContainer.error(BusinessErrorEnum.ILLEGAL_PARAMETER.getErrorCode(),
                    null,BusinessErrorEnum.ILLEGAL_PARAMETER.getMessage()));
        }
        Integer flag = echoService.addAccountTokenRecord(accountTokenDTO);
        //已存在情况返回处理
        if (flag == -1){
            return ResponseEntity.status(400).body(ResponseContainer.error(BusinessErrorEnum.ACCOUNT_EXIST.getErrorCode(),
                    null,BusinessErrorEnum.ACCOUNT_EXIST.getMessage()));
        }
        return ResponseEntity.status(200).body(ResponseContainer.success());
    }

    /**
     *  获取token
     * @author zhan.zhao
     */
    @PostMapping("/get_token")
    @Token
    public ResponseEntity<ResponseContainer<AccountTokenDTO>> getToken(@RequestParam("account_code") String accountCode,@RequestParam("client_time") String clientTime) {
        AccountTokenDTO accountTokenDTO = echoService.getAccountTokenRecordByAccountCode(
                AccountTokenDTO
                        .builder()
                        .accountCode(accountCode)
                        .clientTime(clientTime)
                        .build());
        //查不到情况
        if (accountTokenDTO == null){
            return ResponseEntity.status(404).body(ResponseContainer.error(BusinessErrorEnum.RESOURCE_NOT_FOUND.getErrorCode(),
                    null,BusinessErrorEnum.RESOURCE_NOT_FOUND.getMessage()));
        }
        return ResponseEntity.status(200).body(ResponseContainer.success(accountTokenDTO,null));
    }

}
