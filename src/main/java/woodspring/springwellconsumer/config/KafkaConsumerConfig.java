package woodspring.springwellconsumer.config;

import org.apache.kafka.common.serialization.ByteArrayDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
public class KafkaConsumerConfig {
	
	@Autowired
	private KafkaProperties kafkaProperties;

	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServers;
	
	@Value("${tpd.topic-name}")
	private String topicName;
	
	@Bean
	public ConsumerFactory<String, Object> consumerFactory() {
		final JsonDeserializer<Object> jsonDeserializer = new JsonDeserializer<>();
		jsonDeserializer.addTrustedPackages("*");
		return new DefaultKafkaConsumerFactory<>(kafkaProperties.buildConsumerProperties(), 
						new StringDeserializer(), jsonDeserializer);
	}
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}
	
	// String Consumer configuration
	
	@Bean
	public ConsumerFactory<String, String> stringConsumerFactory() {

		return new DefaultKafkaConsumerFactory<>(kafkaProperties.buildConsumerProperties(), 
						new StringDeserializer(), new StringDeserializer());
	}
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerStringContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(stringConsumerFactory());
		return factory;
	}
	
	@Bean
	public ConsumerFactory<String, byte[]> byteArrayConsumerFactory() {

		return new DefaultKafkaConsumerFactory<>(kafkaProperties.buildConsumerProperties(), 
						new StringDeserializer(), new ByteArrayDeserializer());
	}
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, byte[]> kafkaListenerByteArrayContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, byte[]> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(byteArrayConsumerFactory());
		return factory;
	}
	
	
	
	
}
