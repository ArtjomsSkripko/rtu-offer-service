package configs.steps;

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
import com.github.tomakehurst.wiremock.common.ClasspathFileSource;
import com.github.tomakehurst.wiremock.common.Slf4jNotifier;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.github.tomakehurst.wiremock.standalone.JsonFileMappingsSource;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import offer.exception.OfferNotFoundException;
import offer.rest.model.AddressDTO;
import offer.rest.model.IntercityOfferDTO;
import offer.rest.model.JourneyOfferRequestDTO;
import offer.rest.model.RegionalOfferDTO;
import org.hamcrest.Matchers;
import org.json.JSONException;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.springframework.http.HttpStatus;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

public class OfferStepDefs extends SpringIntegrationTest {

    private JourneyOfferRequestDTO offerRequest = new JourneyOfferRequestDTO();
    private Response response = null;
    private Boolean mockRegionalError = false;

    @Given("^offer request for trip from (.+) to (.+)$")
    public void createOfferRequestWithDepAndDestCities(String depCity, String destCity) throws JSONException {
        AddressDTO addressFrom = new AddressDTO();
        addressFrom.setCity(depCity);
        AddressDTO addressTo = new AddressDTO();
        addressTo.setCity(destCity);

        offerRequest.setAddressFrom(addressFrom);
        offerRequest.setAddressTo(addressTo);
    }

    @And("^add ticket type (.+)$")
    public void addTicketType(String ticketType) {
        offerRequest.setOfferType(ticketType);
    }

    @And("^add passenger type (.+)$")
    public void addPassenger(String passengerType) {
        offerRequest.setPassenger(passengerType);
    }

    @And("^add company name (.+)$")
    public void addCompanyName(String companyName) {
        offerRequest.setCompanyName(companyName);
    }

    @And("^add place type (.+)$")
    public void addPlaceType(String placeType) {
        offerRequest.setPlaceType(placeType);
    }

    @And("^set number of tickets to (\\d+)$")
    public void addNumberOfTickets(Integer numberOfTickets) {
        offerRequest.setNumberOfTickets(numberOfTickets);
    }

    @And("^set number of luggage to (\\d+)$")
    public void addNumberOfLuggage(Integer numberOfLuggage) {
        offerRequest.setNumberOfLuggage(numberOfLuggage);
    }

    @When("^call create offers$")
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

    @And("^add transport type (.+)$")
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

    @And("^add route number (\\d+)$")
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

    @And("^set departure time to (today|after 1 your|tomorrow|after 5 days)$")
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

    @And("^set (departure|arrival) street name (.+) and home number (.+)$")
    public void addAddressDetails(String addressType, String streetName, String homeNumber) throws JSONException {
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

    @Then("^response status is (.+)$")
    public void responseStatusIs(String responseStatus) {
        response.then().statusCode(HttpStatus.valueOf(responseStatus).value());
    }

    @And("^response has (\\d+) offers?$")
    public void responseHasTicket(int ticketCount) {
        response.then().body("ticketId", Matchers.hasSize(ticketCount));
    }

    @And("^response (has|has no) regional offers$")
    public void regionalOffers(String offerPresent) {
        if (offerPresent.equals("has no")) {
            response.then().body("[0].regionalOffers", Matchers.nullValue());
        } else {
            response.then().body("[0].regionalOffers", Matchers.notNullValue());
        }
    }

    @And("^response (has|has no) intercity offers$")
    public void intercityOffer(String offerPresent) {
        if (offerPresent.equals("has no")) {
            response.then().body("[0].intercityOffer", Matchers.nullValue());
        } else {
            response.then().body("[0].intercityOffer", Matchers.notNullValue());
        }
    }

    @And("regional service will return error")
    public void regionalServiceWillReturnError() {
        mockRegionalError = true;
    }
}
