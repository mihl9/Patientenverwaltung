package ch.gbssg.app.ila.kv;

import ch.gbssg.app.model.Fakturen;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class KvModel {
	private ObservableList<Fakturen> fakturenData;
	
	public KvModel(){
		fakturenData = FXCollections.observableArrayList();
	}
	
	public ObservableList<Fakturen> getFakturenData(){
		return this.fakturenData;
	}
}
