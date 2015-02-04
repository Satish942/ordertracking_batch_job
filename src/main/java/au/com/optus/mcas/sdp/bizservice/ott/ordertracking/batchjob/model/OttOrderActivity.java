/**
 * FileName      : $Id: OttOrderActivity.java $
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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * OttOrderActivity.
 * @author dev
 *
 */
@Entity
@Table(name = "OTT_ORDER_ACTIVITY_VW")
public class OttOrderActivity implements Serializable {


    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private OttOrderActivityPK id;

    @Column(name = "ORDER_ID")
    private Integer orderId;


    @Column(name = "TASKNAME")
    private String taskName;


    @Column(name = "TRANSITION_TASK_STATUS")
    private String transitionTaskStatus;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TRANSITION_TASK_MODIFIED_TIME")
    private Date transitionTaskModifiedTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FORECAST_DATETIME")
    private Date forecastDateTime;

    @Column(name = "RECORD_TYPE")
    private String recordType;


    /**
     * @return the id
     */

    public OttOrderActivityPK getId() {
        return id;
    }


    /**
     * @param id the id to set
     */
    public void setId(OttOrderActivityPK id) {
        this.id = id;
    }


    public Integer getOrderId() {
        return orderId;
    }


    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }


    public String getTaskName() {
        return taskName;
    }


    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }


    public String getTransitionTaskStatus() {
        return transitionTaskStatus;
    }


    public void setTransitionTaskStatus(String transitionTaskStatus) {
        this.transitionTaskStatus = transitionTaskStatus;
    }


    public Date getTransitionTaskModifiedTime() {
        return transitionTaskModifiedTime;
    }


    public void setTransitionTaskModifiedTime(Date transitionTaskModifiedTime) {
        this.transitionTaskModifiedTime = transitionTaskModifiedTime;
    }


    public Date getForecastDateTime() {
        return forecastDateTime;
    }


    public void setForecastDateTime(Date forecastDateTime) {
        this.forecastDateTime = forecastDateTime;
    }


    public String getRecordType() {
        return recordType;
    }


    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }



    /*public String getTransTaskModTimeStr() {
        return transTaskModTimeStr;
    }


    public void setTransTaskModTimeStr(String transTaskModTimeStr) {
        this.transTaskModTimeStr = transTaskModTimeStr;
    }*/
}

