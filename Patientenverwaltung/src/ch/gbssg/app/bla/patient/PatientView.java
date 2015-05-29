package ch.gbssg.app.bla.patient;


import java.io.IOException;
import java.util.List;

import org.apache.poi.util.StringUtil;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import ch.gbssg.app.model.Patient;
import ch.gbssg.core.pac.IView;

public class PatientView implements IView {
	public PatientView(PatientController patientController) {
		controller = patientController;
		
		// get root content
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Patient.fxml"));
		loader.setController(this);
		
		try {
			root = (AnchorPane)loader.load();
			
			// bind new pane to parent pane
			AnchorPane.setTopAnchor(root, 0.0);
			AnchorPane.setBottomAnchor(root, 0.0);
			AnchorPane.setLeftAnchor(root, 0.0);
			AnchorPane.setRightAnchor(root, 0.0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setError(List<String> errors) {
		String str = "";
		
		for (String string : errors) {
			str += string + "\n";
		}
		
		state.setText(str);
	}
	
	public void setData(Patient patient) {
		firstname.setText(patient.getFirstname());
		lastname.setText(patient.getLastname());
		birthday.setValue(patient.getBirthday());
		ahv.setText(patient.getAhv());
		plz.setText(patient.getPlz());
		place.setText(patient.getPlace());
		address.setText(patient.getAddress());
		
		this.patient = patient;
	}
	
	@Override
	public Pane getContent() {
		return root;
	}
	
	@FXML
	private void close() {
		controller.closeDialog();
	}
	
	@FXML
	private void save() {
		patient.setFirstname(firstname.getText());
		patient.setLastname(lastname.getText());
		patient.setBirthday(birthday.getValue());
		patient.setAhv(ahv.getText());
		patient.setPlz(plz.getText());
		patient.setPlace(place.getText());
		patient.setAddress(address.getText());
		
		controller.save(patient);
	}
	
	@FXML
	private AnchorPane root;

	@FXML
	private Label state;
	
	@FXML
	private TextField firstname;
	
	@FXML
	private TextField lastname;
	
	@FXML
	private DatePicker birthday;
	
	@FXML
	private TextField ahv;
	
	@FXML
	private TextField plz;
	
	@FXML
	private TextField place;
	
	@FXML
	private TextField address;

	private PatientController controller;
	private Patient patient;
}

