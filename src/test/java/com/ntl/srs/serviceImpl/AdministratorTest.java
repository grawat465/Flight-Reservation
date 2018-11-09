package com.ntl.srs.serviceImpl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ntl.frs.bean.CredentialsBean;
import com.ntl.frs.bean.FlightBean;
import com.ntl.frs.bean.PassengerBean;
import com.ntl.frs.bean.RouteBean;
import com.ntl.frs.bean.ScheduleBean;
import com.ntl.frs.dao.impl.CredentialsBeanDaoImpl;
import com.ntl.frs.dao.impl.FlightBeanDaoImpl;
import com.ntl.frs.dao.impl.PassengerBeanDaoImpl;
import com.ntl.frs.dao.impl.RouteBeanDaoImpl;
import com.ntl.frs.dao.impl.ScheduleBeanDaoImpl;
import com.ntl.frs.service.impl.AdministratorImpl;
import com.ntl.frs.util.impl.LoggedIn;

public class AdministratorTest {

	FlightBeanDaoImpl flight;
	RouteBeanDaoImpl roue;
	ScheduleBeanDaoImpl schedu;
	PassengerBeanDaoImpl passen;

	@Test
	public void testAddFlight() throws SQLException {
		FlightBean sb=new FlightBean("2304","kingfisher",5,10);
		
		flight=mock(FlightBeanDaoImpl.class);
		
			when(flight.createFlightBean(sb)).thenReturn("success");
		
		
		AdministratorImpl admin=new AdministratorImpl(flight);
		
		String result = admin.addFlight(sb);
		
		assertEquals(result,"success");
	}

	@Test
	public void testModifyFlight() throws SQLException {
	FlightBean sb=new FlightBean("2304","kingfisher",5,10);
		
		flight=mock(FlightBeanDaoImpl.class);
		
			when(flight.updateFlightBean(sb)).thenReturn(true);
		
		
		AdministratorImpl admin=new AdministratorImpl(flight);
		
		boolean result = admin.modifyFlight(sb);
		
		assertEquals(result,true);
	}

	@Test
	public void testAddSchedule() throws SQLException {
		
		String dobirth="25/03/2019";
		String dateOfBirth[]=dobirth.split("/");
		LocalDate dat=LocalDate.of(Integer.parseInt(dateOfBirth[2]),Integer.parseInt(dateOfBirth[1]), Integer.parseInt(dateOfBirth[0]));
		
		ScheduleBean sb=new ScheduleBean("InAu2345","InAu3456","Sh7890",dat);
		
		schedu=mock(ScheduleBeanDaoImpl.class);
		
			when(schedu.createScheduleBean(sb)).thenReturn("success");
		
		
		AdministratorImpl admin=new AdministratorImpl(schedu);
		
		String result = admin.addSchedule(sb);
		
		assertEquals(result,"success");
	}

	@Test
	public void testModifySchedule() throws SQLException {
		String dobirth="25/03/2019";
		String dateOfBirth[]=dobirth.split("/");
		LocalDate dat=LocalDate.of(Integer.parseInt(dateOfBirth[2]),Integer.parseInt(dateOfBirth[1]), Integer.parseInt(dateOfBirth[0]));
		
		ScheduleBean sb=new ScheduleBean("InAu2345","InAu4786","th3748",dat);
		
		schedu=mock(ScheduleBeanDaoImpl.class);
		
			when(schedu.updateScheduleBean(sb)).thenReturn(true);
		
		
		AdministratorImpl admin=new AdministratorImpl(schedu);
		
		boolean result = admin.modifySchedule(sb);
		
		assertEquals(result,true);
	}

	@Test
	public void testRemoveSchedule() {
		
		String dobirth="25/03/2019";
		String dateOfBirth[]=dobirth.split("/");
		LocalDate dat=LocalDate.of(Integer.parseInt(dateOfBirth[2]),Integer.parseInt(dateOfBirth[1]), Integer.parseInt(dateOfBirth[0]));
		
		ScheduleBean sb=new ScheduleBean("InAu2345","InAu4786","th3748",dat);
		
		ScheduleBean sbb=new ScheduleBean("ItAu2245","ItAu4486","td3748",dat);
		
		
		schedu=mock(ScheduleBeanDaoImpl.class);
		ArrayList<String> al=new ArrayList<String>();
		al.add("InAu2345");
		al.add("ItAu2245");
		when(schedu.deleteScheduleBean(al)).thenReturn(2);
		
		
		AdministratorImpl admin=new AdministratorImpl(schedu);
		
		int result = admin.removeSchedule(al);
		
		assertEquals(result,2);
	}

	@Test
	public void testAddRoute() throws SQLException {
RouteBean rb=new RouteBean("InAth6","India","Sri Lanks","40",20000);
		
		roue=mock(RouteBeanDaoImpl.class);
		
		when(roue.createRouteBean(rb)).thenReturn("success");
		
		
		AdministratorImpl admin=new AdministratorImpl(roue);
		
		String result = admin.addRoute(rb);
		
		assertEquals(result,"success");
	}

	@Test
	public void testModifyRoute() throws SQLException {
RouteBean rb=new RouteBean("InAu3456","India","Australia","4000",44000);
		
		roue=mock(RouteBeanDaoImpl.class);
		
		when(roue.updateRouteBean(rb)).thenReturn(true);
		
		
		AdministratorImpl admin=new AdministratorImpl(roue);
		
		boolean result = admin.modifyRoute(rb);
		
		assertEquals(result,true);
	}

	@Test
	public void testRemoveRoute() throws SQLException {
RouteBean rb=new RouteBean("InAu3456","India","Australia","4000",44000);
RouteBean rbb=new RouteBean("Ausr3255","Australia","Sri Lanka","4000",44000);

		String s="InAu3456"+ " "+"AuSr3255";
		roue=mock(RouteBeanDaoImpl.class);
		ArrayList<String> al=new ArrayList<String>();
		al.add("InAu3456");
		al.add("AuSr3255");
		when(roue.deleteRouteBean(al)).thenReturn(2);
		
		
		AdministratorImpl admin=new AdministratorImpl(roue);
		
		int result = admin.removeRoute(s);
		
		assertEquals(result,2);
	}

	@Test
	public void testViewByFlightId() throws SQLException {
	FlightBean sb=new FlightBean("2312","Konama",4,12);
		
		flight=mock(FlightBeanDaoImpl.class);
		
			when(flight.findByID(sb.getFlightID())).thenReturn(sb);
		
		
		AdministratorImpl admin=new AdministratorImpl(flight);
		
		FlightBean result = admin.viewByFlightId(sb.getFlightID());
		
		assertEquals(result.getFlightID(),"2312");
	}

	@Test
	public void testViewByRouteId() throws SQLException {
RouteBean rb=new RouteBean("234","kathmandu","bhutan","100",3400);
		
		roue=mock(RouteBeanDaoImpl.class);
		
		when(roue.findByID(rb.getRouteID())).thenReturn(rb);
		
		
		AdministratorImpl admin=new AdministratorImpl(roue);
		
		RouteBean result = admin.viewByRouteId(rb.getRouteID());
		
		assertEquals(result.getRouteID(),"234");
	}

	@Test
	public void testViewByAllFlights() throws SQLException {
	FlightBean sb=new FlightBean("va8529","varun kashyap",789,123);
	FlightBean sbb=new FlightBean("va8529","varun kashyap",789,123);
	ArrayList<FlightBean> sp=new ArrayList<FlightBean>();
	sp.add(sb);
	sp.add(sbb);
		flight=mock(FlightBeanDaoImpl.class);
		
			when(flight.findAll()).thenReturn(sp);
		
		
		AdministratorImpl admin=new AdministratorImpl(flight);
		
		ArrayList<FlightBean> result = admin.viewByAllFlights();
		
		assertEquals(result.size(),2);
	}

	@Test
	public void testViewByAllRoute() throws SQLException {
RouteBean rb=new RouteBean("234","kathmandu","bhutan","100",3400);
RouteBean rbb=new RouteBean("234","kathmandu","bhutan","100",3400);
		roue=mock(RouteBeanDaoImpl.class);
		
		ArrayList<RouteBean> ab=new ArrayList<RouteBean>();
		ab.add(rbb);
		ab.add(rb);
		when(roue.findAll()).thenReturn(ab);
		
		
		AdministratorImpl admin=new AdministratorImpl(roue);
		
		ArrayList<RouteBean> result = admin.viewByAllRoute();
		
		assertEquals(result.size(),2);
	}

	@Test
	public void testViewByAllSchedule() throws SQLException {
		String dobirth="29/11/2106";
		String dateOfBirth[]=dobirth.split("/");
		LocalDate dat=LocalDate.of(Integer.parseInt(dateOfBirth[2]),Integer.parseInt(dateOfBirth[1]), Integer.parseInt(dateOfBirth[0]));
		
		ScheduleBean sb=new ScheduleBean("34511","23423","1f234",dat);
		ArrayList<ScheduleBean> schbean=new ArrayList<ScheduleBean>();
		schbean.add(sb);
		schedu=mock(ScheduleBeanDaoImpl.class);
		
			when(schedu.findAll()).thenReturn(schbean);
		
		
		AdministratorImpl admin=new AdministratorImpl(flight);
		
		ArrayList<ScheduleBean> result = admin.viewByAllSchedule();
		
		assertEquals(result.size(),1);
	}

	@Test
	public void testViewByScheduleId() throws SQLException {
		String dobirth="29/11/2103";
		String dateOfBirth[]=dobirth.split("/");
		LocalDate dat=LocalDate.of(Integer.parseInt(dateOfBirth[2]),Integer.parseInt(dateOfBirth[1]), Integer.parseInt(dateOfBirth[0]));
		
		ScheduleBean sb=new ScheduleBean("345","234","1234",dat);
		
		schedu=mock(ScheduleBeanDaoImpl.class);
		
			when(schedu.findByID(sb.getScheduleID())).thenReturn(sb);
		
		
		AdministratorImpl admin=new AdministratorImpl(schedu);
		
		ScheduleBean result = admin.viewByScheduleId(sb.getScheduleID());
		
		assertEquals(result.getScheduleID(),"345");
	}

	@Test
	public void testViewPasengersByFlight() throws SQLException {
		PassengerBean rb=new PassengerBean("jkka3505","is1091","Varun",76,"Female");
		PassengerBean pb=new PassengerBean("jkka3505","is1091","Varun",76,"Female");
		
		
		String dobirth="29/11/2103";
		String dateOfBirth[]=dobirth.split("/");
		LocalDate dat=LocalDate.of(Integer.parseInt(dateOfBirth[2]),Integer.parseInt(dateOfBirth[1]), Integer.parseInt(dateOfBirth[0]));
		
		ScheduleBean sb=new ScheduleBean("345","1234","234",dat);
		
				passen=mock(PassengerBeanDaoImpl.class);
				
				ArrayList<PassengerBean> ab=new ArrayList<PassengerBean>();
				
				ab.add(rb);
				ab.add(pb);
				when(passen.findByFlight("234")).thenReturn(ab);
				
				
				AdministratorImpl admin=new AdministratorImpl(passen);
				
				ArrayList<PassengerBean> result = admin.viewPasengersByFlight("234");
				
				assertEquals(result.size(),2);
	}

}
