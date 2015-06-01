package ch.gbssg.core.pac;

import java.util.ArrayList;

/**
 * abstract class for pac pattern - controller
 * @author Pedrett Sandro
 * @version 1.0
 */
public abstract class AgentController implements IAgentController {
	/**
	 * Create a new control
	 */
	public AgentController() {
		children = new ArrayList<IAgentController>();
		
		setupAgent();
	}

	/**
	 * is called by create an agent. and setup default values.
	 * @return true if success; otherwise false
	 */
	public abstract boolean setupAgent();
	
	/**
	 * add a child agent control
	 * @param child agent to add to control
	 */
	public void addChild(IAgentController child) {
		child.setParent(this);
		children.add(child);
	}
	
	/**
	 * remove a child agent control
	 * @param child agent to remove from control
	 */
	public void removeCildren(IAgentController child) {
		child.setParent(null);
		if (children.contains(child)) {
			children.remove(child);
		}
	}
	
	/**
	 * handled message from other agents
	 * @param message to handle
	 */
	protected abstract void processMessage(AgentCommand messages);
	
	
	/* (non-Javadoc)
	 * @see ch.javachat.pac.PacControlIfc#getParent()
	 */
	@Override
	public IAgentController getParent() {
		return parent;
	}

	/* (non-Javadoc)
	 * @see ch.javachat.pac.PacControlIfc#setParent(ch.javachat.pac.PacControlIfc)
	 */
	@Override
	public void setParent(IAgentController parent) {
		this.parent = parent;
	}

	/* (non-Javadoc)
	 * @see ch.javachat.pac.PacControlIfc#receiveMessage(ch.javachat.pac.Message, ch.javachat.pac.PacControlIfc)
	 */
	@Override
	public void receiveMessage(AgentCommand message, IAgentController sender) {	
		processMessage(message);
		
		// check if message empty
		if (message.size() > 0) {
			if (getParent() == sender && getParent() != null) { // message from parent
				// send to all children
				for (IAgentController childControl : children) {
					childControl.receiveMessage(message, this);
					if (message.size() == 0) {
						break;
					}
				}
			} else { // message from children
				// send to all chlidren (exept to the sender)
				for (IAgentController childControl : children) {
					if (childControl != message.getSender()) {
						childControl.receiveMessage(message, this);
						if (message.size() == 0) {
							break;
						}
					}
				}
				if (getParent() != null && message.size() != 0) {
					// send message to parent
					getParent().receiveMessage(message, this);	
				}				
			}
		}
	}
	
	/**
	 * send message to dependency agents.
	 * <ul>
	 * <li> 1. Add this Agent as the Sender</li>
     * <li> 2. Send to all children (if exists), abort when finished</li>
     * <li> 3. Send message to parent (if exists and message not empty)</li>
     * </ul>
	 * @param message to send to agents.
	 */
	protected void sendAgentMessage(AgentCommand message) {
		// 1.
		if (message.getSender() == null) {
			message.setSender(this);
		}
		
		// 2.
		for (IAgentController child : children) {
			child.receiveMessage(message, this);
			if (message.size() == 0) {
				break;
			}
		}
		
		// 3.
		if (message.size() != 0 && getParent() != null) {
			getParent().receiveMessage(message, this);
		}
	}
	
	/**
	 * send message to agent
	 * @param agent destination agent
	 * @param message to send to agent
	 */
	protected void sendAgentMessage(IAgentController agent, AgentCommand message) {
		if (message.getSender() == null) {
			message.setSender(this);
		}
		
		agent.receiveMessage(message, this);
	}
	
	/**
	 * a list of all children
	 */
	protected ArrayList<IAgentController> children;
	
	/**
	 * parent of this agent controller
	 */
	private IAgentController parent;
}
