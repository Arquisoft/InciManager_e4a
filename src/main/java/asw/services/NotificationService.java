package asw.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import asw.dbManagement.entities.Notification;
import asw.dbManagement.repositories.NotificationRepository;

@Service
public class NotificationService {
	
	@Autowired
	NotificationRepository notificationRepository;

	public void addIncident(Notification n1) {
		notificationRepository.save(n1);		
	}
	
}
