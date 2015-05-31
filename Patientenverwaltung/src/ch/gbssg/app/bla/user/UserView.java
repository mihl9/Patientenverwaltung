package ch.gbssg.app.bla.user;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import ch.gbssg.app.model.Code;
import ch.gbssg.app.model.User;
import ch.gbssg.app.util.CellFactoryCode;
import ch.gbssg.core.pac.IView;
/**
 * 
 * @author michael.huber
 *
 */
public class UserView  implements IView, Initializable {
	
	public UserView(UserController controller){
		this.controller = controller;
		
		// get root content
		FXMLLoader loader = new FXMLLoader(getClass().getResource("User.fxml"));
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
		cboCodes.setCellFactory(new Callback<ListView<Code>, ListCell<Code>>() {
			
			@Override
			public ListCell<Code> call(ListView<Code> param) {
				// TODO Auto-generated method stub
				return new CellFactoryCode();
			}
		});
		cboCodes.setButtonCell(new CellFactoryCode());
	}
	
	public void fillCombobox(ObservableList<Code> codes){
		cboCodes.setItems(codes);
	}
	
	public void displayData(User model){
		if(model!=null){
			txtFirstname.setText(model.getFirstname());
			txtlastname.setText(model.getLastname());
			txtUsername.setText(model.getLoginname());
			txtPassword.setText("password");
			txtHourlyWage.setText(""+model.getHourlyWage());
			if(model.getRolle()!=null){
				cboCodes.setValue(this.controller.getAssignedCode(model.getRolle().getValue()));
			}
		}
	}
	
	@Override
	public Pane getContent() {
		if(root != null){
			return root;
		}
		return null;
	}
	
	public TextField getTxtFirstname(){
		return txtFirstname;
	}
	
	public TextField getTxtlastname(){
		return txtlastname;
	}
	
	public TextField getTxtUsername(){
		return txtUsername;
	}
	
	public TextField getTxtPassword(){
		return txtPassword;
	}
	
	public TextField getTxtHourlyWage(){
		return txtHourlyWage;
	}
	
	public ComboBox<Code> getCboCodes(){
		return cboCodes;
	}
	
	public Button getBtnSave(){
		return btnSave;
	}
	
	public Button getBtnCancel(){
		return btnCancel;
	}
	
	//Controller
	private UserController controller;
	
	//Javafx Elements
	@FXML
	private AnchorPane root;

	
	
	@FXML
	private TextField txtFirstname;
	
	@FXML
	private TextField txtlastname;
	
	@FXML
	private TextField txtUsername;
	
	@FXML
	private PasswordField txtPassword;
	
	@FXML
	private TextField txtHourlyWage;
	
	@FXML
	private ComboBox<Code> cboCodes;
	
	@FXML
	private Button btnSave;
	
	@FXML
	private Button btnCancel;
}
