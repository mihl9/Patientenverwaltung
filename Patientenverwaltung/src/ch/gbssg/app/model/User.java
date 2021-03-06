package ch.gbssg.app.model;



import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import ch.gbssg.app.util.UserRoll;
import ch.gbssg.core.AbsModel;
import ch.gbssg.core.IFilterEntity;

/**
 * represent a user model.
 * @author pedrett
 * @version 1.0
 */
public class User extends AbsModel implements IFilterEntity<User>  {
	private IntegerProperty id;
	private ObjectProperty<UserRoll> rolle;
	private StringProperty firstname;
	private StringProperty lastname;
	private StringProperty loginname;
	private StringProperty password;
	private DoubleProperty hourlyWage;
	private BooleanProperty isInactive;
	
	public User(){
		id = new SimpleIntegerProperty();
		rolle = new SimpleObjectProperty<UserRoll>();
		firstname = new SimpleStringProperty();
		lastname = new SimpleStringProperty();
		loginname = new SimpleStringProperty();
		password = new SimpleStringProperty();
		hourlyWage = new SimpleDoubleProperty();
		isInactive = new SimpleBooleanProperty();
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
	
	public String getFirstname() {
		return firstname.get();
	}
	
	public void setFirstname(String firstname) {
		this.firstname.set(firstname);
	}
	
	public StringProperty getFirstnameProperty(){
		return this.firstname;
	}
	
	public String getLastname() {
		return lastname.get();
	}
	
	public void setLastname(String lastname) {
		this.lastname.set(lastname);
	}
	
	public StringProperty getLastnameProperty(){
		return this.lastname;
	}
	
	public String getLoginname() {
		return loginname.get();
	}
	
	public void setLoginname(String loginName) {
		this.loginname.set(loginName);
	}
	
	public StringProperty getLoginnameProperty(){
		return this.loginname;
	}
	
	public String getPassword() {
		return password.get();
	}
	
	public void setPassword(String password) {
		this.password.set(password);
	}
	
	public StringProperty getPasswordProperty(){
		return this.password;
	}
	
	public double getHourlyWage() {
		return hourlyWage.get();
	}
	
	public void setHourlyWage(double hourlyWage) {
		this.hourlyWage.set(hourlyWage);
	}

	public DoubleProperty getHourlyWageProperty(){
		return this.hourlyWage;
	}
	
	public Boolean isInactive() {
		return isInactive.getValue();
	}

	public void setInactive(Boolean isDeleted) {
		this.isInactive.setValue(isDeleted);
	}
	
	public BooleanProperty getInactiveProperty(){
		return this.isInactive;
	}
	
	public UserRoll getRolle() {
		return rolle.get();
	}

	public void setRolle(UserRoll rolle) {
		this.rolle.set(rolle);
	}
	
	public ObjectProperty<UserRoll> getRolleProperty(){
		return this.rolle;
	}
	/**
	 * Decrypt the given string into an SHA1 hash.
	 * This is the current decryption which is used for the Password to secure
	 * @param input the readable string
	 * @return the decrypted passwort as string
	 * @throws NoSuchAlgorithmException
	 */
	public String decryptSha1(String input) throws NoSuchAlgorithmException{
		MessageDigest mDigest = null;
		mDigest = MessageDigest.getInstance("SHA1");
		if(mDigest!=null){
	        byte[] result = mDigest.digest(input.getBytes());
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < result.length; i++) {
	            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
	        }
	         
	        return sb.toString();
		}else{
			return null;
		}
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

		if(errors!=null){
			return errors.size() == 0;
		}else{
			return true;
		}
	}
}
