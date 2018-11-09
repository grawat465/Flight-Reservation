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

import com.ntl.frs.bean.RouteBean;
import com.ntl.frs.dao.impl.ProfileBeanDaoImpl;
import com.ntl.frs.dao.impl.RouteBeanDaoImpl;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class RouteBeanDaoImplTest {

	 DataSource mockDataSource=mock(DataSource.class);
	    Connection mockConn=mock(Connection.class);
	    PreparedStatement mockPreparedStmnt=mock(PreparedStatement.class);
	    Statement mockCreateStmt=mock(Statement.class);
	    ResultSet mockResultSet=mock(ResultSet.class);
	    
	    RouteBean route=new RouteBean("234","kathmandu","bhutan","100",3400);
	    
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
	        when(mockResultSet.getString(1)).thenReturn("234");
	        when(mockResultSet.getString(2)).thenReturn("kathmandu");
	        when(mockResultSet.getString(3)).thenReturn("bhutan");
	        
	        when(mockResultSet.getString(4)).thenReturn("100");
	        when(mockResultSet.getInt(5)).thenReturn(3400);
	    }


	@Test
	public void testCreateRouteBean() throws SQLException {
		RouteBeanDaoImpl routebean=new RouteBeanDaoImpl(mockDataSource);
		
		assertEquals("success",routebean.createRouteBean(route));
	}

	@Test
	public void testDeleteRouteBean() throws SQLException {
		RouteBeanDaoImpl routebean=new RouteBeanDaoImpl(mockDataSource);
		ArrayList<String> al=new ArrayList<String>();
		al.add("234");
		assertEquals(1,routebean.deleteRouteBean(al));
	}

	@Test
	public void testUpdateRouteBean() throws SQLException {
		RouteBeanDaoImpl routebean=new RouteBeanDaoImpl(mockDataSource);

		assertEquals(true,routebean.updateRouteBean(route));
	}

	@Test
	public void testFindByID() throws SQLException {
		RouteBeanDaoImpl routebean=new RouteBeanDaoImpl(mockDataSource);

		assertEquals(route.getFare(),routebean.findByID(route.getRouteID()).getFare());
	}

	@Test
	public void testFindAll() throws SQLException {
		RouteBeanDaoImpl routebean=new RouteBeanDaoImpl(mockDataSource);
		ArrayList<RouteBean> al=new ArrayList<RouteBean>();
		al.add(route);
		assertEquals(al.size(),routebean.findAll().size());
	}

}
