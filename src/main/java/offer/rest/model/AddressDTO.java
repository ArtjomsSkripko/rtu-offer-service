package offer.rest.model;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@ApiModel(description = "Destination details")
public class AddressDTO {

    @ApiModelProperty(value = "Street", position = 1)
    private String streetName;

    @ApiModelProperty(value = "Home number", position = 2)
    private String homeNumber;

    @ApiModelProperty(value = "City", position = 3)
    @NotNull
    private String city;

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(String homeNumber) {
        this.homeNumber = homeNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

        AddressDTO castOther = (AddressDTO) other;

        return new EqualsBuilder()
                .append(streetName, castOther.streetName)
                .append(homeNumber, castOther.homeNumber)
                .append(city, castOther.city)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(streetName)
                .append(homeNumber)
                .append(city)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("streetName", streetName)
                .append("homeNumber", homeNumber)
                .append("city", city)
                .toString();
    }
}
