package ch.gbssg.app.bla.patient;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import ch.gbssg.app.model.Code;
import ch.gbssg.app.model.Patient;

public class PatientModel {
	private Patient model;
	private ObservableList<Code> codesData;
	private Scene content;
	
	public PatientModel(){
		codesData = FXCollections.observableArrayList();
		content = null;
	}
	

	public Patient getModel() {
		return model;
	}

	public void setModel(Patient model) {
		this.model = model;
	}
	
	public ObservableList<Code> getCodesData(){
		return this.codesData;
	}	
	
	public Scene getContent() {
		return content;
	}


	public void setContent(Scene content) {
		this.content = content;
	}
}
