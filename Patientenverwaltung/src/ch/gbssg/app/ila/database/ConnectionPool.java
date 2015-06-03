/**
 * 
 */
package ch.gbssg.app.ila.database;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.SQLException;


/**
 * storage helper for connection pool to database.
 * @author pedrett.sandro
 *
 */
public class ConnectionPool {
	private static ConnectionPool instance;
	private static DataSource dataSource;
	
	/**
	 * get the instance
	 * @return
	 */
	public static ConnectionPool getInstance() {
		if (instance == null) {
			instance = new ConnectionPool();
		}
		return instance;
	}
	
	/**
	 * set the datasource for connection to database
	 * @param ds datasource to database
	 */
	public void setDataSource(DataSource ds) {
		dataSource = ds;
	}
	
	/**
	 * create connection or return existing connection.
	 * @return
	 */
	public Connection getConnection() {
		try {
			if (connection == null || connection.isClosed()) {
				connection = dataSource.getConnection();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return connection;
	}
	
	private Connection connection;
}
