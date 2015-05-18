package ch.gbssg.app.ila.doctor;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import ch.gbssg.app.model.Patient;
import ch.gbssg.core.pac.IView;

/**
 * view for doctor agent
 * 
 * @author s.pedrett
 * @version 1.0
 */
public class DoctorView implements IView {

	/**
	 * Create a new instance of a DoctorView.
	 * 
	 * @param controller
	 *            send all events to this controller
	 * @param model
	 *            represent data for this agent
	 */
	public DoctorView(DoctorController controller, DoctorModel model) {
		this.controller = controller;
		this.model = model;

		// load fxml from file and set controller
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Doctor.fxml"));
			loader.setController(this);
			root = (AnchorPane) loader.load();

			// bind new pane to parent pane
			AnchorPane.setTopAnchor(root, 0.0);
			AnchorPane.setBottomAnchor(root, 0.0);
			AnchorPane.setLeftAnchor(root, 0.0);
			AnchorPane.setRightAnchor(root, 0.0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		patientTable.setItems(model.getPatientsData());
	}

	@FXML
	private void initialize() {
		firstnameCol.setCellValueFactory(cellData -> cellData.getValue().getFirstnameProperty());
		lastnameCol.setCellValueFactory(cellData -> cellData.getValue().getLastnameProperty());
		ahvCol.setCellValueFactory(cellData -> cellData.getValue().getAhvProperty());
		
		showPatientDetails(null);
		
		// register listener for selection new patient in table
		patientTable.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> showPatientDetails(newValue)
		);
	}

	/**
	 * Fills all labels to show details about the patient. If the specified
	 * patient is null, all text vield are cleared.
	 * 
	 * @param patient
	 *            to show details
	 */
	private void showPatientDetails(Patient patient) {
		if (patient == null) {
			firstname.setText("");
			lastname.setText("");
			birthday.setText("");
			ahv.setText("");
			place.setText("");
			plz.setText("");
			address.setText("");
		} else {
			firstname.setText(patient.getFirstname());
			lastname.setText(patient.getLastname());
			birthday.setText(patient.getBirthday().toString());
			ahv.setText(patient.getAhv());
			place.setText(patient.getPlace());
			plz.setText(patient.getPlz());
			address.setText(patient.getAddress());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.gbssg.core.pac.IView#getContent()
	 */
	@Override
	public Pane getContent() {
		return root;
	}

	/**
	 * controller for this view
	 */
	private DoctorController controller;
	private DoctorModel model;

	@FXML
	private AnchorPane root;
	
	@FXML
	private TableView<Patient> patientTable;
	
	@FXML
	private TableColumn<Patient, String> firstnameCol;
	
	@FXML
	private TableColumn<Patient, String> lastnameCol;
	
	@FXML
	private TableColumn<Patient, String> ahvCol;
	
	@FXML
	private Label firstname;
	
	@FXML
	private Label lastname;
	
	@FXML
	private Label birthday;
	
	@FXML
	private Label ahv;
	
	@FXML
	private Label plz;
	
	@FXML
	private Label place;
	
	@FXML
	private Label address;
}
