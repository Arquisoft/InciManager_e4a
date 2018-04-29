package asw.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


import asw.dbManagement.entities.Agent;
import asw.dbManagement.entities.AgentPO;
import asw.dbManagement.entities.LoginPO;
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
	
	public AgentPO checkUserAndPass(String user,String pass, String kind) {
		final String uri = "http://localhost:8081/user";

		RestTemplate restTemplate = new RestTemplate();
		
		//String body = "{ 'login': '13864928P', 'password': '123456','kind': 'Person' }";
		LoginPO login = new LoginPO(user, pass, kind);
		AgentPO found = null;
		HttpEntity<LoginPO> request = new HttpEntity<>(login);
		try {
			found = (AgentPO)restTemplate.postForObject(uri, request, AgentPO.class);
			System.out.println(found.nombre);

		}
		catch (HttpClientErrorException e) {
			if(e.getRawStatusCode() == 404) {
				System.out.println("No encontrado");			
				
			}
		}
		
		return found;	
	}

}
