package configs.steps.jbehave;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import configs.steps.IntercityOfferDTOBuilder;
import configs.steps.RegionalOfferDTOBuilder;
import configs.steps.SpringIntegrationTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import offer.exception.OfferNotFoundException;
import offer.rest.model.AddressDTO;
import offer.rest.model.IntercityOfferDTO;
import offer.rest.model.JourneyOfferRequestDTO;
import offer.rest.model.RegionalOfferDTO;
import org.hamcrest.Matchers;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.json.JSONException;
import org.springframework.http.HttpStatus;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

public class JBehaveSteps extends SpringIntegrationTest {

    private JourneyOfferRequestDTO offerRequest = new JourneyOfferRequestDTO();
    private Response response = null;
    private Boolean mockRegionalError = false;

    @Given("offer request for trip from $depCity to $destCity")
    public void createOfferRequestWithDepAndDestCities(String depCity, String destCity) {
        AddressDTO addressFrom = new AddressDTO();
        addressFrom.setCity(depCity);
        AddressDTO addressTo = new AddressDTO();
        addressTo.setCity(destCity);

        offerRequest.setAddressFrom(addressFrom);
        offerRequest.setAddressTo(addressTo);
    }

    @Given("add ticket type $ticketType")
    public void addTicketType(String ticketType) {
        offerRequest.setOfferType(ticketType);
    }

    @Given("add passenger type $passengerType")
    public void addPassenger(String passengerType) {
        offerRequest.setPassenger(passengerType);
    }

    @Given("add company name $companyName")
    public void addCompanyName(String companyName) {
        offerRequest.setCompanyName(companyName);
    }

    @Given("add place type $placeType")
    public void addPlaceType(String placeType) {
        offerRequest.setPlaceType(placeType);
    }

    @Given("set number of tickets to $numberOfTickets")
    public void addNumberOfTickets(Integer numberOfTickets) {
        offerRequest.setNumberOfTickets(numberOfTickets);
    }

    @Given("set number of luggage to $numberOfLuggage")
    public void addNumberOfLuggage(Integer numberOfLuggage) {
        offerRequest.setNumberOfLuggage(numberOfLuggage);
    }

    @When("call create offers")
    public void callCreateOffers() throws JsonProcessingException {
        WireMockServer server = new WireMockServer(wireMockConfig().port(9090));
        IntercityOfferDTO intercityOfferDTO = new IntercityOfferDTOBuilder().withDefaults().build();
        RegionalOfferDTO regionalOfferDTO = new RegionalOfferDTOBuilder().withDefaults().build();
        RegionalOfferDTO regionalOfferDTO2 = new RegionalOfferDTOBuilder().withDefaults().city("DAUGAVPILS").build();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        server.start();
        server.stubFor(
                WireMock.post(WireMock.urlEqualTo("/v1/intercity"))
                        .willReturn(WireMock.aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody(objectMapper.writeValueAsString(Collections.singletonList(intercityOfferDTO)))
                        ));

        if (mockRegionalError) {
            server.stubFor(
                    WireMock.post(WireMock.urlEqualTo("/v1/regional"))
                            .willReturn(WireMock.aResponse()
                                    .withStatus(404)
                                    .withHeader("Content-Type", "application/json")
                                    .withBody(objectMapper.writeValueAsString(new OfferNotFoundException("no regional offers")))
                            ));
        } else {
            server.stubFor(
                    WireMock.post(WireMock.urlEqualTo("/v1/regional"))
                            .willReturn(WireMock.aResponse()
                                    .withStatus(200)
                                    .withHeader("Content-Type", "application/json")
                                    .withBody(objectMapper.writeValueAsString(Arrays.asList(regionalOfferDTO, regionalOfferDTO2)))
                            ));
        }

        response = RestAssured.given()
                .when()
                .contentType("application/json")
                .header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6InRlc3RVc2VyMSIsInVzZXJfaWQiOiIxMjM0MTI1IiwiY29udHJhY3RfbW9kZWwiOiJjb250cmFjdF8xIiwicm9sZSI6IlJFR1VMQVJfVVNFUiIsInRvdWNocG9pbnQiOiJXRUIifQ.vwqvyEYcuZdlmt65QfjwhhZeURNEigCWqfJvGS-kWZA")
                .body(offerRequest)
                .port(8080)
                .post("/v1/offer/");

        response.then().log().all();

        server.stop();

    }

    @Given("add transport type $transportType")
    public void addTransportType(String transportType) {
        if (offerRequest.getTransportTypes() == null) {
            List<String> transportTypes = new ArrayList<>();
            transportTypes.add(transportType);
            offerRequest.setTransportTypes(transportTypes);
        } else {
            List<String> transportTypes = offerRequest.getTransportTypes();
            transportTypes.add(transportType);
        }
    }

    @Given("add route number $routeNumber")
    public void addRouteNumber(Integer routeNumber) {
        if (offerRequest.getRouteNumbers() == null) {
            List<Integer> routeNumbers = new ArrayList<>();
            routeNumbers.add(routeNumber);
            offerRequest.setRouteNumbers(routeNumbers);
        } else {
            List<Integer> routeNumbers = offerRequest.getRouteNumbers();
            routeNumbers.add(routeNumber);
        }
    }

    @Given("set departure time to $depTime")
    public void setDepTime(String depTime) {
        ZonedDateTime departureTime;
        switch (depTime) {
            case "today":
                departureTime = ZonedDateTime.now();
                break;
            case "after 1 your":
                departureTime = ZonedDateTime.now().plusHours(1);
                break;
            case "tomorrow":
                departureTime = ZonedDateTime.now().plusDays(1);
                break;
            default:
                departureTime = ZonedDateTime.now().plusDays(5);
                break;
        }

        offerRequest.setDepartureTime(departureTime);
    }

    @Given("set $addressType street name $streetName and home number $homeNumber")
    public void addAddressDetails(String addressType, String streetName, String homeNumber) {
        if (addressType.equals("departure")) {
            AddressDTO addressFrom = offerRequest.getAddressFrom();
            addressFrom.setStreetName(streetName);
            addressFrom.setHomeNumber(homeNumber);
        } else {
            AddressDTO addressTo = offerRequest.getAddressTo();
            addressTo.setStreetName(streetName);
            addressTo.setHomeNumber(homeNumber);
        }
    }

    @Then("response status is $responseStatus")
    public void responseStatusIs(String responseStatus) {
        response.then().statusCode(HttpStatus.valueOf(responseStatus).value());
    }

    @Then("response has $ticketCount offer(s)")
    public void responseHasTicket(int ticketCount) {
        response.then().body("ticketId", Matchers.hasSize(ticketCount));
    }

    @Then("response $offerPresent regional offers")
    public void regionalOffers(String offerPresent) {
        if (offerPresent.equals("has no")) {
            response.then().body("[0].regionalOffers", Matchers.nullValue());
        } else {
            response.then().body("[0].regionalOffers", Matchers.notNullValue());
        }
    }

    @Then("response $offerPresent intercity offers")
    public void intercityOffer(String offerPresent) {
        if (offerPresent.equals("has no")) {
            response.then().body("[0].intercityOffer", Matchers.nullValue());
        } else {
            response.then().body("[0].intercityOffer", Matchers.notNullValue());
        }
    }

    @When("regional service will return error")
    public void regionalServiceWillReturnError() {
        mockRegionalError = true;
    }
}
