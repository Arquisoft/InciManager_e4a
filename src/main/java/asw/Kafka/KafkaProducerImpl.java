package asw.Kafka;



import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import asw.Kafka.Util.Topics;


public class KafkaProducerImpl implements KafkaProducer {

	
	private static final Logger logger = Logger.getLogger(KafkaProducerImpl.class);

	private KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	public KafkaProducerImpl(KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}
	
	
	@Override
	public void sendNuevaIncidencia(Long idIncidence) {
		send(Topics.NEW_INDIDENCE, "{ \"Nueva incidencia con id\":\"" + idIncidence + "\"}");
		
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

}
