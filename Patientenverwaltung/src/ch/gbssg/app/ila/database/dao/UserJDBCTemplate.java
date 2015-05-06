package ch.gbssg.app.ila.database.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import ch.gbssg.app.ila.database.ICrud;
import ch.gbssg.app.ila.database.mapper.UserMapper;
import ch.gbssg.app.model.User;

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
