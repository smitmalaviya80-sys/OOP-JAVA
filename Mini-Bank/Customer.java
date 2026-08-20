public class Customer {

    private String name;
    private String email;
    private String mobile;

    private final String customerId;

    private static long customerCounter = 101;

    private static String generateCustomerId() {

        return "CUST" + customerCounter++;
    }

    public Customer(String name, String email, String mobile) {

        this.name = name;
        this.email = email;
        this.mobile = mobile;

        customerId = generateCustomerId();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getCustomerId() {
        return customerId;
    }

    // Static nested class
    public static class Address {

        private String line;
        private String city;
        private String pincode;

        public Address(String line, String city, String pincode) {

            this.line = line;
            this.city = city;
            this.pincode = pincode;
        }

        public String getLine() {
            return line;
        }

        public String getCity() {
            return city;
        }

        public String getPincode() {
            return pincode;
        }

        @Override
        public String toString() {

            return "Address: "
                    + line + ", "
                    + city + ", "
                    + pincode;
        }

        public String getAddress() {

            return line + ", "
                    + city + ", "
                    + pincode;
        }
    }

    public static void main(String[] args) {

        Customer a1 = new Customer(
                "smit",
                "smitmalaviya",
                "95106306541561"
        );

        System.out.println(a1.getCustomerId());
        System.out.println(a1.getName());
        System.out.println(a1.getEmail());
        System.out.println(a1.getMobile());

        Address address = new Address(
                "ABC Road",
                "Surat",
                "395006"
        );

        System.out.println(address);
    }
}