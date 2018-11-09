package com.ntl.frs.client;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.ntl.frs.bean.CredentialsBean;
import com.ntl.frs.bean.FlightBean;
import com.ntl.frs.bean.PassengerBean;
import com.ntl.frs.bean.PaymentBean;
import com.ntl.frs.bean.ProfileBean;
import com.ntl.frs.bean.ReservationBean;
import com.ntl.frs.bean.RouteBean;
import com.ntl.frs.bean.ScheduleBean;
import com.ntl.frs.dao.impl.ReservationBeanDaoImpl;
import com.ntl.frs.dao.impl.RouteBeanDaoImpl;
import com.ntl.frs.dao.impl.ScheduleBeanDaoImpl;
import com.ntl.frs.service.Administrator;
import com.ntl.frs.service.impl.AdministratorImpl;
import com.ntl.frs.service.impl.CustomerImpl;
import com.ntl.frs.util.impl.LoggedIn;
import com.ntl.frs.util.impl.PaymentImpl;
import com.ntl.frs.util.impl.SignedUp;




public class FlightMain {
	static String reserveId;
	static Scanner sc=new Scanner(System.in);
	//BasicConfigurator.configure();
	LoggedIn log=new LoggedIn();
	static double tfare=0.0;
	CredentialsBean sign=new CredentialsBean();
	ProfileBean profile=new ProfileBean();
	SignedUp signup=new SignedUp();
	AdministratorImpl admin=new AdministratorImpl();
	CustomerImpl cust=new CustomerImpl();
	static CredentialsBean credit=null;
	PaymentImpl pay=new PaymentImpl();
	RouteBeanDaoImpl rt=new RouteBeanDaoImpl();
	ScheduleBeanDaoImpl st=new ScheduleBeanDaoImpl();
	ReservationBeanDaoImpl re=new ReservationBeanDaoImpl();
	
	static Logger logger= Logger.getLogger(FlightMain.class);
	
	private static void init() {
		//DOMConfigurator.configure("myapp-log4j.xml");
		// OR for property file, should use any one of these
		PropertyConfigurator.configure("myapp-log4j.properties");
	}
	
	public static void main(String z[]) throws ClassNotFoundException, IOException, SQLException
	{
		System.out.println("Welcome the Flight Reservation System");
		boolean val=true;
		while(val) {
		FlightMain userid=new FlightMain();
		val=userid.getData();
		}
	}
	
	public boolean getData() throws IOException, ClassNotFoundException, SQLException
	{    
		
		logger.info("it will check whether it is working or not");
		FlightMain user=new FlightMain();
		System.out.println("Are you an existing User? ");
		System.out.println("yes");
		System.out.println("no");
		System.out.println("exit");
		
		String choose=sc.nextLine();
		if(choose.equalsIgnoreCase("yes"))
		{
			return user.getLogin();
			
		}
		else if(choose.equalsIgnoreCase("no")) {
			return user.getSignup();
			
		}
		else {
			
			System.out.println("Do you you really want to proceed further ?");
			String fin=sc.nextLine();
			if(fin.equalsIgnoreCase("yes"))
			System.exit(0);
			else {
				return true;
			}
		}
		
		return false;
	}
	
	
	public boolean getLogin() throws ClassNotFoundException, IOException, SQLException
	{ 
		
		logger.info("Logging in");
		FlightMain use=new FlightMain();
		
		System.out.println(" Please Enter your  User Id");
		String lid=sc.nextLine();
	
		System.out.println("Enter your password");
		String pass=sc.nextLine();
	
		credit=new CredentialsBean(lid,pass);
		
		String result=signup.login(credit);
		//System.out.println(result);
		if(result.equals("A"))
		{
			System.out.println("Hiii admin");
			boolean admn=true;
			String activity="";
			while(admn)
			{
				System.out.println("DO you want to proceed  any activity further ?");
				System.out.println("yes/no/exit");
				 activity=sc.nextLine();
				if(activity.equalsIgnoreCase("yes"))
				{
					
					
					System.out.println("Which entity you want to alter");
					System.out.println("Flight/Route/Schedule");
					String field=sc.nextLine();
					if(field.equalsIgnoreCase("flight"))
					{
						ArrayList<FlightBean> alflight=new ArrayList<FlightBean>();
						alflight=admin.viewByAllFlights();
						for(FlightBean shi:alflight)
						{
							System.out.println(shi);
						}
						System.out.println("\n");
						System.out.println("Enter the acitvity(add/modify)");
						String taskflight=sc.nextLine();
						if(taskflight.equalsIgnoreCase("add"))
						{
							if(use.flightAdd())
								System.out.println("Added successfully");
							admn=true;
						}
						else if(taskflight.equalsIgnoreCase("modify")) {
							if(use.flightModify())
								System.out.println("Modified successfully");
							else {
								System.out.println("Can not modify");
							}
							admn=true;
						}
						else {
							System.out.println("Something wrong");
							admn=true;
						}
						
					}
					else if(field.equalsIgnoreCase("Route")){
						
						ArrayList<RouteBean> alroute=new ArrayList<RouteBean>();
						alroute=admin.viewByAllRoute();
						for(RouteBean shi:alroute)
						{
							System.out.println(shi);
						}
						System.out.println("\n");
						
						
						System.out.println("Enter the acitvity");
						System.out.println("add/modify/delete");
						String taskflight=sc.nextLine();
						if(taskflight.equalsIgnoreCase("add"))
						{
							if(use.routeAdd())
							{
								System.out.println("Added successfully");
							}
							else {
								System.out.println("Something Wrong");
							}
							admn=true;
						}
						else if(taskflight.equalsIgnoreCase("modify")) {
							if(use.routeModify())
								System.out.println("Modified successfully");
							else {
								System.out.println("Can not modify");
							}
							admn=true;
						}
						else if(taskflight.equalsIgnoreCase("delete")) {
							if(use.routeDelete())
								System.out.println("deleted successfully");
							else {
								System.out.println("something Wrong");
							}
							admn=true;
						}
						else {
							System.out.println("something Wrong ");
							admn=true;
						}
						
					}
					else if(field.equalsIgnoreCase("Schedule")){
						ArrayList<ScheduleBean> alSchedule=new ArrayList<ScheduleBean>();
						alSchedule=admin.viewByAllSchedule();
						for(ScheduleBean shi:alSchedule)
						{
							System.out.println(shi);
						}
						System.out.println("\n");
						System.out.println("enter the specific activity");
						System.out.println("add/modify/delete");
						String taskflight=sc.nextLine();
						if(taskflight.equalsIgnoreCase("add"))
						{
							if(use.scheduleAdd())
							{
								System.out.println("Added Successfully");
							}
							else {
								System.out.println("Something Wrong");
							}
							admn=true;
						}
						else if(taskflight.equalsIgnoreCase("modify")) {
							if(use.scheduleModify())
								System.out.println("Modified successfully");
							
							else {
								System.out.println("Something  goes wrong");
								
							}
							admn=true;
						}
						else if(taskflight.equalsIgnoreCase("delete")) {
							if(use.scheduleDelete())
							{
								System.out.println("Deleted successfully");
								
							}
							else {
								System.out.println("Something Wrong");
								
							}
							admn=true;
						}
						else {
							System.out.println("Something wrong input");
							admn=true;
						}
						
					}
				else{
					System.out.println("Something Wrong input");
					admn=true;
				}
				}
				else if(activity.equalsIgnoreCase("no"))  {
					System.out.println("Gracias !! ");
					admn=false;
					signup.logout(credit.getUserID());
				}
				else if(activity.equalsIgnoreCase("Change"))
				{
					use.changingPassword(credit);
					admn=true;
				}
				else if(activity.equalsIgnoreCase("out"))
				{
					admn=false;
					signup.logout(credit.getUserID());
				}
				else {
					admn=true;
				}
			}
		}
		else if(result.equals("C"))
		{
			System.out.println("Hiii  customer");
			boolean custm=true;
			while(custm) {
			System.out.println("Ticket Reservation Functions");
			System.out.println("reserve/cancel/payment/out");
			String ticket=sc.nextLine();
			if(ticket.equalsIgnoreCase("reserve"))
			{
				reserveId=use.reserveTicket();
				custm=true;
			}
			else if(ticket.equalsIgnoreCase("cancel"))
			{
				System.out.println("Enter your flight Reservation Id");
				String rrid=sc.nextLine();
				
				cust.cancelTicket(rrid);
				custm=true;
			}
			else if(ticket.equalsIgnoreCase("payment"))
			{
				System.out.println("Enter the flight  reservation Id");
				String reId=sc.nextLine();
				
				if(use.paymentCredit())
				{
					ReservationBean reser=new ReservationBean();
					reser=re.findByID(reId);
					cust.changeBookingStatus(reser);
					ArrayList<PassengerBean> ans=new ArrayList<PassengerBean>();
					 ans=use.passengerInput(reId,reser.getNoOfSeats());
					 System.out.println(reId);
					if(ans!=null)
					{
					cust.addingPassengers(ans);
					}
				}
				custm=true;
			}
			else if(ticket.equalsIgnoreCase("view"))
			{
				System.out.println("Enter the flight reservation Id");
				String rrid=sc.nextLine();
				
				Map<ReservationBean,PassengerBean> map = new HashMap<ReservationBean,PassengerBean>();
				map=cust.viewTicket(rrid);
				
				 for ( ReservationBean key : map.keySet() )
			        {
					 for(PassengerBean val:map.values())
					 {
						 System.out.println(key.getReservationID()+" "+key.getTotalFare()+" "+key.getJourneyDate()+" "+val.getName());
					 }
					 
			            //System.out.println( map.get( key ) );
			        }
				 custm=true;
			}
			else if(ticket.equalsIgnoreCase("out"))
			{
				custm=false;
				signup.logout(credit.getUserID());
			}
			else {
				custm=true;
			}
			}
		}
		else if(result.equals("fail"))
		{
			System.out.println(" Already logged in!");
		}
		else {
			System.out.println("No such Kind of User Exists");
		}
		return true;
		
	}
		
	public boolean getSignup() throws IOException, ClassNotFoundException
	{
		
		logger.info("Signing Up");
		System.out.println("Enter information");
		
		System.out.println("Enter your Firstname");
		String fname=sc.nextLine();
		
		
		System.out.println("Enter your Lastname");
		String lname=sc.nextLine();
		
		
		boolean toCheckDate=true;
		String dobirth=null;
		while(toCheckDate) {
		System.out.println("Date of Birth in format (dd/mm/yyyy)");
		 dobirth=sc.nextLine();
		 if(Pattern.matches("(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)",dobirth))
		 {
			 toCheckDate=false;
		 }
		}
		String dateOfBirth[]=dobirth.split("/");
		LocalDate dob=LocalDate.of(Integer.parseInt(dateOfBirth[2]),Integer.parseInt(dateOfBirth[1]), Integer.parseInt(dateOfBirth[0]));
		
		
		System.out.println("Enter your gender");
		String gender=sc.nextLine();
		
		
		System.out.println("Enter your Street no or address");
		String street=sc.nextLine();
	
		
		System.out.println("Enter your  Location");
		String location=sc.nextLine();

		
		System.out.println("Enter city");
		String city=sc.nextLine();
	
		
		System.out.println("Enter state");
		String state=sc.nextLine();
	
		
		String pincode=null;
		boolean toCheckPincode=true;
		while(toCheckPincode) {
		System.out.println("Enter Pincode");
		 pincode=sc.nextLine();
		 if(Pattern.matches("[0-9]{6}", pincode)) {
			 toCheckPincode=false;
		 }
		}
	
		
		String mobile=null;
		boolean toCheckMobile=true;
		while(toCheckMobile) {
		System.out.println("Enter  your Contact-No");
		mobile=sc.nextLine();
		if(Pattern.matches("[7-9][0-9]{9}", mobile)) {
			toCheckMobile=false;
			}
		}
	
		String email=null;
		boolean toCheckEmail=true;
		 String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$"; 
		while(toCheckEmail) {
		System.out.println("Enter your mail credentials");
		 email=sc.nextLine();
		
		 if(Pattern.matches(emailRegex,email)) {
			 toCheckEmail=false;
		 }
		}
	//	profile.setEmailID(email);
		String pass=null;
		boolean toCheckPassword=true;
		while(toCheckPassword) {
		System.out.println("Enter your  Password in right format");
		 pass=sc.nextLine();
		 if(Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", pass)) {
			 toCheckPassword=false;
		 }
		}
		//profile.setPassword(pass);
		
		
		Random rand = new Random();
			String regid=fname.substring(0, 2)+String.format("%04d", rand.nextInt(10000));
			System.out.println("Please NOTE UR UNIQUE ID: "+regid);
		
		ProfileBean profileBean=new ProfileBean(regid,fname,lname,dob,gender,street,location,city,state,pincode,mobile,email,pass);
		
		
		String registration=signup.register(profileBean) ;
		if(registration!=null)
		{
			System.out.println("Registration Successfull");
		}
		else {
			System.out.println("Something Wrong");
		}
			
	return true;
	
	}
	
	public boolean changingPassword(CredentialsBean cb) throws IOException, ClassNotFoundException
	{     
		logger.info("Changing Password");
		System.out.println("Enter your  previous password");
		String passwd=sc.nextLine();
		//profile.setGender(gender);
		
		String pass=null;
		boolean toCheckPass=true;
		while(toCheckPass) {
		System.out.println("Enter your  new password ");
		 pass=sc.nextLine();
		 if(Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", pass)) {
			 toCheckPass=false;
		 }
		}
	//	profile.setStreet(street);
		
		System.out.println("Confirm your  password");
		String cnfpass=sc.nextLine();
//		profile.setLocation(location);
		
		if(cb.getPassword().equals(passwd) && pass.equals(cnfpass))
		{
			cb.setPassword(pass);
			String str=signup.changePassword(cb,pass);
			
			if(str!=null)
			{
				System.out.println("Password changed for user"+str);
			}
			else {
				System.out.println("Unsuccessful..Something is wrong");
			}
		}
		else {
			System.out.println("Something is not correct");
		}
		return true;
	}
	
	public boolean flightAdd() throws SQLException
	{
		
		logger.info("Addind flight");
		System.out.println("enter the Flight Name to want to book");
		String flightName=sc.nextLine();
	
		
		System.out.println("Enter Seating Capacity of that flight");
		int seatCap=sc.nextInt();

		
		System.out.println("Enter Reservation Capacity of that flight");
		int resCap=sc.nextInt();
	
		
		
		
		
		FlightBean flight=new FlightBean(flightName,seatCap,resCap);
		Random rand = new Random();
		flight.setFlightID(flight.getFlightName().substring(0, 2)+String.format("%04d", rand.nextInt(10000)));
		System.out.println("please NOTE UR UNIQUE ID: "+flight.getFlightID());
		
		if(admin.addFlight(flight)!=null) {
			
			return true;
		}
		return false;
	}

	public boolean flightModify() throws SQLException
	{ 
		
		logger.info("Modify theflights");
		System.out.println("enter Flight ID you wanna modify");
		String flightId=sc.nextLine();
		FlightBean flight=admin.viewByFlightId(flightId);
		
		if(flight!=null) {
			
		System.out.println("enter FLight Name of that flight");
		String flightName=sc.nextLine();
		flight.setFlightName(flightName);
		
		System.out.println("Enter Seating Capacity of that flight");
		int seatCap=sc.nextInt();
		flight.setSeatingCapacity(seatCap);
		
		System.out.println("Enter Reservation Capacity of that flight");
		int resCap=sc.nextInt();
		flight.setReservationCapacity(resCap);
		
		if(admin.modifyFlight(flight)) {
			
			return true;
			}
		
		}
		else {
			System.out.println("No such id exists!");
			
		}
		return false;
	}
	
	public boolean scheduleAdd() throws SQLException
	{
		
		logger.info("Adding the schedules for flights");
		System.out.println("enter Flight Id of flight");
		String flightId=sc.nextLine();
	
		
		System.out.println("enter RouteId of th flight");
		String RouteId=sc.nextLine();

		
		
		System.out.println("Starting Date in format (dd/mm/yyyy)");
		String stdate=sc.nextLine();
		String startd[]=stdate.split("/");
		LocalDate startDate=LocalDate.of(Integer.parseInt(startd[2]),Integer.parseInt(startd[1]), Integer.parseInt(startd[0]));
	
	
		
		ScheduleBean sched=new ScheduleBean(RouteId,flightId,startDate);
		
		AdministratorImpl ad=new AdministratorImpl();
		Random rand = new Random();
		RouteBean rot=ad.viewByRouteId(sched.getRouteID());
		sched.setScheduleID(rot.getSource().substring(0, 2)+rot.getDestination().substring(0, 2)+String.format("%04d", rand.nextInt(10000)));
		System.out.println(" NOTE UR UNIQUE ID: "+sched.getScheduleID());
	
		if(admin.addSchedule(sched)!=null) {
			return true;
		}
		return false;
	}
	
	public boolean scheduleModify() throws SQLException
	{ 
		
		logger.info("Modifying the flights");
		System.out.println("Enter Schedule ID you wanna modify");
		String scheduleId=sc.nextLine();
		ScheduleBean sched=admin.viewByScheduleId(scheduleId);
		
		if(sched!=null) {
		System.out.println("Enter your FlightId");
		String flightId=sc.nextLine();
		FlightBean sbean=admin.viewByFlightId(flightId);
		if(sbean!=null)
		sched.setFlightID(flightId);
		else {
			System.out.println("This flight Id doesnot match");
			return true;
		}
		
		System.out.println("Enter Route Id");
		String routeId=sc.nextLine();
		RouteBean rbean=admin.viewByRouteId(routeId);
		if(rbean!=null)
		{
			sched.setRouteID(routeId);
		}
		else {
			System.out.println("This route-Id doesnot match");
			return true;
		}
		
		
		System.out.println("Starting Date in format (dd/mm/yyyy)");
		String stdate=sc.nextLine();
		String startd[]=stdate.split("/");
		LocalDate startDate=LocalDate.of(Integer.parseInt(startd[2]),Integer.parseInt(startd[1]), Integer.parseInt(startd[0]));
		sched.setStartDate(startDate);
		
		if(admin.modifySchedule(sched)) {
			
			return true;
		}}
		else {
			System.out.println("no such id exists");
		}
		return false;
	}
	
	public boolean scheduleDelete() throws SQLException
	{    
		
		
		logger.info("deleting the flights");
		ArrayList<String> al=new ArrayList<String>();
		System.out.println(" If you Want to delete any schedule?");
		String reply=sc.nextLine();
		while(reply.equalsIgnoreCase("yes"))
		{
			System.out.println("Enter ScheduleId you want to delete");
			String schedId=sc.nextLine();
			if(st.findByID(schedId)==null)
			{
				System.out.println("No such id exists!");
				return false;
			}
			al.add(schedId);
			System.out.println("If you want to delete more entities?");
			reply=sc.nextLine();
		}
	
		if(al!=null) {
		
		if(admin.removeSchedule(al)!=0) {
			return true;
		}
	
	}
	else {
		System.out.println("No item selected");
	}
		return false;
	}
	public boolean routeAdd() throws SQLException
	{
		
		logger.info("adding the route");
		System.out.println("Enter the source");
		String source=sc.nextLine();
	
		
		System.out.println("Enter  the destination");
		String dest=sc.nextLine();

		
		System.out.println("Enter Travel-Distance");
		String travelDist=sc.nextLine();
	
		System.out.println("Enter Fare");
		int fare=sc.nextInt();
		
		RouteBean rot=new RouteBean(source,dest,travelDist,fare);
		Random rand = new Random();
		rot.setRouteID(rot.getSource().substring(0, 2)+rot.getDestination().substring(0, 2)+String.format("%04d", rand.nextInt(10000)));
		System.out.println("please NOTE UR UNIQUE ID: "+rot.getRouteID());
		
		if(admin.addRoute(rot)!=null) {
			
			return true;
		}
		return false;
	}
	
	public boolean routeModify() throws SQLException
	{    
		
		
		logger.info("modify the flight");
		System.out.println("Enter route ID you want to modify");
		String routeId=sc.nextLine();
		RouteBean rot=admin.viewByRouteId(routeId);
		
		if(rot!=null) {
		System.out.println("Enter source");
		String source=sc.nextLine();
		rot.setSource(source);
		
		System.out.println("Enter destination");
		String dest=sc.nextLine();
		rot.setDestination(dest);

	
	
		System.out.println("Enter fare");
		int fare=sc.nextInt();
		rot.setFare(fare);
		
		if(admin.modifyRoute(rot)) {
			
			return true;
		}
		
		}
		else {
			System.out.println("Id is wrong");
		}
		return false;
	}
	
	public boolean routeDelete() throws SQLException
	{   
		
		
		logger.info("deleting the route");
		String id="";
		System.out.println("If you Want to delete any schedule?");
		String reply=sc.nextLine();
		while(reply.equalsIgnoreCase("yes"))
		{
			System.out.println("enter routeId to want to delete");
			String routeId=sc.nextLine();
			if(rt.findByID(routeId)==null)
			{
				System.out.println("No such id exists!");
				return false;
			}
			id+=routeId;
			System.out.println(" if you Want to  delete more entities ?");
			reply=sc.nextLine();
		}
	
		if(id!="") 
		{
			
			if(admin.removeRoute(id)!=0) 
			{
				return true;
				
			}
			
		}
		else {
			System.out.println("No item selected");
		}
		return false;
	}
	
	
	public boolean allflights() throws SQLException
	{
		
		logger.info("View all  the flights");
		ArrayList<FlightBean> al=admin.viewByAllFlights();
		for(FlightBean i:al)
		{
			System.out.println(i.getFlightID()+" "+i.getFlightName()+" "+i.getSeatingCapacity()+" "+i.getReservationCapacity());
		}
		return true;
	}
	
	public boolean allRoutes() throws SQLException
	{  
		logger.info("View all Routes");
		ArrayList<RouteBean> al=admin.viewByAllRoute();
		for(RouteBean i:al)
		{
			System.out.println(i.getRouteID()+" "+i.getSource()+" "+i.getDestination()+" "+i.gettravelDuration()+" "+i.getFare());
		}
		return true;
	}
	
	public boolean allSchedule() throws SQLException
	{     logger.info("View all Schedules");
		ArrayList<ScheduleBean> al=admin.viewByAllSchedule();
		for(ScheduleBean i:al)
		{
			System.out.println(i.getScheduleID()+" "+i.getFlightID()+" "+i.getRouteID()+" "+i.getStartDate());
		}
		return true;
	}
	
	public String reserveTicket() throws SQLException 
	{    
		
		logger.info("Booking the Tickets");
		FlightMain spp=new FlightMain();
		ArrayList<ScheduleBean> alsche=new ArrayList();
		int flag=0;
		int count=0;
		System.out.println("Enter the source");
		String src=sc.nextLine();
		
		System.out.println("Enter the destination");
		String dest=sc.nextLine();
		
		System.out.println("Enter  the date");
		String date=sc.nextLine();
		String sdate[]=date.split("/");
		LocalDate startDate=LocalDate.of(Integer.parseInt(sdate[2]),Integer.parseInt(sdate[1]),Integer.parseInt(sdate[0]));
		
		
		
		
		alsche=cust.viewScheduleByRoute(src, dest, startDate);
		System.out.println(alsche);
		for(ScheduleBean sb:alsche)
		{
			System.out.println(sb.getScheduleID()+" route: "+sb.getRouteID()+" flight:"+sb.getFlightID());
		}
		
		System.out.println("Enter the Flights Id for details: ");
		String sid=sc.nextLine();
		
		for(ScheduleBean sb:alsche)
		{
			if(sb.getFlightID().equals(sid) )
			{
				flag=1;
				FlightBean flightb =new FlightBean();
				flightb=admin.viewByFlightId(sid);
				if( flightb!=null)
				{
					System.out.println("Name:"+flightb.getFlightName()+" seats:"+flightb.getSeatingCapacity()+" reservationCapacity:"+flightb.getReservationCapacity());
					break;
				}
				else{
					System.out.println("No such Flight exits");	
				}
			}
			
		}
		if(flag==0)
		{
		
				System.out.println("This flight is not for requested route");
			
		}
		
		
		System.out.println("Enter the route Id  ");
		String rid=sc.nextLine();
		
		for(ScheduleBean sb:alsche)
		{
			if(sb.getRouteID().equals(rid) )
			{
				count=1;
				RouteBean routeb =new RouteBean();
				routeb=admin.viewByRouteId(rid);
				if( routeb!=null)
				{
					System.out.println("Source:"+routeb.getSource()+" Destination:"+routeb.getDestination()+" travelDuration(in hrs):"+routeb.gettravelDuration()+" Fare(in INR):"+routeb.getFare());
					break;
				}
				else{
					System.out.println("No such route exits");
				}
				
			}
			
		}
		if(count==0)
		{
		
				System.out.println("This route is not for requested  route");
			
		}
		
		ReservationBean reservebn=new ReservationBean();
		ArrayList<PassengerBean> ap=new ArrayList<PassengerBean>();
		
		
		System.out.println(" Enter ScheduleId to reserve");
		String rsId=sc.nextLine();

		ScheduleBean schbean=new ScheduleBean();
		schbean=admin.viewByScheduleId(rsId);
		RouteBean rtbn=new RouteBean();
		rtbn=admin.viewByRouteId(schbean.getRouteID());
		if(schbean!=null)
		{
		System.out.println("No. Of seats");
		int seats=sc.nextInt();
		
		Random rand = new Random();
		String reserid=rtbn.getSource().substring(0, 2)+rtbn.getDestination().substring(0, 2)+String.format("%04d", rand.nextInt(10000));
		System.out.println("please NOTE UR UNIQUE ID: "+reserid);
			
		if(seats>0) {
			
			
			
			 tfare=seats*rtbn.getFare();
			 reservebn=new ReservationBean(reserid,rsId,credit.getUserID(),LocalDate.now(),schbean.getStartDate(),seats,tfare,"pending");
			 
			 
			 
	//FlightMain us=new FlightMain();
//=us.passengerInput(rsId,seats);
			 
	
			// if(spp.paymentCredit())
		//	 {
			//	 System.out.println("payment Done!"+reservebn.getNoOfSeats());
			//.changeBookingStatus(reservebn);
			// }
			
			
		}
		else {
			System.out.println("No seats are booked!");
		}
		
		}
		else {
			System.out.println("No Such  id exists");
		}
		
		cust.reserveTicket(reservebn, ap);
		return reservebn.getReservationID();
		
	}
	
	public boolean paymentCredit() throws SQLException
	{     
		
		logger.info("Payment details");
		System.out.println("Want to pay for the booking? yes/no");
		String payes=sc.nextLine();
		if(payes.equalsIgnoreCase("yes"))
		{
			System.out.println("enter your Card details ");
			String card=sc.nextLine();
			
			if(pay.findByCardNumber(credit.getUserID(),card)) {
			
				System.out.println("Gracias  !!!");
			}
			else {
				System.out.println("you need to Enter details");
				
				
				System.out.println("Card validity from");
				String validate=sc.nextLine();
				
				
				System.out.println("card valid to");
				String todate=sc.nextLine();
				
				System.out.println("Balance you have to pay");
				int bal=sc.nextInt();
				
				
				PaymentBean paymentb=new PaymentBean(card,validate,todate,bal,credit.getUserID());
				pay.process(paymentb);
				
			}
			return true;
			
		}
		else {
			System.out.println("your confirmation is still pending!//not conmfirmed");
			return false;
		}
	}

	public ArrayList<PassengerBean> passengerInput(String rsId,int seats)
	{     
		
		logger.info("Passenger Details");
		ArrayList<PassengerBean> ap=new ArrayList<PassengerBean>();
		 for(int j=0;j<seats;j++)
			{
				System.out.println("enter details for passenger "+(j+1));
				
				System.out.println("enter name");
				sc.nextLine();
				String pname=sc.nextLine();
				
				System.out.println("enter age"); 
				int page=sc.nextInt();
				
				System.out.println("enter gender");
				sc.nextLine();
				String pgender=sc.nextLine();
				
				PassengerBean pb=new PassengerBean(rsId,pname,pgender, page,pgender);
				ap.add(pb);
				
			}	
	
		 return ap;
		 
	}
	
}


