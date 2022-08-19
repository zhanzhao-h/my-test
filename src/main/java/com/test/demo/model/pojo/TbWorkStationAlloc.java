package com.test.demo.model.pojo;

import javax.persistence.*;

@Table(name = "tb_work_station_alloc")
public class TbWorkStationAlloc {
    /**
     * id
     */
    @Id
    private Integer id;

    /**
     * code
     */
    @Column(name = "workstation_code")
    private String workstationCode;

    /**
     * account code
     */
    @Column(name = "account_code")
    private String accountCode;

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
     * 获取code
     *
     * @return workstation_code - code
     */
    public String getWorkstationCode() {
        return workstationCode;
    }

    /**
     * 设置code
     *
     * @param workstationCode code
     */
    public void setWorkstationCode(String workstationCode) {
        this.workstationCode = workstationCode;
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