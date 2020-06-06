package configs.steps;

import offer.rest.model.AddressDTO;

public class AddressDTOBuilder {

    private AddressDTO template;

    public AddressDTOBuilder() {
        this.template = new AddressDTO();
    }

    public AddressDTOBuilder streetName(String streetName) {
        template.setStreetName(streetName);
        return this;
    }

    public AddressDTOBuilder homeNumber(String homeNumber) {
        template.setHomeNumber(homeNumber);
        return this;
    }

    public AddressDTOBuilder city(String city) {
        template.setCity(city);
        return this;
    }

    public AddressDTOBuilder withDefaults() {
        return this
                .streetName("Brivibas iela")
                .homeNumber("25")
                .city("RIGA")
                ;
    }

    public AddressDTO build() {
        return template;
    }
}
