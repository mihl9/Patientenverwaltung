package ch.gbssg.app.util.command;

import ch.gbssg.core.AbsModel;
import ch.gbssg.core.pac.ICommand;

/**
 * A Command for inserting the given Model into the Database.
 * @author Michael Huber
 * @param <T> the model Type which should be inserted
 */
public class CmdInsertEntity<T extends AbsModel> implements ICommand {
	private final Class<T> type;
	private int insertedKey;
	private T toAddingEntity;
	
	/**
	 * create a new command to insert new entities into database.
	 * @param type of the given entities
	 * @param entity for inserting.
	 */
	public CmdInsertEntity(Class<T> type, T entity) {
		this.type = type;
		setToAddingEntity(entity);
	}

	/**
	 * gets the Class Type of the model
	 * @return
	 */
	public Class<T> getType() {
		return type;
	}

	/**
	 * returns the ID of the new inserted datarow
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
