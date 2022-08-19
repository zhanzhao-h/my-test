package com.test.demo.controller;

import com.test.demo.common.token.Token;
import com.test.demo.model.dto.AccountWorkStationDTO;
import com.test.demo.model.dto.FreeWorkStationDTO;
import com.test.demo.model.ResponseContainer;
import com.test.demo.service.WorkstationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RequestMapping("/api/v1/workstation")
@RestController
public class WorkstationController {

    private WorkstationService workstationService;

    @Autowired
    public WorkstationController(WorkstationService workstationService) {
        this.workstationService = workstationService;
    }

    /**
     * 查询指定用户的工位
     * @author zhan.zhao
     */
    @GetMapping("/account_code")
    @Token
    public ResponseContainer<AccountWorkStationDTO> getWorkstationForAccountCode(@RequestParam(value = "account_code") String accountCode) {
        AccountWorkStationDTO accountWorkStationDTO = workstationService.getWorkstationForAccountCode(accountCode);
        return ResponseContainer.success(accountWorkStationDTO,"");
    }

    /**
     * 查询空闲工位
     * @author zhan.zhao
     */
    @Token
    @GetMapping("/free")
    public ResponseContainer<FreeWorkStationDTO> getFreeWorkstation() {
        FreeWorkStationDTO freeWorkStationDTO = workstationService.findFreeWorkstation();
        return ResponseContainer.success(freeWorkStationDTO,"");
    }

}
