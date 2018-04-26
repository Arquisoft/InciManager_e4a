package asw.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import asw.dbManagement.entities.Notification;
import asw.dbManagement.repositories.NotificationRepository;

@Service
public class NotificationService {
	
	@Autowired
	NotificationRepository notificationRepository;

	public Notification addIncident(Notification n1) {
		return notificationRepository.save(n1);		
	}

	public List<Notification> getNotifications() {
		List<Notification> exit = new ArrayList<Notification>(); 
		notificationRepository.findAll().forEach(p -> exit.add(p));
		return exit;
		
	}
	
}
