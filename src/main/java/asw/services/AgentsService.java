package asw.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asw.dbManagement.entities.Agent;
import asw.dbManagement.repositories.AgentRepository;

@Service
public class AgentsService {
	
	@Autowired
	AgentRepository agentRepository;

	public void addAgent(Agent agent) {
		agentRepository.save(agent);
		
	}

	public void updateAgent(Agent agente1) {
		agentRepository.save(agente1);
		
	}
	
	public Agent getAgent(String identificador) {
		
		return agentRepository.findByDni(identificador);
	}
	
	public List<Agent> getAgents(){
		List<Agent> agents = new ArrayList<Agent>();
		agentRepository.findAll().forEach(agents::add);
		return agents;
	}
	
	public boolean checkUserAndPass(String user,String pass) {
		List<Agent> agents=getAgents();
		for (int i = 0; i < agents.size(); i++) {
			if(agents.get(i).getDni().equals(user) && (agents.get(i).getPassword().equals(pass))) {
				return true;
			}	
		}
		return false;	
	}

}
