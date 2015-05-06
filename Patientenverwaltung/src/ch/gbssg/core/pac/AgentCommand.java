/**
 * 
 * @modtime 13:29:05
 * @author pedrett
 */
package ch.gbssg.core.pac;

import java.util.LinkedList;



/**
 * Command wrapper for agent commands. This instance includs more than one
 * commands for an agent. It's implemented like a queue. First input - First output
 * 
 * @author pedrett
 * @version 1.0
 */
public class AgentCommand implements ICommand {

	/**
	 * sender which send this wrapper command
	 */
	private IAgentController sender;
	
	/**
	 * list of all queue.
	 */
	private LinkedList<ICommand> commandQueue;
	
	/**
	 * Create a new command wrapper for send data or commands to other agents.
	 * 
	 * @param command Command to send to agent
	 */
	public AgentCommand(ICommand command) {
		commandQueue = new LinkedList<ICommand>();
		if (command != null) {
			add(command);
		}
	}
	
	/**
	 * Create a new empty command Wrapper.
	 */
	public AgentCommand() {
		this(null);
	}
	
	/**
	 * Peek the first message from queue.
	 * @return
	 */
	public ICommand peek() {
		return commandQueue.peekFirst();
	}
	
	/**
	 * Retrieves and removes the first element of this list, or returns null if this list is empty.
	 * @return the first element; otherwise null if the list empty
	 */
	public ICommand poll() {
		return commandQueue.pollFirst();
	}
	
	/**
	 * Add an item to end of the list.
	 * @param command Command add to queue.
	 */
	public void add(ICommand command) {
		this.commandQueue.add(command);
	}
	
	/**
	 * Return the size of current queue-list.
	 * @return size of list.
	 */
	public int size() {
		return commandQueue.size();
	}
	
	/**
	 * Give the sender of this command wrapper.
	 * @return the sender
	 */
	public IAgentController getSender() {
		return sender;
	}
	
	/**
	 * Set the sender of this command wrapper.
	 * 
	 * @param sender sender to set
	 */
	public void setSender(IAgentController sender) {
		this.sender = sender;
	}
}
