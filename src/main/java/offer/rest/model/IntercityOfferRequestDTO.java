package offer.rest.model;

import java.time.ZonedDateTime;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import offer.enumeration.PassengerTypeEnum;
import offer.enumeration.TransportTypeEnum;
import offer.validation.ValidEnum;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@ApiModel(description = "Offer request details")
public class IntercityOfferRequestDTO {

    @ApiModelProperty(value = "Number of tickets", position = 1, required = true)
    @NotNull
    private Integer numberOfTickets;

    @ApiModelProperty(value = "Company name", position = 2)
    private String companyName;

    @ApiModelProperty(value = "Type of passenger", position = 3)
    @ValidEnum(value = PassengerTypeEnum.class, message = "Provided passenger type is not supported")
    private String passenger;

    @ApiModelProperty(value = "Types of transport", position = 4)
    @NotNull
    @ValidEnum(value = TransportTypeEnum.class, message = "Provided transport type is not supported", required = true)
    private String transportType;

    @NotNull
    @ApiModelProperty(value = "Destination City", position = 5, required = true)
    private String destCity;

    @NotNull
    @ApiModelProperty(value = "Departure time", position = 6, required = true)
    private ZonedDateTime departureTime;

    @NotNull
    @ApiModelProperty(value = "Departure City", position = 7, required = true)
    private String depCity;

    @ApiModelProperty(value = "Number of luggage items", position = 8)
    private Integer numberOfLuggage;

    @NotNull
    @ApiModelProperty(value = "Place type", position = 9, required = true)
    private String placeType;

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


    public Integer getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(Integer numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public String getDestCity() {
        return destCity;
    }

    public void setDestCity(String destCity) {
        this.destCity = destCity;
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

    public String getDepCity() {
        return depCity;
    }

    public void setDepCity(String depCity) {
        this.depCity = depCity;
    }

    public ZonedDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(ZonedDateTime departureTime) {
        this.departureTime = departureTime;
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

        IntercityOfferRequestDTO castOther = (IntercityOfferRequestDTO) other;

        return new EqualsBuilder()
                .append(numberOfTickets, castOther.numberOfTickets)
                .append(companyName, castOther.companyName)
                .append(passenger, castOther.passenger)
                .append(transportType, castOther.transportType)
                .append(destCity, castOther.destCity)
                .append(numberOfLuggage, castOther.numberOfLuggage)
                .append(placeType, castOther.placeType)
                .append(depCity, castOther.depCity)
                .append(departureTime, castOther.departureTime)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(numberOfTickets)
                .append(companyName)
                .append(passenger)
                .append(transportType)
                .append(destCity)
                .append(numberOfLuggage)
                .append(placeType)
                .append(depCity)
                .append(departureTime)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("numberOfTickets", numberOfTickets)
                .append("companyName", companyName)
                .append("transportType", transportType)
                .append("destCity", destCity)
                .append("numberOfLuggage", numberOfLuggage)
                .append("placeType", placeType)
                .append("depCity", depCity)
                .append("departureTime", departureTime)
                .toString();
    }
}
