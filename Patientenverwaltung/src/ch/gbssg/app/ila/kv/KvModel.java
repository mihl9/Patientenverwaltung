package ch.gbssg.app.ila.kv;

import ch.gbssg.app.model.Code;
import ch.gbssg.app.model.Fakturen;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class KvModel extends Searchable{
	private ObservableList<Fakturen> fakturenData;
	private ObservableList<Code> codesData;
	
	public KvModel(){
		fakturenData = FXCollections.observableArrayList();
		codesData = FXCollections.observableArrayList();
	}
	
	public ObservableList<Fakturen> getFakturenData(){
		return this.fakturenData;
	}
	
	public ObservableList<Code> getCodesData(){
		return this.codesData;
	}
}
