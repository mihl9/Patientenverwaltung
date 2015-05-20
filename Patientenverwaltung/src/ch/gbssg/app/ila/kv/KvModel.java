package ch.gbssg.app.ila.kv;

import ch.gbssg.app.model.MedicalHistory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class KvModel {
	private ObservableList<MedicalHistory> fakturenData;
	
	public KvModel(){
		fakturenData = FXCollections.observableArrayList();
	}
	
	public ObservableList<MedicalHistory> getFakturenData(){
		return this.fakturenData;
	}
}
