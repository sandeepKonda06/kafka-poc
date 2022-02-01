package com.vertex.kafkapoc.service;

import com.vertex.kafkapoc.dao.InvoiceDao;
import com.vertex.kafkapoc.dto.InvoiceDTO;
import com.vertex.kafkapoc.exception.AppException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InvoiceService {

    private final @NonNull InvoiceDao invoiceDao;

//    public InvoiceDTO getInvoice(String consumerGroup) throws AppException {
//        try {
//            log.info("Executing InvoiceService.getInvoice fetching the invoice from the {} CG of kafka", consumerGroup);
//            return Optional.ofNullable(invoiceDao.getInvoice(consumerGroup))
//                    .orElseThrow(() -> {
//                        String message = String.format("Invoice not found");
//                        log.error(message);
//                        return new AppException(message);
//                    });
//        } finally {
//            log.info("Exiting InvoiceService.getInvoice after fetching the invoice from the {} CG of kafka", consumerGroup);
//        }
//    }

    public InvoiceDTO createInvoice(String key, InvoiceDTO invoiceDTO) throws AppException {
        try {
            log.info("Executing InvoiceService.createInvoice creating the invoice: {}", invoiceDTO);
            invoiceDao.createInvoice(key, invoiceDTO);
            return invoiceDTO;
        } finally {
            log.info("Exiting InvoiceService.createInvoice after invoice creation");
        }
    }

    public List<InvoiceDTO> getAllInvoices(String consumerGroup) throws AppException {
        try {
            log.info("Executing InvoiceService.getAllInvoices fetching all the invoice from the {} CG of kafka", consumerGroup);
            return Optional.ofNullable(invoiceDao.getAllInvoices(consumerGroup))
                    .orElseThrow(() -> {
                        String message = String.format("No Invoice found");
                        log.error(message);
                        return new AppException(message);
                    });
        } finally {
            log.info("Exiting InvoiceService.getAllInvoices after fetching all the invoice from the {} CG of kafka", consumerGroup);
        }
    }
}
