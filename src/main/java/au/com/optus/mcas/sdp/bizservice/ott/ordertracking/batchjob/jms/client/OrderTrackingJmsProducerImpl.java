/**
 * FileName      : $Id: OrderTrackingJmsProducerImpl.java $
 *
 * Copyright Notice: ©2004 Singapore Telecom Pte Ltd -- Confidential and Proprietary
 *
 * All rights reserved.
 * This software is the confidential and proprietary information of SingTel Pte Ltd
 * ("Confidential Information"). You shall not disclose such Confidential Information
 * and shall use it only in accordance with the terms of the license agreement you
 * entered into with SingTel.
 */

package au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.jms.client;





import au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.constants.OrderTrackingConstants;
import au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.exception.OrderTrackingException;
import au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.exception.OrderTrackingExceptionInfo;
import au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.util.OrderTrackingPropertiesContainer;

import java.util.UUID;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author dev
 *
 */
@Service("messageSender")
@Transactional(value = "ott_order_tracking-jpaTransactionManager", propagation = Propagation.REQUIRED)
public class OrderTrackingJmsProducerImpl implements OrderTrackingJmsProducer {


    private static final Logger LOGGER = LoggerFactory
            .getLogger(OrderTrackingJmsProducerImpl.class);

    @Autowired
    private OrderTrackingPropertiesContainer  properties;


    private JmsTemplate jmsTemplate;


    @Override
    public  String sendMessage(final String activitySummaryMessage) throws OrderTrackingException {
        LOGGER.info("========  sending messge to the JmsQueue Starts===========");
        String deliveryStatus = "";
        final String correlationId = UUID.randomUUID().toString();
        try {
            getJmsTemplate().send(new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                    MessageConverter messageConverter = jmsTemplate.getMessageConverter();
                    Message jmsMessage = messageConverter.toMessage(activitySummaryMessage, session);
                    jmsMessage.setStringProperty(properties.getHqLvqName(), activitySummaryMessage);
                    jmsMessage.setJMSCorrelationID(correlationId);
                    return jmsMessage;
                }
            });

            LOGGER.info("========  sending messge to the JmsQueue Ends===========");
            deliveryStatus = OrderTrackingConstants.MSG_STATUS_SUCCESS;

        }  catch (Exception ex) {

            LOGGER.info("====Error occured on sending the message to QUEUE==== "
                    + ex);
            deliveryStatus = OrderTrackingConstants.MSG_STATUS_FAILED;
            throw new OrderTrackingException(
                     OrderTrackingExceptionInfo.FOT006,
                     "Error occured while  creating a queue or sending message to the queue.");

        }
        return deliveryStatus;

    }


    public JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }


    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

}
