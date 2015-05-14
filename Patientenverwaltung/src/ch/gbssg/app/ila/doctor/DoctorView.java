package ch.gbssg.app.ila.doctor;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import ch.gbssg.core.pac.IView;

/**
 * view for doctor agent
 * @author s.pedrett
 * @version 1.0
 */
public class DoctorView implements IView {

	/**
	 * Create a new instance of a DoctorView.
	 * @param controller send all events to this controller
	 */
	public DoctorView(DoctorController controller) {
		this.controller = controller;

		// load fxml from file and set controller
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Doctor.fxml"));
			loader.setController(this);
			root = (AnchorPane) loader.load();
			
			// bind new pane to parent pane
			AnchorPane.setTopAnchor(root,0.0);
			AnchorPane.setBottomAnchor(root,0.0);
			AnchorPane.setLeftAnchor(root,0.0);
			AnchorPane.setRightAnchor(root,0.0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see ch.gbssg.core.pac.IView#getContent()
	 */
	@Override
	public Pane getContent() {
		return root;
	}
	
	/**
	 * controller for this view
	 */
	private DoctorController controller;
	
	@FXML
	private AnchorPane root;
}
