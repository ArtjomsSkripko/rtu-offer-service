package offer.builders;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import offer.enumeration.PassengerTypeEnum;
import offer.enumeration.TransportTypeEnum;
import offer.rest.model.IntercityOfferDTO;

public class IntercityOfferDTOBuilder {

    private IntercityOfferDTO template;

    public IntercityOfferDTOBuilder() {
        this.template = new IntercityOfferDTO();
    }

    public IntercityOfferDTOBuilder depCity(String depCity) {
        template.setDepCity(depCity);
        return this;
    }

    public IntercityOfferDTOBuilder destCity(String destCity) {
        template.setDepCity(destCity);
        return this;
    }

    public IntercityOfferDTOBuilder depTime(ZonedDateTime depTime) {
        template.setDepTime(depTime);
        return this;
    }

    public IntercityOfferDTOBuilder numberOfLuggage(Integer numberOfLuggage) {
        template.setNumberOfLuggage(numberOfLuggage);
        return this;
    }

    public IntercityOfferDTOBuilder passenger(String passenger) {
        template.setPassenger(passenger);
        return this;
    }

    public IntercityOfferDTOBuilder placeType(String placeType) {
        template.setPlaceType(placeType);
        return this;
    }

    public IntercityOfferDTOBuilder priceWithDiscount(String priceWithDiscount) {
        template.setPriceWithDiscount(priceWithDiscount);
        return this;
    }

    public IntercityOfferDTOBuilder originalPrice(String originalPrice) {
        template.setOriginalPrice(originalPrice);
        return this;
    }

    public IntercityOfferDTOBuilder taxRate(String taxRate) {
        template.setTaxRate(taxRate);
        return this;
    }

    public IntercityOfferDTOBuilder discount(String discount) {
        template.setDiscount(discount);
        return this;
    }

    public IntercityOfferDTOBuilder transportType(String transportType) {
        template.setTransportType(transportType);
        return this;
    }

    public IntercityOfferDTOBuilder companyName(String companyName) {
        template.setCompanyName(companyName);
        return this;
    }

    public IntercityOfferDTOBuilder id(String id) {
        template.setId(id);
        return this;
    }

    public IntercityOfferDTOBuilder withDefaults() {
        return this
                .depCity("RIGA")
                .destCity("DAUGAVPILS")
                .depTime(ZonedDateTime.of(2020, 5, 5, 12, 0, 0, 0, ZoneId.systemDefault()))
                .passenger(PassengerTypeEnum.ADULT.name())
                .placeType("ECO")
                .numberOfLuggage(0)
                .taxRate(new BigDecimal(23.00).toPlainString())
                .originalPrice(new BigDecimal(10.10).round(new MathContext(3)).toString())
                .priceWithDiscount(new BigDecimal(10.10).round(new MathContext(3)).toString())
                .discount(new BigDecimal(1.0).toPlainString())
                .transportType(TransportTypeEnum.BUS.name())
                .companyName("RIGAS_SATIKSME")
                .id("offerId1")
                ;
    }
    public IntercityOfferDTO build() {
        return template;
    }
}
