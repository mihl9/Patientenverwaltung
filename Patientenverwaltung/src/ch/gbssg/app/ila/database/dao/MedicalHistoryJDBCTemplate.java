package ch.gbssg.app.ila.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZoneId;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import ch.gbssg.app.ila.database.mapper.MedicalHistoryMapper;
import ch.gbssg.app.model.MedicalHistory;
import ch.gbssg.core.ICrud;

public class MedicalHistoryJDBCTemplate implements ICrud<MedicalHistory> {
	private JdbcTemplate jdbcTemplateObject;
    private DataSource dataSource;
    
	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public int create(MedicalHistory entity) {
		Connection connection = null;
		int generatedKey=0;
		String sqlInsert = " INSERT INTO t_MedicalHistory "
						   + " (`MedHUsrID_FK`,`MedHPatID_FK`,`MedHHour`,`MedHSymptoms`,`MedHDiagnostic`,`MedHNotes`,`MedHBillState_CD`,`MedHBillDueTo`,`MedHDateFrom`) "
						   + " VALUES(?,?,?,?,?,?,?,?,?);";
		if(entity.isValid(null)){
			try {
				//prepare the connection
				connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(sqlInsert,Statement.RETURN_GENERATED_KEYS);
				//set the new Values
				statement.setInt(1, entity.getUserId());
				statement.setInt(2, entity.getPatientId());
				statement.setDouble(3, entity.getHour());
				statement.setString(4, entity.getSymptoms());
				statement.setString(5, entity.getDiagnostic());
				statement.setString(6, entity.getNotes());
				statement.setInt(7, entity.getBillState());
				statement.setDate(8, new java.sql.Date(Date.from(entity.getBillDueTo().atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime()));
				statement.setDate(9, new java.sql.Date(Date.from(entity.getDateFrom().atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime()));
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
	public MedicalHistory getById(int id) {
		String sql = "SELECT * FROM t_MedicalHistory WHERE MedHId=" + id;
		List<MedicalHistory> rs = jdbcTemplateObject.query(sql, new MedicalHistoryMapper());
		if(rs.size()==1){
			return rs.get(0);
		}else{
			return null;
		}
	}

	@Override
	public List<MedicalHistory> get() {
		String sql = "SELECT * FROM t_MedicalHistory";
		return jdbcTemplateObject.query(sql, new MedicalHistoryMapper());
	}
	
	@Override
	public List<MedicalHistory> filterByEntity(MedicalHistory entity) {
		List<String> whereArg;
		String sql=" SELECT * FROM t_MedicalHistory ";
		whereArg = new ArrayList<String>();
		/*go through each field and show if a filter should be set*/
		/*Medical History Infos*/
		if(entity.getId()>0){
			whereArg.add(" MedHId="+entity.getId()+" ");
		}
		if(entity.getUserId()>0){
			whereArg.add(" MedHUsrID_FK="+entity.getUserId()+" ");
		}
		if(entity.getPatientId()>0){
			whereArg.add(" MedHPatID_FK="+entity.getPatientId()+" ");
		}
		if(entity.getDateFrom()!=null){
			//TODO Implement the Filter for the Date
		}
		if(entity.getHour()>0) {
			whereArg.add(" MedHHour="+entity.getHour()+" ");
		}
		if(entity.getSymptoms()!=null && !entity.getSymptoms().isEmpty()){
			whereArg.add(" MedHSymptoms='"+entity.getSymptoms()+"' ");
		}
		if(entity.getDiagnostic()!=null && !entity.getDiagnostic().isEmpty()){
			whereArg.add(" MedHDiagnostic='"+entity.getDiagnostic()+"' ");
		}
		if(entity.getNotes()!=null && !entity.getNotes().isEmpty()){
			whereArg.add(" MedHNotes='"+entity.getNotes()+"' ");
		}
		if(entity.getBillState()>0){
			whereArg.add(" MedHBillState_CD="+entity.getBillState()+" ");
		}
		if(entity.getBillDueTo()!=null){
			//TODO Implement the Filter for the Date
		}
		
		/*If any filter should be set add it to the query*/
		if(whereArg.size()>0){
			sql += " WHERE "+String.join("AND", whereArg);
		}
		
		return jdbcTemplateObject.query(sql, new MedicalHistoryMapper());
	}

	@Override
	public int delete(int id) {
		String sql = "DELETE FROM t_MedicalHistory WHERE MedHId="+id;
		//TODO Clear result handling with statement
		jdbcTemplateObject.execute(sql);
		return 1;
	}

	@Override
	public int update(int id, MedicalHistory newEntity) {
		String sqlUpdate = " UPDATE `t_MedicalHistory` "
						 + " SET "
						 + " `MedHUsrID_FK` = ?, "
						 + " `MedHPatID_FK` = ?, "
						 + " `MedHHour` = ?, "
						 + " `MedHSymptoms` = ?, "
						 + " `MedHDiagnostic` = ?, "
						 + " `MedHNotes` = ?, "
						 + " `MedHBillState_CD` = ?, "
						 + " `MedHBillDueTo` = ?, "
						 + " `MedHDateFrom` = ? "
						 + " WHERE `MedHId` = ?;";
		Connection connection = null;
		int affectedRows = 0;
		if(newEntity.isValid(null)){
			try {
				//prepare the connection
				connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(sqlUpdate,Statement.RETURN_GENERATED_KEYS);
				//set the new Values
				statement.setInt(1, newEntity.getUserId());
				statement.setInt(2, newEntity.getPatientId());
				statement.setDouble(3, newEntity.getHour());
				statement.setString(4, newEntity.getSymptoms());
				statement.setString(5, newEntity.getDiagnostic());
				statement.setString(6, newEntity.getNotes());
				statement.setInt(7, newEntity.getBillState());
				statement.setDate(8, new java.sql.Date(Date.from(newEntity.getBillDueTo().atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime()));
				statement.setDate(9, new java.sql.Date(Date.from(newEntity.getDateFrom().atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime()));
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
