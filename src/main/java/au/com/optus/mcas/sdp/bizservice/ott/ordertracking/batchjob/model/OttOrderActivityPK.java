/**
 * FileName      : $Id: SpringOrderTrackingFacade.java $
 *
 * Copyright Notice: ©2004 Singapore Telecom Pte Ltd -- Confidential and Proprietary
 *
 * All rights reserved.
 * This software is the confidential and proprietary information of SingTel Pte Ltd
 * ("Confidential Information"). You shall not disclose such Confidential Information
 * and shall use it only in accordance with the terms of the license agreement you
 * entered into with SingTel.
 */
package au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


/**
 *
 * @author dev
 *
 */
@Embeddable
public class OttOrderActivityPK  implements Serializable {


    /**
     *
     */
    private static final long serialVersionUID = 1L;


    @Column(name = "OTT_ORD_WKF_TRANS_TASK_ID")
    private Integer  ottOrdWkfTransTaskId;



    @Column(name = "PRODUCT_TYPE")
    private  Integer productType;

    /**
     * @return the ottOrdWkfTransTaskId
     */

    public Integer getOttOrdWkfTransTaskId() {
        return ottOrdWkfTransTaskId;
    }

    /**
     * @param ottOrdWkfTransTaskId the ottOrdWkfTransTaskId to set
     */
    public void setOttOrdWkfTransTaskId(Integer ottOrdWkfTransTaskId) {
        this.ottOrdWkfTransTaskId = ottOrdWkfTransTaskId;
    }



    /**
     * @return the productType
     */


    public Integer getProductType() {
        return productType;
    }

    /**
     * @param productType the productType to set
     */
    public void setProductType(Integer productType) {
        this.productType = productType;
    }


}
