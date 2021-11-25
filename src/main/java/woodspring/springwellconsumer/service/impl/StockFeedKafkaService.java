package woodspring.springwellconsumer.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import com.google.gson.Gson; 
import woodspring.springwellconsumer.entity.StockFeed;

@Service
public class StockFeedKafkaService {
	private final static Logger logger = LoggerFactory.getLogger(StockFeedKafkaService.class);
	
	private StringBuffer dataBuf = new StringBuffer();
	private boolean done = false;
	
	@KafkaListener(topics = "${tpd.topic-name}", groupId = "${spring.kafka.consumer.group-id}")
	public String consume(String message) {
		logger.info("receive msg:{}", message);
		return message;
	}

	@KafkaListener(topics = "${tpd.topic-name}", groupId = "${spring.kafka.consumer.group-id}",
			containerFactory = "kafkaListenerStockFeedContainerFactory")
	public StockFeed consume(@Payload StockFeed stockFeed) {
		logger.info("receive StockFeed:{}", stockFeed.toString());
		done = true;
		Gson gson = new Gson(); 
		String json = gson.toJson(stockFeed);
		dataBuf.append(json);

		return stockFeed;
	}

	public String startListen() {
		dataBuf = new StringBuffer();
		done = false;
		while ( !done) {
			//try {
				//Thread.sleep(1000);
			//} catch (InterruptedException e) {
				// TODO Auto-generated catch block
			//	e.printStackTrace();
			//}
		};
	//String retStr = consumerTmp.listenGetString(null, null)
		return dataBuf.toString();
	}
}
