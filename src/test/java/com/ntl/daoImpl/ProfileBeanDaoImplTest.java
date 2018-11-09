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
import com.ntl.frs.dao.impl.CredentialsBeanDaoImpl;
import com.ntl.frs.dao.impl.ProfileBeanDaoImpl;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProfileBeanDaoImplTest {
	 DataSource mockDataSource=mock(DataSource.class);
	    Connection mockConn=mock(Connection.class);
	    PreparedStatement mockPreparedStmnt=mock(PreparedStatement.class);
	    Statement mockCreateStmt=mock(Statement.class);
	    ResultSet mockResultSet=mock(ResultSet.class);
	
	    String dateof="20/02/2010";
	    String str[]=dateof.split("/");
	    LocalDate dob=LocalDate.of(Integer.parseInt(str[2]),Integer.parseInt(str[1]), Integer.parseInt(str[0]));
		
	    
	    ProfileBean prof=new ProfileBean("go1080","govind","rawat",dob,"male","12","jind","jind","haryana","126102","9812516493","grawat465@gmail.com","NULL");
	    
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
	        when(mockResultSet.getString(1)).thenReturn("is1080");
	        when(mockResultSet.getString(2)).thenReturn("govind");
	        when(mockResultSet.getString(3)).thenReturn("rawat");
	        when(mockResultSet.getDate(4)).thenReturn(Date.valueOf(dob));
	        when(mockResultSet.getString(5)).thenReturn("male");
	        when(mockResultSet.getString(6)).thenReturn("12");
	        when(mockResultSet.getString(7)).thenReturn("jind");
	        when(mockResultSet.getString(8)).thenReturn("jind");
	        when(mockResultSet.getString(9)).thenReturn("haryana");
	        when(mockResultSet.getString(10)).thenReturn("126102");
	        when(mockResultSet.getString(11)).thenReturn("9812516493");
	        when(mockResultSet.getString(12)).thenReturn("grawat465@gmail.com");
	        when(mockResultSet.getString(13)).thenReturn("NULL");
	      //  when(mockResultSet.getInt(4)).thenReturn(0);
	    }



	@Test
	public void testDeleteProfileBean() {
		//fail("Not yet implemented");
	}

	@Test
	public void testUpdateProfileBean() throws SQLException {
		ProfileBeanDaoImpl profile=new ProfileBeanDaoImpl(mockDataSource);

		assertEquals(true,profile.updateProfileBean(prof));
	
	}

	@Test
	public void testFindByID() throws SQLException {
		ProfileBeanDaoImpl profile=new ProfileBeanDaoImpl(mockDataSource);
		System.out.println(profile.findByID(prof.getUserID()).getEmailID());
		assertEquals(prof.getEmailID(),profile.findByID(prof.getUserID()).getEmailID());
	
	}

	@Test
	public void testFindAll() {
	//	fail("Not yet implemented");
	}

}
