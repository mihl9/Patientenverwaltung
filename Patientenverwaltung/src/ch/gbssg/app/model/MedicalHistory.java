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

public class MedicalHistory extends AbsModel{
	
	private IntegerProperty id;
	private IntegerProperty userId;
	private IntegerProperty patientId;
	private ObjectProperty<LocalDate> dateFrom;
	private DoubleProperty hour;
	private StringProperty symptoms;
	private StringProperty diagnostic;
	private StringProperty notes;
	private IntegerProperty billState;
	private ObjectProperty<LocalDate> billDueTo;
	
	public MedicalHistory(){
		id = new SimpleIntegerProperty();
		userId = new SimpleIntegerProperty();
		patientId = new SimpleIntegerProperty();
		dateFrom = new SimpleObjectProperty<LocalDate>(LocalDate.now());
		hour = new SimpleDoubleProperty();
		symptoms = new SimpleStringProperty();
		diagnostic = new SimpleStringProperty();
		notes = new SimpleStringProperty();
		billState = new SimpleIntegerProperty();
		billDueTo = new SimpleObjectProperty<LocalDate>(LocalDate.now());
	}
	
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
	
	public String getSymptoms() {
		return symptoms.get();
	}

	public void setSymptoms(String symptoms) {
		this.symptoms.set(symptoms);
	}

	public StringProperty getSymptomsProperty(){
		return this.symptoms;
	}
	
	public String getDiagnostic() {
		return diagnostic.get();
	}

	public void setDiagnostic(String diagnostic) {
		this.diagnostic.set(diagnostic);
	}

	public StringProperty getDiagnosticProperty(){
		return this.diagnostic;
	}
	public String getNotes() {
		return notes.get();
	}

	public void setNotes(String notes) {
		this.notes.set(notes);
	}

	public StringProperty getNotesProperty(){
		return this.notes;
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
	
	@Override
	public boolean isValid(List<String> errors) {
		// TODO Auto-generated method stub
		return true;
	}

	

}
