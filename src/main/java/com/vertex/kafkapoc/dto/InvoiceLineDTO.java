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
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"ID", "InvoicedQuantity", "LineExtensionAmount", "AccountingCost", "OrderLineReference", "Item", "Price"})
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@Builder
public class InvoiceLineDTO {
    @JsonProperty("ID")
    private String id;
    @JsonProperty("InvoicedQuantity")
    private InvoicedQuantityDTO invoicedQuantity;
    @JsonProperty("LineExtensionAmount")
    private LineExtensionAmountDTO lineExtensionAmount;
    @JsonProperty("AccountingCost")
    private String accountingCost;
    @JsonProperty("OrderLineReference")
    private OrderLineReferenceDTO orderLineReference;
    @JsonProperty("Item")
    private ItemDTO item;
    @JsonProperty("Price")
    private PriceDTO price;
}
