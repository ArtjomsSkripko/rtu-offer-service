package offer.mapper;

import java.util.List;

import offer.builders.AddressDTOBuilder;
import offer.builders.JourneyOfferBuilder;
import offer.builders.JourneyOfferRequestDTOBuilder;
import offer.rest.model.IntercityOfferRequestDTO;
import offer.rest.model.JourneyOfferRequestDTO;
import offer.rest.model.RegionalOfferRequestDTO;
import org.apache.commons.lang3.tuple.Pair;
import org.testng.Assert;
import org.testng.annotations.Test;
import offer.model.JourneyOffer;
import offer.rest.model.JourneyOfferDTO;

public class OfferMapperNGTest {

    private OfferMapper mapper = new OfferMapper();

    @Test
    public void testToDTOffer() {
        JourneyOffer input = new JourneyOfferBuilder().withDefaults().build();

        JourneyOfferDTO output = mapper.toDTOOffer(input);

        Assert.assertNotNull(output);
        Assert.assertEquals(output.getAddressFrom().getCity(), input.getAddressFrom().getCity());
        Assert.assertEquals(output.getAddressFrom().getStreetName(), input.getAddressFrom().getStreetName());
        Assert.assertEquals(output.getAddressFrom().getHomeNumber(), input.getAddressFrom().getHomeNumber());
        Assert.assertEquals(output.getAddressTo().getCity(), input.getAddressTo().getCity());
        Assert.assertEquals(output.getAddressTo().getStreetName(), input.getAddressTo().getStreetName());
        Assert.assertEquals(output.getAddressTo().getHomeNumber(), input.getAddressTo().getHomeNumber());
        Assert.assertEquals(output.getDepartureTime(), input.getDepartureTime());
        Assert.assertEquals(output.getIntercityOffer(), input.getIntercityOffer());
        Assert.assertEquals(output.getRegionalOffers(), input.getRegionalOffer());
    }

    @Test
    public void testToRegionalAndIntercityOfferRequestsBothIntercityAndRegional() {
        JourneyOfferRequestDTO input = new JourneyOfferRequestDTOBuilder().withDefaults().build();

        Pair<List<RegionalOfferRequestDTO>, List<IntercityOfferRequestDTO>> result = mapper.toRegionalAndIntercityOfferRequests(input);


        RegionalOfferRequestDTO resultRegionalRequest = result.getKey().get(0);
        IntercityOfferRequestDTO resultIntercityOfferRequest = result.getValue().get(0);

        Assert.assertEquals(resultRegionalRequest.getAddressFrom(), input.getAddressFrom());
        Assert.assertEquals(resultRegionalRequest.getAddressTo().getCity(), input.getAddressFrom().getCity());
        Assert.assertEquals(resultRegionalRequest.getAddressTo().getHomeNumber(), "1");
        Assert.assertEquals(resultRegionalRequest.getAddressTo().getStreetName(), "Pragas iela");
        Assert.assertEquals(resultIntercityOfferRequest.getDepCity(), input.getAddressFrom().getCity());
        Assert.assertEquals(resultIntercityOfferRequest.getDestCity(), input.getAddressTo().getCity());
        Assert.assertEquals(resultIntercityOfferRequest.getDepartureTime(), input.getDepartureTime());
        Assert.assertEquals(resultIntercityOfferRequest.getPlaceType(), input.getPlaceType());
        Assert.assertEquals(resultIntercityOfferRequest.getPassenger(), input.getPassenger());
        Assert.assertEquals(resultRegionalRequest.getPassenger(), input.getPassenger());
    }

    @Test
    public void testToRegionalAndIntercityOfferRequestsRegionalOnly() {
        JourneyOfferRequestDTO input = new JourneyOfferRequestDTOBuilder().withDefaults()
                .addressTo(new AddressDTOBuilder().withDefaults().build()).build();

        Pair<List<RegionalOfferRequestDTO>, List<IntercityOfferRequestDTO>> result = mapper.toRegionalAndIntercityOfferRequests(input);

        RegionalOfferRequestDTO resultRegionalRequest = result.getKey().get(0);

        Assert.assertTrue(result.getValue().isEmpty());
        Assert.assertEquals(resultRegionalRequest.getAddressFrom(), input.getAddressFrom());
        Assert.assertEquals(resultRegionalRequest.getAddressTo(), input.getAddressTo());
        Assert.assertEquals(resultRegionalRequest.getPassenger(), input.getPassenger());
    }
}
