package au.com.optus.mcas.sdp.bizservice.fixed.order.tracking.dao.impl;

 
 

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.PersistenceException;
import junit.framework.Assert;

import org.hibernate.criterion.DetachedCriteria;
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
import au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.model.OttOmpOrderTrackSchedule;
import au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.model.OttOrderActivity;
import au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.model.OttOrderActivityPK;
import au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.model.OttOrderSummary;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/test-order-tracking-batch-context.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class })
@Transactional(propagation = Propagation.REQUIRED)
public class OttOrderActivityDaoImplUnitTest {
    
  
    @Autowired
    private OttOrderActivityDaoImpl daoImpl;
    
    @Test
    public void testOttOrderActivityDaoImpl() throws ParseException{
    	String toDateAsString = "05-11-13";
    	Date toDate = new SimpleDateFormat("dd-MM-yy").parse(toDateAsString);
    	
    	
    	OttOrderActivityPK ottOrderActivityPk = new OttOrderActivityPK();
    	ottOrderActivityPk.setOttOrdWkfTransTaskId(2);
    	ottOrderActivityPk.setProductType(1);
    	
    	
    	OttOrderActivity ottOrderActivity  = new OttOrderActivity();
    	ottOrderActivity.setId(ottOrderActivityPk);
        ottOrderActivity.setTransitionTaskModifiedTime(new Date());
        ottOrderActivity.setForecastDateTime(new Date());
        ottOrderActivity.setOrderId(12);
        ottOrderActivity.setRecordType("recordType"); 
        ottOrderActivity.setTransitionTaskStatus("Pendiing");
        
        String toDateAsString2 = "05-11-14";
        Date toDate2= new SimpleDateFormat("dd-MM-yy").parse(toDateAsString2);
        
        
        OttOrderActivityPK ottOrderActivityPk1 = new OttOrderActivityPK();
        ottOrderActivityPk1.setOttOrdWkfTransTaskId(3);
        ottOrderActivityPk1.setProductType(2);
        
        OttOrderActivity ottOrderActivity1  = new OttOrderActivity();
        ottOrderActivity1.setId(ottOrderActivityPk1);
        ottOrderActivity1.setTransitionTaskModifiedTime(toDate2);
        ottOrderActivity1.setForecastDateTime(new Date());
        ottOrderActivity1.setOrderId(12);
        ottOrderActivity1.setRecordType("recordType");
        ottOrderActivity1.setTransitionTaskStatus("Pendiing");
        
        String toDateAsString3 = "05-11-12";
        Date toDate3= new SimpleDateFormat("dd-MM-yy").parse(toDateAsString3);
        
        
        OttOrderActivityPK ottOrderActivityPk2 = new OttOrderActivityPK();
        ottOrderActivityPk2.setOttOrdWkfTransTaskId(3);
        ottOrderActivityPk2.setProductType(1);
        
        OttOrderActivity ottOrderActivity2  = new OttOrderActivity();
        ottOrderActivity2.setId(ottOrderActivityPk2);
        ottOrderActivity2.setTransitionTaskModifiedTime(toDate3);
        ottOrderActivity2.setForecastDateTime(new Date());
        ottOrderActivity2.setOrderId(12);
        ottOrderActivity2.setRecordType("recordType");
        ottOrderActivity2.setTransitionTaskStatus("Pendiing");
        
        
        
            daoImpl.createEntity(ottOrderActivity);
            daoImpl.createEntity(ottOrderActivity1);
            daoImpl.updateEntity(ottOrderActivity2);
         
        List<OttOrderActivity> retrieveOrderTrackingActivityDetails = daoImpl.retrieveOrderTrackingActivityDetails(toDate);
        
        System.out.println("list size"+retrieveOrderTrackingActivityDetails.size());
        Assert.assertEquals(retrieveOrderTrackingActivityDetails.get(0).getRecordType(), ottOrderActivity.getRecordType());
        Assert.assertEquals(retrieveOrderTrackingActivityDetails.get(0).getTransitionTaskStatus(), ottOrderActivity.getTransitionTaskStatus());
        Assert.assertEquals(retrieveOrderTrackingActivityDetails.get(0).getForecastDateTime(), ottOrderActivity.getForecastDateTime());
        Assert.assertEquals(retrieveOrderTrackingActivityDetails.get(0).getOrderId(), ottOrderActivity.getOrderId());
        Assert.assertEquals(retrieveOrderTrackingActivityDetails.get(0).getTransitionTaskModifiedTime(), ottOrderActivity.getTransitionTaskModifiedTime());
    }
     
    @Test(expected = PersistenceException.class)
    public void testOttOrderActivityDaoImpl1() throws ParseException{
    	String toDateAsString = "05/11/2015";
    	Date toDate = new SimpleDateFormat("MM/dd/yyyy").parse(toDateAsString);
        OttOrderActivity ottOrderActivity  = new OttOrderActivity();
         
        ottOrderActivity.setTransitionTaskModifiedTime(toDate);
        ottOrderActivity.setForecastDateTime(new Date());
        ottOrderActivity.setOrderId(12);
         
        ottOrderActivity.setRecordType("recordType");
        ottOrderActivity.setTransitionTaskStatus("Pendiing");
       
        OttOrderActivity updateEntity = daoImpl.updateEntity(ottOrderActivity);
        List<OttOrderActivity> retrieveOrderTrackingActivityDetails = daoImpl.retrieveOrderTrackingActivityDetails(null);
       
        Assert.assertEquals(retrieveOrderTrackingActivityDetails.get(0).getTransitionTaskModifiedTime(), ottOrderActivity.getTransitionTaskModifiedTime());
    }
     
}
