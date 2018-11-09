package com.ntl.srs.serviceImpl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ntl.frs.bean.FlightBean;
import com.ntl.frs.bean.PassengerBean;
import com.ntl.frs.bean.ReservationBean;
import com.ntl.frs.bean.ScheduleBean;
import com.ntl.frs.dao.impl.FlightBeanDaoImpl;
import com.ntl.frs.dao.impl.PassengerBeanDaoImpl;
import com.ntl.frs.dao.impl.ReservationBeanDaoImpl;
import com.ntl.frs.service.impl.CustomerImpl;

public class CustomerImplTest {


	@Test
	public void testViewScheduleByRoute() throws SQLException {
		
		String dobirth="25/03/2019";
		String dateOfBirth[]=dobirth.split("/");
		LocalDate dat=LocalDate.of(Integer.parseInt(dateOfBirth[2]),Integer.parseInt(dateOfBirth[1]), Integer.parseInt(dateOfBirth[0]));
		
		ScheduleBean sb=new ScheduleBean("345","234","1234");
		ArrayList<ScheduleBean> asl=new ArrayList<ScheduleBean>();
		asl.add(sb);
		ReservationBeanDaoImpl reseren=mock(ReservationBeanDaoImpl.class);
		
			when(reseren.ScheduletoRoute("kathmandu", "bhutan",dat )).thenReturn(asl);
		
		
		CustomerImpl cust=new CustomerImpl(reseren);
		
		ArrayList<ScheduleBean> result =cust.viewScheduleByRoute("kathmandu", "bhutan",dat);
		
		assertEquals(result.size(),1);
	}

	@Test
	public void testReserveTicket() throws SQLException {
		
		String dobirth="07/11/2018";
		String dateOfBirth[]=dobirth.split("/");
		LocalDate dat=LocalDate.of(Integer.parseInt(dateOfBirth[2]),Integer.parseInt(dateOfBirth[1]), Integer.parseInt(dateOfBirth[0]));
		
		String birth="01/01/2000";
		String OfBirth[]=birth.split("/");
		LocalDate datt=LocalDate.of(Integer.parseInt(OfBirth[2]),Integer.parseInt(OfBirth[1]), Integer.parseInt(OfBirth[0]));
		
		
		ReservationBean rb=new ReservationBean("jkka3505","jkka7475","is1091",dat,datt,2,6912,"confirm");
		
ReservationBeanDaoImpl pas=mock(ReservationBeanDaoImpl.class);
		
	when(pas.createReservationBean(rb)).thenReturn("confirm");
	
	PassengerBean pbbb=new PassengerBean("jkka3505","is1091","Govi",67,"Male");
	PassengerBean pb=new PassengerBean("jkka3505","is1091","Govi",67,"Male");
	ArrayList<PassengerBean> albean=new ArrayList<PassengerBean>();
	
	albean.add(pb);
	albean.add(pbbb);
	
	
	CustomerImpl cus=mock(CustomerImpl.class);
	
	when(cus.addingPassengers(albean)).thenReturn("success");
		//when(passen.allPass("InAu3456")).thenReturn(true);
	
	CustomerImpl custom=new CustomerImpl(pas,cus);
	
	String result=custom.reserveTicket(rb, albean);
	assertEquals(result,"confirm");
		
	}

	@Test
	public void testCancelTicket() throws SQLException {
		PassengerBean rb=new PassengerBean("jkka3505","is1091","Govi",67,"Male");
		PassengerBean pb=new PassengerBean("jkka3505","is1091","Varun",76,"Female");
		
		PassengerBeanDaoImpl passen=mock(PassengerBeanDaoImpl.class);
		
		when(passen.allPass("jkka3505")).thenReturn(true);
		
		
		CustomerImpl cust=new CustomerImpl(passen);
		
		boolean result=cust.cancelTicket("jkka3505");
		assertEquals(result,true);
	}

			

	
	

	@Test
	public void testPrintTicket() {
	//	fail("Not yet implemented");
	}

	@Test
	public void testChangeBookingStatus() {
		
		String dobirth="07/11/2018";
		String dateOfBirth[]=dobirth.split("/");
		LocalDate dat=LocalDate.of(Integer.parseInt(dateOfBirth[2]),Integer.parseInt(dateOfBirth[1]), Integer.parseInt(dateOfBirth[0]));
		
		String birth="01/01/2000";
		String OfBirth[]=birth.split("/");
		LocalDate datt=LocalDate.of(Integer.parseInt(OfBirth[2]),Integer.parseInt(OfBirth[1]), Integer.parseInt(OfBirth[0]));
		
		
		
	ReservationBean rb=new ReservationBean("jkka3505","jkka7475","is1091",dat,datt,2,6912,"confirm");
		
		ReservationBeanDaoImpl reseren=mock(ReservationBeanDaoImpl.class);
		
			when(reseren.updateReservationBean(rb)).thenReturn(true);
		
		
			CustomerImpl cust=new CustomerImpl(reseren);
		
		boolean result = cust.changeBookingStatus(rb);
		
		assertEquals(result,true);
	}

	@Test
	public void testAddingPassengers() throws SQLException {
		
		PassengerBean rb=new PassengerBean("jkka3505","is1091","Govi",67,"Male");
		PassengerBean pb=new PassengerBean("jkka3505","is1091","Varun",76,"Female");
		
		ArrayList<PassengerBean>	albean=new ArrayList<PassengerBean>();
		
		albean.add(pb);
		albean.add(rb);
		
		PassengerBeanDaoImpl passen=mock(PassengerBeanDaoImpl.class);
		
		when(passen.createPassengerBean(pb)).thenReturn("success");
		when(passen.createPassengerBean(rb)).thenReturn("success");
		
		CustomerImpl cust=new CustomerImpl(passen);
		
		String status=cust.addingPassengers(albean);
		assertEquals(status,"success");
		
	}

}
