package ch.gbssg.core.pac.fake;

import ch.gbssg.core.pac.AgentCommand;
import ch.gbssg.core.pac.AgentController;
import ch.gbssg.core.pac.IAgentController;
import ch.gbssg.core.pac.ICommand;

public class FakeController extends AgentController {

	@Override
	public boolean setupAgent() {
		return true;
	}

	@Override
	protected void processMessage(AgentCommand messages) {
		ICommand cmd = messages.peek();
		
		if (cmd instanceof FakeCommand) {
			FakeCommand fakeCmd = (FakeCommand)messages.poll();
			FakeTest();
		}
	}


	public void FakeTest() {
		
	}
	
	public void Send() {
		sendAgentMessage(new AgentCommand(new FakeCommand()));
	}
}
