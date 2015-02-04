/**
 * FileName      : $Id: OrderTrackingFacade.java $
 *
 * Copyright Notice: ©2004 Singapore Telecom Pte Ltd -- Confidential and Proprietary
 *
 * All rights reserved.
 * This software is the confidential and proprietary information of SingTel Pte Ltd
 * ("Confidential Information"). You shall not disclose such Confidential Information
 * and shall use it only in accordance with the terms of the license agreement you
 * entered into with SingTel.
 */
package au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.facade;



import au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.exception.OrderTrackingException;

/**
 *
 * @author dev
 *
 */
public interface OrderTrackingFacade {

    /** Spring bean id .*/
    String BEAN_ID = "au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.facade.orderTrackingFacade";

    /**
     * Deletes all pending customers.
     *
     * @throws OrderTrackingException
     *             it throws OrderTrackingException
     */
    void publishOrderTracking() throws OrderTrackingException;

}
