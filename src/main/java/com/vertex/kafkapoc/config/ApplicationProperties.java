package com.vertex.kafkapoc.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
public class ApplicationProperties {
    @Value("${app.data.kafka.connection.host}")
    private String kafkaHost;
    @Value("${app.data.kafka.connection.port}")
    private Integer kafkaPort;
    @Value("${app.data.kafka.invoice.topic}")
    private String invoiceKafkaTopic;
    @Value("${app.data.kafka.connection.timeout}")
    private Integer kafkaConnectionTimeout;
    @Value("${app.data.kafka.connection.linger}")
    private Integer kafkaConnectionLinger;
    @Value("${app.data.kafka.connection.retries}")
    private Integer kafkaRetries;
    @Value("${app.data.kafka.connection.batchSize}")
    private Integer kafkaBatchSize;
    @Value("${app.data.kafka.connection.bufferMemory}")
    private Integer kafkaBufferMemory;
    @Value("${app.data.kafka.connection.maxRequestSize}")
    private Integer kafkaMaxRequestSize;
}
