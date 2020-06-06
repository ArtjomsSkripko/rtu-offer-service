package offer.rest.model;

import java.time.ZonedDateTime;
import java.util.Map;

import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class JourneyOfferDTO {

    @ApiModelProperty(value = "Departure address")
    private AddressDTO addressFrom;
    @ApiModelProperty(value = "Destination address")
    private AddressDTO addressTo;
    @ApiModelProperty(value = "Departure time")
    private ZonedDateTime departureTime;
    @ApiModelProperty(value = "Regional offers")
    private Map<String, RegionalOfferDTO> regionalOffersByCity;
    @ApiModelProperty(value = "Intercity offers")
    private IntercityOfferDTO intercityOffer;

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

    public Map<String, RegionalOfferDTO> getRegionalOffers() {
        return regionalOffersByCity;
    }

    public void setRegionalOffers(Map<String, RegionalOfferDTO> regionalOffers) {
        this.regionalOffersByCity = regionalOffers;
    }

    public IntercityOfferDTO getIntercityOffer() {
        return intercityOffer;
    }

    public void setIntercityOffer(IntercityOfferDTO intercityOffer) {
        this.intercityOffer = intercityOffer;
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

        JourneyOfferDTO castOther = (JourneyOfferDTO) other;

        return new EqualsBuilder()
                .append(regionalOffersByCity, castOther.regionalOffersByCity)
                .append(intercityOffer, castOther.intercityOffer)
                .append(addressFrom, castOther.addressFrom)
                .append(addressTo, castOther.addressTo)
                .append(departureTime, castOther.departureTime)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(regionalOffersByCity)
                .append(intercityOffer)
                .append(addressFrom)
                .append(addressTo)
                .append(departureTime)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("regionalOffersByCity", regionalOffersByCity)
                .append("intercityOffer", intercityOffer)
                .append("addressFrom", addressFrom)
                .append("addressTo", addressTo)
                .append("departureTime", departureTime)
                .toString();
    }
}
