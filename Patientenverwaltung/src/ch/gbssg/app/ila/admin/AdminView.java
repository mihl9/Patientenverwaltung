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
import ch.gbssg.app.model.User;
import ch.gbssg.core.pac.IView;
/**
 * Represents the View of the Intermediate level Agent Admin
 * Loads the belonging fxml and initialize the view and all other elements
 * Handles the Events and redirect them to the controller. 
 * @author Michael Huber
 * @version 1.0
 */
public class AdminView implements IView, Initializable {
	/**
	 * Constructor
	 * @param controller
	 */
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
	/**
	 * Is executed when the FXML file is loaded
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		firstnameCol.setCellValueFactory(cellData -> cellData.getValue().getFirstnameProperty());
		lastnameCol.setCellValueFactory(cellData -> cellData.getValue().getLastnameProperty());
		usernameCol.setCellValueFactory(cellData -> cellData.getValue().getLoginnameProperty());
		rolleCol.setCellValueFactory(cellData -> this.controller.getAssignedCode(cellData.getValue().getRolle().getValue()).getDescriptionProperty());
		
	}
	/**
	 * Fill the Table with the User data list
	 * @param user list of users
	 */
	public void fillTableData(ObservableList<User> user){
		userTable.setItems(user);
	}
	/**
	 * @return all gui elements created with the FXML file
	 */
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
		this.controller.delUser(userTable.getSelectionModel().getSelectedItem());
	}
	@FXML
	private void addUser(){
		this.controller.addUser();
	}
	@FXML
	private void editUser(){
		this.controller.editUser(userTable.getSelectionModel().getSelectedItem());
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
