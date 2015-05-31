package ch.gbssg.app.bla.medicalHistory;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import ch.gbssg.app.model.MedicalHistory;
import ch.gbssg.core.pac.IView;
/**
 * Represents the View of the Medical History Agent
 * Loads the belonging fxml and initialize the view and all other elements
 * Handles the Events and redirect them to the controller. 
 * @author Michael Huber
 * @version 1.0
 */
public class MedicalHistoryView implements IView, Initializable{
	/**
	 * Constructor of the class
	 * @param medHisController the Controller
	 */
	public MedicalHistoryView(MedicalHistoryController medHisController) {
		controller = medHisController;
		
		// get root content
		FXMLLoader loader = new FXMLLoader(getClass().getResource("MedicalHistory.fxml"));
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
	/**
	 * sets the Error message and display it to the message box
	 * @param errors
	 */
	public void setError(List<String> errors) {
		String str = "";
		
		for (String string : errors) {
			str += string + "\n";
		}
		
		state.setText(str);
	}
	/**
	 * Fills the Data from the model into the form
	 * @param model
	 */
	public void setData(MedicalHistory model) {
		dateFrom.setValue(model.getDateFrom());
		hour.setText(""+model.getHour());
		symptoms.setText(model.getSymptoms());
		diagnostic.setText(model.getDiagnostic());
		notes.setText(model.getNotes());
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
		controller.save();
	}
	
	@FXML
	private AnchorPane root;
	
	@FXML
	private Label state;
	
	@FXML
	private DatePicker dateFrom;

	@FXML
	private TextField hour;
	
	@FXML
	private TextArea symptoms;
	
	@FXML
	private TextArea diagnostic;
	
	@FXML
	private TextArea notes;
	
	private MedicalHistoryController controller;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

	public Label getState() {
		return state;
	}

	public DatePicker getDateFrom() {
		return dateFrom;
	}

	public TextField getHour() {
		return hour;
	}

	public TextArea getSymptoms() {
		return symptoms;
	}

	public TextArea getDiagnostic() {
		return diagnostic;
	}

	public TextArea getNotes() {
		return notes;
	}

	
}
