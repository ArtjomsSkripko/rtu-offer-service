package offer.service;

import java.util.ArrayList;
import java.util.List;

import offer.authorization.Utils;
import offer.exception.OfferNotFoundException;
import offer.rest.model.IntercityOfferDTO;
import offer.rest.model.IntercityOfferRequestDTO;
import offer.rest.model.RegionalOfferDTO;
import offer.rest.model.RegionalOfferRequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RegionalAndIntercityOfferRestClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegionalAndIntercityOfferRestClient.class);
    private final ServicePathConfig interCityPathConfig;
    private final ServicePathConfig regionalPathConfig;

    public static final ParameterizedTypeReference<List<IntercityOfferDTO>> INTERCITY_OFFER_RESPONSE
            = new ParameterizedTypeReference<List<IntercityOfferDTO>>() {
    };

    public static final ParameterizedTypeReference<List<RegionalOfferDTO>> REGIONAL_OFFER_RESPONSE
            = new ParameterizedTypeReference<List<RegionalOfferDTO>>() {
    };

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    public RegionalAndIntercityOfferRestClient(
            @Qualifier("intercityServiceRestClientConfig") ServicePathConfig interCityPathConfig,
            @Qualifier("regionalServiceRestClientConfig") ServicePathConfig regionalPathConfig) {
        this.interCityPathConfig = interCityPathConfig;
        this.regionalPathConfig = regionalPathConfig;
    }

    public List<RegionalOfferDTO> getRegionalOffers(RegionalOfferRequestDTO regionalOfferRequest) {
        ResponseEntity<List<RegionalOfferDTO>> regionalServiceResponse;
        try {
            regionalServiceResponse =
                    restTemplate.exchange(regionalPathConfig.buildUri(), HttpMethod.POST,
                            new HttpEntity<>(regionalOfferRequest, Utils.getAuthorizationHeader()), REGIONAL_OFFER_RESPONSE);
        } catch (Exception e) {
            LOGGER.error("An error happened while fetching regional offers");
            throw new OfferNotFoundException("No regional offers found");
        }

        return regionalServiceResponse.getBody();
    }

    public List<IntercityOfferDTO> getIntercityOffers(IntercityOfferRequestDTO intercityOfferRequest) {
        ResponseEntity<List<IntercityOfferDTO>> intercityServiceResponse ;
        try {
            intercityServiceResponse =
                    restTemplate.exchange(interCityPathConfig.buildUri(), HttpMethod.POST, new HttpEntity<>(intercityOfferRequest, Utils.getAuthorizationHeader()), INTERCITY_OFFER_RESPONSE);
        } catch (Exception e) {
            LOGGER.error("An error happened while fetching intercity offers");
            throw new OfferNotFoundException("No intercity offers");
        }

        return intercityServiceResponse != null ? intercityServiceResponse.getBody() : new ArrayList<>();
    }
}
