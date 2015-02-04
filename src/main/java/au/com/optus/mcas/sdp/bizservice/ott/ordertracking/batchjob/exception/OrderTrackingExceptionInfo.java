/**
 * FileName      : $Id: FixedOrderTrackingExceptionInfo.java $
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

import au.com.optus.mcas.core.exception.ServiceExceptionInfo;
import au.com.optus.mcas.core.util.ToStringBuilder;

/**
 * Exception information for the Template service.
 *
 * @author dev
 *
 */
public enum OrderTrackingExceptionInfo implements ServiceExceptionInfo {

    /** Error code- Record not found 8n the table. **/
    FOT001("Record not found in OttOmpOrderTrackSchedule table"),

    /**
     * Error code- An Illegal Argument exception caught.
     **/

    FOT002(
            "An Illegal Argument exception caught when convert order tracking details List to String"),

    /**
     *       An Illegal Access exception caught.
     */
    FOT003(
            "An Illegal Access exception caught when convert order tracking details List to String"),

    /**
     * Error code - File Not Found or Service is down.
     */
    FOT004("Exeption caught due to File Not Found or Service is down"),

    /**
     * Error -queue was not found for the service.
     */
    FOT005("Error occured while sending Message to the Queue."),


    /**
     * Error -queue was not found for the queue.
     */

    FOT006("Error occured while  creating a queue or sending message to the queue.");




    /** the fault text. */
    private final String faultString;

    /**
     * Creates a new instance of this enum.
     *
     * @param faultString
     *            String
     */
    private OrderTrackingExceptionInfo(final String faultStr) {
        this.faultString = faultStr;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getFaultCode() {
        return this.name();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getFaultString() {
        return faultString;
    }

    /**
     * Returns the enum code (its name) followed by the fault code text for
     * logging purposes.
     *
     * @return object String
     */
    @Override
    public String toString() {
        final ToStringBuilder builder = new ToStringBuilder(this);
        builder.append(this.name(), faultString);
        return builder.toString();
    }

}
