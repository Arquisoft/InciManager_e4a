package asw.Kafka;

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

	

	private KafkaTemplate<String, String> kafkaTemplate;

	private Gson gson;

	@Autowired
	public KafkaProducerImpl(KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
		this.gson = new Gson();
	}

	@Override
	public void sendNuevaNotificacion(Notification notification) {

		GsonBuilder gsonBuilder = new GsonBuilder();

		NotificationSerializer serializer = new NotificationSerializer();

		gsonBuilder.registerTypeAdapter(Notification.class, serializer);

		Gson gson = gsonBuilder.create();
		String str = gson.toJson(notification);

		send(Topics.NEW_INDIDENCE, str);

	}

	public void send(String topic, String data) {
		this.kafkaTemplate.send(topic, data);
		System.out.println("Success on sending message \"" + data + "\" to topic " + topic);
//		logger.info("Success on sending message \"" + data + "\" to topic " + topic);

	}

	@Override
	public void sendNuevaNotificacion(Incidence incidence) {
		send(Topics.NEW_INDIDENCE, "{ \"Nueva incidencia \":\"" + incidence + "\"}");

	}

}
