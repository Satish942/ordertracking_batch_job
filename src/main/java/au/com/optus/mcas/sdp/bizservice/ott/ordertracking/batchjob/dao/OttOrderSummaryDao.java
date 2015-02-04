/**
 * FileName      : $Id: OttOrderSummaryDao.java $
 *
 * Copyright Notice: ©2004 Singapore Telecom Pte Ltd -- Confidential and Proprietary
 *
 * All rights reserved.
 * This software is the confidential and proprietary information of SingTel Pte Ltd
 * ("Confidential Information"). You shall not disclose such Confidential Information
 * and shall use it only in accordance with the terms of the license agreement you
 * entered into with SingTel.
 */
package au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.dao;

import au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.model.OttOrderSummary;

import java.util.Date;
import java.util.List;

/**
 *
 * @author dev
 *
 */
public interface OttOrderSummaryDao extends OrderTrackingJobGenericDao<OttOrderSummary> {

    /**
     * This method is used to retrieve order tracking summary details using the last succes run
     * and return the list .
     * @param lastSuccessRun
     *       Date
     * @return List
     */
    List<OttOrderSummary> retrieveOrderTrackingSummaryDetails(Date lastSuccessRun);

}
