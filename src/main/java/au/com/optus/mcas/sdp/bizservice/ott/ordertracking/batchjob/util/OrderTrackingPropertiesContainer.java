/**
 * FileName      : $Id: OrderTrackingPropertiesContainer.java $
 *
 * Copyright Notice: ©2004 Singapore Telecom Pte Ltd -- Confidential and Proprietary
 *
 * All rights reserved.
 * This software is the confidential and proprietary information of SingTel Pte Ltd
 * ("Confidential Information"). You shall not disclose such Confidential Information
 * and shall use it only in accordance with the terms of the license agreement you
 * entered into with SingTel.
 */
package au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.util;

import au.com.optus.mcas.core.properties.AbstractPropertiesContainer;
import au.com.optus.mcas.core.properties.Property;
import org.springframework.stereotype.Repository;

/**
 * OrderTrackingPropertiesContainer.
 * @author dev
 *
 */
@Repository(value = "order_tracking_batch_job")
public class OrderTrackingPropertiesContainer  extends AbstractPropertiesContainer {

    @Property(key = "order.tracking.service.dateFormat")
    private String dateFormat;

    @Property(key = "order.tracking.service.hq.lvq.name")
    private String  hqLvqName;

    /**
     * @return the dateFormat
     */
    public String getDateFormat() {
        return dateFormat;
    }

    /**
     * @param dateFormat the dateFormat to set
     */
    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    /**
     * @return the hqLvqName
     */
    public String getHqLvqName() {
        return hqLvqName;
    }

    /**
     * @param hqLvqName the hqLvqName to set
     */
    public void setHqLvqName(String hqLvqName) {
        this.hqLvqName = hqLvqName;
    }



}
