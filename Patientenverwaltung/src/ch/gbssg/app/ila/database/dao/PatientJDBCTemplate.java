package ch.gbssg.app.ila.database.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import ch.gbssg.app.ila.database.mapper.PatientMapper;
import ch.gbssg.app.model.Patient;
import ch.gbssg.core.ICrud;

public class PatientJDBCTemplate implements ICrud<Patient> {
	private JdbcTemplate jdbcTemplateObject;
    private DataSource dataSource;
    
	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public int create(Patient entity) {
		Connection connection = null;
		int generatedKey=0;
		String sqlInsert = " INSERT INTO `t_Patient` "
						 + " (`PatGender_CD`,`PatLastName`,`PatFirstName`,`PatBirthdate`,`PatAddress`,`PatPLZ`,`PatPlace`,`PatInsuranceNumber`,`PatAhv`) "
						 + " VALUES(?,?,?,?,?,?,?,?,?);";
		if(entity.isValid(null)){
			try {
				//prepare the connection
				connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(sqlInsert,Statement.RETURN_GENERATED_KEYS);
				//set the new Values
				statement.setInt(1, entity.getGenderCode());
				statement.setString(2, entity.getLastname());
				statement.setString(3, entity.getFirstname());
				statement.setDate(4, new java.sql.Date(Date.from(entity.getBirthday().atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime()));
				statement.setString(5, entity.getAddress());
				statement.setString(6, entity.getPlz());
				statement.setString(7, entity.getPlace());
				statement.setString(8, entity.getInsuranceNumber());
				statement.setString(9, entity.getAhv());
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
	public Patient getById(int id) {
		String sql = "SELECT * FROM t_Patient WHERE PatID=" + id;
		List<Patient> rs = jdbcTemplateObject.query(sql, new PatientMapper());
		if(rs.size()==1){
			return rs.get(0);
		}else{
			return null;
		}
	}

	@Override
	public List<Patient> get() {
		String sql = "SELECT * FROM t_Patient";
		return jdbcTemplateObject.query(sql, new PatientMapper());
	}
	
	@Override
	public List<Patient> filterByEntity(Patient entity) {
		List<String> whereArg;
		String sql=" SELECT * FROM t_Patient ";
		whereArg = new ArrayList<String>();
		/*go through each field and show if a filter should be set*/
		if(entity.getId()>0){
			whereArg.add(" PatID="+entity.getId()+" ");
		}
		/*Patient Information*/
		if(entity.getGenderCode() > 0){
			whereArg.add(" PatGender_CD=8 ");
			//whereArg.add(" PatGender_CD="+entity.getGenderCode()+" ");
		}
		if(entity.getFirstname()!=null && !entity.getFirstname().isEmpty()){
			whereArg.add(" PatFirstName='"+entity.getFirstname()+"' ");
		}
		if(entity.getLastname()!=null && !entity.getLastname().isEmpty()){
			whereArg.add(" PatLastName='"+entity.getLastname()+"' ");
		}
		if(entity.getBirthday()!=null){
			//TODO Implement the Filter for the Date
		}
		if(entity.getAddress()!=null && !entity.getAddress().isEmpty()){
			whereArg.add(" PatAddress='"+entity.getAddress()+"' ");
		}
		if(entity.getPlz()!=null && !entity.getPlz().isEmpty()){
			whereArg.add(" PatPLZ='"+entity.getPlz()+"' ");
		}
		if(entity.getPlace()!=null && !entity.getPlace().isEmpty()){
			whereArg.add(" PatPlace='"+entity.getPlace()+"' ");
		}
		if(entity.getAhv()!=null && !entity.getAhv().isEmpty()){
			whereArg.add(" PatAhv='"+entity.getAhv()+"' ");
		}
		if(entity.getInsuranceNumber()!=null && !entity.getInsuranceNumber().isEmpty()){
			whereArg.add(" PatInsuranceNumber='"+entity.getInsuranceNumber()+"' ");
		}

		return jdbcTemplateObject.query(sql, new PatientMapper());
	}

	@Override
	public int delete(int id) {
		String sql = "DELETE FROM t_Patient WHERE PatID="+id;
		//TODO Clear result handling with statement
		jdbcTemplateObject.execute(sql);
		return 1;
	}

	@Override
	public int update(int id, Patient newEntity) {
		String sqlUpdate = " UPDATE `t_Patient` "
						 + " SET "
						 + " `PatGender_CD` = ?, "
						 + " `PatLastName` = ?, "
						 + " `PatFirstName` = ?, "
						 + " `PatBirthdate` = ?, "
						 + " `PatAddress` = ?, "
						 + " `PatPLZ` = ?, "
						 + " `PatPlace` = ?, "
						 + " `PatInsuranceNumber` = ?, "
						 + " `PatAhv` = ? "
						 + " WHERE `PatID` = ?;";
		Connection connection = null;
		int affectedRows = 0;
		if(newEntity.isValid(null)){
			try {
				//prepare the connection
				connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(sqlUpdate,Statement.RETURN_GENERATED_KEYS);
				//set the new Values
				statement.setInt(1, newEntity.getGenderCode());
				statement.setString(2, newEntity.getLastname());
				statement.setString(3, newEntity.getFirstname());
				statement.setDate(4, new java.sql.Date(Date.from(newEntity.getBirthday().atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime()));
				statement.setString(5, newEntity.getAddress());
				statement.setString(6, newEntity.getPlz());
				statement.setString(7, newEntity.getPlace());
				statement.setString(8, newEntity.getInsuranceNumber());
				statement.setString(9, newEntity.getAhv());
				statement.setInt(10, id);
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
