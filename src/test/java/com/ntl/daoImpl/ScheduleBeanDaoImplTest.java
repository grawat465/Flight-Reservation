package com.ntl.daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
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

import com.ntl.frs.bean.ProfileBean;
import com.ntl.frs.bean.ScheduleBean;
import com.ntl.frs.dao.impl.CredentialsBeanDaoImpl;
import com.ntl.frs.dao.impl.ProfileBeanDaoImpl;
import com.ntl.frs.dao.impl.ScheduleBeanDaoImpl;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import static org.mockito.Mockito.when;
public class ScheduleBeanDaoImplTest {

	 DataSource mockDataSource=mock(DataSource.class);
	    Connection mockConn=mock(Connection.class);
	    PreparedStatement mockPreparedStmnt=mock(PreparedStatement.class);
	    Statement mockCreateStmt=mock(Statement.class);
	    ResultSet mockResultSet=mock(ResultSet.class);
	
	    String dateof="20/08/2019";
	    String str[]=dateof.split("/");
	    LocalDate dob=LocalDate.of(Integer.parseInt(str[2]),Integer.parseInt(str[1]), Integer.parseInt(str[0]));
		
	 ScheduleBean schedule=new ScheduleBean("Nech0960","567","ra9792",dob);
	 
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
	        when(mockResultSet.first()).thenReturn(true);
	        when(mockResultSet.getString(1)).thenReturn("Nech0960");
	        when(mockResultSet.getString(2)).thenReturn("567");
	        when(mockResultSet.getString(3)).thenReturn("");
	        when(mockResultSet.getDate(4)).thenReturn(Date.valueOf(dob));
	   
	      //  when(mockResultSet.getInt(4)).thenReturn(0);
	    }

	@Test
	public void testCreateScheduleBean() throws SQLException {
		ScheduleBeanDaoImpl sched=new ScheduleBeanDaoImpl(mockDataSource);

		assertEquals("success",sched.createScheduleBean(schedule));
	}

	@Test
	public void testDeleteScheduleBean() throws SQLException {
		ScheduleBeanDaoImpl sched=new ScheduleBeanDaoImpl(mockDataSource);
		ArrayList<String> al=new ArrayList<String>();
		al.add(schedule.getScheduleID());
		
		assertEquals(1,sched.deleteScheduleBean(al));
	}

	@Test
	public void testUpdateScheduleBean() throws SQLException {
		ScheduleBeanDaoImpl sched=new ScheduleBeanDaoImpl(mockDataSource);

		assertEquals(true,sched.updateScheduleBean(schedule));
	}


	@Test
	public void testFindAll() throws SQLException {
		ScheduleBeanDaoImpl sched=new ScheduleBeanDaoImpl(mockDataSource);
		ArrayList<ScheduleBean> al=new ArrayList<ScheduleBean>();
		al.add(schedule);
		
		assertEquals(al.size(),sched.findAll().size());
	}

}
