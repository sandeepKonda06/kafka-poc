package com.vertex.kafkapoc.dao;

import com.vertex.kafkapoc.config.ApplicationProperties;
import com.vertex.kafkapoc.dto.InvoiceDTO;
import com.vertex.kafkapoc.exception.AppException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Repository;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InvoiceDao {
    private final @NonNull ApplicationProperties properties;
    private final @NonNull KafkaTemplate<String, InvoiceDTO> kafkaTemplate;
    private final @NonNull KafkaConsumer<String, InvoiceDTO> invoiceAmazonMultipleMessageConsumer;

    public void createInvoice(String key, InvoiceDTO invoiceDTO) throws AppException {
        try {
            log.info("Executing InvoiceDao.createInvoice for inserting the invoice in the kafka: {},{}", key, invoiceDTO);
            ListenableFuture<SendResult<String, InvoiceDTO>> response = kafkaTemplate.send(properties.getInvoiceKafkaTopic(), key, invoiceDTO);
            response.addCallback(new ListenableFutureCallback<>() {
                @Override
                public void onFailure(Throwable ex) {
                    log.error("Error Sending Message[" + invoiceDTO + "] with error message = [" + ex.getMessage() + "]");
                }

                @Override
                public void onSuccess(SendResult<String, InvoiceDTO> result) {
                    log.info("Sent Message[" + invoiceDTO + "] with offset = [" + result.getRecordMetadata().offset() + "]");
                }
            });
        } catch (Exception ex) {
            log.error("Error Executing InvoiceDao.createInvoice : {0}", ex);
            throw new AppException("Error Executing InvoiceDao.createInvoice : " + ex.getMessage());
        } finally {
            log.info("Exiting InvoiceDao.createInvoice after inserted the invoice in the kafka");
        }
    }

//    public InvoiceDTO getInvoice(String consumerGroup) throws AppException {
//        try {
//            log.info("Executing InvoiceDao.getInvoice fetching the invoice from the {} CG of kafka", consumerGroup);
//            return null;
//        } catch (Exception ex) {
//            log.error("Error Executing InvoiceDao.getInvoice : {0}", ex);
//            throw new AppException("Error Executing InvoiceDao.getInvoice");
//        } finally {
//            log.info("Exiting InvoiceDao.getInvoice after fetching the invoice from the kafka");
//        }
//    }

//    @KafkaListener(topics = "invoice", containerFactory = "invoiceAmazonListenerContainerFactory")
//    public void consumeAmazonTopic(@Payload InvoiceDTO message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) String partitionId) {
//        log.info("*********Receive Message Start*********");
//        log.info("PartitionId : " + partitionId);
//        log.info("Message : " + message);
//        log.info("*********Receive Message End***********");
//    }

    @KafkaListener(topics = "invoice", topicPartitions = @org.springframework.kafka.annotation.TopicPartition(topic = "invoice", partitionOffsets = {
            @PartitionOffset(partition = "2", initialOffset = "0", relativeToCurrent = "true")
    }),containerFactory = "invoiceFlipkartListenerContainerFactory")
    public void consumeFlipkartTopic(@Payload InvoiceDTO message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) String partitionId) {
        log.info("*********Receive Message Start*********");
        log.info("PartitionId : " + partitionId);
        log.info("Message : " + message);
        log.info("*********Receive Message End***********");
    }

    public List<InvoiceDTO> getAllInvoices(String consumerGroup) throws AppException {
        try {
            log.info("Executing InvoiceDao.getAllInvoices fetching all the invoice from the {} CG of kafka", consumerGroup);
            invoiceAmazonMultipleMessageConsumer.assign(Arrays.asList(new TopicPartition(properties.getInvoiceKafkaTopic(),
                    Utils.toPositive(Utils.murmur2("Amazon".getBytes(StandardCharsets.UTF_8))) % 3)));
            ConsumerRecords<String, InvoiceDTO> records =  invoiceAmazonMultipleMessageConsumer.poll(Duration.ofSeconds(10));
            Iterator<ConsumerRecord<String, InvoiceDTO>> itr =  records.iterator();
            List<InvoiceDTO> result = new ArrayList<>();
            while (itr.hasNext()) {
                ConsumerRecord<String, InvoiceDTO> record = itr.next();
                result.add(record.value());
            }
            return result;
        } catch (Exception ex) {
            log.error("Error Executing InvoiceDao.getAllInvoices : {0}", ex);
            throw new AppException("Error Executing InvoiceDao.getAllInvoices");
        } finally {
            log.info("Exiting InvoiceDao.getAllInvoices after fetching all the invoice from the {} CG of kafka", consumerGroup);
        }
    }
}
