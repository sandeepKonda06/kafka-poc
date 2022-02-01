package com.vertex.kafkapoc.config;

import com.vertex.kafkapoc.dto.InvoiceDTO;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class KafkaConsumerConfig {

    private final @NonNull ApplicationProperties properties;


    public Map<String, Object>  invoiceAmazonConsumerProperty() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, getBootstrapAddress());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "AmazonCG");
        props.put(ConsumerConfig.RETRY_BACKOFF_MS_CONFIG, properties.getKafkaRetries());
        props.put(ConsumerConfig.REQUEST_TIMEOUT_MS_CONFIG, properties.getKafkaConnectionTimeout());
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, InvoiceDTODeserializer.class);

        return props;
    }

    @Bean
    public ConsumerFactory<String, InvoiceDTO> invoiceAmazonConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(invoiceAmazonConsumerProperty() );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, InvoiceDTO> invoiceAmazonListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, InvoiceDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(invoiceAmazonConsumerFactory());
        return  factory;
    }

    @Bean
    public KafkaConsumer<String, InvoiceDTO> invoiceAmazonSingleMessageConsumer() {
        Map<String, Object> props = invoiceAmazonConsumerProperty();
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, properties.getKafkaBatchSize());
        KafkaConsumer<String, InvoiceDTO> consumer = new KafkaConsumer<>(props);
        return  consumer;
    }

    @Bean
    public KafkaConsumer<String, InvoiceDTO> invoiceAmazonMultipleMessageConsumer() {
        Map<String, Object> props = invoiceAmazonConsumerProperty();
        KafkaConsumer<String, InvoiceDTO> consumer = new KafkaConsumer<>(props);
        return  consumer;
    }

    @Bean
    public ConsumerFactory<String, InvoiceDTO> invoiceFlipkartConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, getBootstrapAddress());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "FlipkartCG");
        props.put(ConsumerConfig.RETRY_BACKOFF_MS_CONFIG, properties.getKafkaRetries());
        props.put(ConsumerConfig.REQUEST_TIMEOUT_MS_CONFIG, properties.getKafkaConnectionTimeout());
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, InvoiceDTODeserializer.class);

        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, InvoiceDTO> invoiceFlipkartListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, InvoiceDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(invoiceFlipkartConsumerFactory());
        return  factory;
    }

    private String getBootstrapAddress() {
        String host = properties.getKafkaHost();
        String port = String.valueOf(properties.getKafkaPort());
        String bootstrapAddress = host + ":" + port;
        return bootstrapAddress;
    }

}
