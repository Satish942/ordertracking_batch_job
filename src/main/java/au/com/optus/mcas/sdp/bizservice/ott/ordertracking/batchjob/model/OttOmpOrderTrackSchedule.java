/**
 * FileName      : $Id: OttOmpOrderTrackSchedule.java $
 *
 * Copyright Notice: ©2004 Singapore Telecom Pte Ltd -- Confidential and Proprietary
 *
 * All rights reserved.
 * This software is the confidential and proprietary information of SingTel Pte Ltd
 * ("Confidential Information"). You shall not disclose such Confidential Information
 * and shall use it only in accordance with the terms of the license agreement you
 * entered into with SingTel.
 */
package au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.model;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the OTT_OMP_ORDER_TRACK_SCHEDULE database table.
 *
 */
@Entity
@Table(name = "OTT_OMP_ORDER_TRACK_SCHEDULE")
public class OttOmpOrderTrackSchedule implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "BATCH_NAME")
    private String batchName;


    @Column(name = "END_TIME")
    private Date endTime;


    @Column(name = "LAST_SUCCESS_RUN")
    private Date lastSuccessRun;


    @Column(name = "START_TIME")
    private Date startTime;

    @Column(name = "STATUS")
    private String status;

    /**
     * Defalut constructor.
     */
    public OttOmpOrderTrackSchedule() {
    }

    public String getBatchName() {
        return this.batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }


    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    /**
     * @return the endTime
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * @return the lastSuccessRun
     */
    public Date getLastSuccessRun() {
        return lastSuccessRun;
    }

    /**
     * @param lastSuccessRun the lastSuccessRun to set
     */
    public void setLastSuccessRun(Date lastSuccessRun) {
        this.lastSuccessRun = lastSuccessRun;
    }

    /**
     * @return the startTime
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(java.sql.Timestamp endTime) {
        this.endTime = endTime;
    }


}
