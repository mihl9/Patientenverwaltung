package ch.gbssg.app.ila.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import ch.gbssg.app.ila.database.mapper.UserMapper;
import ch.gbssg.app.model.User;
import ch.gbssg.core.ICrud;
/**
 * JDBC Template for the User model
 * Contains all basic SQL transaction, like Select, Insert, Update and delete
 * @author Michael Huber
 * @version 1.0
 */
public class UserJDBCTemplate implements ICrud<User> {
	private JdbcTemplate jdbcTemplateObject;
    private DataSource dataSource;
    
	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public int create(User entity) {
		Connection connection = null;
		int generatedKey=0;
		String sqlInsert = " INSERT INTO `t_User` "
						 + " (`UsrRollId_CD`,`UsrFirstName`,`UsrLastName`,`UsrLoginName`,`UsrPw`,`UsrHourlyWage`,`UsrInactive`) "
						 + " VALUES(?,?,?,?,?,?,?); ";
		if(entity.isValid(null)){
			try {
				//prepare the connection
				connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(sqlInsert,Statement.RETURN_GENERATED_KEYS);
				//set the new Values
				statement.setInt(1, entity.getRolle().getValue());
				statement.setString(2, entity.getFirstname());
				statement.setString(3, entity.getLastname());
				statement.setString(4, entity.getLoginname());
				statement.setString(5, entity.getPassword());
				statement.setDouble(6, entity.getHourlyWage());
				statement.setBoolean(7, entity.isInactive());
				//execute sql query
				int affectedRows = statement.executeUpdate();
				
				if(affectedRows==0){
					throw new SQLException("Erstellen des SQL Objektes schlug fehl.");
				}
				
				ResultSet generatedKeys = statement.getGeneratedKeys();
				if(generatedKeys.next()){
					//return the generated ID
					generatedKey = generatedKeys.getInt(1);
				}else{
					throw new SQLException("Erstellen des SQL Objektes schlug fehl. Keine ID wurde vergeben");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				if (connection != null) {
					try {
						connection.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return generatedKey;
	}

	@Override
	public User getById(int id) {
		String sql = "SELECT * FROM t_User WHERE UsrId=" + id;
		List<User> rs = jdbcTemplateObject.query(sql, new UserMapper());
		if(rs.size()==1){
			return rs.get(0);
		}else{
			return null;
		}
	}

	@Override
	public List<User> get() {
		String sql = "select * from t_User";
		return jdbcTemplateObject.query(sql, new UserMapper());
	}
	
	@Override
	public List<User> filterByEntity(User entity) {
		List<String> whereArg;
		String sql=" SELECT * FROM t_User ";
		whereArg = new ArrayList<String>();
		/*go throug each field and show if a filter should be set*/
		if(entity.getId()>0){
			whereArg.add(" UsrId="+entity.getId()+" ");
		}
		if(entity.getRolle()!=null){
			whereArg.add(" UsrRollId_CD="+entity.getRolle().getValue()+" ");
		}
		if(entity.getFirstname()!=null && !entity.getFirstname().isEmpty()){
			whereArg.add(" UsrFirstName='"+entity.getFirstname()+"' ");
		}
		if(entity.getLastname()!=null && !entity.getLastname().isEmpty()){
			whereArg.add(" UsrLastName='"+entity.getLastname()+"' ");
		}
		if(entity.getLoginname()!=null && !entity.getLoginname().isEmpty()){
			whereArg.add(" UsrLoginName='"+entity.getLoginname()+"' ");
		}
		if(entity.getPassword()!=null && !entity.getPassword().isEmpty()){
			whereArg.add(" UsrPw='"+entity.getPassword()+"' ");
		}
		if(entity.getHourlyWage()>0){
			whereArg.add(" UsrHourlyWage="+entity.getHourlyWage()+" ");
		}
		if(entity.isInactive()!=null){
			whereArg.add(" UsrInactive="+entity.isInactive()+" ");
		}
		/*If any filter should be set add it to the query*/
		if(whereArg.size()>0){
			sql += " WHERE "+String.join("AND", whereArg);
		}
		return jdbcTemplateObject.query(sql, new UserMapper());
	}

	@Override
	public int delete(int id) {
		String sql = "DELETE FROM t_User WHERE UsrId="+id;
		//TODO Clear result handling with statement
		jdbcTemplateObject.execute(sql);
		return 1;
	}

	@Override
	public int update(int id, User newEntity) {
		String sqlUpdate = " UPDATE `t_User` "
						 + " SET "
						 + " `UsrRollId_CD` = ?, "
						 + " `UsrFirstName` = ?, "
						 + " `UsrLastName` = ?, "
						 + " `UsrLoginName` = ?, "
						 + " `UsrPw` = ?, "
						 + " `UsrHourlyWage` = ?, "
						 + " `UsrInactive` = ? "
						 + " WHERE `UsrId` = ?;";
		Connection connection = null;
		int affectedRows = 0;
		if(newEntity.isValid(null)){
			try {
				//prepare the connection
				connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(sqlUpdate,Statement.RETURN_GENERATED_KEYS);
				//set the new Values
				statement.setInt(1, newEntity.getRolle().getValue());
				statement.setString(2, newEntity.getFirstname());
				statement.setString(3, newEntity.getLastname());
				statement.setString(4, newEntity.getLoginname());
				statement.setString(5, newEntity.getPassword());
				statement.setDouble(6, newEntity.getHourlyWage());
				statement.setBoolean(7, newEntity.isInactive());
				statement.setInt(8, id);
				//execute sql query
				affectedRows = statement.executeUpdate();
				
				if(affectedRows==0){
					throw new SQLException("Erstellen des SQL Objektes schlug fehl.");
				}
					
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				if (connection != null) {
					try {
						connection.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return affectedRows;
	}
}
