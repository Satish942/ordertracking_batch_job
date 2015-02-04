package au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.service.impl;

 
 

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.PersistenceException;

import junit.framework.Assert;

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
import au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.exception.OrderTrackingExceptionInfo;
import au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.facade.spring.SpringOrderTrackingFacade;
import au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.model.OttOmpOrderTrackSchedule;
import au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.model.OttOrderActivity;
import au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.model.OttOrderActivityPK;
import au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.model.OttOrderSummary;
import au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.service.OrderTrackingProcessService;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/test-order-tracking-batch-context.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class })
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class OttOrderServiceImplTest {
    
  
    @Autowired
    private OttOrderActivityDaoImpl daoImpl;
    
    @Autowired
    private OttOrderSummaryDaoImpl daoImplSummaryDaoImpl;
    
    
    @Autowired
    private OttOmpOrderTrackScheduleDaoImpl ottOmpOrderTrackScheduleDaoImpl;
    
    @Autowired
    private OrderTrackingProcessService fotProcessingServiceImpl;
    
    
     
     
    @Test
    public void testOttOmpOrderTrackScheduleDaoImpl() throws OrderTrackingException, IllegalArgumentException, IllegalAccessException, ParseException{
    	 
    	OttOmpOrderTrackSchedule ottOmpOrderTrackSchedule = new OttOmpOrderTrackSchedule();
		ottOmpOrderTrackSchedule.setBatchName(OrderTrackingConstants.BATCHNAME);
		Date todayDate = new Date();
		String toDateAsString = "05-11-13";
        Date toDate = new SimpleDateFormat("dd-MM-yy").parse(toDateAsString);
        
        String OrderCompletionDateTimeString= "05-11-12";
        Date OrderCompletionDate = new SimpleDateFormat("dd-MM-yy").parse(OrderCompletionDateTimeString);
		 java.sql.Timestamp sq = new java.sql.Timestamp(todayDate.getTime());
		ottOmpOrderTrackSchedule.setEndTime(sq);
		ottOmpOrderTrackSchedule.setLastSuccessRun(toDate);
		ottOmpOrderTrackSchedule.setStartTime(sq);
		ottOmpOrderTrackSchedule.setStatus("Pending");
		ottOmpOrderTrackScheduleDaoImpl.createEntity(ottOmpOrderTrackSchedule);
    	
		    OttOrderSummary ottOrderSummary1 = new OttOrderSummary();
	        ottOrderSummary1.setCarriageType(null);
	        ottOrderSummary1.setEmail(null);
	        ottOrderSummary1.setOrderCompletionDateTime(OrderCompletionDate);
	        ottOrderSummary1.setParentOrderNumber(null);
	        ottOrderSummary1.setOrderId(234);
	        ottOrderSummary1.setOrderStatus(null);
	        ottOrderSummary1.setOrderType(null);
	        
	        ottOrderSummary1.setParentOrderSystem(null);
	        ottOrderSummary1.setParentOrderType(null);
	        ottOrderSummary1.setPostCode(null);
	        ottOrderSummary1.setRecordType("summary1");
	        ottOrderSummary1.setSystemName(null);
	        ottOrderSummary1.setOrderModifiedDate(OrderCompletionDate);
	       
	        /*OttOrderSummary ottOrderSummary2 = new OttOrderSummary();
	        ottOrderSummary2.setCarriageType("DSLD");
	        ottOrderSummary2.setEmail("somebody@optus.com.au");
	        ottOrderSummary2.setOrderCompletionDateTime(new Date());
	        
	        ottOrderSummary2.setOrderId(235);
	        ottOrderSummary2.setOrderStatus("COMPLETED");
	        ottOrderSummary2.setOrderType("OTT");
	        ottOrderSummary2.setParentOrderNumber(null);
	        ottOrderSummary2.setParentOrderSystem("POS");
	        ottOrderSummary2.setParentOrderType("SOS");
	        ottOrderSummary2.setPostCode("P");
	        ottOrderSummary2.setRecordType("SUMMARY2");
	        //ottOrderSummary2.setSystemName("OTT");
	        ottOrderSummary2.setOrderModifiedDate(new Date());
	        
	        OttOrderSummary ottOrderSummary3 = new OttOrderSummary();
	        ottOrderSummary3.setCarriageType("FUSION");
	        ottOrderSummary3.setEmail("somebody@optus.com.au");
	        ottOrderSummary3.setOrderCompletionDateTime(new Date());
	        ottOrderSummary3.setParentOrderNumber(null);
	        ottOrderSummary3.setOrderId(236);
	        ottOrderSummary3.setOrderStatus("FAILED");
	        ottOrderSummary3.setOrderType("OTT"); 
	         
	        ottOrderSummary3.setParentOrderSystem("POS");
	        ottOrderSummary3.setParentOrderType("SOS");
	        ottOrderSummary3.setPostCode("P");
	        ottOrderSummary3.setRecordType("SUMMARY3");
	        ottOrderSummary3.setSystemName("OTT");
	        ottOrderSummary3.setOrderModifiedDate(new Date());
    	    daoImplSummaryDaoImpl.updateEntity(ottOrderSummary2);
    	    OttOrderSummary updateEntity = daoImplSummaryDaoImpl.updateEntity(ottOrderSummary3);*/
    	    daoImplSummaryDaoImpl.updateEntity(ottOrderSummary1);
    	   
    	    OttOrderActivityPK ottOrderActivityPk = new OttOrderActivityPK();
            ottOrderActivityPk.setOttOrdWkfTransTaskId(1);
            ottOrderActivityPk.setProductType(1);
            
    	    OttOrderActivity ottOrderActivity1 = new OttOrderActivity();
    	    ottOrderActivity1.setId(ottOrderActivityPk);
            ottOrderActivity1.setTransitionTaskModifiedTime(OrderCompletionDate);
            ottOrderActivity1.setForecastDateTime(new Date());
            ottOrderActivity1.setOrderId(234);
            ottOrderActivity1.setTaskName("taskName");
            ottOrderActivity1.setRecordType("Activity");
            ottOrderActivity1.setTransitionTaskStatus("TransitionStatus");
             
           /* OttOrderActivityPK ottOrderActivityPk2 = new OttOrderActivityPK();
            ottOrderActivityPk2.setOttOrdWkfTransTaskId(1);
            ottOrderActivityPk2.setProductType(2);
            
            OttOrderActivity ottOrderActivity2 = new OttOrderActivity();
            ottOrderActivity2.setId(ottOrderActivityPk2);
            ottOrderActivity2.setTransitionTaskModifiedTime(new Date());
            ottOrderActivity2.setForecastDateTime(new Date());
            ottOrderActivity2.setOrderId(235);
            
            ottOrderActivity2.setRecordType("Acti");
            //ottOrderActivity2.setTransitionTaskStatus("C");
            daoImpl.updateEntity(ottOrderActivity2);*/

            daoImpl.updateEntity(ottOrderActivity1);
               	
          String excuteOrderTrackingBatch = fotProcessingServiceImpl.excuteOrderTrackingBatch();
          System.out.println("output of the combined string" +excuteOrderTrackingBatch);
         Assert.assertNotNull(excuteOrderTrackingBatch);
		 
    }
    
    @Test(expected = OrderTrackingException.class)
    public void testOttOmpOrderTrackScheduleDaoImplIfnull() throws OrderTrackingException, IllegalArgumentException, IllegalAccessException{
        OrderTrackingConstants.BATCHNAME.toString();
        OrderTrackingConstants.FAILURE.toString();
        OrderTrackingException exceptionInfo1 = new OrderTrackingException(OrderTrackingExceptionInfo.FOT001, new Throwable());
        OrderTrackingException exception = new OrderTrackingException(null);
          String excuteOrderTrackingBatch = fotProcessingServiceImpl.excuteOrderTrackingBatch();
         Assert.assertNotNull(excuteOrderTrackingBatch);
         
    }
    

}
