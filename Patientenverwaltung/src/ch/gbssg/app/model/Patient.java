package ch.gbssg.app.model;

import java.util.Date;
import java.util.Calendar;
import java.util.List;

import ch.gbssg.core.AbsModel;

/**
 * represent a Patient model.
 * @author Michael Huber
 * @version 1.0
 */
public class Patient extends AbsModel {

	private int id;
	private int genderCode;
	private String firstname;
	private String lastname;
	private Date birthday;
	private String address;
	private String plz;
	private String place;
	private String insuranceNumber;
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getGenderCode() {
		return genderCode;
	}


	public void setGenderCode(int genderCode) {
		this.genderCode = genderCode;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public Date getBirthday() {
		return birthday;
	}


	public void setBirthday(Date date) {
		this.birthday = date;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getPlz() {
		return plz;
	}


	public void setPlz(String plz) {
		this.plz = plz;
	}


	public String getPlace() {
		return place;
	}


	public void setPlace(String place) {
		this.place = place;
	}


	public String getInsuranceNumber() {
		return insuranceNumber;
	}


	public void setInsuranceNumber(String insuranceNumber) {
		this.insuranceNumber = insuranceNumber;
	}


	@Override
	public boolean isValid(List<String> errors) {
		// TODO Auto-generated method stub
		return true;
	}

}
