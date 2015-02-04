/**
 * FileName      : $Id: OttOmpOrderTrackScheduleDaoImpl.java $
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







import au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.dao.OttOmpOrderTrackScheduleDao;
import au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.model.OttOmpOrderTrackSchedule;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
/**
 * OttOmpOrderTrackScheduleDaoImpl.
 * @author dev
 *
 */
@Repository("au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.dao.impl.OttOmpOrderTrackScheduleDaoImpl")
public class OttOmpOrderTrackScheduleDaoImpl extends
        AbstractDaoImpl<OttOmpOrderTrackSchedule> implements
        OttOmpOrderTrackScheduleDao {


    private static final Logger LOG = LoggerFactory.getLogger(OttOmpOrderTrackSchedule.class);

    /**
     * This method retrieves the record using the batch name and return the
     * OttOmpOrderTrackSchedule object.
     * @param batchName
     *        String
     * @return OttOmpOrderTrackSchedule
     */
    public OttOmpOrderTrackSchedule retrieveOrderTrackingSchdule(String batchName) {
        LOG.info("retrieveOrderTrackingSchdule Method ----------- STARTS");
        OttOmpOrderTrackSchedule ottOmpOrderTrackSchedule = null;
        if (StringUtils.isNotEmpty(batchName)) {
            DetachedCriteria criteria = super.createDetachedCriteria();
            criteria.add(Restrictions.eq("batchName", batchName));
            List<OttOmpOrderTrackSchedule> ottOmpOrderTrackScheduleList = findByCriteria(criteria);
            if (!ottOmpOrderTrackScheduleList.isEmpty()) {
                LOG.debug("OttOmpOrderTrackScheduleDaoImpl : retrieveOrderTrackingSchdule : "
                        + "Record found for batch name "
                        + batchName
                        + " in OTT_OMP_ORDER_TRACK_SCHEDULE table");
                ottOmpOrderTrackSchedule = ottOmpOrderTrackScheduleList.get(0);
                return ottOmpOrderTrackSchedule;
            }
        }
        LOG.debug("OttOmpOrderTrackScheduleDaoImpl : retrieveOrderTrackingSchdule : Record not found for batch name "
                + batchName
                + " in OTT_OMP_ORDER_TRACK_SCHEDULE table");
        LOG.info("retrieveOrderTrackingSchdule Method ----------- ENDS");
        return ottOmpOrderTrackSchedule;
    }



}
