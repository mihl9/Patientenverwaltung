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

import ch.gbssg.app.ila.database.ConnectionPool;
import ch.gbssg.app.ila.database.mapper.CodeMapper;
import ch.gbssg.app.model.Code;
import ch.gbssg.core.ICrud;
/**
 * JDBC Template for the Code model
 * Contains all basic SQL transaction, like Select, Insert, Update and delete
 * @author Michael Huber
 * @version 1.0
 */
public class CodeJDBCTemplate implements ICrud<Code> {
	private JdbcTemplate jdbcTemplateObject;
    private DataSource dataSource;
    
	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public int create(Code entity) {
		Connection connection = null;
		int generatedKey=0;
		String sqlInsert = "INSERT INTO t_Codes (CdsDesc, CdsCdtID_FK) VALUES (?,?)";
		if(entity.isValid(null)){
			try {
				//prepare the connection
				connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(sqlInsert,Statement.RETURN_GENERATED_KEYS);
				//set the new Values
				statement.setString(1, entity.getDescription());
				statement.setInt(2, entity.getCodeTypeId());
				
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
	public Code getById(int id) {
		String sql = "SELECT * FROM t_Codes WHERE CdsID=" + id;
		List<Code> rs = jdbcTemplateObject.query(sql, new CodeMapper());
		if(rs.size()==1){
			return rs.get(0);
		}else{
			return null;
		}
	}

	@Override
	public List<Code> get() {
		String sql = "SELECT * FROM t_Codes";
		return jdbcTemplateObject.query(sql, new CodeMapper());
	}
	
	@Override
	public List<Code> filterByEntity(Code entity) {
		List<String> whereArg;
		String sql=" SELECT * FROM t_Codes ";
		whereArg = new ArrayList<String>();
		/*go throug each field and show if a filter should be set*/
		if(entity.getId()>0){
			whereArg.add(" CdsId="+entity.getId()+" ");
		}
		if(entity.getDescription()!=null && !entity.getDescription().isEmpty()){
			whereArg.add(" CdsDesc='"+entity.getDescription()+"' ");
		}
		if(entity.getCodeTypeId()>0){
			whereArg.add(" CdsCdtID_FK="+entity.getCodeTypeId()+" ");
		}
		
		/*If any filter should be set add it to the query*/
		if(whereArg.size()>0){
			sql += " WHERE "+String.join("AND", whereArg);
		}
		return jdbcTemplateObject.query(sql, new CodeMapper());
	}

	@Override
	public int delete(int id) {
		String sql = "DELETE FROM t_Codes WHERE CdsID="+id;
		//TODO Clear result handling with statement
		jdbcTemplateObject.execute(sql);
		return 1;
	}

	@Override
	public int update(int id, Code newEntity) {
		String sqlUpdate = "UPDATE t_Codes SET CdsDesc=?, CdsCdtID_FK=? WHERE CdsID=?";
		Connection connection = null;
		int affectedRows = 0;
		if(newEntity.isValid(null)){
			try {
				//prepare the connection
				connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(sqlUpdate,Statement.RETURN_GENERATED_KEYS);
				//set the new Values
				statement.setString(1, newEntity.getDescription());
				statement.setInt(2, newEntity.getCodeTypeId());
				statement.setInt(3, id);
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
