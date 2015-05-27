package ch.gbssg.app.ila.admin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import ch.gbssg.app.model.Code;
import ch.gbssg.app.model.Faktura;
import ch.gbssg.app.model.User;
import ch.gbssg.core.pac.IView;

public class AdminView implements IView, Initializable {

	public AdminView(AdminController controller){
		this.controller = controller;
		
		// get root content
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin.fxml"));
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
		firstnameCol.setCellValueFactory(cellData -> cellData.getValue().getFirstnameProperty());
		lastnameCol.setCellValueFactory(cellData -> cellData.getValue().getLastnameProperty());
		usernameCol.setCellValueFactory(cellData -> cellData.getValue().getLoginnameProperty());
		rolleCol.setCellValueFactory(cellData -> this.controller.getAssignedCode(cellData.getValue().getRolle().getValue()).getDescriptionProperty());
		
	}

	public void fillTableData(ObservableList<User> user){
		userTable.setItems(user);
	}
	
	@Override
	public Pane getContent() {
		if(root != null){
			return root;
		}
		return null;
	}
	
	//Controller
	private AdminController controller;
	
	//Javafx events
	@FXML
	private void deleteUser(){
		
	}
	@FXML
	private void addUser(){
		
	}
	@FXML
	private void editUser(){
		
	}
	
	//Javafx Elements
	@FXML
	private AnchorPane root;
	
	/*table*/
	@FXML
	private TableView<User> userTable;
	@FXML
	private TableColumn<User, String> firstnameCol;
	@FXML
	private TableColumn<User, String> lastnameCol;
	@FXML
	private TableColumn<User, String> usernameCol;
	@FXML
	private TableColumn<User, String> rolleCol;
	
}
