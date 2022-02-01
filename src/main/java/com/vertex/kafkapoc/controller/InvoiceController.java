package com.vertex.kafkapoc.controller;

import com.vertex.kafkapoc.dto.InvoiceDTO;
import com.vertex.kafkapoc.exception.AppException;
import com.vertex.kafkapoc.service.InvoiceService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/invoice")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InvoiceController {
    private final @NonNull InvoiceService invoiceService;

//    @GetMapping(path = "/amazon", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
//    public InvoiceDTO getAmazonInvoice() throws AppException {
//        try {
//            log.info("Executing InvoiceController.getAmazonInvoice for fetching invoice");
//            return invoiceService.getInvoice("AmazonCG");
//        } finally {
//            log.info("Exiting InvoiceController.getAmazonInvoice after fetching invoice");
//        }
//    }
//
//    @GetMapping(path = "/flipkart", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
//    public InvoiceDTO getFlipkartInvoice() throws AppException {
//        try {
//            log.info("Executing InvoiceController.getFlipkartInvoice for fetching invoice");
//            return invoiceService.getInvoice("FlipkartCG");
//        } finally {
//            log.info("Exiting InvoiceController.getFlipkartInvoice after fetching invoice");
//        }
//    }

    @GetMapping(path = "/amazon/all",consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<InvoiceDTO>> getAllAmazonInvoices() throws AppException {
        try {
            log.info("Executing InvoiceController.getAllInvoices fetching all invoices");
            return ResponseEntity.ok(invoiceService.getAllInvoices("AmazonCG"));
        } finally {
            log.info("Exiting InvoiceController.getAllInvoices after fetching all invoices");
        }
    }

//    @GetMapping(path = "/flipkart/all",consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
//    public ResponseEntity<List<InvoiceDTO>> getAllFlipkartInvoices() throws AppException {
//        try {
//            log.info("Executing InvoiceController.getAllInvoices fetching all invoices");
//            return ResponseEntity.ok(invoiceService.getAllInvoices("FlipkartCG"));
//        } finally {
//            log.info("Exiting InvoiceController.getAllInvoices after fetching all invoices");
//        }
//    }

    @PostMapping(path = "/amazon", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<InvoiceDTO> createAmazonInvoice(@RequestBody InvoiceDTO invoiceDTO) throws AppException {
        try {
            log.info("Executing InvoiceController.createInvoice creating the invoice: {}", invoiceDTO);
            return ResponseEntity.ok(invoiceService.createInvoice("Amazon", invoiceDTO));
        } finally {
            log.info("Exiting InvoiceController.createInvoice after invoice creation");
        }
    }

    @PostMapping(path = "/flipkart", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<InvoiceDTO> createFlipkartInvoice(@RequestBody InvoiceDTO invoiceDTO) throws AppException {
        try {
            log.info("Executing InvoiceController.createInvoice creating the invoice: {}", invoiceDTO);
            return ResponseEntity.ok(invoiceService.createInvoice("Flipkart", invoiceDTO));
        } finally {
            log.info("Exiting InvoiceController.createInvoice after invoice creation");
        }
    }
}
