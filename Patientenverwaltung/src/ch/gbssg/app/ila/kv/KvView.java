package ch.gbssg.app.ila.kv;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import ch.gbssg.app.model.Code;
import ch.gbssg.app.model.Faktura;
import ch.gbssg.app.util.CellFactoryCode;
import ch.gbssg.core.pac.IView;
/**
 * 
 * @author Michael
 *
 */
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
		setComboboxCellFacotry();
	}
	
	private void setComboboxCellFacotry(){
		cboCodes.setButtonCell(new CellFactoryCode());
		cboCodes.setCellFactory(new Callback<ListView<Code>, ListCell<Code>>() {
			@Override
			public ListCell<Code> call(ListView<Code> arg0) {
				// TODO Auto-generated method stub
				return new CellFactoryCode();
			}
		});
	}
	
	private void setTableCellValueFactory(){
		dateFromCol.setCellValueFactory(cellData -> cellData.getValue().getDateFromProperty());
		firstnameCol.setCellValueFactory(cellData -> cellData.getValue().getFirstnameProperty());
		lastnameCol.setCellValueFactory(cellData -> cellData.getValue().getLastnameProperty());
		insuranceNumberCol.setCellValueFactory(cellData -> cellData.getValue().getInsuranceNumberProperty());
		placeCol.setCellValueFactory(cellData -> cellData.getValue().getPlaceProperty());
		plzCol.setCellValueFactory(cellData -> cellData.getValue().getPlzProberty());
		streetCol.setCellValueFactory(cellData -> cellData.getValue().getAddressProperty());
		billStateCol.setCellValueFactory(cellData -> this.controller.getAssignedCode(cellData.getValue().getBillState()).getDescriptionProperty());
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
	
	public void fillTableData(ObservableList<Faktura> fakturen){
		faktTable.setItems(fakturen);
	}
	
	public void fillCombobox(ObservableList<Code> codes){
		cboCodes.setItems(codes);
	}
	/*Several Events*/
	@FXML
	private void setFilter(){
		this.controller.FilterTable(cboCodes.getSelectionModel().getSelectedItem(), fromDatePicker.getValue(), toDatePicker.getValue());
	}
	
	@FXML 
	private void removeFilter(){
		this.controller.FilterTable(null, null, null);
		cboCodes.setValue(null);
		fromDatePicker.setValue(null);
		toDatePicker.setValue(null);
	}
	
	@FXML
	private void printFakturen(){
		this.controller.generateInvoice(this.faktTable.getSelectionModel().getSelectedItem());
	}
	@FXML
	private void changeState(){
		this.controller.changeState(this.faktTable.getSelectionModel().getSelectedItem());
	}
	/*The GUI Elements of the View*/
	@FXML
	private AnchorPane root;
	
	/*Filter menu*/
	@FXML
	ComboBox<Code> cboCodes;
	@FXML
	DatePicker fromDatePicker;
	@FXML
	DatePicker toDatePicker;
	@FXML
	Button btnSetFilter;
	@FXML
	Button btnRemoveFilter;
	/*Table*/
	@FXML
	private TableView<Faktura> faktTable;
	@FXML
	private TableColumn<Faktura, LocalDate> dateFromCol;
	@FXML
	private TableColumn<Faktura, String> firstnameCol;
	@FXML
	private TableColumn<Faktura, String> lastnameCol;
	@FXML
	private TableColumn<Faktura, String> insuranceNumberCol;
	@FXML
	private TableColumn<Faktura, String> placeCol;
	@FXML
	private TableColumn<Faktura, String> plzCol;
	@FXML
	private TableColumn<Faktura, String> streetCol;
	@FXML
	private TableColumn<Faktura, String> billStateCol;

	
}
