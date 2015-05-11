package ch.gbssg.core.pac;

import org.junit.Assert;
import org.junit.Test;

import ch.gbssg.core.pac.fake.FakeController;

public class AgentFactoryTest {

	@Test
	public void testRequestNewAgent() {
		// create new agent
		FakeController fake = AgentFactory.getInstance().requestAgent(FakeController.class);
		Assert.assertNotNull(fake);
		
		// get same agent
		FakeController fakeEquals = AgentFactory.getInstance().requestAgent(FakeController.class);
		Assert.assertEquals(fake, fakeEquals);
		
		// remove agent
		AgentFactory.getInstance().releaseAgent(fake);
		
		// get new same agent
		FakeController fakeNew = AgentFactory.getInstance().requestAgent(FakeController.class);
		Assert.assertNotNull(fakeNew);
		Assert.assertNotEquals(fakeNew, fakeEquals);
	}

}
