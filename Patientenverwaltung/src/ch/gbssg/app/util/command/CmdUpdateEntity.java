package ch.gbssg.app.util.command;

import ch.gbssg.core.AbsModel;
import ch.gbssg.core.pac.ICommand;

/**
 * A Command for updating the given Model on the Database.
 * @author Michael Huber
 * @param <T> the model Type which should be updated
 */
public class CmdUpdateEntity<T extends AbsModel> implements ICommand {
	private final Class<T> type;
	private int affectedRows;
	private T toUpdatingEntity;
	
	/**
	 * create a new command for Updating entities from database.
	 * @param type of returned entities
	 * @param entity for filter result.
	 */
	public CmdUpdateEntity(Class<T> type, T entity) {
		this.type = type;
		setToUpdatingEntity(entity);
	}

	/**
	 * returns the class type of the model
	 * @return
	 */
	public Class<T> getType() {
		return type;
	}

	/**
	 * returns the amount of affected rows.
	 * @return
	 */
	public int getAffectedRows() {
		return affectedRows;
	}

	public void setAffectedRows(int key) {
		this.affectedRows = key;
	}

	public T getToUpdatingEntity() {
		return toUpdatingEntity;
	}

	public void setToUpdatingEntity(T toUpdatingEntity) {
		this.toUpdatingEntity = toUpdatingEntity;
	}	
}
