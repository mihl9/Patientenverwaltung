package ch.gbssg.core.pac;

import java.util.ArrayList;

/**
 * Singleton factory for create new agents or return an existing agent.
 * @author Pedrett Sandro
 * @version 1.0
 */
public class AgentFactory {
	/**
	 * save instance for singleton.
	 */
	private static AgentFactory instance;
	
	/**
	 * Constructor. Create a instance of AganetFactory
	 */
	public AgentFactory() {
		agentInstanceList = new ArrayList<IAgentController>();
	}
	
	/**
	 * return agent, if exist, else create a new agent.
	 * @param agentClass class of agent which will be returned
	 * @return agent looking for agent
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("unchecked")
	public <T extends IAgentController> T requestAgent(Class<T> agentClass) {
		// serach in agentInstanceList for instance
		for (IAgentController pacControlIfc : agentInstanceList) {
			if (pacControlIfc.getClass() == agentClass) {
				return (T)pacControlIfc;
			}
		}
		
		// else create a new instance an return it.
		T agent = null;
		try {
			agent = agentClass.newInstance();
			agentInstanceList.add((IAgentController)agent);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return agent;
	}
	
	/**
	 * remove a agent
	 * @param agent to remove
	 */
	public void releaseAgent(IAgentController agent) {
		if (agentInstanceList.contains(agent)) {
			agentInstanceList.remove(agent);
		}
	}
	
	/**
	 * Create a instance of AgentFactory. If there already exist, return this.
	 * @return
	 */
	public static AgentFactory getInstance() {
		if (instance == null) {
			instance = new AgentFactory();
		}
		return instance;
	}
	
	/**
	 * a list of all agents in this app.
	 */
	private ArrayList<IAgentController> agentInstanceList;
}
