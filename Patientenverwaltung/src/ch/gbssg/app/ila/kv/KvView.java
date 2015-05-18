package ch.gbssg.app.ila.kv;


import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Observable;
import java.util.ResourceBundle;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import ch.gbssg.app.model.MedicalHistory;
import ch.gbssg.app.model.Patient;
import ch.gbssg.core.pac.IView;

public class KvView implements IView, Initializable{

	public KvView(KvController controller){
		this.controller = controller;
		
		// get root content
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Kv.fxml"));
		loader.setController(this);
		
		try {
			root = (AnchorPane)loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		dateFromCol.setCellValueFactory(new PropertyValueFactory<MedicalHistory, java.util.Date>("dateFrom"));
		
		/*firstnameCol.setCellValueFactory(new PropertyValueFactory<S, T>("firstname"));*/
		billStateCol.setCellValueFactory(new PropertyValueFactory<MedicalHistory, Integer>("billstate"));
	}
	
	/**
	 * controller for this view
	 */
	private KvController controller;

	@Override
	public Pane getContent() {
		if(root != null){
			return root;
		}
		return null;
	}
	
	public void fillTableData(ObservableList<MedicalHistory> fakturen){
		faktTable.setItems(fakturen);
	}
	@FXML
	private AnchorPane root;
	
	/*Table*/
	@FXML
	private TableView<MedicalHistory> faktTable;
	@FXML
	private TableColumn<MedicalHistory, Date> dateFromCol;
	@FXML
	private TableColumn<Patient, String> firstnameCol;
	@FXML
	private TableColumn<Patient, String> lastnameCol;
	@FXML
	private TableColumn<Patient, String> insuranceNumberCol;
	@FXML
	private TableColumn<Patient, String> placeCol;
	@FXML
	private TableColumn<Patient, String> plzCol;
	@FXML
	private TableColumn<Patient, String> streetCol;
	@FXML
	private TableColumn<MedicalHistory, Integer> billStateCol;

	
}
