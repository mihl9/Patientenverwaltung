/**
 * 
 */
package ch.gbssg.app.util.command;

import java.util.List;

import ch.gbssg.core.AbsModel;
import ch.gbssg.core.pac.ICommand;

/**
 * 
 * @author pedrett.sandro
 *
 * @param <T>
 */
public class CmdFilterEntity<T extends AbsModel> implements ICommand {
	private final Class<T> type;
	private List<T> matcheEntites;
	private T filterEntity;
	
	/**
	 * create a new command for get filterd entities from database.
	 * @param type of returned entities
	 * @param entity for filter result.
	 */
	public CmdFilterEntity(Class<T> type, T entity) {
		this.type = type;
		setFilterEntity(entity);
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
	public List<T> getEntities() {
		return matcheEntites;
	}

	@SuppressWarnings("unchecked")
	public void setEntities(List<?> matches) {
		this.matcheEntites = (List<T>) matches;
	}

	public T getFilterEntity() {
		return filterEntity;
	}

	public void setFilterEntity(T filterEntity) {
		this.filterEntity = filterEntity;
	}	
}
