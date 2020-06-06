package offer.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.common.collect.ImmutableMap;
import offer.builders.IntercityOfferRequestDTOBuilder;
import offer.builders.JourneyOfferRequestBuilder;
import offer.builders.RegionalOfferRequestDTOBuilder;
import offer.exception.OfferNotFoundException;
import offer.model.JourneyOfferRequest;
import offer.rest.model.IntercityOfferRequestDTO;
import offer.rest.model.RegionalOfferRequestDTO;
import org.apache.commons.lang3.tuple.Pair;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import offer.builders.JourneyOfferBuilder;
import offer.model.JourneyOffer;
import offer.rest.model.RegionalOfferDTO;
import org.testng.annotations.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;

public class OfferServiceNGTest {

    private static OfferService service;
    private static RegionalAndIntercityOfferRestClient restClient;

    @BeforeSuite
    public static void initiate() {
        restClient = mock(RegionalAndIntercityOfferRestClient.class);
        service = new OfferService(restClient);
    }

    @BeforeClass
    public void setup() {
        reset(restClient);
    }

    @Test
    public void testCreateOffers() {
        JourneyOfferRequest journeyOfferRequest = new JourneyOfferRequestBuilder().withDefaults().build();
        RegionalOfferRequestDTO regionalOfferRequestDTO = new RegionalOfferRequestDTOBuilder().withDefaults().build();
        IntercityOfferRequestDTO intercityOfferRequestDTO = new IntercityOfferRequestDTOBuilder().withDefaults().build();

        JourneyOffer expectedOffer = new JourneyOfferBuilder().withDefaults().build();

        Mockito.when(restClient.getIntercityOffers(intercityOfferRequestDTO)).thenReturn(Collections.singletonList(expectedOffer.getIntercityOffer()));

        RegionalOfferDTO expectedRegionalOffer = expectedOffer.getRegionalOffer().values().iterator().next();
        Mockito.when(restClient.getRegionalOffers(regionalOfferRequestDTO)).thenReturn(Collections.singletonList(expectedRegionalOffer));

        List<JourneyOffer> results = service.createOffers(Pair.of(Collections.singletonList(regionalOfferRequestDTO), Collections.singletonList(intercityOfferRequestDTO)), journeyOfferRequest);

        Assert.assertNotNull(results);
        Assert.assertFalse(results.isEmpty());
        Assert.assertEquals(results.size(), 1);
        Assert.assertEquals(results.get(0).getIntercityOffer(), expectedOffer.getIntercityOffer());
        Assert.assertEquals(results.get(0).getRegionalOffer(), ImmutableMap.of(expectedOffer.getAddressFrom().getCity(), expectedRegionalOffer));

        Mockito.verify(restClient, Mockito.times(1)).getIntercityOffers(any());
        Mockito.verify(restClient, Mockito.times(1)).getRegionalOffers(any());
    }

    @Test(expectedExceptions = OfferNotFoundException.class)
    public void testCreateTicketWhenOffersAlreadyUsed() {
        JourneyOfferRequest journeyOfferRequest = new JourneyOfferRequestBuilder().withDefaults().build();
        RegionalOfferRequestDTO regionalOfferRequestDTO = new RegionalOfferRequestDTOBuilder().withDefaults().build();
        IntercityOfferRequestDTO intercityOfferRequestDTO = new IntercityOfferRequestDTOBuilder().withDefaults().build();
        Mockito.when(restClient.getIntercityOffers(intercityOfferRequestDTO)).thenReturn(new ArrayList<>());
        Mockito.when(restClient.getRegionalOffers(regionalOfferRequestDTO)).thenReturn(new ArrayList<>());

        service.createOffers(Pair.of(Collections.singletonList(regionalOfferRequestDTO), Collections.singletonList(intercityOfferRequestDTO)), journeyOfferRequest);
    }
}
