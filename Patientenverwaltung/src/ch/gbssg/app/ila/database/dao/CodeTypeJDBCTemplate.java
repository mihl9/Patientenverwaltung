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

import ch.gbssg.app.ila.database.mapper.CodeTypeMapper;
import ch.gbssg.app.model.CodeType;
import ch.gbssg.core.ICrud;
/**
 * JDBC Template for the CodeType model
 * Contains all basic SQL transaction, like Select, Insert, Update and delete
 * @author Michael Huber
 * @version 1.0
 */
public class CodeTypeJDBCTemplate implements ICrud<CodeType> {
	private JdbcTemplate jdbcTemplateObject;
    private DataSource dataSource;
    
	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public int create(CodeType entity) {
		Connection connection = null;
		int generatedKey=0;
		String sqlInsert = "INSERT INTO t_CodeType (CdtDesc) VALUES (?)";
		if(entity.isValid(null)){
			try {
				//prepare the connection
				connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(sqlInsert,Statement.RETURN_GENERATED_KEYS);
				//set the new Values
				statement.setString(1, entity.getDesc());
				
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
	public CodeType getById(int id) {
		String sql = "SELECT * FROM t_CodeType WHERE CdtID=" + id;
		List<CodeType> rs = jdbcTemplateObject.query(sql, new CodeTypeMapper());
		if(rs.size()==1){
			return rs.get(0);
		}else{
			return null;
		}
	}

	@Override
	public List<CodeType> get() {
		String sql = "SELECT * FROM t_CodeType";
		return jdbcTemplateObject.query(sql, new CodeTypeMapper());
	}
	
	@Override
	public List<CodeType> filterByEntity(CodeType entity) {
		List<String> whereArg;
		String sql=" SELECT * FROM t_CodeType ";
		whereArg = new ArrayList<String>();
		/*go throug each field and show if a filter should be set*/
		if(entity.getId()>0){
			whereArg.add(" CdtID="+entity.getId()+" ");
		}
		if(entity.getDesc()!=null && !entity.getDesc().isEmpty()){
			whereArg.add(" CdtDesc='"+entity.getDesc()+"' ");
		}
		
		
		/*If any filter should be set add it to the query*/
		if(whereArg.size()>0){
			sql += " WHERE "+String.join("AND", whereArg);
		}
		return jdbcTemplateObject.query(sql, new CodeTypeMapper());
	}

	@Override
	public int delete(int id) {
		String sql = "DELETE FROM t_CodeType WHERE CdtID="+id;
		//TODO Clear result handling with statement
		jdbcTemplateObject.execute(sql);
		return 1;
	}

	@Override
	public int update(int id, CodeType newEntity) {
		String sqlUpdate = "UPDATE t_CodeType SET CdtDesc=? WHERE CdtID=?";
		Connection connection = null;
		int affectedRows = 0;
		if(newEntity.isValid(null)){
			try {
				//prepare the connection
				connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(sqlUpdate,Statement.RETURN_GENERATED_KEYS);
				//set the new Values
				statement.setString(1, newEntity.getDesc());
				statement.setInt(2, id);
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
