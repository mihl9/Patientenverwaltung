package ch.gbssg.app.util.command;

import ch.gbssg.core.AbsModel;
import ch.gbssg.core.pac.ICommand;

/**
 * 
 * @author Michael Huber
 *
 * @param <T>
 */
public class CmdDeleteEntity<T extends AbsModel> implements ICommand {
	private final Class<T> type;
	private int result;
	private T toDeletingEntity;
	
	/**
	 * create a new command for Updating entities from database.
	 * @param type of returned entities
	 * @param entity for filter result.
	 */
	public CmdDeleteEntity(Class<T> type, T entity) {
		this.type = type;
		setToDeletingEntity(entity);
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
	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public T getToDeletingEntity() {
		return toDeletingEntity;
	}

	public void setToDeletingEntity(T toDeletingEntity) {
		this.toDeletingEntity = toDeletingEntity;
	}	
}
