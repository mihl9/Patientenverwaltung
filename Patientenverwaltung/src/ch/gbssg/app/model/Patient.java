package ch.gbssg.app.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import ch.gbssg.core.AbsModel;

/**
 * represent a Patient model.
 * @author Michael Huber
 * @version 1.0
 */
public class Patient extends AbsModel {

	private int id;
	private int genderCode;
	private StringProperty firstname;
	private StringProperty lastname;
	private ObjectProperty<LocalDate> birthday;
	private StringProperty address;
	private StringProperty plz;
	private StringProperty ahv;
	private StringProperty place;
	private StringProperty insuranceNumber;
	
	public Patient() {
		firstname = new SimpleStringProperty();
		lastname = new SimpleStringProperty();
		address = new SimpleStringProperty();
		plz = new SimpleStringProperty();
		ahv = new SimpleStringProperty();
		place = new SimpleStringProperty();
		insuranceNumber = new SimpleStringProperty();
		birthday = new SimpleObjectProperty<LocalDate>(LocalDate.now());
	}
	
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
		return firstname.get();
	}


	public void setFirstname(String firstname) {
		this.firstname.set(firstname);
	}

	public StringProperty getFirstnameProperty() {
		return firstname;
	}

	public String getLastname() {
		return lastname.get();
	}


	public void setLastname(String lastname) {
		this.lastname.set(lastname);
	}

	public StringProperty getLastnameProperty() {
		return lastname;
	}

	public LocalDate getBirthday() {
		return birthday.get();
	}


	public void setBirthday(LocalDate birthday) {
		this.birthday.set(birthday);
	}


	public String getAddress() {
		return address.get();
	}


	public void setAddress(String address) {
		this.address.set(address);
	}

	public StringProperty getAddressProperty() {
		return address;
	}
	
	public String getAhv() {
		return ahv.get();
	}
	
	public void setAhv(String ahv) {
		this.ahv.set(ahv);
	}
	
	public StringProperty getAhvProperty() {
		return ahv;
	}

	public String getPlz() {
		return plz.get();
	}


	public void setPlz(String plz) {
		this.plz.set(plz);
	}

	public StringProperty getPlzProberty() {
		return plz;
	}

	public String getPlace() {
		return place.get();
	}


	public void setPlace(String place) {
		this.place.set(place);
	}

	public StringProperty getPlaceProperty() {
		return place;
	}

	public String getInsuranceNumber() {
		return insuranceNumber.get();
	}


	public void setInsuranceNumber(String insuranceNumber) {
		this.insuranceNumber.set(insuranceNumber);
	}

	public StringProperty getInsuranceNumberProperty() {
		return insuranceNumber;
	}

	@Override
	public boolean isValid(List<String> errors) {
		// TODO Auto-generated method stub
		return true;
	}

}
