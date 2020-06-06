package offer.rest.model;

import java.time.ZonedDateTime;
import java.util.List;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import offer.enumeration.OfferTypeEnum;
import offer.enumeration.PassengerTypeEnum;
import offer.validation.ValidEnum;

@ApiModel(description = "Offer request details")
public class JourneyOfferRequestDTO {

    @ApiModelProperty(value = "Type of offer", position = 1)
    @ValidEnum(value = OfferTypeEnum.class, message = "Provided offer type is not supported", required = true)
    private String offerType;

    @ApiModelProperty(value = "Number of tickets", position = 2)
    private Integer numberOfTickets;

    @ApiModelProperty(value = "Company name", position = 3)
    @NotNull
    private String companyName;

    @ApiModelProperty(value = "Type of passenger", position = 4)
    @ValidEnum(value = PassengerTypeEnum.class, message = "Provided passenger type is not supported")
    private String passenger;

    @ApiModelProperty(value = "Types of transport", position = 5)
    private List<String> transportTypes;

    @ApiModelProperty(value = "Route numbers", position = 6)
    private List<Integer> routeNumbers;

    @NotNull
    @ApiModelProperty(value = "Departure address", position = 7)
    private AddressDTO addressFrom;

    @NotNull
    @ApiModelProperty(value = "Destination address", position = 8)
    private AddressDTO addressTo;

    @ApiModelProperty(value = "Departure time", position = 9, required = true)
    private ZonedDateTime departureTime;

    @ApiModelProperty(value = "Number of luggage items", position = 10)
    private Integer numberOfLuggage;

    @ApiModelProperty(value = "Place type", position = 11)
    private String placeType;

    public String getOfferType() {
        return offerType;
    }

    public void setOfferType(String offerType) {
        this.offerType = offerType;
    }

    public Integer getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(Integer numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPassenger() {
        return passenger;
    }

    public void setPassenger(String passenger) {
        this.passenger = passenger;
    }

    public List<String> getTransportTypes() {
        return transportTypes;
    }

    public void setTransportTypes(List<String> transportTypes) {
        this.transportTypes = transportTypes;
    }

    public List<Integer> getRouteNumbers() {
        return routeNumbers;
    }

    public void setRouteNumbers(List<Integer> routeNumbers) {
        this.routeNumbers = routeNumbers;
    }

    public AddressDTO getAddressFrom() {
        return addressFrom;
    }

    public void setAddressFrom(AddressDTO addressFrom) {
        this.addressFrom = addressFrom;
    }

    public AddressDTO getAddressTo() {
        return addressTo;
    }

    public void setAddressTo(AddressDTO addressTo) {
        this.addressTo = addressTo;
    }

    public ZonedDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(ZonedDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public Integer getNumberOfLuggage() {
        return numberOfLuggage;
    }

    public void setNumberOfLuggage(Integer numberOfLuggage) {
        this.numberOfLuggage = numberOfLuggage;
    }

    public String getPlaceType() {
        return placeType;
    }

    public void setPlaceType(String placeType) {
        this.placeType = placeType;
    }

    /* Equals & HashCode */

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        JourneyOfferRequestDTO castOther = (JourneyOfferRequestDTO) other;

        return new EqualsBuilder()
                .append(offerType, castOther.offerType)
                .append(numberOfTickets, castOther.numberOfTickets)
                .append(companyName, castOther.companyName)
                .append(passenger, castOther.passenger)
                .append(transportTypes, castOther.transportTypes)
                .append(routeNumbers, castOther.routeNumbers)
                .append(addressFrom, castOther.addressFrom)
                .append(addressTo, castOther.addressTo)
                .append(placeType, castOther.placeType)
                .append(numberOfLuggage, castOther.numberOfLuggage)
                .append(departureTime, castOther.departureTime)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(offerType)
                .append(numberOfTickets)
                .append(companyName)
                .append(passenger)
                .append(transportTypes)
                .append(routeNumbers)
                .append(addressFrom)
                .append(addressTo)
                .append(placeType)
                .append(numberOfLuggage)
                .append(departureTime)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("offerType", offerType)
                .append("numberOfTickets", numberOfTickets)
                .append("companyName", companyName)
                .append("passenger", passenger)
                .append("transportTypes", transportTypes)
                .append("routeNumbers", routeNumbers)
                .append("addressFrom", addressFrom)
                .append("addressTo", addressTo)
                .append("placeType", placeType)
                .append("numberOfLuggage", numberOfLuggage)
                .append("departureTime", departureTime)
                .toString();
    }
}
