package com.ntl.frs.bean;

public class PassengerBean {

	private String reservationID ; 
	private  String scheduleID;
	private  String name ;
	private  int age ;
	private  String gender;
	/**
	 * 
	 */
	public PassengerBean() {
		super();
	}
	/**
	 * @param reservationID
	 * @param scheduleID
	 * @param name
	 * @param age
	 * @param gender
	 */
	public PassengerBean(String reservationID, String scheduleID, String name, int age, String gender) {
		super();
		this.reservationID = reservationID;
		this.scheduleID = scheduleID;
		this.name = name;
		this.age = age;
		this.gender = gender;
	}
	/**
	 * @return the reservationID
	 */
	public String getReservationID() {
		return reservationID;
	}
	/**
	 * @param reservationID the reservationID to set
	 */
	public void setReservationID(String reservationID) {
		this.reservationID = reservationID;
	}
	/**
	 * @return the scheduleID
	 */
	public String getScheduleID() {
		return scheduleID;
	}
	/**
	 * @param scheduleID the scheduleID to set
	 */
	public void setScheduleID(String scheduleID) {
		this.scheduleID = scheduleID;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PassengerBean [reservationID=" + reservationID + ", scheduleID=" + scheduleID + ", name=" + name
				+ ", age=" + age + ", gender=" + gender + "]";
	}
	
	
	
	
	
	
	
	
	

	
	
}