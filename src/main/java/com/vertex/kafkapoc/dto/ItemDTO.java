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
@JsonPropertyOrder({"Description", "Name", "StandardItemIdentification", "OriginCountry", "CommodityClassification", "ClassifiedTaxCategory"})
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@Builder
public class ItemDTO {
    @JsonProperty("Description")
    private String description;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("StandardItemIdentification")
    private StandardItemIdentificationDTO standardItemIdentification;
    @JsonProperty("OriginCountry")
    private OriginCountryDTO originCountry;
    @JsonProperty("CommodityClassification")
    private CommodityClassificationDTO commodityClassification;
    @JsonProperty("ClassifiedTaxCategory")
    private ClassifiedTaxCategoryDTO classifiedTaxCategory;
}
