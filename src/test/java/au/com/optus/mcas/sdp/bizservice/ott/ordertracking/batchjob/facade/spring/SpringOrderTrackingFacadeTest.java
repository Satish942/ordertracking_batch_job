package au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.facade.spring;

 
 

import java.text.ParseException;
import java.util.Date;

import javax.persistence.PersistenceException;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.constants.OrderTrackingConstants;
import au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.dao.impl.OttOmpOrderTrackScheduleDaoImpl;
import au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.dao.impl.OttOrderActivityDaoImpl;
import au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.dao.impl.OttOrderSummaryDaoImpl;
import au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.exception.OrderTrackingException;
import au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.facade.OrderTrackingFacade;
import au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.model.OttOmpOrderTrackSchedule;
import au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.model.OttOrderActivity;
import au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.model.OttOrderActivityPK;
import au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.model.OttOrderSummary;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/test-order-tracking-batch-context.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class })
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class SpringOrderTrackingFacadeTest {
    
  
    @Autowired
    private OttOrderActivityDaoImpl daoImpl;
    
    @Autowired
    private OttOrderSummaryDaoImpl daoImplSummaryDaoImpl;
    
    
    @Autowired
    private OttOmpOrderTrackScheduleDaoImpl ottOmpOrderTrackScheduleDaoImpl;
    
    
    @Autowired
    private  OrderTrackingFacade publishOrderTracking;
    
    
  
    @Test(expected = OrderTrackingException.class)
    public void testOttOmpOrderTrackScheduleDaoImpl() throws OrderTrackingException, IllegalArgumentException, IllegalAccessException {
        
        OttOmpOrderTrackSchedule ottOmpOrderTrackSchedule = new OttOmpOrderTrackSchedule();
        ottOmpOrderTrackSchedule.setBatchName(OrderTrackingConstants.BATCHNAME);
        Date todayDate = new Date();
        java.sql.Timestamp sq = new java.sql.Timestamp(todayDate.getTime());
        ottOmpOrderTrackSchedule.setEndTime(sq);
        ottOmpOrderTrackSchedule.setLastSuccessRun(sq);
        ottOmpOrderTrackSchedule.setStartTime(sq);
        ottOmpOrderTrackSchedule.setStatus("Pending");
        ottOmpOrderTrackScheduleDaoImpl.createEntity(ottOmpOrderTrackSchedule);
        
            OttOrderSummary ottOrderSummary1 = new OttOrderSummary();
            ottOrderSummary1.setCarriageType("NBN");
            ottOrderSummary1.setEmail("somebody@optus.com.au");
            ottOrderSummary1.setOrderCompletionDateTime(ottOmpOrderTrackSchedule.getLastSuccessRun());
            ottOrderSummary1.setParentOrderNumber(null);
            ottOrderSummary1.setOrderId(234);
            ottOrderSummary1.setOrderStatus("PENDING");
            ottOrderSummary1.setOrderType("OTT");
             
            ottOrderSummary1.setParentOrderSystem("XXX");
            ottOrderSummary1.setParentOrderType("NEW");
            ottOrderSummary1.setPostCode("P");
            ottOrderSummary1.setRecordType("SUMMARY");
            ottOrderSummary1.setSystemName("OTT");
            ottOrderSummary1.setOrderModifiedDate(ottOmpOrderTrackSchedule.getLastSuccessRun());
           
            OttOrderSummary ottOrderSummary2 = new OttOrderSummary();
            ottOrderSummary2.setCarriageType("DSLD");
            ottOrderSummary2.setEmail("somebody@optus.com.au");
            ottOrderSummary2.setOrderCompletionDateTime(ottOmpOrderTrackSchedule.getLastSuccessRun());
            ottOrderSummary2.setParentOrderNumber("");
            ottOrderSummary2.setOrderId(235);
            ottOrderSummary2.setOrderStatus("COMPLETED");
            ottOrderSummary2.setOrderType("OTT");
             
            ottOrderSummary2.setParentOrderSystem("POS");
            ottOrderSummary2.setParentOrderType("SOS");
            ottOrderSummary2.setPostCode("P");
            ottOrderSummary2.setRecordType("SUMMARY");
            ottOrderSummary2.setSystemName("OTT");
            ottOrderSummary2.setOrderModifiedDate(ottOmpOrderTrackSchedule.getLastSuccessRun());
            
            OttOrderSummary ottOrderSummary3 = new OttOrderSummary();
            ottOrderSummary3.setCarriageType("FUSION");
            ottOrderSummary3.setEmail("somebody@optus.com.au");
            ottOrderSummary3.setOrderCompletionDateTime(ottOmpOrderTrackSchedule.getLastSuccessRun());
            
            ottOrderSummary3.setOrderId(236);
            ottOrderSummary3.setOrderStatus("FAILED");
            ottOrderSummary3.setOrderType("OTT"); 
            
            ottOrderSummary3.setParentOrderSystem("POS");
            ottOrderSummary3.setParentOrderType("SOS");
            ottOrderSummary3.setPostCode("P");
            ottOrderSummary3.setRecordType("SUMMARY");
            ottOrderSummary3.setSystemName("OTT");
            ottOrderSummary3.setOrderModifiedDate(ottOmpOrderTrackSchedule.getLastSuccessRun());
            daoImplSummaryDaoImpl.updateEntity(ottOrderSummary1);
            daoImplSummaryDaoImpl.updateEntity(ottOrderSummary2);
            OttOrderSummary updateEntity = daoImplSummaryDaoImpl.updateEntity(ottOrderSummary3);
          
            OttOrderActivityPK ottOrderActivityPk = new OttOrderActivityPK();
            ottOrderActivityPk.setOttOrdWkfTransTaskId(1);
            ottOrderActivityPk.setProductType(1);
            
            OttOrderActivity ottOrderActivity1 = new OttOrderActivity();
            ottOrderActivity1.setId(ottOrderActivityPk);
            ottOrderActivity1.setTransitionTaskModifiedTime(ottOmpOrderTrackSchedule.getLastSuccessRun());
            ottOrderActivity1.setForecastDateTime(new Date());
            ottOrderActivity1.setOrderId(234);
            ottOrderActivity1.setRecordType("ACTIVITY");
            ottOrderActivity1.setTransitionTaskStatus("P");
           
            OttOrderActivityPK ottOrderActivityPk2 = new OttOrderActivityPK();
            ottOrderActivityPk2.setOttOrdWkfTransTaskId(1);
            ottOrderActivityPk2.setProductType(1);
            OttOrderActivity ottOrderActivity2 = new OttOrderActivity();
            ottOrderActivity2.setId(ottOrderActivityPk2);
            ottOrderActivity2.setTransitionTaskModifiedTime(ottOmpOrderTrackSchedule.getLastSuccessRun());
            ottOrderActivity2.setForecastDateTime(new Date());
            ottOrderActivity2.setOrderId(235);
            ottOrderActivity2.setRecordType("ACTIVITY");
            ottOrderActivity2.setTransitionTaskStatus("C");
            daoImpl.updateEntity(ottOrderActivity1);
            daoImpl.updateEntity(ottOrderActivity2);
        
        
          publishOrderTracking.publishOrderTracking();
        //OMPPublishEnablerClient ompPublishEnablerClient = new OMPPublishEnablerClient();
        /*String targetURL = "http://localhost:7001/Order_Tracking/ProxyService/order_ps";
        ompPublishEnablerClient.pushMessageToQueue(targetURL, excuteOrderTrackingBatch);*/
        
        
       // System.out.println("**********************\n"+updateEntity.getOrderModifiedDate());
        
       // System.out.println("############################\n"+excuteOrderTrackingBatch);
         
    }
    
    
}
