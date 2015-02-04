/**
 * FileName      : $Id: OrderTrackingProcessService.java $
 *
 * Copyright Notice: ©2004 Singapore Telecom Pte Ltd -- Confidential and Proprietary
 *
 * All rights reserved.
 * This software is the confidential and proprietary information of SingTel Pte Ltd
 * ("Confidential Information"). You shall not disclose such Confidential Information
 * and shall use it only in accordance with the terms of the license agreement you
 * entered into with SingTel.
 */
package au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.service;

import au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.exception.OrderTrackingException;

/**
 * OrderTrackingProcessService.
 * @author dev
 *
 */
public interface OrderTrackingProcessService {



    /**
     * This method is used to execute and return the common string using the acitivity string
     *  and summary string.
     *  It uses the lastsuccessrun OttOmpOrderTrackSchedule to retrieve the date from
     *  the activity view and summary view.
     * @return String
     * @throws OrderTrackingException it throws OrderTrackingException
     */
    String excuteOrderTrackingBatch() throws OrderTrackingException;


}
