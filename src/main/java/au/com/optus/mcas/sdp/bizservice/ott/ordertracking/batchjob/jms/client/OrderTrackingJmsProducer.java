/**
 * FileName      : $Id: OrderTrackingJmsProducer.java $
 *
 * Copyright Notice: ©2004 Singapore Telecom Pte Ltd -- Confidential and Proprietary
 *
 * All rights reserved.
 * This software is the confidential and proprietary information of SingTel Pte Ltd
 * ("Confidential Information"). You shall not disclose such Confidential Information
 * and shall use it only in accordance with the terms of the license agreement you
 * entered into with SingTel.
 */


package au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.jms.client;

import au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.exception.OrderTrackingException;

/**
 *
 * @author dev
 *
 */
public interface OrderTrackingJmsProducer {

    /**
     *  This Method is used to send the PipeLined String in the JMSQueue.
     * @param hash
     *        String
     * @return String
     *
     * @throws OrderTrackingException it throws OrderTrackingException
     */
    String sendMessage(final String hash)  throws OrderTrackingException;

}

