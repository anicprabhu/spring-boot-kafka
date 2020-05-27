1. start a blank project from Start.spring.io, add dependencies of web, kafka, actuator, devtools, 

2. create a class KafkaConsumer, annotate with @Service

3. create a Kafka Listener
	@KafkaListener(topics = "Kafka_Example", groupId = "group_id")
    public void consume(String message){
        System.out.println("Consumed Message - "+message);

    }

4. Create a class KafkaConfiguration, annotate with @EnableKafka, @Configuration

5. Add code below
	@Bean
    public ConsumerFactory<String, String> consumerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return new DefaultKafkaConsumerFactory<String, String>(config);
    }

    
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }


  6. Run the project

  7. Start a console Producer that producec to topic "Kafka_Example"

  	bin\windows\kafka-console-producer.bat --broker-list localhost:9092 --topic Kafka_Example


  8. Send a message and check in the Application logs the message

  =======================================================

  1. JSON Object in message

  1. Create  a User Model with Getters, setters, Tostring as well as default and parameterised constructor

  2. Update the Configuration

  	@Bean
    public ConsumerFactory<String, User> userConsumerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_json");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(config,new StringDeserializer(), new JsonDeserializer<>(User.class));
    }


    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, User> userKafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String, User> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(userConsumerFactory());
        return factory;
    }

  3. Update the listener
	  @KafkaListener(topics = "Kafka_Example_Json", groupId = "group_json", containerFactory = "userKafkaListenerContainerFactory")
	    public void consumeJson(User user){
	        System.out.println("Consumed JSON Message - "+ user);

	    }

4. Start the Program

5. Create a new Topic

	bin\kafka-topics.sh --create --zookeeper localhost:2182 --replication-factor 1 --partitions 1 --topic Kafka_Example_Json


6. Publish to the new Topic

	bin\windows\kafka-console-producer.bat --broker-list localhost:9092 --topic Kafka_Example_Json


	{"name":"anic","department":"L&D","salary":"100"}

7. Check if the object is printed in the console


=================================================================

Connect the publisher and the consumer

1. Start the publisher Project

	curl localhost:8090/kafka/publish/object

2. Check the logs of the Consumer for the Object details
