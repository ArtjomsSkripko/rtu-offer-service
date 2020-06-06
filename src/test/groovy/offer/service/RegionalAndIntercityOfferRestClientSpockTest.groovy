package offer.service

import offer.builders.IntercityOfferDTOBuilder
import offer.builders.IntercityOfferRequestDTOBuilder
import offer.builders.RegionalOfferDTOBuilder
import offer.builders.RegionalOfferRequestDTOBuilder
import offer.rest.model.IntercityOfferDTO
import offer.rest.model.IntercityOfferRequestDTO
import offer.rest.model.RegionalOfferDTO
import offer.rest.model.RegionalOfferRequestDTO
import org.mockito.Mock
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.util.ReflectionTestUtils
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

class RegionalAndIntercityOfferRestClientSpockTest extends Specification {

    RestTemplate restTemplate
    RegionalAndIntercityOfferRestClient restClient
    ServicePathConfig interCityPathConfig
    ServicePathConfig regionalPathConfig
    def setup() {
        restTemplate = Mock(RestTemplate.class)
        interCityPathConfig = Mock(ServicePathConfig.class)
        regionalPathConfig = Mock(ServicePathConfig.class)
        restClient = new RegionalAndIntercityOfferRestClient(interCityPathConfig, regionalPathConfig)
        ReflectionTestUtils.setField(restClient, "restTemplate", restTemplate, RestTemplate.class)
        interCityPathConfig.buildUri() >> "http://localhost:8084/v1/intercity"
        regionalPathConfig.buildUri() >> "http://localhost:8083/v1/regional"

    }

    void "create regional offers"() {
        given:
        RegionalOfferRequestDTO request = new RegionalOfferRequestDTOBuilder().withDefaults().build()
        List<RegionalOfferDTO> expectedOffers = [new RegionalOfferDTOBuilder().withDefaults().build()]

        and:
        restTemplate.exchange("http://localhost:8083/v1/regional", HttpMethod.POST, _ as HttpEntity<?> , _ as ParameterizedTypeReference<List<RegionalOfferDTO>>) >>
                new ResponseEntity<>(expectedOffers, HttpStatus.OK)

        when:
        List<RegionalOfferDTO> result = restClient.getRegionalOffers(request)

        then:
        result == expectedOffers

        1 * restTemplate.exchange("http://localhost:8083/v1/regional", HttpMethod.POST, _ as HttpEntity<?>, _ as ParameterizedTypeReference<List<RegionalOfferDTO>>) >>
                new ResponseEntity<>(expectedOffers, HttpStatus.OK)
    }

    void "create intercity offers"() {
        given:
        IntercityOfferRequestDTO request = new IntercityOfferRequestDTOBuilder().withDefaults().build();
        List<IntercityOfferDTO> expectedOffers = [new IntercityOfferDTOBuilder().withDefaults().build()]

        and:
        restTemplate.exchange("http://localhost:8084/v1/intercity", HttpMethod.POST, _ as HttpEntity, _ as ParameterizedTypeReference<List<RegionalOfferDTO>>) >>
                new ResponseEntity<>(expectedOffers, HttpStatus.OK)

        when:
        List<IntercityOfferDTO> result = restClient.getIntercityOffers(request)

        then:
        result == expectedOffers

        1 * restTemplate.exchange("http://localhost:8084/v1/intercity", HttpMethod.POST, new HttpEntity<>(request), _ as ParameterizedTypeReference<List<RegionalOfferDTO>>) >>
                new ResponseEntity<>(expectedOffers, HttpStatus.OK)
    }

}
