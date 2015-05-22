package ch.gbssg.core;

import java.util.List;

import javax.sql.DataSource;


/**
 * interface for DAO objects.
 * 
 * @author pedrett.sandro
 * @version 1.0
 */
public interface ICrud<T extends AbsModel> {
	/**
	 * used to initialize database resources connection.
	 * 
	 * @param dataSource database connection
	 */
	void setDataSource(DataSource dataSource);
	
	/**
	 * create a new entity of implement dao
	 * 
	 * @param entity new model
	 * @return the new id of created entity
	 */
	int create(T entity);
	
	/**
	 * looking for a id from implement dao
	 * @param id of dao entity
	 * @return the dao object
	 */
	T getById(int id);
	
	/**
	 * gives all the records form the dao object
	 * @return a list of dao entities
	 */
	List<T> get();
	
	/**
	 * returns all filtered entities.
	 * @param entity to filter
	 * @return a list of all filterted entites.
	 */
	List<T> filterByEntity(T entity);
	
	/**
	 * delete a record from dao object
	 * @param id of entity
	 * @return the number of affected records
	 */
	int delete(int id);
	
	/**
	 * update a entity of dao objects
	 * @param id to update
	 * @param newEntity includes the new values for update a entity
	 * @return the number of affected records
	 */
	int update(int id, T newEntity);
}
