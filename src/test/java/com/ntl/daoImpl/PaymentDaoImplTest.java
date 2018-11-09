package com.ntl.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import org.junit.Before;
import org.junit.Test;

import com.ntl.frs.bean.PaymentBean;
import com.ntl.frs.dao.impl.CredentialsBeanDaoImpl;
import com.ntl.frs.dao.impl.PaymentDaoImpl;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PaymentDaoImplTest {

	 DataSource mockDataSource=mock(DataSource.class);
	    Connection mockConn=mock(Connection.class);
	    PreparedStatement mockPreparedStmnt=mock(PreparedStatement.class);
	    Statement mockCreateStmt=mock(Statement.class);
	    ResultSet mockResultSet=mock(ResultSet.class);

	    PaymentBean payment=new PaymentBean("1234","11","23",678,"is1091");
	    
	    @Before
	    public void setUp() throws SQLException {
	//	 when(mockDataSource.getDBConnection("jdbc")).thenReturn(mockConn);
		 
		// ship=new ShipBeanDaoImpl(dbutil);
		 
		 
	    //    doNothing().when(mockConn).commit();
			 when(mockDataSource.getConnection()).thenReturn(mockConn);
	        when(mockConn.prepareStatement(anyString())).thenReturn(mockPreparedStmnt);
	        when(mockConn.createStatement()).thenReturn(mockCreateStmt);
	        when(mockCreateStmt.executeQuery(anyString())).thenReturn(mockResultSet);
	       when(mockCreateStmt.executeUpdate(anyString())).thenReturn(1);
	        doNothing().when(mockPreparedStmnt).setString(anyInt(), anyString());
	        doNothing().when(mockPreparedStmnt).setInt(anyInt(), anyInt());
	        when(mockPreparedStmnt.executeUpdate()).thenReturn(1);
	        when(mockPreparedStmnt.executeQuery()).thenReturn(mockResultSet);
	       // when(mockPreparedStmnt.getGeneratedKeys()).thenReturn(mockResultSet);
	        when(mockResultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
	        
	        when(mockResultSet.getString(1)).thenReturn("1234");
	        when(mockResultSet.getString(2)).thenReturn("11");
	        when(mockResultSet.getString(3)).thenReturn("23");
	        when(mockResultSet.getInt(4)).thenReturn(678);
	        when(mockResultSet.getString(3)).thenReturn("is1091");
	    }
	    
	    
	@Test
	public void testCreatePaymentBean() throws SQLException {
		
PaymentDaoImpl pay=new PaymentDaoImpl(mockDataSource);

assertEquals("success",pay.createPaymentBean(payment));
	}

	@Test
	public void testUpdatePaymentBean() {
		
	}

	@Test
	public void testFindByID() throws SQLException {
		PaymentDaoImpl pay=new PaymentDaoImpl(mockDataSource);

		assertEquals(payment.getBalance(),pay.findByID(payment.getUserId(), payment.getCreditCard()).getBalance());
	}

}
