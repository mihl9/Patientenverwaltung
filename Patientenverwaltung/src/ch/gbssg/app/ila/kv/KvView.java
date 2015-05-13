package ch.gbssg.app.ila.kv;


import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import ch.gbssg.core.pac.IView;

public class KvView implements IView{

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
	
	@FXML
	private AnchorPane root;
	
}
