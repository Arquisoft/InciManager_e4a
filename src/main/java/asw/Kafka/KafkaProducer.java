package asw.Kafka;

import asw.dbManagement.entities.Incidence;
import asw.dbManagement.entities.Notification;

public interface KafkaProducer {
	
	public void sendNuevaIncidencia(Notification notification);

	public void sendNuevaIncidencia(Incidence incidence);


	
}