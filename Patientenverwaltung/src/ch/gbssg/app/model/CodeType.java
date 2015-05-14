package ch.gbssg.app.model;

import java.util.List;

import ch.gbssg.core.AbsModel;

public class CodeType extends AbsModel {
	
	private int id;
	private String desc;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public boolean isValid(List<String> errors) {
		// TODO Auto-generated method stub
		return true;
	}

}
