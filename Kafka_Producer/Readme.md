Kafka Producer

1. Create a SpringBoot Project form Spring Initializer

2. add the dependencies of web, devtools, actuator, kafka

3. start the zookeeper service
	bin\windows\zookeeper-server-start.bat config\zookeeper.properties

4. start the kafka service
	bin\windows\kafka-server-start.bat config\server.properties

5. create a sample topic
	bin\windows\kafka-topics.bat --create --zookeeper localhost:2182 --partitions 1 --replication-factor 1 --topic Kafka_Example

6. Listen for the Changes on the topic
	bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic Kafka_Example --from-beginning


7. Kafka Publisher using simple String
	Create a class UserResource

	Add annotation @RestController


	@Autowired
    KafkaTemplate<String, String> kafkaTemplate;


    private static final String TOPIC = "Kafka_Example";
    
    @GetMapping("/publish/{message}")
    public String post(@PathVariable("message") final String message){
                
        kafkaTemplate.send(TOPIC, message);
    	
        return "Published Sucessfully";
    }


8. Kafka Publisher using JSON Object

	Create a package model

	create class User with fields Name, Department, Salary

9. Create a serializer class - KafkaSerializer	
	@Configuration
	public class KafkaSerializer {

	    @Bean
	    public ProducerFactory<String,User> producerFactory() {
	        Map<String, Object> configs = new HashMap<>();
	        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
	        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
	        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,JsonSerializer.class);
	        return new DefaultKafkaProducerFactory<>(configs);
	    }

	    @Bean
	    public KafkaTemplate<String, User> kafkaTemplate(){
	        return new KafkaTemplate<String, User>(producerFactory());

	    }
	}


10.	Modify the Publisher as follows:

		@RestController
		@RequestMapping("kafka")
		public class UserResource {

			@Autowired
		    KafkaTemplate<String, User> kafkaTemplate;
			
			private static final String TOPIC = "Kafka_Example";
		    
		    @GetMapping("/publish/{message}")
		    public String post(@PathVariable("message") final String message){
		                
		        User user = new User("Baba", "bondu", "0.001");

				kafkaTemplate.send(TOPIC, user);
		    	
		        return "Published Sucessfully";
		    }
		}



		