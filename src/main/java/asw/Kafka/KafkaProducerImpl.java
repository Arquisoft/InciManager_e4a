package asw.Kafka;



import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import asw.Kafka.Util.Topics;
import asw.dbManagement.entities.Incidence;
import asw.dbManagement.entities.Notification;
import asw.json.NotificationSerializer;

@Service
public class KafkaProducerImpl implements KafkaProducer {

	
	private static final Logger logger = Logger.getLogger(KafkaProducerImpl.class);

	private KafkaTemplate<String, String> kafkaTemplate;

	private Gson gson; 
	@Autowired
	public KafkaProducerImpl(KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
		this.gson = new Gson(); 
	}
	
	
	@Override
	public void sendNuevaNotificacion(Notification notification) {
		
GsonBuilder gsonBuilder = new GsonBuilder() ;
		
		NotificationSerializer serializer = new NotificationSerializer();	
		
		gsonBuilder.registerTypeAdapter(Notification.class, serializer);
		
		Gson gson = gsonBuilder.create();
		String str = gson.toJson(notification);
		
		send(Topics.NEW_INDIDENCE, str);
		
	}
	
	
	
	public void send(String topic, String data) {
		ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, data);
		future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
			@Override
			public void onSuccess(SendResult<String, String> result) {
				logger.info("Success on sending message \"" + data + "\" to topic " + topic);
			}

			@Override
			public void onFailure(Throwable ex) {
				logger.error("Error on sending message \"" + data + "\", stacktrace " + ex.getMessage());
			}
		});
	}


	@Override
	public void sendNuevaNotificacion(Incidence incidence) {
		send(Topics.NEW_INDIDENCE, "{ \"Nueva incidencia \":\"" + incidence + "\"}");
		
	}


	
}
