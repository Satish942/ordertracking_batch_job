/**
 * FileName      : $Id: BillingAndChargingException.java $
 *
 * Copyright Notice: ©2004 Singapore Telecom Pte Ltd -- Confidential and Proprietary
 *
 * All rights reserved.
 * This software is the confidential and proprietary information of SingTel Pte Ltd
 * ("Confidential Information"). You shall not disclose such Confidential Information
 * and shall use it only in accordance with the terms of the license agreement you
 * entered into with SingTel.
 */
package au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.exception;

import au.com.optus.mcas.core.exception.CheckedException;
import au.com.optus.mcas.core.exception.ServiceExceptionInfo;


/**
 * Template  checked exception class.
 * @author dev
 *
 */
public class OrderTrackingException extends CheckedException {

    private static final long serialVersionUID = 1L;

    /**
     * Creates an exception using the specified information and the exception
     * that caused the error.
     *
     * @param exceptionInfo
     *            Exception information returned in the SOAP fault to the
     *            client.
     * @param cause
     *            Exception that caused the error in the first place.
     */
    public OrderTrackingException(final OrderTrackingExceptionInfo exceptionInfo,
        final Throwable cause) {
        super(exceptionInfo, cause);
    }

    /**
     * Creates an exception using the specified information.
     *
     * @param exceptionInfo
     *            Exception information returned in the SOAP fault to the
     *            client.
     */
    public OrderTrackingException(final ServiceExceptionInfo exceptionInfo) {
        super(exceptionInfo);
    }

    /**
     * Creates an exception using the specified information.
     * @param exceptionInfo
     *          BillingAndChargingExceptionInfo
     * @param message
     *          SOAP fault message
     */
    public OrderTrackingException(final OrderTrackingExceptionInfo exceptionInfo, final String message) {
        super(exceptionInfo, message);
    }
}
