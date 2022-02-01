package com.vertex.kafkapoc.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.vertex.kafkapoc.dto.InvoiceDTO;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class KafkaProducerConfig {

    private final @NonNull ApplicationProperties properties;
//
//    @Bean
//    public KafkaAdmin kafkaAdmin() {
//        Map<String, Object> configs = new HashMap<>();
//        String host = properties.getKafkaHost();
//        String port = String.valueOf(properties.getKafkaPort());
//        String bootstrapAddress = host + port;
//        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
//        return new KafkaAdmin(configs);
//    }

    @Bean
    public ProducerFactory<String, InvoiceDTO> invoiceProducerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, getBootstrapAddress());
        props.put(ProducerConfig.LINGER_MS_CONFIG, properties.getKafkaConnectionLinger());
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, properties.getKafkaBufferMemory());
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, properties.getKafkaBatchSize());
        props.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, properties.getKafkaMaxRequestSize());
        props.put(ProducerConfig.RETRIES_CONFIG, properties.getKafkaRetries());
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, InvoiceDTOSerializer.class);
        props.put(ProducerConfig.SOCKET_CONNECTION_SETUP_TIMEOUT_MS_CONFIG, properties.getKafkaConnectionTimeout());
        return new DefaultKafkaProducerFactory<>(props);
    }

    private String getBootstrapAddress() {
        String host = properties.getKafkaHost();
        String port = String.valueOf(properties.getKafkaPort());
        String bootstrapAddress = host + ":" + port;
        return bootstrapAddress;
    }

    @Bean
    public KafkaTemplate<String, InvoiceDTO> invoiceKafkaTemplate() {
        return new KafkaTemplate<>(invoiceProducerFactory());
    }
}
