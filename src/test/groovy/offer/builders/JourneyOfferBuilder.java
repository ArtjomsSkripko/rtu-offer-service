package offer.builders;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import offer.model.Address;
import offer.model.JourneyOffer;
import offer.rest.model.IntercityOfferDTO;
import offer.rest.model.RegionalOfferDTO;

public class JourneyOfferBuilder {

    private JourneyOffer template;

    public JourneyOfferBuilder() {
        this.template = new JourneyOffer();
    }

    public JourneyOfferBuilder regionalOffer(Map<String, RegionalOfferDTO> regionalOffer) {
        template.setRegionalOffer(regionalOffer);
        return this;
    }

    public JourneyOfferBuilder addressFrom(Address addressFrom) {
        template.setAddressFrom(addressFrom);
        return this;
    }

    public JourneyOfferBuilder addressTo(Address addressTo) {
        template.setAddressTo(addressTo);
        return this;
    }

    public JourneyOfferBuilder depTime(ZonedDateTime depTime) {
        template.setDepartureTime(depTime);
        return this;
    }

    public JourneyOfferBuilder intercityOffer(IntercityOfferDTO offer) {
        template.setIntercityOffer(offer);
        return this;
    }

    public JourneyOfferBuilder withDefaults() {
        return this
                .depTime(ZonedDateTime.of(2020, 5, 5, 12, 0, 0, 0, ZoneId.systemDefault()))
                .addressFrom(new AddressBuilder().withDefaults().city("RIGA").build())
                .addressTo(new AddressBuilder().withDefaults().city("DAUGAVPILS").build())
                .regionalOffer(ImmutableMap.of("RIGA", new RegionalOfferDTOBuilder().withDefaults().build(), "DAUGAVPILS", new RegionalOfferDTOBuilder().withDefaults().id("offerId3").build()))
                .intercityOffer(new IntercityOfferDTOBuilder().withDefaults().build())
                ;
    }
    public JourneyOffer build() {
        return template;
    }
}
