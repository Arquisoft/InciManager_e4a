package Kafka;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaTest {

	@Autowired
	private KafkaProducerImpl producer;
	
	private Set<String> expectedMessages;
	private Set<String> unexpectedMessages;

	public KafkaTest() {
		resetMessages();
	}

	@Before
	public void setUp() throws Exception {
		Thread.sleep(2000);
		resetMessages();
	}

	private void resetMessages() {
		expectedMessages = Collections
				.synchronizedSet(new HashSet<>());
		unexpectedMessages = Collections
				.synchronizedSet(new HashSet<>());
	}

	

	@KafkaListener(topics = "newVote")
	public void listen(String message) {
		if (!expectedMessages.remove(message)) {
			unexpectedMessages.add(message);
		}
	}
}