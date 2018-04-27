package asw.Kafka;

import asw.dbManagement.entities.Incidence;
import asw.dbManagement.entities.Notification;

public interface KafkaProducer {
	
	public void sendNuevaNotificacion(Notification notification);

	public void sendNuevaNotificacion(Incidence incidence);


	
}