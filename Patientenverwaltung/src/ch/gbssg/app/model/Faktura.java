package ch.gbssg.app.model;

import java.time.LocalDate;
import java.util.List;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import ch.gbssg.core.AbsModel;

public class Faktura extends AbsModel{
	/*Medical History Infos*/
	private IntegerProperty id;
	private IntegerProperty userId;
	private IntegerProperty patientId;
	private ObjectProperty<LocalDate> dateFrom;
	private DoubleProperty hour;
	private IntegerProperty billState;
	private ObjectProperty<LocalDate> billDueTo;
	/*Patient Information*/
	private IntegerProperty genderCode;
	private StringProperty firstname;
	private StringProperty lastname;
	private StringProperty address;
	private StringProperty plz;
	private StringProperty ahv;
	private StringProperty place;
	private StringProperty insuranceNumber;
	/*User Information*/
	private StringProperty usrFirstname;
	private StringProperty usrLastname;
	private DoubleProperty hourlyWage;
	
	public Faktura(){
		/*Medical History Infos*/
		id = new SimpleIntegerProperty();
		userId = new SimpleIntegerProperty();
		patientId = new SimpleIntegerProperty();
		dateFrom = new SimpleObjectProperty<LocalDate>(LocalDate.now());
		hour = new SimpleDoubleProperty();
		billState = new SimpleIntegerProperty();
		billDueTo = new SimpleObjectProperty<LocalDate>(LocalDate.now());
		/*Patient Information*/
		genderCode = new SimpleIntegerProperty();
		firstname = new SimpleStringProperty();
		lastname = new SimpleStringProperty();
		address = new SimpleStringProperty();
		plz = new SimpleStringProperty();
		ahv = new SimpleStringProperty();
		place = new SimpleStringProperty();
		insuranceNumber = new SimpleStringProperty();
		/*User Information*/
		usrFirstname = new SimpleStringProperty();
		usrLastname = new SimpleStringProperty();
		hourlyWage = new SimpleDoubleProperty();
	}
	/*Medical History Information*/
	public int getId() {
		return id.get();
	}

	public void setId(int id) {
		this.id.set(id);
	}

	public IntegerProperty getIdProperty(){
		return this.id;
	}
	
	public int getUserId() {
		return userId.get();
	}

	public void setUserId(int userId) {
		this.userId.set(userId);
	}

	public IntegerProperty getUserIdProperty(){
		return this.userId;
	}
	
	public int getPatientId() {
		return patientId.get();
	}

	public void setPatientId(int patientId) {
		this.patientId.set(patientId);
	}
	
	public IntegerProperty getPatientIdProperty(){
		return this.patientId;
	}
	
	public double getHour() {
		return hour.get();
	}

	public void setHour(double hour) {
		this.hour.set(hour);
	}

	public DoubleProperty getHourProperty(){
		return this.hour;
	}
	
	public int getBillState() {
		return billState.get();
	}

	public void setBillState(int billState) {
		this.billState.set(billState);
	}

	public IntegerProperty getBillStateProperty(){
		return this.billState;
	}
	
	public LocalDate getBillDueTo() {
		return billDueTo.get();
	}

	public void setBillDueTo(LocalDate billDueTo) {
		this.billDueTo.set(billDueTo);
	}

	public ObjectProperty<LocalDate> getBillDueToProperty(){
		return this.billDueTo;
	}
	
	public LocalDate getDateFrom() {
		return dateFrom.get();
	}

	public void setDateFrom(LocalDate dateFrom) {
		this.dateFrom.set(dateFrom);
	}
	
	public ObjectProperty<LocalDate> getDateFromProperty(){
		return this.dateFrom;
	}
	
	/*Patient Information*/
	public int getGenderCode() {
		return genderCode.get();
	}

	public void setGenderCode(int genderCode) {
		this.genderCode.set(genderCode);
	}

	public IntegerProperty getGenderCodeProperty() {
		return genderCode;
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
	
	/*User Information*/
	public String getUsrFirstname() {
		return usrFirstname.get();
	}


	public void setUsrFirstname(String usrFirstname) {
		this.usrFirstname.set(usrFirstname);
	}

	public StringProperty getUsrFirstnameProperty() {
		return usrFirstname;
	}
	
	public String getUsrLastname() {
		return usrLastname.get();
	}


	public void setUsrLastname(String usrLastname) {
		this.usrLastname.set(usrLastname);
	}

	public StringProperty getUsrLastnameProperty() {
		return usrLastname;
	}
	
	public double getHourlyWage() {
		return hourlyWage.get();
	}


	public void setHourlyWage(double hourlyWage) {
		this.hourlyWage.set(hourlyWage);
	}

	public DoubleProperty getHourlyWageProperty() {
		return hourlyWage;
	}
	
	@Override
	public boolean isValid(List<String> errors) {
		// TODO Auto-generated method stub
		return true;
	}

	

}
