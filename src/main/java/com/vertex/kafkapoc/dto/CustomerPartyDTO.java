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
@JsonPropertyOrder({
        "EndpointID",
        "PartyIdentification",
        "PartyName",
        "PostalAddress",
        "PartyTaxScheme",
        "PartyLegalEntity",
        "Contact"
})
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@Builder
public class CustomerPartyDTO {
    @JsonProperty("EndpointID")
    private EndpointIDDTO endpointID;
    @JsonProperty("PartyIdentification")
    private CustomerPartyIdentificationDTO partyIdentification;
    @JsonProperty("PartyName")
    private PartyNameDTO partyName;
    @JsonProperty("PostalAddress")
    private PostalAddressDTO postalAddress;
    @JsonProperty("PartyTaxScheme")
    private PartyTaxSchemeDTO partyTaxScheme;
    @JsonProperty("PartyLegalEntity")
    private CustomerPartyLegalEntityDTO partyLegalEntity;
    @JsonProperty("Contact")
    private ContactDTO contact;
}
