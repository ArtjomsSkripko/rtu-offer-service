package offer.builders;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import offer.enumeration.OfferTypeEnum;
import offer.enumeration.PassengerTypeEnum;
import offer.enumeration.TransportTypeEnum;
import offer.rest.model.AddressDTO;
import offer.rest.model.RegionalOfferDTO;

public class RegionalOfferDTOBuilder {

    private RegionalOfferDTO template;

    public RegionalOfferDTOBuilder() {
        this.template = new RegionalOfferDTO();
    }

    public RegionalOfferDTOBuilder addressFrom(AddressDTO address) {
        template.setAddressFrom(address);
        return this;
    }

    public RegionalOfferDTOBuilder addressTo(AddressDTO address) {
        template.setAddressTo(address);
        return this;
    }

    public RegionalOfferDTOBuilder passenger(String passenger) {
        template.setPassenger(passenger);
        return this;
    }

    public RegionalOfferDTOBuilder offerType(String offerType) {
        template.setOfferType(offerType);
        return this;
    }

    public RegionalOfferDTOBuilder priceWithDiscount(String priceWithDiscount) {
        template.setPriceWithDiscount(priceWithDiscount);
        return this;
    }

    public RegionalOfferDTOBuilder originalPrice(String originalPrice) {
        template.setOriginalPrice(originalPrice);
        return this;
    }

    public RegionalOfferDTOBuilder taxRate(String taxRate) {
        template.setTaxRate(taxRate);
        return this;
    }

    public RegionalOfferDTOBuilder discount(String discount) {
        template.setDiscount(discount);
        return this;
    }

    public RegionalOfferDTOBuilder routeNumbers(List<String> routeNumber) {
        template.setRouteNumbers(routeNumber);
        return this;
    }

    public RegionalOfferDTOBuilder transportTypes(List<String> transportTypes) {
        template.setTransportTypes(transportTypes);
        return this;
    }

    public RegionalOfferDTOBuilder companyName(String companyName) {
        template.setCompanyName(companyName);
        return this;
    }

    public RegionalOfferDTOBuilder id(String id) {
        template.setId(id);
        return this;
    }

    public RegionalOfferDTOBuilder city(String city) {
        template.setCity(city);
        return this;
    }

    public RegionalOfferDTOBuilder numberOfTickets(Integer numberOfTickets) {
        template.setNumberOfTickets(numberOfTickets);
        return this;
    }

    public RegionalOfferDTOBuilder withDefaults() {
        return this
                .id("offerId2")
                .addressFrom(new AddressDTOBuilder().withDefaults().build())
                .passenger(PassengerTypeEnum.ADULT.name())
                .numberOfTickets(1)
                .offerType(OfferTypeEnum.SINGLE_TICKET.name())
                .taxRate(new BigDecimal(23.00).round(new MathContext(3)).toPlainString())
                .originalPrice(new BigDecimal(10.10).round(new MathContext(3)).toPlainString())
                .priceWithDiscount(new BigDecimal(10.10).round(new MathContext(3)).toPlainString())
                .routeNumbers(Arrays.asList("5", "6"))
                .transportTypes(Collections.singletonList(TransportTypeEnum.BUS.name()))
                .companyName("RIGAS_SATIKSME")
                .city("RIGA")
                ;
    }
    public RegionalOfferDTO build() {
        return template;
    }
}
