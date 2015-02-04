/**
 * FileName      : $Id: OttOrderSummary.java $
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * OttOrderSummary.
 *
 * @author dev
 *
 */
@Entity
@Table(name = "OTT_ORDER_SUMMARY_VW")
public class OttOrderSummary implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ORDER_ID")
    private Integer orderId;

    @Column(name = "OTT_CUSTOMER_ID")
    private Integer customerId;

    @Column(name = "ORDER_TYPE")
    private String orderType;

    @Column(name = "SYSTEM_NAME")
    private String systemName;

    @Column(name = "PARENT_ORDER_TYPE ")
    private String parentOrderType;

    @Column(name = "PARENT_ORDER_NUMBER")
    private String parentOrderNumber;

    @Column(name = "PARENT_ORDER_SYSTEM")
    private String parentOrderSystem;

    @Column(name = "CARRIAGE_TYPE")
    private String carriageType;

    @Column(name = "POST_CODE")
    private String postCode;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "ORDER_STATUS")
    private String orderStatus;

    @Column(name = " ORDER_CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderCreatedDate;

    @Column(name = "ORDER_COMPLETION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderCompletionDateTime;

    @Column(name = "ORDER_MODIFIED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderModifiedDate;

    @Column(name = "ORDER_CUSTOMER_MODIFIED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderCustomerModifiedDate;

    @Column(name = "ORDER_FORECAST_DATE")
    private String orderForeCastDateTime;

    @Column(name = "RECORD_TYPE")
    private String recordType;



    /*
     * @Column(name = "ORDER_MODIFIED_DATE_TOCHAR") private String
     * orderModifiedDateToChar;
     */

    /**
     * @return the orderId
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * @param orderId
     *            the orderId to set
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * @return the orderType
     */
    public String getOrderType() {
        return orderType;
    }

    /**
     * @param orderType
     *            the orderType to set
     */
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    /**
     * @return the systemName
     */
    public String getSystemName() {
        return systemName;
    }

    /**
     * @param systemName
     *            the systemName to set
     */
    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    /**
     * @return the parentOrderType
     */
    public String getParentOrderType() {
        return parentOrderType;
    }

    /**
     * @param parentOrderType
     *            the parentOrderType to set
     */
    public void setParentOrderType(String parentOrderType) {
        this.parentOrderType = parentOrderType;
    }

    /**
     * @return the parentOrderSystem
     */
    public String getParentOrderSystem() {
        return parentOrderSystem;
    }

    /**
     * @param parentOrderSystem
     *            the parentOrderSystem to set
     */
    public void setParentOrderSystem(String parentOrderSystem) {
        this.parentOrderSystem = parentOrderSystem;
    }

    /**
     * @return the carriageType
     */
    public String getCarriageType() {
        return carriageType;
    }

    /**
     * @param carriageType
     *            the carriageType to set
     */
    public void setCarriageType(String carriageType) {
        this.carriageType = carriageType;
    }

    /**
     * @return the postCode
     */
    public String getPostCode() {
        return postCode;
    }

    /**
     * @param postCode
     *            the postCode to set
     */
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     *            the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the orderStatus
     */
    public String getOrderStatus() {
        return orderStatus;
    }

    /**
     * @param orderStatus
     *            the orderStatus to set
     */
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderForeCastDateTime() {
        return orderForeCastDateTime;
    }

    public void setOrderForeCastDateTime(String orderForeCastDateTime) {
        this.orderForeCastDateTime = orderForeCastDateTime;
    }

    /**
     * @return the recordType
     */
    public String getRecordType() {
        return recordType;
    }

    /**
     * @param recordType
     *            the recordType to set
     */
    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public Date getOrderModifiedDate() {
        return orderModifiedDate;
    }

    public void setOrderModifiedDate(Date orderModifiedDate) {
        this.orderModifiedDate = orderModifiedDate;
    }

    public Date getOrderCustomerModifiedDate() {
        return orderCustomerModifiedDate;
    }

    public void setOrderCustomerModifiedDate(Date orderCustomerModifiedDate) {
        this.orderCustomerModifiedDate = orderCustomerModifiedDate;
    }

    /**
     * @return the customerId
     */
    public Integer getCustomerId() {
        return customerId;
    }

    /**
     * @return the parentOrderNumber
     */
    public String getParentOrderNumber() {
        return parentOrderNumber;
    }

    /**
     * @param parentOrderNumber
     *            the parentOrderNumber to set
     */
    public void setParentOrderNumber(String parentOrderNumber) {
        this.parentOrderNumber = parentOrderNumber;
    }

    /**
     * @return the orderCreatedDate
     */
    public Date getOrderCreatedDate() {
        return orderCreatedDate;
    }

    /**
     * @param orderCreatedDate
     *            the orderCreatedDate to set
     */
    public void setOrderCreatedDate(Date orderCreatedDate) {
        this.orderCreatedDate = orderCreatedDate;
    }

    /**
     * @return the orderCompletionDateTime
     */
    public Date getOrderCompletionDateTime() {
        return orderCompletionDateTime;
    }

    /**
     * @param orderCompletionDateTime
     *            the orderCompletionDateTime to set
     */
    public void setOrderCompletionDateTime(Date orderCompletionDateTime) {
        this.orderCompletionDateTime = orderCompletionDateTime;
    }

    /**
     * @param customerId
     *            the customerId to set
     */
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

}
