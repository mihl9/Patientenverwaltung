package ch.gbssg.app.ila.kv;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import ch.gbssg.app.model.Fakturen;
import ch.gbssg.core.pac.IView;

public class KvView implements IView, Initializable{

	public KvView(KvController controller){
		this.controller = controller;
		
		// get root content
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Kv.fxml"));
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
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		setTableCellValueFactory();
	}
	
	private void setTableCellValueFactory(){
		dateFromCol.setCellValueFactory(cellData -> cellData.getValue().getDateFromProperty());
		firstnameCol.setCellValueFactory(cellData -> cellData.getValue().getFirstnameProperty());
		lastnameCol.setCellValueFactory(cellData -> cellData.getValue().getLastnameProperty());
		insuranceNumberCol.setCellValueFactory(cellData -> cellData.getValue().getInsuranceNumberProperty());
		placeCol.setCellValueFactory(cellData -> cellData.getValue().getPlaceProperty());
		plzCol.setCellValueFactory(cellData -> cellData.getValue().getPlzProberty());
		streetCol.setCellValueFactory(cellData -> cellData.getValue().getAddressProperty());
		billStateCol.setCellValueFactory(cellData -> cellData.getValue().getBillStateProperty());

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
	
	public void fillTableData(ObservableList<Fakturen> fakturen){
		faktTable.setItems(fakturen);
	}
	@FXML
	private AnchorPane root;
	
	/*Table*/
	@FXML
	private TableView<Fakturen> faktTable;
	@FXML
	private TableColumn<Fakturen, LocalDate> dateFromCol;
	@FXML
	private TableColumn<Fakturen, String> firstnameCol;
	@FXML
	private TableColumn<Fakturen, String> lastnameCol;
	@FXML
	private TableColumn<Fakturen, String> insuranceNumberCol;
	@FXML
	private TableColumn<Fakturen, String> placeCol;
	@FXML
	private TableColumn<Fakturen, String> plzCol;
	@FXML
	private TableColumn<Fakturen, String> streetCol;
	@FXML
	private TableColumn<Fakturen, Number> billStateCol;

	
}
