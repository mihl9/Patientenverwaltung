package ch.gbssg.app.bla.user;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
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
	private Stage window;
	private Scene form;
	
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
		
	}
	
	public void fillCombobox(ObservableList<Code> codes){
		cboCodes.setItems(codes);
	}
	
	public void showDialog(Stage owner, User model){
		Stage dialog = new Stage();

		dialog.setTitle("Exportieren");
		dialog.initOwner(owner);
		dialog.initModality(Modality.WINDOW_MODAL);
		//dialog.setX(owner.getX() + owner.getWidth());
	    //dialog.setY(owner.getY());
	    dialog.centerOnScreen();
	    if(form==null){
	    	form = new Scene(root);
	    	//form.setr
	    }else{
	    	//form.setRoot(null);
	    }
	    dialog.setScene(form);
	    
	    dialog.show();
	    dialog.toFront();
	    this.window = dialog;
	    displayData(model);
	}
	
	public void displayData(User model){
		
	}
	
	@Override
	public Pane getContent() {
		if(root != null){
			return root;
		}
		return null;
	}
	
	//Controller
	private UserController controller;
	
	//Javafx Elements
	@FXML
	private AnchorPane root;

	@FXML
	ComboBox<Code> cboCodes;
}
