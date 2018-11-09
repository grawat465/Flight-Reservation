package com.ntl.UtilImpl;


import static org.junit.Assert.assertEquals;

import java.sql.Connection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnit;

import com.ntl.frs.util.impl.DBUtilImpl;

//import test.com.ntl.srs.utilImpl.MockitoJUnitRunner;



public class DBUtilImplTest {

	

	@Test
	public void testGetDBConnection() {
		Connection conn=null;
		conn=DBUtilImpl.getDBConnection("jdbc");
		assertEquals(conn.toString(), "com.mysql.cj.jdbc.ConnectionImpl@1f2586d6");

	}

}
