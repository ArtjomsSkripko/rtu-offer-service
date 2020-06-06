package offer.builders;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import offer.enumeration.OfferTypeEnum;
import offer.enumeration.PassengerTypeEnum;
import offer.enumeration.TransportTypeEnum;
import offer.model.Address;
import offer.model.JourneyOfferRequest;
import offer.rest.model.AddressDTO;
import offer.rest.model.RegionalOfferRequestDTO;

public class JourneyOfferRequestBuilder {

    private JourneyOfferRequest template;

    public JourneyOfferRequestBuilder() {
        this.template = new JourneyOfferRequest();
    }

    public JourneyOfferRequestBuilder addressTo(Address address) {
        template.setAddressTo(address);
        return this;
    }

    public JourneyOfferRequestBuilder addressFrom(Address address) {
        template.setAddressFrom(address);
        return this;
    }

    public JourneyOfferRequestBuilder passenger(PassengerTypeEnum passenger) {
        template.setPassenger(passenger);
        return this;
    }

    public JourneyOfferRequestBuilder offerType(OfferTypeEnum offerType) {
        template.setOfferType(offerType);
        return this;
    }

    public JourneyOfferRequestBuilder numberOfTickets(Integer numberOfTickets) {
        template.setNumberOfTickets(numberOfTickets);
        return this;
    }

    public JourneyOfferRequestBuilder routeNumbers(List<Integer> routeNumber) {
        template.setRouteNumbers(routeNumber);
        return this;
    }

    public JourneyOfferRequestBuilder transportTypes(List<TransportTypeEnum> transportTypes) {
        template.setTransportTypes(transportTypes);
        return this;
    }

    public JourneyOfferRequestBuilder companyName(String companyName) {
        template.setCompanyName(companyName);
        return this;
    }

    public JourneyOfferRequestBuilder placeType(String placeType) {
        template.setPlaceType(placeType);
        return this;
    }

    public JourneyOfferRequestBuilder withDefaults() {
        return this
                .passenger(PassengerTypeEnum.ADULT)
                .offerType(OfferTypeEnum.SINGLE_TICKET)
                .routeNumbers(Arrays.asList(5, 6))
                .transportTypes(Collections.singletonList(TransportTypeEnum.BUS))
                .companyName("Company name")
                .addressFrom(new AddressBuilder().withDefaults().build())
                .addressTo(new AddressBuilder().withDefaults().build())
                .companyName("RIGAS_SATIKSME")
                ;
    }

    public JourneyOfferRequest build() {
        return template;
    }
}
