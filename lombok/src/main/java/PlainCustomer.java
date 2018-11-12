import java.util.Objects;

public class PlainCustomer {
    private String firstName;
    private String lastName;
    private PlainAddress address;

    public PlainCustomer() {
    }

    public PlainCustomer(String firstName, String lastName, PlainAddress address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public PlainAddress getAddress() {
        return address;
    }

    public void setAddress(PlainAddress address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlainCustomer customer = (PlainCustomer) o;
        return Objects.equals(firstName, customer.firstName) &&
                Objects.equals(lastName, customer.lastName) &&
                Objects.equals(address, customer.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, address);
    }

    @Override
    public String toString() {
        return "PlainCustomer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address=" + address +
                '}';
    }
}
