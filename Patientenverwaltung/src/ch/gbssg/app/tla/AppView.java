package ch.gbssg.app.tla;

import java.util.List;

import ch.gbssg.app.model.User;
import ch.gbssg.core.pac.IView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class AppView implements IView {
	public AppView(AppController controller) {
		this.controller = controller;
	}
	
	/**
	 * set a error on screen
	 * @param errors list of errors
	 */
	public void setError(List<String> errors) {
		String errorString = "";
		
		for (String string : errors) {
			errorString += string + "\n";
		}
		
		lblErrorCode.setText(errorString);
	}
	
	/**
	 * is fire if user click login button.
	 */
	@FXML
	public void Login() {
		String username =  txtUsername.getText();
		String password = txtPassword.getText();
		
		User u = new User();
		u.setLoginname(username);
		u.setPassword(password);
		
		// login
		controller.LoginValid(u);
	}
	
	/**
	 * controller for this view
	 */
	private AppController controller;
	
	@FXML
	private TextField txtUsername;
	
	@FXML
	private TextField txtPassword;
	
	@FXML
	private Label lblErrorCode;
	
	@FXML 
	private Pane container;

	@Override
	public Pane getContent() {
		container.getChildren().clear(); // TODO if blink screen by change pane, clear manuell befor add new pane
		return container;
	}
}
