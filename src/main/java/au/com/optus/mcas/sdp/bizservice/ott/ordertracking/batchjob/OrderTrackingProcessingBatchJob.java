/**
 * FileName      : $Id: OrderTrackingProcessingBatchJob.java $
 *
 * Copyright Notice: ©2004 Singapore Telecom Pte Ltd -- Confidential and Proprietary
 *
 * All rights reserved.
 * This software is the confidential and proprietary information of SingTel Pte Ltd
 * ("Confidential Information"). You shall not disclose such Confidential Information
 * and shall use it only in accordance with the terms of the license agreement you
 * entered into with SingTel.
 */
package au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob;


import au.com.optus.mcas.core.scheduler.SchedulerJob;
import au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.exception.OrderTrackingException;
import au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.facade.OrderTrackingFacade;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
/**
 * OrderTrackingProcessingBatchJob.
 * @author dev
 *
 */
public class OrderTrackingProcessingBatchJob extends SchedulerJob {

    private static final Logger LOG = LoggerFactory
            .getLogger(OrderTrackingProcessingBatchJob.class);

    /**
     * This field is automatically populated by the Spring-managed Quartz Job
     * factory.
     * @param jobContext
     *        JobExecutionContext
     * @throws JobExecutionException it throws JobExecutionException
     */

    @Override
    protected void executeJob(final JobExecutionContext jobContext)
        throws JobExecutionException {
        final String jobName = jobContext.getJobDetail().getName();

        try {

            LOG.info("executeJob Method ----------- STARTS");
            ApplicationContext appCtx = (ApplicationContext) jobContext
                    .getScheduler().getContext().get(APPLICATION_CONTEXT_KEY);

            ((OrderTrackingFacade) appCtx.getBean(OrderTrackingFacade.BEAN_ID))
                    .publishOrderTracking();
            LOG.info("Starting execution of job: {}...", jobName);

        } catch (OrderTrackingException ote) {
            LOG.error(String
                    .format("Execution of  Publish Order Tracking'%s' has failed: %s",
                             ote.getExceptionInfo().getFaultCode(), ote.getExceptionInfo().getFaultString()));
        } catch (final Exception ex) {
            LOG.error(String
                    .format("Execution of  Order Tracking Processing Batch Job '%s' has failed: %s",
                            jobName, ex.getMessage(), ex));
            throw new JobExecutionException(ex);
        }

        LOG.info(
                "Finished execution of  Order Tracking File Processing Batch Job '{}'",
                jobName);
    }

}
