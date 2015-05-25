package ch.gbssg.app.ila.database.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import ch.gbssg.app.ila.database.mapper.FakturaMapper;
import ch.gbssg.app.ila.database.mapper.MedicalHistoryMapper;
import ch.gbssg.app.model.Faktura;
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MedicalHistory getById(int id) {
		// TODO Auto-generated method stub
		return null;
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
		if(entity.getBillState()>0){
			whereArg.add(" MedHBillState_CD="+entity.getBillState()+" ");
		}
		if(entity.getBillDueTo()!=null){
			//TODO Implement the Filter for the Date
		}
		
		return jdbcTemplateObject.query(sql, new MedicalHistoryMapper());
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(int id, MedicalHistory newEntity) {
		// TODO Auto-generated method stub
		return 0;
	}
}
