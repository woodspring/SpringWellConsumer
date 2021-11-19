package woodspring.springwellconsumer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import woodspring.springwellconsumer.service.ConsumerKafkaService;


@RestController
@RequestMapping("/kafka")
public class KafkaConsumerController {
	
	
	private final static Logger logger = LoggerFactory.getLogger(KafkaConsumerController.class);
	@Autowired
    private  ConsumerKafkaService consumerService;

    @GetMapping(value = "/consumer")
    public String listenMessage() {
    	
    	logger.info(" consumer start listening ============================ ");
    	
    	String retStr = consumerService.startListening();
    	
    	logger.info(" consumer get message=========[{}] ", retStr);
        return retStr;
    }

}
