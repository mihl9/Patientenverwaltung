/**
 * 
 * @modtime 13:30:29
 * @author pedrett
 */
package ch.gbssg.core.pac;



/**
 * interface to agent control
 * @author pedrett
 * @version 1.0
 */
public interface IAgentController {
	/**
	 * get parent control
	 * @return parent control
	 */
	IAgentController getParent();
	
	/**
	 * set parent control
	 * @param parent to set
	 */
	void setParent(IAgentController parent);
	
	/**
	 * receive message from other agents
	 * <ul>
	 * <li>
	 * a) Message from parent:
	 * <ul> 
     * <li>1. Process inside the agent, abort when finished</li>
     * <li>2. Send to all children (if exists), abort when finished</li>
     * </ul>
     * </li>
     * <li>
	 * b) Message from children: 
	 * <ul>
	 * <li>1. Process inside the agent, abort when finished</li>
     * <li>2. Send to all children (except to the the sender), abort when finished</li>
     * <li>3. Send message to parent (if exists and message not empty)</li>
     * </ul>
	 * </li>
     * </ul>
	 * @param message queue the send to agents
	 * @param sender send which send message
	 */
	void receiveMessage(AgentCommand message, IAgentController sender);
}
