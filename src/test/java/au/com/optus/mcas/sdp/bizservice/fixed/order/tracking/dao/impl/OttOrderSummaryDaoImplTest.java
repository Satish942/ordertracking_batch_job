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
public class OttOrderSummaryDaoImplTest {
    
  
    @Autowired
    private OttOrderSummaryDaoImpl daoImplSummaryDaoImpl;
    
    @Test
    public void testOttOrderSummaryDaoImplEqual() throws ParseException{
        
        String toDateAsString = "05-11-13";
        Date toDate = new SimpleDateFormat("dd-MM-yy").parse(toDateAsString);
        OttOrderSummary ottOrderSummary = new OttOrderSummary();
        ottOrderSummary.setCarriageType("NBN");
        ottOrderSummary.setEmail("someone@optus.com.au");
        ottOrderSummary.setOrderCompletionDateTime(new Date());
         
        ottOrderSummary.setOrderId(12);
        ottOrderSummary.setOrderStatus("PENDING");
        ottOrderSummary.setOrderType("online");
        ottOrderSummary.setParentOrderNumber(null);
        ottOrderSummary.setParentOrderSystem("parentOrderSystem");
        ottOrderSummary.setParentOrderType("parentOrderType");
        ottOrderSummary.setPostCode("postcode");
        ottOrderSummary.setRecordType("RecordType");
        ottOrderSummary.setSystemName("systemName");
        ottOrderSummary.setOrderModifiedDate(new Date());
        ottOrderSummary.setOrderCustomerModifiedDate(new Date());
         
        daoImplSummaryDaoImpl.updateEntity(ottOrderSummary);
        
        List<OttOrderSummary> retrieveOrderTrackingSummaryDetails = daoImplSummaryDaoImpl.retrieveOrderTrackingSummaryDetails(toDate);
         Assert.assertEquals(retrieveOrderTrackingSummaryDetails.get(0).getCarriageType(), ottOrderSummary.getCarriageType());
         Assert.assertEquals(retrieveOrderTrackingSummaryDetails.get(0).getEmail(), ottOrderSummary.getEmail());
         Assert.assertEquals(retrieveOrderTrackingSummaryDetails.get(0).getOrderStatus(), ottOrderSummary.getOrderStatus());
         Assert.assertEquals(retrieveOrderTrackingSummaryDetails.get(0).getOrderType(), ottOrderSummary.getOrderType());
         Assert.assertEquals(retrieveOrderTrackingSummaryDetails.get(0).getParentOrderSystem(), ottOrderSummary.getParentOrderSystem());
         Assert.assertEquals(retrieveOrderTrackingSummaryDetails.get(0).getParentOrderType(), ottOrderSummary.getParentOrderType());
         Assert.assertEquals(retrieveOrderTrackingSummaryDetails.get(0).getPostCode(), ottOrderSummary.getPostCode());
         Assert.assertEquals(retrieveOrderTrackingSummaryDetails.get(0).getRecordType(), ottOrderSummary.getRecordType());
         Assert.assertEquals(retrieveOrderTrackingSummaryDetails.get(0).getOrderCustomerModifiedDate(), ottOrderSummary.getOrderCustomerModifiedDate());
         Assert.assertEquals(retrieveOrderTrackingSummaryDetails.get(0).getOrderForeCastDateTime(), ottOrderSummary.getOrderForeCastDateTime());
         Assert.assertEquals(retrieveOrderTrackingSummaryDetails.get(0).getOrderId(), ottOrderSummary.getOrderId());
         Assert.assertEquals(retrieveOrderTrackingSummaryDetails.get(0).getOrderCompletionDateTime(), ottOrderSummary.getOrderCompletionDateTime());
         System.out.println("*************************parent order system" + retrieveOrderTrackingSummaryDetails.get(0).getParentOrderSystem());
    }
    
    
    @Test
    public void testOttOrderSummaryDaoImplGreater() throws ParseException{
        String toDateAsString = "05/11/2015";
        Date toDate = new SimpleDateFormat("MM/dd/yyyy").parse(toDateAsString);
        
        String lastsuccesrunString = "06/12/2015";
        Date lastsuccesrun = new SimpleDateFormat("MM/dd/yyyy").parse(lastsuccesrunString);
        
        OttOrderSummary ottOrderSummary = new OttOrderSummary();
        ottOrderSummary.setCarriageType("NBN");
        ottOrderSummary.setEmail("someone@optus.com.au");
        ottOrderSummary.setOrderCompletionDateTime(toDate);
         
        ottOrderSummary.setOrderId(12);
        ottOrderSummary.setOrderStatus("PENDING");
        ottOrderSummary.setOrderType("online");
         
        ottOrderSummary.setParentOrderSystem("parentOrderSystem");
        ottOrderSummary.setParentOrderType("parentOrderType");
        ottOrderSummary.setPostCode("postcode");
        ottOrderSummary.setRecordType("RecordType");
        ottOrderSummary.setSystemName("systemName");
        ottOrderSummary.setOrderModifiedDate(toDate);
        daoImplSummaryDaoImpl.updateEntity(ottOrderSummary);
        // lastsuccessrun >= modifieddate
        List<OttOrderSummary> retrieveOrderTrackingSummaryDetails = daoImplSummaryDaoImpl.retrieveOrderTrackingSummaryDetails(lastsuccesrun);
        System.out.println("+++++++++++++++++++++++++out put of the order modified date"+toDate);
        System.out.println("************************out put of the order lastsuccesrun"+lastsuccesrun);
         Assert.assertNotNull(retrieveOrderTrackingSummaryDetails.size()==0);
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void testOttOrderSummaryDaoImplNUll() throws ParseException{
        String toDateAsString = "05/11/2015";
        Date toDate = new SimpleDateFormat("MM/dd/yyyy").parse(toDateAsString);
        OttOrderSummary ottOrderSummary = new OttOrderSummary();
        ottOrderSummary.setCarriageType("NBN");
        ottOrderSummary.setEmail("someone@optus.com.au");
        ottOrderSummary.setOrderCompletionDateTime(toDate);
         
        ottOrderSummary.setOrderId(12);
        ottOrderSummary.setOrderStatus("PENDING");
        ottOrderSummary.setOrderType("online");
         
        ottOrderSummary.setParentOrderSystem("parentOrderSystem");
        ottOrderSummary.setParentOrderType("parentOrderType");
        ottOrderSummary.setPostCode("postcode");
        ottOrderSummary.setRecordType("RecordType");
        ottOrderSummary.setSystemName("systemName");
        daoImplSummaryDaoImpl.updateEntity(ottOrderSummary);
        
        List<OttOrderSummary> retrieveOrderTrackingSummaryDetails = daoImplSummaryDaoImpl.retrieveOrderTrackingSummaryDetails(null);
        
         Assert.assertEquals(retrieveOrderTrackingSummaryDetails.get(0).getOrderCompletionDateTime(), ottOrderSummary.getOrderCompletionDateTime());
    }
    
     
}
