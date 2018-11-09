package com.ntl.frs.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.ntl.frs.bean.CredentialsBean;
import com.ntl.frs.bean.ProfileBean;
import com.ntl.frs.dao.ProfileBeanDao;
import com.ntl.frs.util.impl.DBUtilImpl;

public class ProfileBeanDaoImpl  implements ProfileBeanDao{
	
	
	Connection con=DBUtilImpl.getDBConnection("jdbc");
	

	
	public ProfileBeanDaoImpl(DataSource mockDataSource) throws SQLException {
		// TODO Auto-generated constructor stub
		con=mockDataSource.getConnection();
	}




	public ProfileBeanDaoImpl() {
		// TODO Auto-generated constructor stub
	}




	public String createProfileBean(ProfileBean profileBean) {


		Statement stmt;
		 ResultSet rs;
		 
		 
			Connection con=DBUtilImpl.getDBConnection("jdbc");
		try {
		
			
		
			
			PreparedStatement pss=con.prepareStatement("insert into frs_TBL_User_Credentials values (?,?,?,?)");
			pss.setString(1, profileBean.getUserID());
			pss.setString(2, profileBean.getPassword());
			pss.setString(3, "C");
			pss.setInt(4, 0);
			
			int storage=pss.executeUpdate();
			
			
			PreparedStatement ps=con.prepareStatement("insert into  frs_TBL_User_Profile (Userid,Firstname,Lastname,DateOfBirth,Gender,Street,Location,City,State,Pincode,MobileNo,EmailId,password) values (?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, profileBean.getUserID());
			ps.setString(2, profileBean.getFirstName());
			ps.setString(3, profileBean.getLastName());
			ps.setDate(4, Date.valueOf(profileBean.getDateOfBirth()));
			ps.setString(5, profileBean.getGender());
			ps.setString(6, profileBean.getStreet());
			ps.setString(7, profileBean.getLocation());
			ps.setString(8, profileBean.getCity());
			ps.setString(9, profileBean.getState());
			ps.setString(10, profileBean.getPincode());
			ps.setString(11, profileBean.getMobileNo());
			ps.setString(12, profileBean.getEmailID());
			ps.setString(13,profileBean.getPassword());
			
			int store=ps.executeUpdate();
			
	
			
			if(store>0 && storage>0)
			{
				return "success";
			}
			else {
				System.out.println("hii");
				return null;
			}
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return null;
	}
	


	
	public int deleteProfileBean(ArrayList<String> al) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public boolean updateProfileBean(ProfileBean ProfileBean) {

Connection con=DBUtilImpl.getDBConnection("jdbc");
		
		try {
			int z=0;
			PreparedStatement ps=con.prepareStatement("update frs_TBL_User_Credentials set LoginStatus="+z+" where UserId='"+ProfileBean.getUserID()+"'");
			int change=ps.executeUpdate();
			if(change>0)
			{
				return true;
			}
			return false;
		}
		catch(Exception e)
		{
          System.out.println(e);
		}
		
		return false;
	
	}

	
	public ProfileBean findByID(String id) {
Connection con=DBUtilImpl.getDBConnection("jdbc");
		Statement stmt=null;
		ResultSet rs=null;
		int flag=0;
		try {	
		stmt=con.createStatement();
		rs=stmt.executeQuery("select * from frs_TBL_User_Profile where UserId='"+id+"'");
		
			
				if(rs.first())
				{
					
					flag=1;
					
					Date today =rs.getDate(4);
					Instant instant = Instant.ofEpochMilli(today.getTime()); 
					LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
					LocalDate localDate = localDateTime.toLocalDate();

						
					ProfileBean prof=new ProfileBean(rs.getString(1),rs.getString(2),rs.getString(3),localDate,rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13));
					
					return prof;
				}
			
			if(flag==0)
			{
				
				return null;
			}
		}
		catch(SQLException sq)
		{
			System.out.println(sq);
		}
		
		
		return null;
	}

	
	public ArrayList<ProfileBean> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
