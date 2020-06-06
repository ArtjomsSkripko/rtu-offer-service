package offer.service;

import java.util.Collections;
import java.util.List;

import offer.builders.IntercityOfferDTOBuilder;
import offer.builders.IntercityOfferRequestDTOBuilder;
import offer.builders.RegionalOfferDTOBuilder;
import offer.builders.RegionalOfferRequestDTOBuilder;
import offer.rest.model.IntercityOfferDTO;
import offer.rest.model.IntercityOfferRequestDTO;
import offer.rest.model.RegionalOfferDTO;
import offer.rest.model.RegionalOfferRequestDTO;
import org.mockito.Mock;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static offer.service.RegionalAndIntercityOfferRestClient.INTERCITY_OFFER_RESPONSE;
import static offer.service.RegionalAndIntercityOfferRestClient.REGIONAL_OFFER_RESPONSE;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RegionalAndIntercityOfferRestClientNGTest {

    private RestTemplate restTemplate;
    private RegionalAndIntercityOfferRestClient restClient;

    @BeforeSuite
    public void setUp() {
        restTemplate = mock(RestTemplate.class);
        ServicePathConfig interCityPathConfig = mock(ServicePathConfig.class);
        ServicePathConfig regionalPathConfig = mock(ServicePathConfig.class);
        restClient = new RegionalAndIntercityOfferRestClient(interCityPathConfig, regionalPathConfig);
        when(regionalPathConfig.buildUri()).thenReturn("http://localhost:8083/v1/regional");
        when(interCityPathConfig.buildUri()).thenReturn("http://localhost:8084/v1/intercity");
        ReflectionTestUtils.setField(restClient, "restTemplate", restTemplate, RestTemplate.class);
    }

    @Test
    public void testGetRegionalOffers() {
        RegionalOfferRequestDTO request = new RegionalOfferRequestDTOBuilder().withDefaults().build();

        List<RegionalOfferDTO> expectedOffers = Collections.singletonList(new RegionalOfferDTOBuilder().withDefaults().build());
        when(restTemplate.exchange(eq("http://localhost:8083/v1/regional"), eq(HttpMethod.POST), any(HttpEntity.class), eq(REGIONAL_OFFER_RESPONSE)))
                .thenReturn(new ResponseEntity<>(expectedOffers, HttpStatus.OK));

        List<RegionalOfferDTO> result = restClient.getRegionalOffers(request);

        Assert.assertEquals(result, expectedOffers);

        verify(restTemplate, times(1))
                .exchange(eq("http://localhost:8083/v1/regional"), eq(HttpMethod.POST), any(HttpEntity.class), eq(REGIONAL_OFFER_RESPONSE));

    }

    @Test
    public void testGetIntercityOffers() {
        IntercityOfferRequestDTO request = new IntercityOfferRequestDTOBuilder().withDefaults().build();

        List<IntercityOfferDTO> expectedOffers = Collections.singletonList(new IntercityOfferDTOBuilder().withDefaults().build());
        when(restTemplate.exchange(eq("http://localhost:8084/v1/intercity"), eq(HttpMethod.POST), any(HttpEntity.class), eq(INTERCITY_OFFER_RESPONSE)))
                .thenReturn(new ResponseEntity<>(expectedOffers, HttpStatus.OK));

        List<IntercityOfferDTO> result = restClient.getIntercityOffers(request);

        Assert.assertEquals(result, expectedOffers);

        verify(restTemplate, times(1))
                .exchange(eq("http://localhost:8084/v1/intercity"), eq(HttpMethod.POST), any(HttpEntity.class), eq(INTERCITY_OFFER_RESPONSE));

    }
}
