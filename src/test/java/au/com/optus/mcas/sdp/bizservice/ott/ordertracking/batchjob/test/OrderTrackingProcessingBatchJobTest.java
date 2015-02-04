package au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.test;

import static org.mockito.BDDMockito.given;

import java.text.ParseException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;

import au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.OrderTrackingProcessingBatchJob;

 


@RunWith(MockitoJUnitRunner.class)
public class OrderTrackingProcessingBatchJobTest {
    
    
    
    @InjectMocks 
    private au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.OrderTrackingProcessingBatchJob OrderTrackingProcessingBatchJob;
    
    @Mock
    private JobExecutionContext context;
    
    @Mock
    JobDetail job;
    
    @Test(expected = JobExecutionException.class)
    public void testOrderTrackingProcessingBatchJob() throws SchedulerException, ParseException{
         
        job.setName("dummyJobName");
        job.setJobClass(OrderTrackingProcessingBatchJob.class);
        given(this.job.getName()).willReturn("dummyJobName");
        given(this.context.getJobDetail()).willReturn(job);
      // given(this.context.getJobDetail().getName()).willReturn(job.getName());
        OrderTrackingProcessingBatchJob.execute(context);
        
    }

}
