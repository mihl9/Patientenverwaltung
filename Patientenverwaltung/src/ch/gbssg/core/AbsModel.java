package ch.gbssg.core;

import java.util.ArrayList;
import java.util.List;

/**
 * abstract model for all models. 
 * @author pedrett
 * @version 1.0
 */
public abstract class AbsModel implements IValidatableObject {

	/**
	 * abstract valid methode. for check if object are valid.
	 * @param errors a list of errors.
	 * @return true if object has no errors; otherwise false.
	 */
	public abstract boolean isValid(List<String> errors);
	
	/*
	 * (non-Javadoc)
	 * @see ch.gbssg.core.IValidatableObject#Validate()
	 */
	@Override
	public List<String> Validate() {
		List<String> errors = new ArrayList<String>();
		isValid(errors);
		return errors;
	}

}
