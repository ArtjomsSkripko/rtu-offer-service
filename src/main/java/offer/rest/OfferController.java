package offer.rest;

import java.util.List;
import java.util.stream.Collectors;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import offer.rest.model.JourneyOfferDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import offer.authorization.UserToken;
import offer.authorization.Utils;
import offer.exception.UnauthorizedException;
import offer.mapper.OfferMapper;
import offer.model.JourneyOffer;
import offer.rest.model.JourneyOfferRequestDTO;
import offer.service.OfferService;

@RestController
@Validated
@Api(protocols = "http, https")
@RequestMapping(value = "v1/offer")
public class OfferController {

    private OfferService offerService;
    private OfferMapper mapper;

    @Autowired
    public OfferController(OfferService offerService, OfferMapper mapper) {
        this.offerService = offerService;
        this.mapper = mapper;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ApiOperation(
            value = "Generates journey offers",
            notes = "Generates journey offers based on input criteria.",
            tags = {"Journey offers"}
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "InvalidRequestError, code 350: Invalid request"),
            @ApiResponse(code = 500, message = "SomeError")
    })
    public List<JourneyOfferDTO> createJourneyOffers(@RequestBody JourneyOfferRequestDTO requestDto) {
        UserToken token = Utils.getServiceUser();
        if (token == null) {
           throw new UnauthorizedException("current user is not authorized to fetch offers");
        }

        List<JourneyOffer> offers = offerService.createOffers(mapper.toRegionalAndIntercityOfferRequests(requestDto),
                mapper.toDomainRequest(requestDto));
        return offers.stream().map(o -> mapper.toDTOOffer(o)).collect(Collectors.toList());
    }
}

