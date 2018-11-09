package com.ntl.UtilImpl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ntl.frs.bean.PassengerBean;
import com.ntl.frs.bean.PaymentBean;
import com.ntl.frs.dao.impl.PassengerBeanDaoImpl;
import com.ntl.frs.dao.impl.PaymentDaoImpl;
import com.ntl.frs.service.impl.CustomerImpl;
import com.ntl.frs.util.impl.PaymentImpl;

public class PaymentImplTest {


	@Test
	public void testFindByCardNumber() throws SQLException {
		
		
		PaymentBean paid=new PaymentBean("1234","11","23",678,"is1091");
		
		PaymentDaoImpl pay=mock(PaymentDaoImpl.class);
		
		when(pay.findByID("is1091","1234")).thenReturn(paid);
		
		
		PaymentImpl cust=new PaymentImpl(pay);
		
		boolean status=cust.findByCardNumber("is1091","1234");
		assertEquals(status,true);
		
	}

	@Test
	public void testProcess() throws SQLException {
		
		PaymentBean paid=new PaymentBean("1234","11","23",678,"is1091");
		
		PaymentDaoImpl pay=mock(PaymentDaoImpl.class);
		
		when(pay.createPaymentBean(paid)).thenReturn("success");
		
		
		PaymentImpl cust=new PaymentImpl(pay);
		
		String status=cust.process(paid);
		assertEquals(status,"success");
		
	}

}
