package woodspring.springwellconsumer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import woodspring.springwellconsumer.service.ConsumerKafkaService;
import woodspring.springwellconsumer.service.impl.StockFeedKafkaService;


@RestController
@RequestMapping("/kafka")
public class KafkaConsumerController {
	
	
	private final static Logger logger = LoggerFactory.getLogger(KafkaConsumerController.class);
	@Autowired
    private  ConsumerKafkaService consumerService;
	
	@Autowired
	private StockFeedKafkaService stockFeedSrv;

    @GetMapping(value = "/consumer")
    public String listenMessage() {
    	
    	logger.info(" consumer start listening ============================ ");
    	
    	String retStr = consumerService.startListening();
    	
    	logger.info(" consumer get message=========[{}] ", retStr);
        return retStr;
    }
    
    @GetMapping(value = "/stock")
    public String listenStockFeed() {
    	
    	logger.info(" consumer start listenStockFeed ============================ ");
    	
    	String retStr = stockFeedSrv.startListen();
    	
    	logger.info(" consumer get message=========[{}] ", retStr);
        return retStr;
    }

}
