package ch.gbssg.app.ila.kv;

import ch.gbssg.app.model.Code;
import ch.gbssg.app.model.Faktura;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * 
 * @author Michael
 *
 */
public class KvModel{
	private ObservableList<Faktura> fakturenData;
	private ObservableList<Faktura> fakturenFilteredData;
	private ObservableList<Code> codesData;
	
	public KvModel(){
		fakturenData = FXCollections.observableArrayList();
		fakturenFilteredData = FXCollections.observableArrayList();
		codesData = FXCollections.observableArrayList();
	}
	
	public ObservableList<Faktura> getFakturenData(){
		return this.fakturenData;
	}
	
	public ObservableList<Faktura> getFakturenFilteredData(){
		return this.fakturenFilteredData;
	}
	
	
	public ObservableList<Code> getCodesData(){
		return this.codesData;
	}
}
