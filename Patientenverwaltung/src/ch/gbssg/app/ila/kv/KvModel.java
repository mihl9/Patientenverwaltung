package ch.gbssg.app.ila.kv;

import ch.gbssg.app.model.Code;
import ch.gbssg.app.model.Faktura;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Represents the Model for the Intermediate Level agent Admin
 * In this class all necessary data is saved
 * @author Michael Huber
 * @version 1.0
 */
public class KvModel{
	private ObservableList<Faktura> fakturenData;
	private ObservableList<Faktura> fakturenFilteredData;
	private ObservableList<Code> codesData;
	/**
	 * Constructor
	 */
	public KvModel(){
		fakturenData = FXCollections.observableArrayList();
		fakturenFilteredData = FXCollections.observableArrayList();
		codesData = FXCollections.observableArrayList();
	}
	/**
	 * get the Faktura data list
	 * @return
	 */
	public ObservableList<Faktura> getFakturenData(){
		return this.fakturenData;
	}
	/**
	 * get the Filtered Faktura data list
	 * @return
	 */
	public ObservableList<Faktura> getFakturenFilteredData(){
		return this.fakturenFilteredData;
	}
	
	/**
	 * get the Codes data list
	 * @return
	 */
	public ObservableList<Code> getCodesData(){
		return this.codesData;
	}
}
