package offer.model;

import java.time.ZonedDateTime;
import java.util.List;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import offer.enumeration.OfferTypeEnum;
import offer.enumeration.PassengerTypeEnum;
import offer.enumeration.TransportTypeEnum;
import offer.rest.model.AddressDTO;
import offer.validation.ValidEnum;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@ApiModel(description = "Offer request details")
public class JourneyOfferRequest {

    private OfferTypeEnum offerType;
    private Integer numberOfTickets;
    private String companyName;
    private PassengerTypeEnum passenger;
    private List<TransportTypeEnum> transportTypes;
    private List<Integer> routeNumbers;
    private Address addressFrom;
    private Address addressTo;
    private ZonedDateTime departureTime;
    private Integer numberOfLuggage;
    private String placeType;

    public OfferTypeEnum getOfferType() {
        return offerType;
    }

    public void setOfferType(OfferTypeEnum offerType) {
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

    public PassengerTypeEnum getPassenger() {
        return passenger;
    }

    public void setPassenger(PassengerTypeEnum passenger) {
        this.passenger = passenger;
    }

    public List<TransportTypeEnum> getTransportTypes() {
        return transportTypes;
    }

    public void setTransportTypes(List<TransportTypeEnum> transportTypes) {
        this.transportTypes = transportTypes;
    }

    public List<Integer> getRouteNumbers() {
        return routeNumbers;
    }

    public void setRouteNumbers(List<Integer> routeNumbers) {
        this.routeNumbers = routeNumbers;
    }

    public Address getAddressFrom() {
        return addressFrom;
    }

    public void setAddressFrom(Address addressFrom) {
        this.addressFrom = addressFrom;
    }

    public Address getAddressTo() {
        return addressTo;
    }

    public void setAddressTo(Address addressTo) {
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

        JourneyOfferRequest castOther = (JourneyOfferRequest) other;

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
