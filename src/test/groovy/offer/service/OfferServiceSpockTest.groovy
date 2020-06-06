package offer.service

import com.google.common.collect.ImmutableMap
import offer.builders.IntercityOfferRequestDTOBuilder
import offer.builders.JourneyOfferBuilder
import offer.builders.JourneyOfferRequestBuilder
import offer.builders.RegionalOfferRequestDTOBuilder
import offer.exception.OfferNotFoundException
import offer.model.JourneyOffer
import offer.model.JourneyOfferRequest
import offer.rest.model.IntercityOfferRequestDTO
import offer.rest.model.RegionalOfferDTO
import offer.rest.model.RegionalOfferRequestDTO
import org.apache.commons.lang3.tuple.Pair
import spock.lang.Specification

class OfferServiceSpockTest extends Specification {

    OfferService service
    RegionalAndIntercityOfferRestClient restClient

    def setup() {
        restClient = Mock(RegionalAndIntercityOfferRestClient.class)
        service = new OfferService(restClient)
    }

    void "create offers"() {
        given:
        JourneyOfferRequest journeyOfferRequest = new JourneyOfferRequestBuilder().withDefaults().build()
        RegionalOfferRequestDTO regionalOfferRequestDTO = new RegionalOfferRequestDTOBuilder().withDefaults().build()
        IntercityOfferRequestDTO intercityOfferRequestDTO = new IntercityOfferRequestDTOBuilder().withDefaults().build()
        JourneyOffer expectedOffer = new JourneyOfferBuilder().withDefaults().build()
        RegionalOfferDTO expectedRegionalOffer = expectedOffer.getRegionalOffer().values().iterator().next()

        and:
        restClient.getRegionalOffers(_ as RegionalOfferRequestDTO) >> [expectedRegionalOffer]
        restClient.getIntercityOffers(_ as IntercityOfferRequestDTO) >> [expectedOffer.getIntercityOffer()]

        when:
        def results = service.createOffers(Pair.of([regionalOfferRequestDTO], [intercityOfferRequestDTO]), journeyOfferRequest)

        then:
        results != null
        !results.isEmpty()
        results.size() == 1
        results.get(0).intercityOffer == expectedOffer.intercityOffer
        results.get(0).regionalOffer == ImmutableMap.of(expectedOffer.getAddressFrom().getCity(), expectedRegionalOffer)

        1 * restClient.getRegionalOffers(_ as RegionalOfferRequestDTO) >> [expectedRegionalOffer]
        1 * restClient.getIntercityOffers(_ as IntercityOfferRequestDTO) >> [expectedOffer.getIntercityOffer()]
    }

    void "create offers when no offers found"() {
        given:
        JourneyOfferRequest journeyOfferRequest = new JourneyOfferRequestBuilder().withDefaults().build()
        RegionalOfferRequestDTO regionalOfferRequestDTO = new RegionalOfferRequestDTOBuilder().withDefaults().build()
        IntercityOfferRequestDTO intercityOfferRequestDTO = new IntercityOfferRequestDTOBuilder().withDefaults().build()

        and:
        restClient.getRegionalOffers(_ as RegionalOfferRequestDTO) >> []
        restClient.getIntercityOffers(_ as IntercityOfferRequestDTO) >> []

        when:
        service.createOffers(Pair.of([regionalOfferRequestDTO], [intercityOfferRequestDTO]), journeyOfferRequest)

        then:
        thrown(OfferNotFoundException.class)
    }


}
