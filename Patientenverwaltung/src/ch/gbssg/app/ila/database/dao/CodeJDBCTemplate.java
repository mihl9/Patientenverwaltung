package ch.gbssg.app.ila.database.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import ch.gbssg.app.ila.database.mapper.CodeMapper;
import ch.gbssg.app.model.Code;
import ch.gbssg.core.ICrud;

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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Code getById(int id) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(int id, Code newEntity) {
		// TODO Auto-generated method stub
		return 0;
	}
}
