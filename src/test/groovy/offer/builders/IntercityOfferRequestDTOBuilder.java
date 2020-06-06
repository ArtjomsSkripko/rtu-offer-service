package offer.builders;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import offer.enumeration.PassengerTypeEnum;
import offer.enumeration.TransportTypeEnum;
import offer.rest.model.IntercityOfferRequestDTO;

public class IntercityOfferRequestDTOBuilder {

    private IntercityOfferRequestDTO template;

    public IntercityOfferRequestDTOBuilder() {
        this.template = new IntercityOfferRequestDTO();
    }

    public IntercityOfferRequestDTOBuilder depCity(String depCity) {
        template.setDepCity(depCity);
        return this;
    }

    public IntercityOfferRequestDTOBuilder destCity(String destCity) {
        template.setDepCity(destCity);
        return this;
    }

    public IntercityOfferRequestDTOBuilder depTime(ZonedDateTime depTime) {
        template.setDepartureTime(depTime);
        return this;
    }

    public IntercityOfferRequestDTOBuilder numberOfLuggage(Integer numberOfLuggage) {
        template.setNumberOfLuggage(numberOfLuggage);
        return this;
    }

    public IntercityOfferRequestDTOBuilder passenger(String passenger) {
        template.setPassenger(passenger);
        return this;
    }

    public IntercityOfferRequestDTOBuilder placeType(String placeType) {
        template.setPlaceType(placeType);
        return this;
    }

    public IntercityOfferRequestDTOBuilder transportType(String transportType) {
        template.setTransportType(transportType);
        return this;
    }

    public IntercityOfferRequestDTOBuilder companyName(String companyName) {
        template.setCompanyName(companyName);
        return this;
    }

    public IntercityOfferRequestDTOBuilder withDefaults() {
        return this
                .depCity("RIGA")
                .destCity("DAUGAVPILS")
                .depTime(ZonedDateTime.of(2020, 5, 5, 12, 0, 0, 0, ZoneId.systemDefault()))
                .passenger(PassengerTypeEnum.ADULT.name())
                .placeType("ECO")
                .numberOfLuggage(0)
                .transportType(TransportTypeEnum.BUS.name())
                .companyName("RIGAS_SATIKSME")
                ;
    }

    public IntercityOfferRequestDTO build() {
        return template;
    }
}
