package offer.builders;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import offer.enumeration.OfferTypeEnum;
import offer.enumeration.PassengerTypeEnum;
import offer.enumeration.TransportTypeEnum;
import offer.rest.model.AddressDTO;
import offer.rest.model.RegionalOfferRequestDTO;

public class RegionalOfferRequestDTOBuilder {

    private RegionalOfferRequestDTO template;

    public RegionalOfferRequestDTOBuilder() {
        this.template = new RegionalOfferRequestDTO();
    }

    public RegionalOfferRequestDTOBuilder addressTo(AddressDTO address) {
        template.setAddressTo(address);
        return this;
    }

    public RegionalOfferRequestDTOBuilder addressFrom(AddressDTO address) {
        template.setAddressFrom(address);
        return this;
    }

    public RegionalOfferRequestDTOBuilder passenger(String passenger) {
        template.setPassenger(passenger);
        return this;
    }

    public RegionalOfferRequestDTOBuilder offerType(String offerType) {
        template.setOfferType(offerType);
        return this;
    }

    public RegionalOfferRequestDTOBuilder numberOfTickets(Integer numberOfTickets) {
        template.setNumberOfTickets(numberOfTickets);
        return this;
    }

    public RegionalOfferRequestDTOBuilder routeNumbers(List<Integer> routeNumber) {
        template.setRouteNumbers(routeNumber);
        return this;
    }

    public RegionalOfferRequestDTOBuilder transportTypes(List<String> transportTypes) {
        template.setTransportTypes(transportTypes);
        return this;
    }

    public RegionalOfferRequestDTOBuilder companyName(String companyName) {
        template.setCompanyName(companyName);
        return this;
    }

    public RegionalOfferRequestDTOBuilder withDefaults() {
        return this
                .passenger(PassengerTypeEnum.ADULT.name())
                .offerType(OfferTypeEnum.SINGLE_TICKET.name())
                .transportTypes(Collections.singletonList(TransportTypeEnum.BUS.name()))
                .companyName("Company name")
                .addressFrom(new AddressDTOBuilder().withDefaults().build())
                .numberOfTickets(1)
                .routeNumbers(Arrays.asList(5, 6))
                .companyName("RIGAS_SATIKSME")
                ;
    }

    public RegionalOfferRequestDTO build() {
        return template;
    }
}
