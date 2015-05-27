package ch.gbssg.app.model;

import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import ch.gbssg.core.AbsModel;
/**
 * Represent a CodeType model
 * @author Michael Huber
 *
 */
public class CodeType extends AbsModel {
	
	private IntegerProperty id;
	private StringProperty desc;
	
	public CodeType(){
		id = new SimpleIntegerProperty();
		desc = new SimpleStringProperty();
	}
	
	public int getId() {
		return id.get();
	}

	public void setId(int id) {
		this.id.set(id);
	}
	
	public IntegerProperty getIdProperty(){
		return this.id;
	}

	public String getDesc() {
		return desc.get();
	}

	public void setDesc(String desc) {
		this.desc.set(desc);
	}

	public StringProperty getDescProperty(){
		return this.desc;
	}
	
	@Override
	public boolean isValid(List<String> errors) {
		// TODO Auto-generated method stub
		return true;
	}

}
