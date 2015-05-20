package ch.gbssg.app.ila.database;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ch.gbssg.app.ila.database.dao.CodeJDBCTemplate;
import ch.gbssg.app.ila.database.dao.UserJDBCTemplate;
import ch.gbssg.app.model.Code;
import ch.gbssg.app.model.User;

public class DatabaseModel {
 // wenn u1 != null
		// jdbctamplte.get(p1)
 // jdbctample.get();
	private static ApplicationContext ctx;
	
	private UserJDBCTemplate userJdbcTemplate;
	private CodeJDBCTemplate codeJdbcTemplate;
	public DatabaseModel() {
		// include the beans config file.
		ctx = new ClassPathXmlApplicationContext("Beans.xml");
		
		userJdbcTemplate = (UserJDBCTemplate)ctx.getBean("UserJDBCTemplate");
		codeJdbcTemplate = (CodeJDBCTemplate)ctx.getBean("CodeJDBCTemplate");
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
	
	/**
	 * returns a list of matches Codes. don't found any matches, return null.
	 * @param user filtered Code; by null return all Codes
	 * @return get a list of matches users which matches with user parameter
	 */
	public List<Code> getFilteredCode(Code code) {
		if (code != null) {
			return  codeJdbcTemplate.filterByEntity(code);
		} else {
			return codeJdbcTemplate.get();
		}
	}
}
