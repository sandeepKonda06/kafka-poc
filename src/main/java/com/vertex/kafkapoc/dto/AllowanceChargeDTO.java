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
@JsonPropertyOrder({"ChargeIndicator", "AllowanceChargeReason", "Amount", "TaxCategory"})
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@Builder
public class AllowanceChargeDTO {
    @JsonProperty("ChargeIndicator")
    private String chargeIndicator;
    @JsonProperty("AllowanceChargeReason")
    private String allowanceChargeReason;
    @JsonProperty("Amount")
    private AmountDTO amount;
    @JsonProperty("TaxCategory")
    private TaxCategoryDTO taxCategory;
}
