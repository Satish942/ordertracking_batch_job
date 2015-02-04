/**
 * FileName      : $Id: OttOmpOrderTrackScheduleDao.java $
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

import au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.model.OttOmpOrderTrackSchedule;

/**
 * OttOmpOrderTrackScheduleDao.
 * @author dev
 *
 */
public interface OttOmpOrderTrackScheduleDao extends OrderTrackingJobGenericDao<OttOmpOrderTrackSchedule> {

    /**
     * This method retrieves the record using the batch name and return the
     * OttOmpOrderTrackSchedule object.
     * @param batchName
     *        String
     * @return OttOmpOrderTrackSchedule
     */
    OttOmpOrderTrackSchedule retrieveOrderTrackingSchdule(String batchName);

}
