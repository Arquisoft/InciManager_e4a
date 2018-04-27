package asw.Kafka;



import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.google.gson.Gson;

import asw.Kafka.Util.Topics;
import asw.dbManagement.entities.Incidence;
import asw.dbManagement.entities.Notification;

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
		send(Topics.NEW_INDIDENCE, gson.toJson(notification));
		
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
