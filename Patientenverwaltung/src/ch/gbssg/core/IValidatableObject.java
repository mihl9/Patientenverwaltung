/**
 * 
 */
package ch.gbssg.core;

import java.util.List;

/**
 * validatable object for check if object valid.
 * @author pedrett
 * @version 1.0
 */
public interface IValidatableObject {
	/**
	 * check if object valid
	 * @return a list of error strings
	 */
	List<String> Validate();
}
