package com.test.demo.service;

import com.test.demo.model.dto.AccountWorkStationDTO;
import com.test.demo.model.dto.FreeWorkStationDTO;

public interface WorkstationService {

    AccountWorkStationDTO getWorkstationForAccountCode(String accountCode);

    FreeWorkStationDTO findFreeWorkstation();


}
