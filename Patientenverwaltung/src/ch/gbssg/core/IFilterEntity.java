package ch.gbssg.core;


/**
 * Represent a filter for database entities. You can send a IFilterEntity 
 * to the DatabaseController via CmdFilterEntity. So, the ICrud<T> Interface returns 
 * all merged entities of the database collection.
 * 
 * @author Pedrett Sandro
 * @version 1.0
 * 
 * @param <T> the type of entity. It must be extension of a ICommand
 */
public interface IFilterEntity<T extends AbsModel> {

}
