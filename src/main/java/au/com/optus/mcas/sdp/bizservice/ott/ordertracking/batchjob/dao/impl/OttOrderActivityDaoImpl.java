/**
 * FileName      : $Id: OttOrderActivityDaoImpl.java $
 *
 * Copyright Notice: ©2004 Singapore Telecom Pte Ltd -- Confidential and Proprietary
 *
 * All rights reserved.
 * This software is the confidential and proprietary information of SingTel Pte Ltd
 * ("Confidential Information"). You shall not disclose such Confidential Information
 * and shall use it only in accordance with the terms of the license agreement you
 * entered into with SingTel.
 */
package au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.dao.impl;





import au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.dao.OttOrderActivityDao;
import au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.model.OttOrderActivity;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
/**
 * OttOrderActivityDaoImpl.
 * @author dev
 *
 */
@Repository("au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.dao.impl.OttOrderActivityDaoImpl")
public class OttOrderActivityDaoImpl extends AbstractDaoImpl<OttOrderActivity> implements OttOrderActivityDao {

    private static final Logger LOG = LoggerFactory.getLogger(OttOrderActivity.class);

    /**
     * This method is used to retrieve OrderTrackingActivityDetails using the last success run
     * and return the list of OttOrderActivity objects.
     * @param lastSuccessRun
     *        Date
     * @return List
     */
    public List<OttOrderActivity>  retrieveOrderTrackingActivityDetails(Date lastSuccessRun) {
        LOG.info("retrieveOrderTrackingActivityDetails Method ----------- STARTS");
        List<OttOrderActivity> ottOrderActivityList = new ArrayList<OttOrderActivity>();
        if (lastSuccessRun != null) {
            DetachedCriteria criteria = super.createDetachedCriteria();
            Calendar c = Calendar.getInstance();
            c.setTime(lastSuccessRun);
            Date time = c.getTime();
            criteria.add(Restrictions.disjunction().
                    add(Restrictions.eq("transitionTaskModifiedTime", time))
                   .add(Restrictions.gt("transitionTaskModifiedTime", time)));

            ottOrderActivityList = findByCriteria(criteria);
            if (!ottOrderActivityList.isEmpty()) {
                LOG.debug("OttOrderActivityDaoImpl : retrieveOrderTrackingActivityDetails : "
                        + "Record found for lastSuccessRun " + lastSuccessRun
                        + " in OTT_ORDER_ACTIVITY_VIEW table");

            }

        }
        LOG.debug("OttOrderActivityDaoImpl : retrieveOrderTrackingActivityDetails : "
                + "Record not found for lastSuccessRun " + lastSuccessRun
                 + " in OTT_ORDER_ACTIVITY_VIEW table");
        LOG.info("retrieveOrderTrackingActivityDetails Method ----------- ENDS");
        return ottOrderActivityList;

    }

}
