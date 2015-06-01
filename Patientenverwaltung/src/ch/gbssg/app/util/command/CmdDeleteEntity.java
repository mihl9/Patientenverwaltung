package ch.gbssg.app.util.command;

import ch.gbssg.core.AbsModel;
import ch.gbssg.core.pac.ICommand;

/**
 * A Command for deleting the given Model from the Database.
 * @author Michael Huber
 * @param <T> the model Type which should be deleted
 */
public class CmdDeleteEntity<T extends AbsModel> implements ICommand {
	private final Class<T> type;
	private int result;
	private T toDeletingEntity;
	
	/**
	 * create a new command for deleting entities from database.
	 * @param type of the entity which should be deleted
	 * @param entity to delete.
	 */
	public CmdDeleteEntity(Class<T> type, T entity) {
		this.type = type;
		setToDeletingEntity(entity);
	}

	/**
	 * gets the Class Type of the model
	 * @return
	 */
	public Class<T> getType() {
		return type;
	}

	/**
	 * returns the state of the Action.
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
