package ch.gbssg.app.bla.medicalHistory;

import javafx.scene.Scene;
import ch.gbssg.app.model.MedicalHistory;

public class MedicalHistoryModel {
	private MedicalHistory model;
	private Scene content;
	
	public MedicalHistoryModel(){
		content = null;
	}
	

	public MedicalHistory getModel() {
		return model;
	}

	public void setModel(MedicalHistory model) {
		this.model = model;
	}
	
	public Scene getContent() {
		return content;
	}


	public void setContent(Scene content) {
		this.content = content;
	}
}
