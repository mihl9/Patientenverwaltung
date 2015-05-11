package ch.gbssg.app.model;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;


public class UserTest {

	@Test
	public void testUserIsInValid() {
		List<String> errors = new ArrayList<String>();
		
		// invalid user
		User u = new User();
		Assert.assertTrue(!u.isValid(errors));
		Assert.assertTrue(errors.size() >= 1);
		
		errors.clear();
		User u1 = new User();
		u1.setLoginname("Admin");
		Assert.assertTrue(!u1.isValid(errors));
		Assert.assertTrue(errors.size() >= 1);
		
		errors.clear();
		User u2 = new User();
		u2.setPassword("pass");
		Assert.assertTrue(!u2.isValid(errors));
		Assert.assertTrue(errors.size() >= 1);
	}

	@Test
	public void testUserIsValid() {
		List<String> errors = new ArrayList<String>();
	
		// valid user
		User u1 = new User();
		u1.setLoginname("Admin");
		u1.setPassword("test");
		Assert.assertTrue(u1.isValid(errors));
	}
}
