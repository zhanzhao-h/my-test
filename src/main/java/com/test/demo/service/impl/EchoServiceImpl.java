package com.test.demo.service.impl;

import com.test.demo.dao.mapper.TbAccountTokenRecordMapper;
import com.test.demo.model.dto.AccountTokenDTO;
import com.test.demo.model.pojo.TbAccountTokenRecord;
import com.test.demo.service.EchoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service
@Slf4j
public class EchoServiceImpl implements EchoService {

    private TbAccountTokenRecordMapper tbAccountTokenRecordMapper;

    @Autowired
    public EchoServiceImpl(TbAccountTokenRecordMapper tbAccountTokenRecordMapper) {
        this.tbAccountTokenRecordMapper = tbAccountTokenRecordMapper;
    }

    /**
     *  新增用户token record
     * @author zhan.zhao
     * @param accountTokenDTO
     * @return java.lang.Integer
     */
    @Override
    public Integer addAccountTokenRecord(AccountTokenDTO accountTokenDTO){
        TbAccountTokenRecord tbAccountTokenRecord = accountTokenDTOToPo(accountTokenDTO);
        //查询是否存在，已存在则返回-1标识
        int i = tbAccountTokenRecordMapper.selectCount(tbAccountTokenRecord);
        if (i != 0){
            return -1;
        }
        tbAccountTokenRecord.setCreateTime(System.currentTimeMillis());
        tbAccountTokenRecord.setUpdateTime(System.currentTimeMillis());
        return tbAccountTokenRecordMapper.insert(tbAccountTokenRecord);
    }

    /**
     *  查询用户token record
     * @author zhan.zhao
     */
    @Override
    public AccountTokenDTO getAccountTokenRecordByAccountCode(AccountTokenDTO accountTokenDTO){
        Example example = new Example(TbAccountTokenRecord.class);
        example.selectProperties("id","token","accountCode","clientTime").and().andEqualTo("accountCode",accountTokenDTO.getAccountCode());
        TbAccountTokenRecord tbAccountTokenRecord = tbAccountTokenRecordMapper.selectOneByExample(example);
        //查不到返回
        if (tbAccountTokenRecord == null){
            return null;
        }
        //查到更新clientTime
        updateAccountTokenRecordByAccountCode(tbAccountTokenRecord.getId(),accountTokenDTO.getClientTime());
        accountTokenDTO.setToken(tbAccountTokenRecord.getToken());
        return accountTokenDTO;
    }

    /**
     *  更新用户 client_time
     * @author zhan.zhao
     */
    public void updateAccountTokenRecordByAccountCode(int id,String clientTime){
        TbAccountTokenRecord tbAccountTokenRecord = new TbAccountTokenRecord();
        tbAccountTokenRecord.setId(id);
        tbAccountTokenRecord.setClientTime(clientTime);
        tbAccountTokenRecord.setUpdateTime(System.currentTimeMillis());
        tbAccountTokenRecordMapper.updateByPrimaryKeySelective(tbAccountTokenRecord);
    }

    /**
     *  dto转po
     * @author zhan.zhao
     */
    public TbAccountTokenRecord accountTokenDTOToPo(AccountTokenDTO accountTokenDTO){
        TbAccountTokenRecord tbAccountTokenRecord = new TbAccountTokenRecord();
        tbAccountTokenRecord.setAccountCode(accountTokenDTO.getAccountCode());
        tbAccountTokenRecord.setToken(accountTokenDTO.getToken());
        return tbAccountTokenRecord;
    }

}
