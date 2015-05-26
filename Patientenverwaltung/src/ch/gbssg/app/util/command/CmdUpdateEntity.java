package ch.gbssg.app.util.command;

import ch.gbssg.core.AbsModel;
import ch.gbssg.core.pac.ICommand;

/**
 * 
 * @author Michael Huber
 *
 * @param <T>
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
	 * 
	 * @return
	 */
	public Class<T> getType() {
		return type;
	}

	/**
	 * returns null if no entities matches; otherwise a list of entities.
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
