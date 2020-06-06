package offer.builders;

import offer.model.Address;

public class AddressBuilder {

    private Address template;

    public AddressBuilder() {
        this.template = new Address();
    }

    public AddressBuilder streetName(String streetName) {
        template.setStreetName(streetName);
        return this;
    }

    public AddressBuilder homeNumber(String homeNumber) {
        template.setHomeNumber(homeNumber);
        return this;
    }

    public AddressBuilder city(String city) {
        template.setCity(city);
        return this;
    }

    public AddressBuilder withDefaults() {
        return this
                .streetName("Brivibas iela")
                .homeNumber("25")
                .city("RIGA")
                ;
    }

    public Address build() {
        return template;
    }
}
