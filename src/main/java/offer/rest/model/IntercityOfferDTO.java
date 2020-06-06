package offer.rest.model;

import java.time.ZonedDateTime;

import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class IntercityOfferDTO {

    @ApiModelProperty(value = "Id of offer")
    private String id;

    @ApiModelProperty(value = "Number of tickets")
    private Integer numberOfTickets;

    @ApiModelProperty(value = "Company name")
    private String companyName;

    @ApiModelProperty(value = "Type of passenger")
    private String passenger;

    @ApiModelProperty(value = "Types of transport")
    private String transportType;

    @ApiModelProperty(value = "Destination City")
    private String destCity;

    @ApiModelProperty(value = "Departure City")
    private String depCity;

    @ApiModelProperty(value = "Number of luggage items")
    private Integer numberOfLuggage;

    @ApiModelProperty(value = "Place type")
    private String placeType;

    @ApiModelProperty(value = "Discount")
    private String discount;

    @ApiModelProperty(value = "Price without discount")
    private String originalPrice;

    @ApiModelProperty(value = "Price with discount")
    private String priceWithDiscount;

    @ApiModelProperty(value = "Tax rate")
    private String taxRate;

    @ApiModelProperty(value = "Departure time")
    private ZonedDateTime depTime;

    public IntercityOfferDTO() {
        // default constructor
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

    public String getDepCity() {
        return depCity;
    }

    public void setDepCity(String depCity) {
        this.depCity = depCity;
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

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getPriceWithDiscount() {
        return priceWithDiscount;
    }

    public void setPriceWithDiscount(String priceWithDiscount) {
        this.priceWithDiscount = priceWithDiscount;
    }

    public String getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(String taxRate) {
        this.taxRate = taxRate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ZonedDateTime getDepTime() {
        return depTime;
    }

    public void setDepTime(ZonedDateTime depTime) {
        this.depTime = depTime;
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

        IntercityOfferDTO castOther = (IntercityOfferDTO) other;

        return new EqualsBuilder()
                .append(id, castOther.id)
                .append(numberOfTickets, castOther.numberOfTickets)
                .append(companyName, castOther.companyName)
                .append(passenger, castOther.passenger)
                .append(taxRate, castOther.taxRate)
                .append(transportType, castOther.transportType)
                .append(destCity, castOther.destCity)
                .append(depCity, castOther.depCity)
                .append(numberOfLuggage, castOther.numberOfLuggage)
                .append(placeType, castOther.placeType)
                .append(discount, castOther.discount)
                .append(originalPrice, castOther.originalPrice)
                .append(priceWithDiscount, castOther.priceWithDiscount)
                .append(depTime, castOther.depTime)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(id)
                .append(numberOfTickets)
                .append(companyName)
                .append(passenger)
                .append(taxRate)
                .append(transportType)
                .append(destCity)
                .append(depCity)
                .append(numberOfLuggage)
                .append(placeType)
                .append(discount)
                .append(originalPrice)
                .append(priceWithDiscount)
                .append(depTime)
           .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", id)
                .append("numberOfTickets", numberOfTickets)
                .append("companyName", companyName)
                .append("passenger", passenger)
                .append("taxRate", taxRate)
                .append("transportType", transportType)
                .append("destCity", destCity)
                .append("depCity", depCity)
                .append("numberOfLuggage", numberOfLuggage)
                .append("placeType", placeType)
                .append("discount", discount)
                .append("originalPrice", originalPrice)
                .append("priceWithDiscount", priceWithDiscount)
                .append("depTime", depTime)
                .toString();
    }
}
