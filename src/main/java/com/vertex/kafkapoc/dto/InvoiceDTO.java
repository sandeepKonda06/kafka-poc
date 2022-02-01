package com.vertex.kafkapoc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"CustomizationID", "ProfileID", "ID", "IssueDate", "DueDate", "InvoiceTypeCode", "DocumentCurrencyCode", "AccountingCost", "BuyerReference", "AccountingSupplierParty", "AccountingCustomerParty", "Delivery", "PaymentMeans", "PaymentTerms", "AllowanceCharge", "TaxTotal", "LegalMonetaryTotal", "InvoiceLine"})
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@Builder
public class InvoiceDTO {
    @JsonProperty("CustomizationID")
    private String customizationID;
    @JsonProperty("ProfileID")
    private String profileID;
    @JsonProperty("ID")
    private @NonNull String id;
    @JsonProperty("IssueDate")
    private String issueDate;
    @JsonProperty("DueDate")
    private String dueDate;
    @JsonProperty("InvoiceTypeCode")
    private String invoiceTypeCode;
    @JsonProperty("DocumentCurrencyCode")
    private String documentCurrencyCode;
    @JsonProperty("AccountingCost")
    private String accountingCost;
    @JsonProperty("BuyerReference")
    private String buyerReference;
    @JsonProperty("AccountingSupplierParty")
    private AccountingSupplierPartyDTO accountingSupplierParty;
    @JsonProperty("AccountingCustomerParty")
    private AccountingCustomerPartyDTO accountingCustomerParty;
    @JsonProperty("Delivery")
    private DeliveryDTO delivery;
    @JsonProperty("PaymentMeans")
    private PaymentMeansDTO paymentMeans;
    @JsonProperty("PaymentTerms")
    private PaymentTermsDTO paymentTerms;
    @JsonProperty("AllowanceCharge")
    private AllowanceChargeDTO allowanceCharge;
    @JsonProperty("TaxTotal")
    private TaxTotalDTO taxTotal;
    @JsonProperty("LegalMonetaryTotal")
    private LegalMonetaryTotalDTO legalMonetaryTotal;
    @JsonProperty("InvoiceLine")
    private List<InvoiceLineDTO> invoiceLine = new ArrayList<>();
}
