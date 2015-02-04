/**
 * FileName      : $Id: OttOrderSummaryDaoImpl.java $
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



import au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.dao.OttOrderSummaryDao;
import au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.model.OttOrderSummary;
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
 * OttOrderSummaryDaoImpl.
 * @author dev
 *
 */
@Repository("au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.dao.impl.OttOrderSummaryDaoImpl")
public class OttOrderSummaryDaoImpl extends AbstractDaoImpl<OttOrderSummary> implements OttOrderSummaryDao {

    private static final Logger LOG = LoggerFactory.getLogger(OttOrderSummary.class);
   /**
    * This method is used the retrieve OrderTrackingSummaryDetails using last success run
    * and returns the list of OttOrderSummary object.
    * @param lastSuccessRun
    *         Date
    * @return List
    *
    */
    public List<OttOrderSummary> retrieveOrderTrackingSummaryDetails(Date lastSuccessRun) {
        LOG.info("retrieveOrderTrackingSummaryDetails Method ----------- STARTS");
        List<OttOrderSummary> ottOrderSummaryList = new ArrayList<OttOrderSummary>();
        if (lastSuccessRun != null) {
            DetachedCriteria criteria = super.createDetachedCriteria();
            Calendar c = Calendar.getInstance();
            c.setTime(lastSuccessRun);
            criteria.add(Restrictions.disjunction().
                     add(Restrictions.eq("orderModifiedDate", c.getTime()))
                    .add(Restrictions.gt("orderModifiedDate", c.getTime()))
                    .add(Restrictions.eq("orderCustomerModifiedDate", c.getTime()))
                    .add(Restrictions.gt("orderCustomerModifiedDate", c.getTime())));

            ottOrderSummaryList = findByCriteria(criteria);
            if (!ottOrderSummaryList.isEmpty()) {
                LOG.debug("OttOrderSummaryDaoImpl : retrieveOrderTrackingSummaryDetails : "
                        + "Record found for lastSuccessRun "
                        + lastSuccessRun
                        + " in OTT_ORDER_SUMMARY_VIEW table");
                return ottOrderSummaryList;

            }
        }
        LOG.debug("OttOrderSummaryDaoImpl : retrieveOrderTrackingSummaryDetails : Record not found for lastSuccessRun "
                + lastSuccessRun
                + " in OTT_ORDER_SUMMARY_VIEW table");
        LOG.info("retrieveOrderTrackingSummaryDetails Method ----------- ENDS");
        return ottOrderSummaryList;



    }

}
