package au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.facade.spring;

import static org.mockito.BDDMockito.given;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.constants.OrderTrackingConstants;
import au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.dao.OttOmpOrderTrackScheduleDao;
import au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.exception.OrderTrackingException;
import au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.jms.client.OrderTrackingJmsProducer;
import au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.model.OttOmpOrderTrackSchedule;
import au.com.optus.mcas.sdp.bizservice.ott.ordertracking.batchjob.service.OrderTrackingProcessService;

@RunWith(MockitoJUnitRunner.class)
public class SpringOrderTrackingFacadeMockTest {
    
    @InjectMocks
    private SpringOrderTrackingFacade  springOrderFacade;
    
    @Mock
    private OrderTrackingProcessService orderTrackingProcessService;
    
    @Mock
    private OrderTrackingJmsProducer nettyConnectionFactory;
    
    @Mock
    private OttOmpOrderTrackScheduleDao ottOmpOrderTrackScheduleDao;
    
    @Mock
    private OttOmpOrderTrackSchedule updateEntity;
    
    private OttOmpOrderTrackSchedule updateEntity2;
    
    @Test
    public void testSpringOrderTrackingFacadeIfSuccess() throws OrderTrackingException{
        
        //given(this.transactionDao.getTransaction(context.getOttOrderId(), TransactionType.ADD)).willReturn(context.getTransaction());
        OttOmpOrderTrackSchedule ottOmpOrderTrackSchedule = new OttOmpOrderTrackSchedule();
        ottOmpOrderTrackSchedule.setBatchName(OrderTrackingConstants.BATCHNAME);
        Date todayDate = new Date();
        java.sql.Timestamp sq = new java.sql.Timestamp(todayDate.getTime());
        ottOmpOrderTrackSchedule.setEndTime(sq);
        ottOmpOrderTrackSchedule.setLastSuccessRun(sq);
        ottOmpOrderTrackSchedule.setStartTime(sq);
        ottOmpOrderTrackSchedule.setStatus("Pending");
        OttOmpOrderTrackSchedule updateEntity1 = ottOmpOrderTrackScheduleDao.updateEntity(ottOmpOrderTrackSchedule);
        given(this.orderTrackingProcessService.excuteOrderTrackingBatch()).willReturn("xxx");
        given(this.nettyConnectionFactory.sendMessage("xxx")).willReturn(OrderTrackingConstants.MSG_STATUS_SUCCESS);
        
        given(this.ottOmpOrderTrackScheduleDao.retrieveOrderTrackingSchdule(OrderTrackingConstants.BATCHNAME)).willReturn(updateEntity);
        given(this.ottOmpOrderTrackScheduleDao.updateEntity(updateEntity)).willReturn(updateEntity);
        
        springOrderFacade.publishOrderTracking();
        
    }
    
    @Test
    public void testSpringOrderTrackingFacadeIfFailure() throws OrderTrackingException{
        
        //given(this.transactionDao.getTransaction(context.getOttOrderId(), TransactionType.ADD)).willReturn(context.getTransaction());
        OttOmpOrderTrackSchedule ottOmpOrderTrackSchedule = new OttOmpOrderTrackSchedule();
        ottOmpOrderTrackSchedule.setBatchName(OrderTrackingConstants.BATCHNAME);
        Date todayDate = new Date();
        java.sql.Timestamp sq = new java.sql.Timestamp(todayDate.getTime());
        ottOmpOrderTrackSchedule.setEndTime(sq);
        ottOmpOrderTrackSchedule.setLastSuccessRun(sq);
        ottOmpOrderTrackSchedule.setStartTime(sq);
        ottOmpOrderTrackSchedule.setStatus("Pending");
        OttOmpOrderTrackSchedule updateEntity1 = ottOmpOrderTrackScheduleDao.updateEntity(ottOmpOrderTrackSchedule);
        given(this.orderTrackingProcessService.excuteOrderTrackingBatch()).willReturn("xxx");
        given(this.nettyConnectionFactory.sendMessage("xxx")).willReturn(OrderTrackingConstants.MSG_STATUS_FAILED);
        
        given(this.ottOmpOrderTrackScheduleDao.retrieveOrderTrackingSchdule(OrderTrackingConstants.BATCHNAME)).willReturn(updateEntity);
        given(this.ottOmpOrderTrackScheduleDao.updateEntity(updateEntity)).willReturn(updateEntity);
        
        springOrderFacade.publishOrderTracking();
        
    }


}
