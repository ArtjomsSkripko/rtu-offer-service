package offer.model;

import java.time.ZonedDateTime;
import java.util.Map;

import offer.rest.model.IntercityOfferDTO;
import offer.rest.model.RegionalOfferDTO;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class JourneyOffer {

    private Address addressFrom;
    private Address addressTo;
    private ZonedDateTime departureTime;
    private Map<String, RegionalOfferDTO> regionalOffer;
    private IntercityOfferDTO intercityOffer;

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

    public Map<String, RegionalOfferDTO> getRegionalOffer() {
        return regionalOffer;
    }

    public void setRegionalOffer(Map<String, RegionalOfferDTO> regionalOffer) {
        this.regionalOffer = regionalOffer;
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

        JourneyOffer castOther = (JourneyOffer) other;

        return new EqualsBuilder()
                .append(regionalOffer, castOther.regionalOffer)
                .append(intercityOffer, castOther.intercityOffer)
                .append(addressFrom, castOther.addressFrom)
                .append(addressTo, castOther.addressTo)
                .append(departureTime, castOther.departureTime)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(regionalOffer)
                .append(intercityOffer)
                .append(addressFrom)
                .append(addressTo)
                .append(departureTime)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("regionalOffer", regionalOffer)
                .append("intercityOffer", intercityOffer)
                .append("addressFrom", addressFrom)
                .append("addressTo", addressTo)
                .append("departureTime", departureTime)
                .toString();
    }
}
