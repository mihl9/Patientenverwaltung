package ch.gbssg.core.pac;

import static org.mockito.Mockito.*;

import org.junit.Assert;
import org.junit.Test;

import ch.gbssg.core.pac.fake.FakeCommand;
import ch.gbssg.core.pac.fake.FakeController;

public class AgentControllerTest {

	@Test
	public void testSetupAgent() {
		FakeController fake = (FakeController) spy(new FakeController());
		Assert.assertTrue(fake.setupAgent());
		verify(fake, times(1)).setupAgent();
	}

	@Test
	public void testReceiveMessage() {
		FakeCommand cmd = new FakeCommand();
		FakeController fakeTop = (FakeController) spy(new FakeController());
		FakeController fakeMiddle = (FakeController) spy(new FakeController());
		FakeController fakeBottom = (FakeController) spy(new FakeController());
		
		fakeTop.addChild(fakeMiddle);
		fakeMiddle.addChild(fakeBottom);
		fakeMiddle.setParent(fakeTop);
		fakeBottom.setParent(fakeMiddle);
		
		fakeTop.Send();
		verify(fakeMiddle, times(1)).FakeTest();
		verify(fakeBottom, times(0)).FakeTest();
		verify(fakeTop, times(0)).FakeTest();
		
		fakeMiddle.Send();
		verify(fakeMiddle, times(1)).FakeTest();
		verify(fakeBottom, times(1)).FakeTest();
		verify(fakeTop, times(0)).FakeTest();
		
		fakeBottom.Send();
		verify(fakeMiddle, times(2)).FakeTest();
		verify(fakeBottom, times(1)).FakeTest();
		verify(fakeTop, times(0)).FakeTest();
	}
}
