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
public class OttOmpOrderTrackScheduleDaoImplUnitTest {
    
    @Autowired
    private OttOmpOrderTrackScheduleDaoImpl ottOmpOrderTrackScheduleDaoImpl;
    
    @Test
    public void testOttOmpOrderTrackScheduleDaoImpl(){
    	
		OttOmpOrderTrackSchedule ottOmpOrderTrackSchedule = new OttOmpOrderTrackSchedule();
		ottOmpOrderTrackSchedule.setBatchName(OrderTrackingConstants.BATCHNAME);
		Date todayDate = new Date();
        java.sql.Timestamp sq = new java.sql.Timestamp(todayDate.getTime());
		ottOmpOrderTrackSchedule.setEndTime(sq);
		ottOmpOrderTrackSchedule.setLastSuccessRun(sq);
		ottOmpOrderTrackSchedule.setStartTime(sq);
		ottOmpOrderTrackSchedule.setStatus("Pending");
		ottOmpOrderTrackScheduleDaoImpl.createEntity(ottOmpOrderTrackSchedule);
		OttOmpOrderTrackSchedule retrieveOrderTrackingSchdule = ottOmpOrderTrackScheduleDaoImpl.retrieveOrderTrackingSchdule(OrderTrackingConstants.BATCHNAME);
		Assert.assertEquals( retrieveOrderTrackingSchdule.getEndTime(), ottOmpOrderTrackSchedule.getEndTime());
		Assert.assertEquals( retrieveOrderTrackingSchdule.getStatus(), ottOmpOrderTrackSchedule.getStatus());
		Assert.assertEquals( retrieveOrderTrackingSchdule.getBatchName(), ottOmpOrderTrackSchedule.getBatchName());
    }
     
    @Test
    public void testOttOmpOrderTrackScheduleDaoImpl1(){
        
        OttOmpOrderTrackSchedule ottOmpOrderTrackSchedule = new OttOmpOrderTrackSchedule();
        ottOmpOrderTrackSchedule.setBatchName(OrderTrackingConstants.BATCHNAME);
        Date todayDate = new Date();
        java.sql.Timestamp sq = new java.sql.Timestamp(todayDate.getTime());
        ottOmpOrderTrackSchedule.setEndTime(sq);
        ottOmpOrderTrackSchedule.setLastSuccessRun(sq);
        ottOmpOrderTrackSchedule.setStartTime(sq);
        ottOmpOrderTrackSchedule.setStatus("Pending");
        ottOmpOrderTrackScheduleDaoImpl.saveOrUpdateEntity(ottOmpOrderTrackSchedule);
        DetachedCriteria criteria = DetachedCriteria.forClass(OttOmpOrderTrackSchedule.class);
        ottOmpOrderTrackScheduleDaoImpl.findByCriteria(criteria,  1,  2);
        OttOmpOrderTrackSchedule retrieveOrderTrackingSchdule = ottOmpOrderTrackScheduleDaoImpl.retrieveOrderTrackingSchdule(OrderTrackingConstants.BATCHNAME);
        Assert.assertEquals( retrieveOrderTrackingSchdule.getEndTime(), ottOmpOrderTrackSchedule.getEndTime());
        Assert.assertEquals( retrieveOrderTrackingSchdule.getStatus(), ottOmpOrderTrackSchedule.getStatus());
        Assert.assertEquals( retrieveOrderTrackingSchdule.getBatchName(), ottOmpOrderTrackSchedule.getBatchName());
    }
}
