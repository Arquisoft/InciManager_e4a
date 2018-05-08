package asw.agent;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import asw.dbManagement.entities.AgentPO;
import asw.dbManagement.entities.LoginPO;

@Service
public class GetAgentInfo {

	public AgentPO checkUserAndPass(String user, String pass, String kind) {
		final String uri = "http://ec2-54-149-62-135.us-west-2.compute.amazonaws.com:8081/user";

		RestTemplate restTemplate = new RestTemplate();

		// String body = "{ 'login': '13864928P', 'password': '123456','kind': 'Person'
		// }";
		LoginPO login = new LoginPO(user, pass, kind);
		AgentPO found = null;
		HttpEntity<LoginPO> request = new HttpEntity<>(login);
		try {
			found = (AgentPO) restTemplate.postForObject(uri, request, AgentPO.class);
			System.out.println(found.nombre);

		} catch (HttpClientErrorException e) {
			if (e.getRawStatusCode() == 404) {
				System.out.println("No encontrado");

			}
		}
		
		return found;
	}

}
