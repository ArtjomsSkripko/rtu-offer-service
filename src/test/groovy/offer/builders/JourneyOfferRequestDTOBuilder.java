package offer.builders;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import offer.enumeration.OfferTypeEnum;
import offer.enumeration.PassengerTypeEnum;
import offer.enumeration.TransportTypeEnum;
import offer.rest.model.AddressDTO;
import offer.rest.model.JourneyOfferRequestDTO;

public class JourneyOfferRequestDTOBuilder {

    private JourneyOfferRequestDTO template;

    public JourneyOfferRequestDTOBuilder() {
        this.template = new JourneyOfferRequestDTO();
    }

    public JourneyOfferRequestDTOBuilder addressTo(AddressDTO address) {
        template.setAddressTo(address);
        return this;
    }

    public JourneyOfferRequestDTOBuilder addressFrom(AddressDTO address) {
        template.setAddressFrom(address);
        return this;
    }

    public JourneyOfferRequestDTOBuilder passenger(String passenger) {
        template.setPassenger(passenger);
        return this;
    }

    public JourneyOfferRequestDTOBuilder offerType(String offerType) {
        template.setOfferType(offerType);
        return this;
    }

    public JourneyOfferRequestDTOBuilder numberOfTickets(Integer numberOfTickets) {
        template.setNumberOfTickets(numberOfTickets);
        return this;
    }

    public JourneyOfferRequestDTOBuilder routeNumbers(List<Integer> routeNumber) {
        template.setRouteNumbers(routeNumber);
        return this;
    }

    public JourneyOfferRequestDTOBuilder transportTypes(List<String> transportTypes) {
        template.setTransportTypes(transportTypes);
        return this;
    }

    public JourneyOfferRequestDTOBuilder companyName(String companyName) {
        template.setCompanyName(companyName);
        return this;
    }

    public JourneyOfferRequestDTOBuilder placeType(String placeType) {
        template.setPlaceType(placeType);
        return this;
    }

    public JourneyOfferRequestDTOBuilder withDefaults() {
        return this
                .passenger(PassengerTypeEnum.ADULT.name())
                .offerType(OfferTypeEnum.SINGLE_TICKET.name())
                .routeNumbers(Arrays.asList(5, 6))
                .transportTypes(Collections.singletonList(TransportTypeEnum.BUS.name()))
                .companyName("Company name")
                .addressFrom(new AddressDTOBuilder().withDefaults().build())
                .addressTo(new AddressDTOBuilder().withDefaults().city("JELGAVA").build())
                .companyName("RIGAS_SATIKSME")
                ;
    }

    public JourneyOfferRequestDTO build() {
        return template;
    }
}
