package ch.gbssg.app.ila.database.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.mysql.jdbc.StringUtils;

import ch.gbssg.app.ila.database.mapper.FakturaMapper;
import ch.gbssg.app.ila.database.mapper.UserMapper;
import ch.gbssg.app.model.Faktura;
import ch.gbssg.app.model.User;
import ch.gbssg.app.util.UserRoll;
import ch.gbssg.core.ICrud;
import ch.gbssg.core.IFilterEntity;

public class FakturaJDBCTemplate implements ICrud<Faktura> {
	private JdbcTemplate jdbcTemplateObject;
    private DataSource dataSource;
    
	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public int create(Faktura entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Faktura getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Faktura> get() {
		String sql = "SELECT * FROM v_Faktura";
		return jdbcTemplateObject.query(sql, new FakturaMapper());
	}
	
	@Override
	public List<Faktura> filterByEntity(Faktura entity) {
		List<String> whereArg;
		String sql=" SELECT * FROM v_Faktura ";
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
		/*Patient Information*/
		if(entity.getGenderCode()>0){
			whereArg.add(" PatGender_CD="+entity.getGenderCode()+" ");
		}
		if(entity.getFirstname()!=null && !entity.getFirstname().isEmpty()){
			whereArg.add(" PatFirstName='"+entity.getFirstname()+"' ");
		}
		if(entity.getLastname()!=null && !entity.getLastname().isEmpty()){
			whereArg.add(" PatLastName='"+entity.getLastname()+"' ");
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
		/*User Information*/
		if(entity.getUsrFirstname()!=null && !entity.getUsrFirstname().isEmpty()){
			whereArg.add(" UsrFirstName='"+entity.getUsrFirstname()+"' ");
		}
		if(entity.getUsrLastname()!=null && !entity.getUsrLastname().isEmpty()){
			whereArg.add(" UsrLastName='"+entity.getUsrLastname()+"' ");
		}
		if(entity.getHourlyWage()>0){
			whereArg.add(" UsrHourlyWage="+entity.getHourlyWage()+" ");
		}
		/*If any filter should be set add it to the query*/
		if(whereArg.size()>0){
			sql += " WHERE "+String.join("AND", whereArg);
		}
		return jdbcTemplateObject.query(sql, new FakturaMapper());
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(int id, Faktura newEntity) {
		// TODO Auto-generated method stub
		return 0;
	}
}
