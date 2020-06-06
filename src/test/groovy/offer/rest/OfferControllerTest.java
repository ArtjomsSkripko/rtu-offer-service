package offer.rest;

import offer.rest.model.JourneyOfferRequestDTO;
import offer.service.OfferService;
import org.junit.BeforeClass;
import org.junit.Test;
import offer.exception.UnauthorizedException;
import offer.mapper.OfferMapper;

import static org.mockito.Mockito.mock;

public class OfferControllerTest {

    private static OfferService service;
    private static OfferMapper mapper;
    private static OfferController controller;

    @BeforeClass
    public static void initiate() {
        mapper = mock(OfferMapper.class);
        service = mock(OfferService.class);
        controller = new OfferController(service, mapper);
    }

    @Test(expected = UnauthorizedException.class)
    public void testGetOffersUnauthorized() {
        JourneyOfferRequestDTO requestDTO = new JourneyOfferRequestDTO();
        controller.createJourneyOffers(requestDTO);
    }

}
