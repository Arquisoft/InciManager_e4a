package asw.Kafka.Util;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
@EnableKafka
public class KafkaProducerConfig {
	// CONFIGURACION PRODUCTOR DE MENSAJES

	@Value("${CLOUDKARAFKA_SERVERS}")
	String server;
	@Value("${CLOUDKARAKFA_USERNAME}")
	String username;
	@Value("${CLOUDKARAKFA_PASSWORD}")
	String password;


	@Bean
	public KafkaTemplate<String, String> kafkaTemplate() {
		return new KafkaTemplate<String, String>(producerFactory());
	}

	@Bean
	public ProducerFactory<String, String> producerFactory() {
		return new DefaultKafkaProducerFactory<String, String>(producerConfigs());
	}

	@Bean
	public Map<String, Object> producerConfigs() {
		String jaasTemplate = "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"%s\" password=\"%s\";";
		String jaasCfg = String.format(jaasTemplate, username, password);
		Map<String, Object> props = new HashMap<String, Object>();
		props.put("bootstrap.servers", server);
		props.put("group.id", username + "-consumer");
		props.put("enable.auto.commit", "true");
		props.put("auto.commit.interval.ms", "1000");
		props.put("auto.offset.reset", "earliest");
		props.put("session.timeout.ms", "30000");
		props.put("key.serializer", StringSerializer.class);
		props.put("value.serializer", StringSerializer.class);
		props.put("security.protocol", "SASL_SSL");
		props.put("sasl.mechanism", "SCRAM-SHA-256");
		props.put("sasl.jaas.config", jaasCfg);
		return props;
	}

}
