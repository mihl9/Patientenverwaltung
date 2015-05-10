package ch.gbssg.app.model;

public class User {
	private int id;
	private int rolleId;
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
	
	public int getRolleId() {
		return rolleId;
	}
	
	public void setRolleId(int rolleId) {
		this.rolleId = rolleId;
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
}
