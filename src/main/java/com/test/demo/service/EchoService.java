package com.test.demo.service;

import com.test.demo.model.dto.AccountTokenDTO;

public interface EchoService {

    Integer addAccountTokenRecord(AccountTokenDTO accountTokenDTO);

    AccountTokenDTO getAccountTokenRecordByAccountCode(AccountTokenDTO accountTokenDTO);

}
