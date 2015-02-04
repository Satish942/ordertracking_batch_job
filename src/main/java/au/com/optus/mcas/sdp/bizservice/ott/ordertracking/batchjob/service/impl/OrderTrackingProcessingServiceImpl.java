/**
 * FileName      : $Id: OrderTrackingProcessingServiceImpl.java $
 *
 * Copyright Notice: ©2004 Singapore Telecom Pte Ltd -- Confidential and Proprietary
 *
 * All rights reserved.
 * This software is the confidential and proprietary information of SingTel Pte Ltd
 * ("Confidential Information"). You shall not disclose such Confidential Information
 * and shall use it only in accordance with the terms of the license agreement you
 * entered into with SingTel.
 */
package au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.service.impl;

import au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.constants.OrderTrackingConstants;
import au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.dao.OttOmpOrderTrackScheduleDao;
import au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.dao.OttOrderActivityDao;
import au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.dao.OttOrderSummaryDao;
import au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.exception.OrderTrackingException;
import au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.exception.OrderTrackingExceptionInfo;
import au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.model.OttOmpOrderTrackSchedule;
import au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.model.OttOrderActivity;
import au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.model.OttOrderSummary;
import au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.service.OrderTrackingProcessService;
import au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.util.OrderTrackingPropertiesContainer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * OrderTrackingProcessingServiceImpl.
 *
 * @author dev
 *
 */
@Service("au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.service.impl.OrderTrackingProcessingServiceImpl")
@Transactional(value = "ott_order_tracking-jpaTransactionManager", propagation = Propagation.REQUIRED)
public class OrderTrackingProcessingServiceImpl implements
        OrderTrackingProcessService {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(OrderTrackingProcessingServiceImpl.class);

    private static final String PIPELINE_DELEMITER = "|";

    private static final String NEXTLINE_DELEMITER = "\r\n";
    
    
    @Autowired
    private OttOmpOrderTrackScheduleDao ottOmpOrderTrackScheduleDao;

    @Autowired
    private OttOrderActivityDao ottOrderActivityDao;

    @Autowired
    private OttOrderSummaryDao ottOrderSummaryDao;

    @Autowired
    @Qualifier("order_tracking_batch_job")
    private OrderTrackingPropertiesContainer properties;

    /**
     * This method is used to execute and return the common string using the
     * acitivity string and summary string. It uses the lastsuccessrun
     * OttOmpOrderTrackSchedule to retrieve the date from the activity view and
     * summary view.
     *
     * @return String
     * @throws OrderTrackingException
     *             it throws OrderTrackingException
     */

    @Override
    public String excuteOrderTrackingBatch() throws OrderTrackingException {
        LOGGER.info("excuteOrderTrackingBatch Method ----------- STARTS");

        Date lastSuccessRun;
        OttOmpOrderTrackSchedule retrieveOrderTrackingSchdule = null;

        retrieveOrderTrackingSchdule = ottOmpOrderTrackScheduleDao
                .retrieveOrderTrackingSchdule(OrderTrackingConstants.BATCHNAME);

        if (retrieveOrderTrackingSchdule == null) {
            debugMsgLogger(
                    "Record not found in OTT_OMP_ORDER_TRACK_SCHEDULE table when "
                            + "retrieveOrderTrackingSchdule with batch name: {} ",
                    retrieveOrderTrackingSchdule);
            LOGGER.info("Record not found in OTT_OMP_ORDER_TRACK_SCHEDULE table when"
                    + "retrieveOrderTrackingSchdule with batch name");
            throw new OrderTrackingException(OrderTrackingExceptionInfo.FOT001,
                    "Record not found in OTT_OMP_ORDER_TRACK_SCHEDULE table");
        }

        lastSuccessRun = retrieveOrderTrackingSchdule.getLastSuccessRun();

        LOGGER.info("The Date of the LastSuccessRun" + lastSuccessRun);

        updateTransaction(retrieveOrderTrackingSchdule);

        String commonActivityAndSummaryString = retrieveOrderTrackingDetails(lastSuccessRun);
        LOGGER.info("excuteOrderTrackingBatch Method ----------- ENDS");
        return commonActivityAndSummaryString;
    }

    /**
     * This method is used to make a common string of both activity string and
     * summary string.
     *
     * @param lastSuccessRun
     *            Date
     * @return String
     * @throws OrderTrackingException
     *             it throws OrderTrackingException
     */
    private String retrieveOrderTrackingDetails(Date lastSuccessRun)
        throws OrderTrackingException {

        LOGGER.info("retrieveOrderTrackingDetails Method ----------- STARTS");
        StringBuilder commonString = new StringBuilder();
        try {
            String convertOrderTrackingActivityDetailsToString = convertOttOrderActivityDetailsToString(lastSuccessRun);
            debugMsgLogger(
                    "output string when converting OrderTrackingActivityDetailsList toString\n: {} ",
                    convertOrderTrackingActivityDetailsToString);
            String convertOttOrderSummaryDetailsToString = convertOttOrderSummaryDetailsToString(lastSuccessRun);
            debugMsgLogger(
                    "output string when converting OttOrderSummaryDetailsList toString\n: {} ",
                    convertOttOrderSummaryDetailsToString);
            commonString.append(convertOrderTrackingActivityDetailsToString)
                    .append(convertOttOrderSummaryDetailsToString);
            debugMsgLogger(
                    "output string obtained with the combination of Activty and Summary List\n: {} ",
                    commonString);

        } catch (IllegalArgumentException e) {

            throw new OrderTrackingException(
                    OrderTrackingExceptionInfo.FOT002,
                    "An Illegal Argument exception caught when convert order tracking details List to String");
        } catch (IllegalAccessException e) {
            throw new OrderTrackingException(
                    OrderTrackingExceptionInfo.FOT003,
                    "An Illegal Access exception caught when convert order tracking details List to String");

        }
        LOGGER.info("retrieveOrderTrackingDetails Method ----------- ENDS");
        return commonString.toString();

    }

    /**
     * This Method is used to convert activity details to a string.
     *
     * @param lastSuccessRun
     *            Date
     * @return String
     * @throws IllegalArgumentException
     *             it throws IllegalArgumentException
     * @throws IllegalAccessException
     *             it throws IllegalAccessException
     */
    public String convertOttOrderActivityDetailsToString(Date lastSuccessRun)
        throws IllegalArgumentException, IllegalAccessException {
        LOGGER.info("convertOttOrderActivityDetailsToString Method ----------- STARTS");
        List<OttOrderActivity> orderTrackingActivityDetailsList = ottOrderActivityDao
                .retrieveOrderTrackingActivityDetails(lastSuccessRun);
        LOGGER.info("The Date of the LastSuccessRun from OTT_OMP_ORDER_TRACK_SCHEDULE table\n"
                + lastSuccessRun);
        StringBuilder fotBuilder = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat(properties.getDateFormat());
        for (OttOrderActivity ottOrderActivity : orderTrackingActivityDetailsList) {
            fotBuilder.append(appendedWithDelimiter(ottOrderActivity
                    .getOrderId()));
            fotBuilder.append(appendedWithDelimiter(ottOrderActivity
                    .getTaskName()));
            fotBuilder.append(appendedWithDelimiter(ottOrderActivity
                    .getTransitionTaskStatus()));
            if (ottOrderActivity.getTransitionTaskModifiedTime() != null) {
                fotBuilder.append(appendedWithDelimiter(sdf
                        .format(ottOrderActivity.getTransitionTaskModifiedTime())));
            } else {
                fotBuilder.append(appendedWithDelimiter(
                        ottOrderActivity.getTransitionTaskModifiedTime()));
            }
            fotBuilder.append(appendedWithDelimiter(ottOrderActivity.getId()
                    .getProductType()));

            if (ottOrderActivity.getForecastDateTime() != null) {
                fotBuilder.append(appendedWithDelimiter(sdf.format(ottOrderActivity
                        .getForecastDateTime())));
            } else {
                fotBuilder.append(appendedWithDelimiter(
                        ottOrderActivity.getForecastDateTime()));
            }

            fotBuilder.append(appendedWithDelimiter(ottOrderActivity
                    .getRecordType()));
            fotBuilder.append(NEXTLINE_DELEMITER);
        }
        LOGGER.info("convertOttOrderActivityDetailsToString Method ----------- ENDS");
        return fotBuilder.toString();
    }

    /**
     *
     * @param appendedWithDelimiter
     *        Object
     * @return String
     *
     */
    public String appendedWithDelimiter(Object appendedWithDelimiter) {
        LOGGER.info("appendedWithDelimiter Method ----------- STARTS::"
                + appendedWithDelimiter);

        String appendedWithDelimiterString = null;

        StringBuilder appendedWithDelimiterSB = new StringBuilder();

        if (appendedWithDelimiter != null) {
            appendedWithDelimiterString = appendedWithDelimiter.toString();
        }
        LOGGER.info("appendedWithDelimiter Method -----------BEFORE::"
                + appendedWithDelimiterString);

        if (appendedWithDelimiterString != null
                && !appendedWithDelimiterString.isEmpty()
                && !appendedWithDelimiterString.trim().equalsIgnoreCase("null")) {
            appendedWithDelimiterSB.append(appendedWithDelimiterString);
        }

        appendedWithDelimiterSB.append(PIPELINE_DELEMITER);
        LOGGER.info("appendedWithDelimiter Method ----------- AFTER::"
                + appendedWithDelimiterSB.toString());


        return appendedWithDelimiterSB.toString();
    }

    /**
     * This method is used to convert the summary details to string.
     *
     * @param lastSuccessRun
     *            Date
     * @return String
     * @throws IllegalArgumentException
     *             it throws IllegalArgumentException
     * @throws IllegalAccessException
     *             it throws IllegalAccessException
     */
    public String convertOttOrderSummaryDetailsToString(Date lastSuccessRun)
        throws IllegalArgumentException, IllegalAccessException {
        LOGGER.info("convertOttOrderSummaryDetailsToString Method ----------- STARTS");
        List<OttOrderSummary> orderTrackingSummaryDetails = ottOrderSummaryDao
                .retrieveOrderTrackingSummaryDetails(lastSuccessRun);
        StringBuilder fotBuilder = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat(properties.getDateFormat());
        for (OttOrderSummary ottOrderSummary : orderTrackingSummaryDetails) {

            fotBuilder.append(appendedWithDelimiter(ottOrderSummary
                    .getOrderId()));
            fotBuilder.append(appendedWithDelimiter(ottOrderSummary
                    .getOrderType()));
            fotBuilder.append(appendedWithDelimiter(ottOrderSummary
                    .getSystemName()));
            fotBuilder.append(appendedWithDelimiter(ottOrderSummary
                    .getParentOrderNumber()));
            fotBuilder.append(appendedWithDelimiter(ottOrderSummary
                    .getParentOrderType()));
            fotBuilder.append(appendedWithDelimiter(ottOrderSummary
                    .getParentOrderSystem()));
            fotBuilder.append(appendedWithDelimiter(ottOrderSummary
                    .getCarriageType()));
            fotBuilder.append(appendedWithDelimiter(ottOrderSummary
                    .getPostCode()));
            fotBuilder.append(appendedWithDelimiter(ottOrderSummary
                    .getEmail()));
            fotBuilder.append(appendedWithDelimiter(ottOrderSummary
                    .getOrderStatus()));
            if (ottOrderSummary.getOrderCompletionDateTime() != null) {
                fotBuilder.append(appendedWithDelimiter(sdf.format(ottOrderSummary
                    .getOrderCompletionDateTime())));
            } else {
                fotBuilder.append(appendedWithDelimiter(ottOrderSummary
                        .getOrderCompletionDateTime()));
            }

            if (ottOrderSummary.getOrderForeCastDateTime() != null) {

                fotBuilder.append(appendedWithDelimiter(sdf.format(ottOrderSummary
                    .getOrderForeCastDateTime())));
            } else {
                fotBuilder.append(appendedWithDelimiter(ottOrderSummary
                        .getOrderForeCastDateTime()));
            }

            fotBuilder.append(appendedWithDelimiter(ottOrderSummary
                    .getRecordType()));

            fotBuilder.append(NEXTLINE_DELEMITER);
        }
        LOGGER.info("convertOttOrderSummaryDetailsToString Method ----------- ENDS");
        return fotBuilder.toString();

    }

    /**
     *
     * @param retrieveOrderTrackingSchdule
     *            OttOmpOrderTrackSchedule
     */
    public void updateTransaction(
            OttOmpOrderTrackSchedule retrieveOrderTrackingSchdule) {
        Date currentrunstarttime = new Date();
        retrieveOrderTrackingSchdule.setStartTime(currentrunstarttime);
        retrieveOrderTrackingSchdule.setStatus(OrderTrackingConstants.PENDING);
        retrieveOrderTrackingSchdule.setEndTime(null);
        ottOmpOrderTrackScheduleDao.updateEntity(retrieveOrderTrackingSchdule);
    }

    /**
     * Debug Message Logger.
     *
     * @param msg
     *            the msg.
     * @param params
     *            the String params list.
     */
    private void debugMsgLogger(String msg, Object... params) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(msg, params);
        }
    }

}
