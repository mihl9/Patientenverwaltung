package ch.gbssg.core.pac;

import org.junit.Assert;
import org.junit.Test;

import ch.gbssg.core.pac.fake.FakeCommand;

public class AgentCommandTest {
	@Test
	public void testCreateAgentCommand() {
		AgentCommand cmd = new AgentCommand();
		Assert.assertTrue(cmd.size() == 0);
		
		AgentCommand cmd2 = new AgentCommand(new FakeCommand());
		Assert.assertTrue(cmd2.size() == 1);
	}
	
	@Test
	public void testAddCommand() {
		AgentCommand cmd = new AgentCommand();
		cmd.add(new FakeCommand());
		
		Assert.assertTrue(cmd.size() == 1);
	}
	
	@Test
	public void testPeekPoll() {
		FakeCommand fake1 = new FakeCommand();
		
		AgentCommand cmd = new AgentCommand();
		cmd.add(fake1);
		
		FakeCommand fake1Equals1 = (FakeCommand)cmd.peek();
		Assert.assertEquals(fake1, fake1Equals1);
		
		FakeCommand fake1Equals2 = (FakeCommand)cmd.poll();
		Assert.assertEquals(fake1, fake1Equals2);
		Assert.assertTrue(cmd.size() == 0);
		
		FakeCommand fake1Equals3 = (FakeCommand)cmd.poll();
		Assert.assertNull(fake1Equals3);
	}

}
