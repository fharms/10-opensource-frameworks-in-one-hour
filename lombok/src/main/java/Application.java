public class Application {

    public static void main(String[] args) {
        traditionalBean();
        lombokBean();
    }

    private static void lombokBean() {
        LombokAddress address = LombokAddress.builder()
                .street("Holmeolstrupvej 32")
                .city("Aalborg")
                .postalCode(9000)
                .country("Danmark")
                .build();

        LombokCustomer customer = new LombokCustomer("John", "Olsen", address);
        System.out.println("Lombok bean: " + customer);
    }

    private static void traditionalBean() {
        PlainAddress address = new PlainAddress();
        address.setStreet("Holmeolstrupvej 32");
        address.setCity("Aalborg");
        address.setPostalCode(9000);
        address.setCountry("Danmark");

        PlainCustomer customer = new PlainCustomer("John", "Olsen", address);
        System.out.println("Traditional bean: " + customer);
    }
}
