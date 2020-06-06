package offer.rest

import offer.exception.UnauthorizedException
import offer.mapper.OfferMapper
import offer.rest.model.JourneyOfferRequestDTO
import offer.service.OfferService
import spock.lang.Specification

class OfferControllerSpockTest extends Specification {

    OfferService service
    OfferMapper mapper
    OfferController controller

    def setup() {
        mapper = Mock(OfferMapper.class)
        service = Mock(OfferService.class)
        controller = new OfferController(service, mapper)
    }

    void "call create offers unauthorized"() {
        given:
        JourneyOfferRequestDTO requestDTO = new JourneyOfferRequestDTO();
        when:
        controller.createJourneyOffers(requestDTO)
        then:
        thrown(UnauthorizedException.class)
    }
}
