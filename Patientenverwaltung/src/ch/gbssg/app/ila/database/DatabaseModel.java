package ch.gbssg.app.ila.database;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ch.gbssg.app.ila.database.dao.UserJDBCTemplate;
import ch.gbssg.app.model.User;

public class DatabaseModel {
 // wenn u1 != null
		// jdbctamplte.get(p1)
 // jdbctample.get();
	private static ApplicationContext ctx;
	
	private UserJDBCTemplate userJdbcTemplate;
	
	public DatabaseModel() {
		// include the beans config file.
		ctx = new ClassPathXmlApplicationContext("Beans.xml");
		
		userJdbcTemplate = (UserJDBCTemplate)ctx.getBean("UserJDBCTemplate");
	}
	
	/**
	 * returns a list of matches user. don't found any matches, return null.
	 * @param user filtered user; by null return all users
	 * @return get a list of matches users which matches with user parameter
	 */
	public List<User> getFilteredUser(User user) {
		if (user != null) {
			return  userJdbcTemplate.filterByEntity(user);
		} else {
			return userJdbcTemplate.get();
		}
	}
}
