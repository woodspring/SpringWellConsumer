package woodspring.springwellconsumer.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import woodspring.springwellconsumer.listeners.ConsumerKafkaTemplate;
import woodspring.springwellconsumer.service.ConsumerKafkaService;


@Service
public class SimpleConsumerListening implements ConsumerKafkaService{
	
	private static final Logger logger = LoggerFactory.getLogger(SimpleConsumerListening.class );
	private static StringBuffer dataBuf;
	
	private static boolean done;
	
	@Autowired
	ConsumerKafkaTemplate consumerTmp;

	@Override
	public String startListening() {
		dataBuf = new StringBuffer();
		done = false;
		consumerTmp.setBuffer(dataBuf, done);
		while ( !done);
		//String retStr = consumerTmp.listenGetString(null, null)
		return dataBuf.toString();
		
	}

}
