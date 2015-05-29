package ch.gbssg.app.tla;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import ch.gbssg.app.model.User;
import ch.gbssg.core.pac.IView;

public class AppView implements IView {
	public AppView(AppController controller) {
		this.controller = controller;
	}
	
	/**
	 * set a error on screen
	 * @param errors list of errors
	 */
	public void setErrors(List<String> errors) {
		String errorString = "";
		
		for (String string : errors) {
			errorString += string + "\n";
		}
		
		lblErrorCode.setText(errorString);
	}
	
	public void setError(String error) {
		List<String> errors = new ArrayList<String>();
		errors.add(error);
		setErrors(errors);
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
		try {
			u.setPassword(u.decryptSha1(password));
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
		}
		
		// login
		controller.LoginValid(u);
	}
	
	@FXML
	private void logout(){
		this.controller.logout();
	}
	
	@FXML
	private void close(){
		this.controller.closeApp();
	}
	
	/**
	 * controller for this view
	 */
	private AppController controller;
	
	@FXML
	private TextField txtUsername;
	
	@FXML
	private PasswordField txtPassword;
	
	@FXML
	private Label lblErrorCode;
	
	@FXML 
	private Pane container;

	@Override
	public Pane getContent() {
		return container;
	}
}
