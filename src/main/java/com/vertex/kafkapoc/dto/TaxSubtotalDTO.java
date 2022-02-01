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
@JsonPropertyOrder({"TaxableAmount", "TaxAmount", "TaxCategory"})
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@Builder
public class TaxSubtotalDTO {
    @JsonProperty("TaxableAmount")
    private TaxableAmountDTO taxableAmount;
    @JsonProperty("TaxAmount")
    private TaxAmountDTO taxAmount;
    @JsonProperty("TaxCategory")
    private TaxCategoryDTO taxCategory;
}
