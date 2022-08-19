package com.test.demo.service.impl;

import com.test.demo.dao.mapper.TbWorkStationAllocMapper;
import com.test.demo.dao.mapper.TbWorkstationMapper;
import com.test.demo.model.dto.AccountWorkStationDTO;
import com.test.demo.model.dto.FreeWorkStationDTO;
import com.test.demo.model.pojo.TbWorkStationAlloc;
import com.test.demo.service.EchoService;
import com.test.demo.service.WorkstationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class WorkstationServiceImpl implements WorkstationService {

    private TbWorkStationAllocMapper tbWorkStationAllocMapper;

    private TbWorkstationMapper tbWorkstationMapper;

    @Autowired
    public WorkstationServiceImpl(TbWorkStationAllocMapper tbWorkStationAllocMapper, TbWorkstationMapper tbWorkstationMapper) {
        this.tbWorkStationAllocMapper = tbWorkStationAllocMapper;
        this.tbWorkstationMapper = tbWorkstationMapper;
    }

    /**
     *  查询指定用户的工位
     * @author zhan.zhao
     */
    @Override
    public AccountWorkStationDTO getWorkstationForAccountCode(String accountCode) {
        Example example = new Example(TbWorkStationAlloc.class);
        example.selectProperties("workstationCode").and().andEqualTo("accountCode",accountCode);
        List<TbWorkStationAlloc> tbWorkstationCodeList = tbWorkStationAllocMapper.selectByExample(example);
        List<String> accountWorkStationList = tbWorkstationCodeList.stream().map(TbWorkStationAlloc::getWorkstationCode).collect(Collectors.toList());
        return AccountWorkStationDTO
                .builder()
                .accountWorkStations(accountWorkStationList)
                .accountCode(accountCode)
                .build();
    }

    /**
     *  查询所有空闲空位
     * @author zhan.zhao
     */
    @Override
    public FreeWorkStationDTO findFreeWorkstation(){
        List<String> freeWorkstation = tbWorkstationMapper.findFreeWorkstation();
        return FreeWorkStationDTO
                .builder()
                .freeWorkStations(freeWorkstation)
                .build();
    }

}
