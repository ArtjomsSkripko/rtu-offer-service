package offer.builders;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import offer.rest.model.AddressDTO;
import offer.rest.model.IntercityOfferDTO;
import offer.rest.model.JourneyOfferDTO;
import offer.rest.model.RegionalOfferDTO;

public class JourneyOfferDTOBuilder {

    private JourneyOfferDTO template;

    public JourneyOfferDTOBuilder() {
        this.template = new JourneyOfferDTO();
    }

    public JourneyOfferDTOBuilder regionalOffer(Map<String, RegionalOfferDTO> regionalOffer) {
        template.setRegionalOffers(regionalOffer);
        return this;
    }

    public JourneyOfferDTOBuilder addressFrom(AddressDTO addressFrom) {
        template.setAddressFrom(addressFrom);
        return this;
    }

    public JourneyOfferDTOBuilder addressTo(AddressDTO addressTo) {
        template.setAddressTo(addressTo);
        return this;
    }

    public JourneyOfferDTOBuilder depTime(ZonedDateTime depTime) {
        template.setDepartureTime(depTime);
        return this;
    }

    public JourneyOfferDTOBuilder intercityOffer(IntercityOfferDTO offer) {
        template.setIntercityOffer(offer);
        return this;
    }

    public JourneyOfferDTOBuilder withDefaults() {
        return this
                .depTime(ZonedDateTime.of(2020, 5, 5, 12, 0, 0, 0, ZoneId.systemDefault()))
                .addressFrom(new AddressDTOBuilder().withDefaults().city("RIGA").build())
                .addressTo(new AddressDTOBuilder().withDefaults().city("DAUGAVPILS").build())
                .regionalOffer(ImmutableMap.of("RIGA", new RegionalOfferDTOBuilder().withDefaults().build(), "DAUGAVPILS", new RegionalOfferDTOBuilder().withDefaults().id("offerId3").build()))
                .intercityOffer(new IntercityOfferDTOBuilder().withDefaults().build())
                ;
    }
    public JourneyOfferDTO build() {
        return template;
    }
}
