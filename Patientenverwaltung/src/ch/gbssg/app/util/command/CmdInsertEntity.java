package ch.gbssg.app.util.command;

import ch.gbssg.core.AbsModel;
import ch.gbssg.core.pac.ICommand;

/**
 * 
 * @author Michael Huber
 *
 * @param <T>
 */
public class CmdInsertEntity<T extends AbsModel> implements ICommand {
	private final Class<T> type;
	private int insertedKey;
	private T toAddingEntity;
	
	/**
	 * create a new command for get filterd entities from database.
	 * @param type of returned entities
	 * @param entity for filter result.
	 */
	public CmdInsertEntity(Class<T> type, T entity) {
		this.type = type;
		setToAddingEntity(entity);
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
	public int getInsertedKey() {
		return insertedKey;
	}

	public void setInsertedKey(int key) {
		this.insertedKey = key;
	}

	public T getToAddingEntity() {
		return toAddingEntity;
	}

	public void setToAddingEntity(T toAddingEntity) {
		this.toAddingEntity = toAddingEntity;
	}	
}
