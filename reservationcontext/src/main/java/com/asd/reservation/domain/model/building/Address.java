package java.com.asd.reservation.domain.model.building;

public class Address {
    private final String street;
    private final String streetNr;
    private final String postalCode;
    private final String city;

    public Address(String street, String streetNr, String postalCode, String city) {
        this.street = street;
        this.streetNr = streetNr;
        this.postalCode = postalCode;
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public String getStreetNr() {
        return streetNr;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }
}
