package ch.gbssg.app.model;

import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import ch.gbssg.core.AbsModel;
/**
 * represent a Code model
 * @author Michael Huber
 * @version 1.0
 */
public class Code extends AbsModel {

	private IntegerProperty id;
	private StringProperty description;
	private IntegerProperty codeTypeId;
	
	public Code(){
		id = new SimpleIntegerProperty();
		description = new SimpleStringProperty();
		codeTypeId = new SimpleIntegerProperty();
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
	public String getDescription() {
		return description.get();
	}

	public void setDescription(String description) {
		this.description.set(description);
	}

	public StringProperty getDescriptionProperty(){
		return this.description;
	}
	
	public int getCodeTypeId() {
		return codeTypeId.get();
	}

	public void setCodeTypeId(int codeTypeId) {
		this.codeTypeId.set(codeTypeId);
	}

	public IntegerProperty getCodetypeIdProperty(){
		return this.codeTypeId;
	}

	@Override
	public boolean isValid(List<String> errors) {
		// TODO Auto-generated method stub
		return true;
	}

}
