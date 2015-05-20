package ch.gbssg.app.model;



import java.util.List;

import ch.gbssg.app.util.UserRoll;
import ch.gbssg.core.AbsModel;
import ch.gbssg.core.IFilterEntity;

/**
 * represent a user model.
 * @author pedrett
 * @version 1.0
 */
public class User extends AbsModel implements IFilterEntity<User>  {
	private int id;
	private UserRoll rolle;
	private String firstname;
	private String lastname;
	private String loginname;
	private String password;
	private double hourlyWage;
	private boolean isInactive;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getFirstname() {
		return firstname;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getLoginname() {
		return loginname;
	}
	
	public void setLoginname(String loginName) {
		this.loginname = loginName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public double getHourlyWage() {
		return hourlyWage;
	}
	
	public void setHourlyWage(double hourlyWage) {
		this.hourlyWage = hourlyWage;
	}

	public boolean isInactive() {
		return isInactive;
	}

	public void setInactive(boolean isDeleted) {
		this.isInactive = isDeleted;
	}
	
	public UserRoll getRolle() {
		return rolle;
	}

	public void setRolle(UserRoll rolle) {
		this.rolle = rolle;
	}
	
	/*
	 * (non-Javadoc)
	 * @see ch.gbssg.core.AbsModel#isValid(java.util.List)
	 */
	@Override
	public boolean isValid(List<String> errors) {
		if (getLoginname() == null || getLoginname().isEmpty()) {
			errors.add("Username is empty");
		}
		if (getPassword() == null || getPassword().isEmpty()) {
			errors.add("Password is empty");
		}

		
		return errors.size() == 0;
	}
}
