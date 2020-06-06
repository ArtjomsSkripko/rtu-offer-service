package offer.mapper

import offer.builders.AddressDTOBuilder
import offer.builders.JourneyOfferBuilder
import offer.builders.JourneyOfferRequestDTOBuilder
import offer.model.JourneyOffer
import offer.rest.model.IntercityOfferRequestDTO
import offer.rest.model.JourneyOfferDTO
import offer.rest.model.JourneyOfferRequestDTO
import offer.rest.model.RegionalOfferRequestDTO
import org.apache.commons.lang3.tuple.Pair
import spock.lang.Specification

class OfferMapperTestSpock extends Specification {

    OfferMapper mapper = new OfferMapper()

    void "testing toOfferDTO method"() {
        given:
        JourneyOffer input = new JourneyOfferBuilder().withDefaults().build()

        when:
        JourneyOfferDTO output = mapper.toDTOOffer(input)

        then:
        output != null
        output.getAddressFrom().getCity() == input.getAddressFrom().getCity()
        output.getAddressFrom().getStreetName() == input.getAddressFrom().getStreetName()
        output.getAddressFrom().getHomeNumber() == input.getAddressFrom().getHomeNumber()
        output.getAddressTo().getCity() == input.getAddressTo().getCity()
        output.getAddressTo().getStreetName() == input.getAddressTo().getStreetName()
        output.getAddressTo().getHomeNumber() == input.getAddressTo().getHomeNumber()
        output.getDepartureTime() == input.getDepartureTime()
        output.getIntercityOffer() == input.getIntercityOffer()
        output.getIntercityOffer() == input.getIntercityOffer()
        output.getRegionalOffers() == input.getRegionalOffer()
    }

    void "testing ToRegionalAndIntercityOfferRequests when regional request only"() {
        given:
        JourneyOfferRequestDTO input = new JourneyOfferRequestDTOBuilder().withDefaults()
                .addressTo(new AddressDTOBuilder().withDefaults().build()).build()

        when:
        Pair<List<RegionalOfferRequestDTO>, List<IntercityOfferRequestDTO>> result = mapper.toRegionalAndIntercityOfferRequests(input)

        then:
        result.getValue().isEmpty()
        RegionalOfferRequestDTO resultRegionalRequest = result.getKey().get(0)
        resultRegionalRequest.getAddressFrom() == input.getAddressFrom()
        resultRegionalRequest.getAddressTo() == input.getAddressTo()
        resultRegionalRequest.getPassenger() == input.getPassenger()
    }

    void "testing ToRegionalAndIntercityOfferRequests when both regional and intercity requests"() {
        given:
        JourneyOfferRequestDTO input = new JourneyOfferRequestDTOBuilder().withDefaults().build()

        when:
        Pair<List<RegionalOfferRequestDTO>, List<IntercityOfferRequestDTO>> result = mapper.toRegionalAndIntercityOfferRequests(input)

        then:
        RegionalOfferRequestDTO resultRegionalRequest = result.getKey().get(0)
        IntercityOfferRequestDTO resultIntercityOfferRequest = result.getValue().get(0)

        resultRegionalRequest.getAddressFrom() == input.getAddressFrom()
        resultRegionalRequest.getAddressTo().getCity() == input.getAddressFrom().getCity()
        resultRegionalRequest.getAddressTo().getHomeNumber() == "1"
        resultRegionalRequest.getAddressTo().getStreetName() == "Pragas iela"
        resultIntercityOfferRequest.getDepCity() == input.getAddressFrom().getCity()
        resultIntercityOfferRequest.getDestCity() == input.getAddressTo().getCity()
        resultIntercityOfferRequest.getDepartureTime() == input.getDepartureTime()
        resultIntercityOfferRequest.getPlaceType() == input.getPlaceType()
        resultIntercityOfferRequest.getPassenger() == input.getPassenger()
        resultRegionalRequest.getPassenger() == input.getPassenger()
    }
}
