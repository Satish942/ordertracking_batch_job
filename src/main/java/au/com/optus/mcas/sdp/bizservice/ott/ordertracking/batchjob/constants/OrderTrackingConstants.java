/**
 * FileName      : $Id: OrderTrackingConstants.java $
 *
 * Copyright Notice: ©2004 Singapore Telecom Pte Ltd -- Confidential and Proprietary
 *
 * All rights reserved.
 * This software is the confidential and proprietary information of SingTel Pte Ltd
 * ("Confidential Information"). You shall not disclose such Confidential Information
 * and shall use it only in accordance with the terms of the license agreement you
 * entered into with SingTel.
 */
package au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.constants;

/**
 * Constants defined for orderTracking.
 *
 * @author dev
 *
 */
public final class OrderTrackingConstants {

    /**  **/
    public static final String QUEUESUCCESS = "SUCCESS";

    /**
     * Parameter value to determine to customer Status.
     */
    public static final String BATCHNAME = "OTT_ORDER_TRACKING";

    /**
     * Parameter value to determine to customer Status.
     */
    public static final String FAILURE = "F";

    /**
     * Parameter value to determine to customer Status.
     */
    public static final String PENDING = "P";

    /**
     *  Parameter value to determine to customer Status.
     */
    public static final String SUCCESS = "S";

    /**
     *  Parameter value to determine to  msg Status.
     */

    public static final String MSG_STATUS_SUCCESS = "SUCCESS";

    /**
     *  Parameter value to determine to  description Status.
     */

    public static final String MSG_DESCRIPTION_SUCCESS = "Successfully delivered the message to the DPMReceiveQueue";


    /**
     * MSG_STATUS_FAILED.
     */
    public static final String MSG_STATUS_FAILED = "FAILED";

    /**
     * default constructor.
     */
    private OrderTrackingConstants() {

    }

}
