import java.util.Objects;


public class PlainAddress {
    private String street;
    private int postalCode;
    private String city;
    private String country;

    public PlainAddress() {
    }

    public PlainAddress(String street, int postalCode, String city, String country) {
        this.street = street;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlainAddress that = (PlainAddress) o;
        return postalCode == that.postalCode &&
                Objects.equals(street, that.street) &&
                Objects.equals(city, that.city) &&
                Objects.equals(country, that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, postalCode, city, country);
    }

    @Override
    public String toString() {
        return "PlainAddress{" +
                "street='" + street + '\'' +
                ", postalCode=" + postalCode +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
