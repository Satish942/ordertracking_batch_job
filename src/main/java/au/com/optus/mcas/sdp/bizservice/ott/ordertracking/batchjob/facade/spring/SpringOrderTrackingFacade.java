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
package au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.facade.spring;






import au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.constants.OrderTrackingConstants;
import au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.dao.OttOmpOrderTrackScheduleDao;
import au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.exception.OrderTrackingException;
import au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.exception.OrderTrackingExceptionInfo;
import au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.facade.OrderTrackingFacade;
import au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.jms.client.OrderTrackingJmsProducer;
import au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.model.OttOmpOrderTrackSchedule;
import au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.service.OrderTrackingProcessService;


import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author dev
 *
 */

@Service(OrderTrackingFacade.BEAN_ID)
@Transactional(value = "ott_order_tracking-jpaTransactionManager", propagation = Propagation.REQUIRED)
public class SpringOrderTrackingFacade implements OrderTrackingFacade {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(SpringOrderTrackingFacade.class);

    @Autowired
    private OrderTrackingProcessService orderTrackingProcessService;

    @Autowired
    private OttOmpOrderTrackScheduleDao ottOmpOrderTrackScheduleDao;


    @Autowired
    private OrderTrackingJmsProducer nettyConnectionFactory;





    /**
     * This method is used to call the client to push the data into the JMSQueue
     * and updates the OTT_OMP_ORDER_TRACK_SCHEDULE table depending on the status.
     *
     * @throws OrderTrackingException it throws OrderTrackingException
     */
    public void publishOrderTracking() throws OrderTrackingException {

        LOGGER.info("publishOrderTracking Method ----------- STARTS");



        String responseMessage = OrderTrackingConstants.MSG_STATUS_FAILED;
        try {
            String activtyAndSummaryString = orderTrackingProcessService.excuteOrderTrackingBatch();

            responseMessage  = nettyConnectionFactory.sendMessage(activtyAndSummaryString);

            debugMsgLogger(
                    "Status of the message when data was pushed into the queue: {} ",
                    responseMessage);

           /* OMPEnablerPublisherService_PortType ompEnablerPublisherServiceSOAP = service
                    .getOMPEnablerPublisherServiceSOAP();
            detailsRequestDto.setOrderTrackingDetails(activtyAndSummaryString);
            OrderTrackingDetailsResponseDto publishOrderTrackingDetails = ompEnablerPublisherServiceSOAP
                    .publishOrderTrackingDetails(detailsRequestDto);
            responseMessage = publishOrderTrackingDetails.getResponseMessage();*/

        } catch (Exception ex) {
            debugMsgLogger(
                    "Status of the message when data was pushed into the queue: {} ",
                    responseMessage);
            OttOmpOrderTrackSchedule retrieveOrderTrackingSchdule = ottOmpOrderTrackScheduleDao
                    .retrieveOrderTrackingSchdule(OrderTrackingConstants.BATCHNAME);
            Date todayDate = new Date();
            retrieveOrderTrackingSchdule.setEndTime(todayDate);
            retrieveOrderTrackingSchdule
                    .setStatus(OrderTrackingConstants.FAILURE);
            OttOmpOrderTrackSchedule updateEntity = ottOmpOrderTrackScheduleDao
                    .updateEntity(retrieveOrderTrackingSchdule);
            debugMsgLogger(
                    "The status of the LastSuccess Run : {} ",
                    updateEntity.getLastSuccessRun());
            debugMsgLogger(
                    " The status of the Batch End Time: {} ",
                    updateEntity.getEndTime());

            LOGGER.error(
                    "Error occured while sending Message to the Queue.",
                    ex);
            throw new OrderTrackingException(
                    OrderTrackingExceptionInfo.FOT005,
                    "Error occured while sending Message to the Queue.");

        }

        if (OrderTrackingConstants.MSG_STATUS_SUCCESS.equals(responseMessage)) {
            debugMsgLogger(
                    "Status of the message when data was pushed into the queue: {} ",
                    responseMessage);
            OttOmpOrderTrackSchedule retrieveOrderTrackingSchdule = ottOmpOrderTrackScheduleDao
                    .retrieveOrderTrackingSchdule(OrderTrackingConstants.BATCHNAME);
            Date todayDate = new Date();
            retrieveOrderTrackingSchdule.setEndTime(todayDate);
            retrieveOrderTrackingSchdule
                    .setLastSuccessRun(retrieveOrderTrackingSchdule
                            .getStartTime());
            retrieveOrderTrackingSchdule
                    .setStatus(OrderTrackingConstants.SUCCESS);
            OttOmpOrderTrackSchedule updateEntity = ottOmpOrderTrackScheduleDao
                    .updateEntity(retrieveOrderTrackingSchdule);
            debugMsgLogger(
                    "The status of the Batch LastSuccess Run : {} ",
                    updateEntity.getLastSuccessRun());
            debugMsgLogger(
                    " The status of the Batch End Time: {} ",
                    updateEntity.getEndTime());

        } else {

            debugMsgLogger(
                    "Status of the message when data was pushed into the queue: {} ",
                    responseMessage);
            OttOmpOrderTrackSchedule retrieveOrderTrackingSchdule = ottOmpOrderTrackScheduleDao
                    .retrieveOrderTrackingSchdule(OrderTrackingConstants.BATCHNAME);
            Date todayDate = new Date();
            retrieveOrderTrackingSchdule.setEndTime(todayDate);
            retrieveOrderTrackingSchdule
                    .setStatus(OrderTrackingConstants.FAILURE);
            OttOmpOrderTrackSchedule updateEntity = ottOmpOrderTrackScheduleDao
                    .updateEntity(retrieveOrderTrackingSchdule);
            debugMsgLogger(
                    "The status of the Batch LastSuccess Run : {} ",
                    updateEntity.getLastSuccessRun());
            debugMsgLogger(
                    "The status of the Batch End Time: {} ",
                    updateEntity.getEndTime());
        }

        LOGGER.info("publishOrderTracking Method ----------- ENDS");
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
