package net.apmoller.crb.ohm.microservices.GSISKafka.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Getter
@Slf4j
@RequiredArgsConstructor
public class Config {

    @Value("${kafka.bootstrapserver}")
    private String bootstrapServers;
    @Value("${kafka.consumer.groupIds.gsis-group-id}")
    private String gsisConsumerGroupId;
    @Value("${kafka.consumer.auto-offset-reset:earliest}")
    private String consumerOffsetAutoReset;
    @Value("${kafka.properties.max.poll.records}")
    private String consumerMaxPollRecords;
    @Value("${kafka.properties.sasl.mechanism:PLAIN}")
    private String saslMechanism;
    @Value("${kafka.properties.security.protocol:SASL_SSL}")
    private String securityProtocol;
    @Value("${kafka.properties.sasl.jaas.config:org.apache.kafka.common.security.plain.PlainLoginModule}")
    private String loginModule;
    @Value("${kafka.properties.saslRequired:true}")
    private String saslRequired;
    @Value("${kafka.listener.concurrency:1}")
    private int consumerConcurrency;
    @Value("${kafka.consumer.enable-consumer.gsis:true}")
    private String gsisAutoStartup;
    @Value("${kafka.properties.schema.registry.url}")
    private String schemaRegistryUrl;
    @Value("${kafka.properties.schema.registry.basic.auth.user.info}")
    private String schemaRegistryAuthUserInfo;
    @Value("${kafka.properties.schema.registry.basic.auth.user.info}")
    private String basicAuthInfo;
    @Value("${kafka.properties.basic.auth.credentials.source}")
    private String basicAuthCredentialsSource;
//    @Value("${kafka.truststore-location:}")
//    private String truststoreLocation;
//    @Value("${kafka.truststore-password:}")
//    private String truststorePassword;


    @Bean
    public StringJsonMessageConverter stringJsonMessageConverter(ObjectMapper mapper) {
        return new StringJsonMessageConverter(mapper);
    }

    private void addSaslProperties(Map<String, Object> properties, String saslMechanism, String securityProtocol,
                                   String loginModule) {
        if (Boolean.parseBoolean(saslRequired)) {
            properties.put("security.protocol", securityProtocol);
            properties.put("sasl.mechanism", saslMechanism);
            properties.put("sasl.jaas.config", loginModule);
        }
    }
    @Bean
    public ConsumerFactory<String, Object> gsisConsumerFactory() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        properties.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS,
                "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS,
                "io.confluent.kafka.serializers.KafkaAvroDeserializer");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, gsisConsumerGroupId);
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, consumerOffsetAutoReset);
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        properties.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, consumerMaxPollRecords);
        addSaslProperties(properties, saslMechanism, securityProtocol, loginModule);
        properties.put("schema.registry.url", schemaRegistryUrl);
        properties.put("schema.registry.basic.auth.user.info", schemaRegistryAuthUserInfo);
        properties.put("basic.auth.user.info", basicAuthInfo);
        properties.put("basic.auth.credentials.source", basicAuthCredentialsSource);
        //addTruststoreProperties(properties);
        DefaultKafkaConsumerFactory<String, Object> gsisCf = new DefaultKafkaConsumerFactory<>(properties);

        return gsisCf;
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Object>> gsisListenerContainerFactory(
            StringJsonMessageConverter messageConverter) {
        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(gsisConsumerFactory());
        factory.setConcurrency(consumerConcurrency);
        factory.setMessageConverter(messageConverter);
        //factory.setErrorHandler(errorHandler("VESSEL_VOYAGE"));
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
        factory.setAutoStartup(Boolean.valueOf(gsisAutoStartup));
        return factory;
    }




}
