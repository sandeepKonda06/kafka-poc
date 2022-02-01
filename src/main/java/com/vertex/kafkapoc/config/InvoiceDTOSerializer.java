package com.vertex.kafkapoc.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vertex.kafkapoc.dto.InvoiceDTO;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;
import java.util.Objects;

//@Configuration
//@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
@NoArgsConstructor
public class InvoiceDTOSerializer implements Serializer<InvoiceDTO> {

    private final @NonNull ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public byte[] serialize(String topic, InvoiceDTO data) {
        try {
            if (Objects.isNull(data)) {
                log.error("Error serializing InvoiceDTO : Invalid input null");
                throw new SerializationException("Error serializing InvoiceDTO : Invalid input null");
            }
            return objectMapper.writeValueAsBytes(data);
        } catch (Exception ex) {
            log.error("Error serializing InvoiceDTO : ", ex);
            throw new SerializationException("Error serializing InvoiceDTO");
        }
    }

    @Override
    public void close() {
        Serializer.super.close();
    }
}
