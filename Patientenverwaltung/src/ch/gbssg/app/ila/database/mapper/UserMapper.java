package ch.gbssg.app.ila.database.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ch.gbssg.app.model.User;
import ch.gbssg.app.util.UserRoll;
/**
 * This class defines the relation between the User Model and the table t_User
 * @author Michael Huber
 * @version 1.0
 */
public class UserMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("UsrId"));
		user.setRolle(UserRoll.convert(rs.getInt("UsrRollId_CD")));
		user.setFirstname(rs.getString("UsrFirstName"));
		user.setLastname(rs.getString("UsrLastName"));
		user.setLoginname(rs.getString("UsrLoginName"));
		user.setPassword(rs.getString("UsrPw"));
		user.setHourlyWage(rs.getDouble("UsrHourlyWage"));
		user.setInactive(rs.getBoolean("UsrInactive"));
		return user;
	}

}
