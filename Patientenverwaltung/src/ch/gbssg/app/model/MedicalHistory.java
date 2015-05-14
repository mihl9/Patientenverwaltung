package ch.gbssg.app.model;

import java.sql.Date;
import java.util.List;

import ch.gbssg.core.AbsModel;

public class MedicalHistory extends AbsModel{
	
	private int id;
	private int userId;
	private int patientId;
	private double hour;
	private String symptoms;
	private String diagnostic;
	private String notes;
	private int billState;
	private Date billDueTo;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public double getHour() {
		return hour;
	}

	public void setHour(double hour) {
		this.hour = hour;
	}

	public String getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}

	public String getDiagnostic() {
		return diagnostic;
	}

	public void setDiagnostic(String diagnostic) {
		this.diagnostic = diagnostic;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public int getBillState() {
		return billState;
	}

	public void setBillState(int billState) {
		this.billState = billState;
	}

	public Date getBillDueTo() {
		return billDueTo;
	}

	public void setBillDueTo(Date billDueTo) {
		this.billDueTo = billDueTo;
	}

	@Override
	public boolean isValid(List<String> errors) {
		// TODO Auto-generated method stub
		return true;
	}

}
