/**
 * FileName      : $Id: OttOrderActivityDao.java $
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

import au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.model.OttOrderActivity;

import java.util.Date;
import java.util.List;

/**
 * OttOrderActivityDao.
 * @author dev
 *
 */
public interface OttOrderActivityDao extends OrderTrackingJobGenericDao<OttOrderActivity>
{
    /**
     * This method is used to retrieve OrderTrackingActivityDetails using the last success run
     * and return the list of OttOrderActivity objects.
     * @param lastSuccessRun
     *        Date
     * @return List
     */
    List<OttOrderActivity>  retrieveOrderTrackingActivityDetails(Date lastSuccessRun);

}
