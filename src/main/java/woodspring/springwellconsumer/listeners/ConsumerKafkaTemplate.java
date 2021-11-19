package woodspring.springwellconsumer.listeners;

import java.util.stream.StreamSupport;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Headers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import woodspring.springwellconsumer.entity.StockFeed;


@Component
public class ConsumerKafkaTemplate {
	private final static Logger logger = LoggerFactory.getLogger(ConsumerKafkaTemplate.class);
	
	private static StringBuffer dataBuf;
	private static boolean done;
	
	@KafkaListener(topics = "northyork", clientIdPrefix = "json", containerFactory = "kafkaListenerContainerFactory") 
	public void listenAsObject(ConsumerRecord<String, StockFeed> conRec, @Payload StockFeed feed) {
		logger.info("Logger 1[JSON] received key: {} Type:[{}] payload:[{}]", 
				conRec.key(), typeIdHeader(conRec.headers()), feed, conRec.toString());
	}
	
	@KafkaListener(topics = "northyork", clientIdPrefix = "string",  containerFactory = "kafkaListenerContainerFactory") 
	public void listenAsString(ConsumerRecord<String, String> conRec, @Payload String message) {
		logger.info("Logger 2[String] received key: {} Type:[{}] payload:[{}] record:[{}]", 
				conRec.key(), typeIdHeader(conRec.headers()), message, conRec.toString());
		done = false;
		dataBuf.append(String.format("Logger 2[String] received key: %s Type:[%s] payload:[%s] record:[%s]", 
				conRec.key(), typeIdHeader(conRec.headers()), message, conRec.toString()));
		done = true;
		
	}
	
	@KafkaListener(topics = "northyork", clientIdPrefix = "string",  containerFactory = "kafkaListenerContainerFactory") 
	public String listenGetString(ConsumerRecord<String, String> conRec, @Payload String message) {
		logger.info("Logger 2[String] received key: {} Type:[{}] payload:[{}] record:[{}]", 
				conRec.key(), typeIdHeader(conRec.headers()), message, conRec.toString());
		done = false;
		dataBuf = new StringBuffer();
		dataBuf.append(String.format("Logger 2[String] received key: %s Type:[%s] payload:[%s] record:[%s]", 
				conRec.key(), typeIdHeader(conRec.headers()), message, conRec.toString()));
		done = true;
		return dataBuf.toString();
		
	}
	
	@KafkaListener(topics = "northyork", clientIdPrefix = "bytearray", containerFactory = "kafkaListenerContainerFactory") 
	public void listenAsByteArray(ConsumerRecord<String, StockFeed> conRec, @Payload StockFeed byteMessage) {
		logger.info("Logger 3[byteArray] received key: {} Type:[{}] payload:[{}]", 
				conRec.key(), typeIdHeader(conRec.headers()), byteMessage, conRec.toString());
	}
	
	private static String typeIdHeader(Headers headers) {
		return StreamSupport.stream(headers.spliterator(), false)
				.filter( header -> header.key().equalsIgnoreCase("__TypeId__"))
				.findFirst()
				.map( header -> new String(header.value()))
				.orElse("N/A");
	}
	
	public static boolean setBuffer(StringBuffer dataBuf_, boolean isDOne) {
		dataBuf = dataBuf_;
		done = isDOne;
		return done;
	}

}
