package offer.rest.model;

import java.util.List;
import java.util.Objects;

public class RegionalOfferDTO {

    private String id;
    private String offerType;
    private String companyName;
    private String passenger;
    private List<String> transportTypes;
    private String taxRate;
    private List<String> routeNumbers;
    private String city;
    private AddressDTO addressFrom;
    private AddressDTO addressTo;
    private String discount;
    private String originalPrice;
    private String priceWithDiscount;
    private Integer numberOfTickets;

    public RegionalOfferDTO() {
        // default constructor
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOfferType() {
        return offerType;
    }

    public void setOfferType(String offerType) {
        this.offerType = offerType;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegionalOfferDTO that = (RegionalOfferDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(offerType, that.offerType) &&
                Objects.equals(companyName, that.companyName) &&
                Objects.equals(passenger, that.passenger) &&
                Objects.equals(transportTypes, that.transportTypes) &&
                Objects.equals(taxRate, that.taxRate) &&
                Objects.equals(routeNumbers, that.routeNumbers) &&
                Objects.equals(city, that.city) &&
                Objects.equals(addressFrom, that.addressFrom) &&
                Objects.equals(addressTo, that.addressTo) &&
                Objects.equals(discount, that.discount) &&
                Objects.equals(originalPrice, that.originalPrice) &&
                Objects.equals(priceWithDiscount, that.priceWithDiscount) &&
                Objects.equals(numberOfTickets, that.numberOfTickets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, offerType, companyName, passenger, transportTypes, taxRate, routeNumbers, city, addressFrom, addressTo, discount, originalPrice, priceWithDiscount, numberOfTickets);
    }

    @Override
    public String toString() {
        return "RegionalOfferDTO{" +
                "id='" + id + '\'' +
                ", offerType='" + offerType + '\'' +
                ", companyName='" + companyName + '\'' +
                ", passenger='" + passenger + '\'' +
                ", transportTypes=" + transportTypes +
                ", taxRate='" + taxRate + '\'' +
                ", routeNumbers=" + routeNumbers +
                ", city='" + city + '\'' +
                ", addressFrom=" + addressFrom +
                ", addressTo=" + addressTo +
                ", discount='" + discount + '\'' +
                ", originalPrice='" + originalPrice + '\'' +
                ", priceWithDiscount='" + priceWithDiscount + '\'' +
                ", numberOfTickets=" + numberOfTickets +
                '}';
    }

    public Integer getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(Integer numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    public String getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(String taxRate) {
        this.taxRate = taxRate;
    }

    public List<String> getRouteNumbers() {
        return routeNumbers;
    }

    public void setRouteNumbers(List<String> routeNumbers) {
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    /* Equals & HashCode */

}
