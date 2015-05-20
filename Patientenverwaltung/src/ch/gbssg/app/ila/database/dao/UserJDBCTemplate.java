package ch.gbssg.app.ila.database.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.mysql.jdbc.StringUtils;

import ch.gbssg.app.ila.database.mapper.UserMapper;
import ch.gbssg.app.model.User;
import ch.gbssg.app.util.UserRoll;
import ch.gbssg.core.ICrud;
import ch.gbssg.core.IFilterEntity;

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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public User getById(int id) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(int id, User newEntity) {
		// TODO Auto-generated method stub
		return 0;
	}
}
