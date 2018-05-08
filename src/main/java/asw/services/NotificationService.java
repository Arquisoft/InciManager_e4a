package asw.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asw.dbManagement.entities.AgentPO;
import asw.dbManagement.entities.Notification;
import asw.dbManagement.repositories.NotificationRepository;

@Service
public class NotificationService {
	
	@Autowired
	NotificationRepository notificationRepository;

	public Notification addIncident(Notification n1) {
		return notificationRepository.save(n1);		
	}

	public List<Notification> getNotifications(String id) {
		List<Notification> exit = new ArrayList<Notification>(); 
		notificationRepository.findAll().forEach(p -> {if (p.getIncidencia().getAgent().equals(id)) exit.add(p); });
		return exit;
		
	}
	
}
