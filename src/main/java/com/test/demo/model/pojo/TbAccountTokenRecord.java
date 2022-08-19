package com.test.demo.model.pojo;

import javax.persistence.*;

@Table(name = "tb_account_token_record")
public class TbAccountTokenRecord {
    /**
     * id
     */
    @Id
    private Integer id;

    /**
     * account code
     */
    @Column(name = "account_code")
    private String accountCode;

    /**
     * account token
     */
    private String token;

    /**
     * 最后一次访问时间
     */
    @Column(name = "client_time")
    private String clientTime;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Long createTime;

    /**
     * 最后修改时间
     */
    @Column(name = "update_time")
    private Long updateTime;

    /**
     * 获取id
     *
     * @return id - id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取account code
     *
     * @return account_code - account code
     */
    public String getAccountCode() {
        return accountCode;
    }

    /**
     * 设置account code
     *
     * @param accountCode account code
     */
    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    /**
     * 获取account token
     *
     * @return token - account token
     */
    public String getToken() {
        return token;
    }

    /**
     * 设置account token
     *
     * @param token account token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * 获取最后一次访问时间
     *
     * @return client_time - 最后一次访问时间
     */
    public String getClientTime() {
        return clientTime;
    }

    /**
     * 设置最后一次访问时间
     *
     * @param clientTime 最后一次访问时间
     */
    public void setClientTime(String clientTime) {
        this.clientTime = clientTime;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取最后修改时间
     *
     * @return update_time - 最后修改时间
     */
    public Long getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置最后修改时间
     *
     * @param updateTime 最后修改时间
     */
    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }


}