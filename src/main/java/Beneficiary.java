public class Beneficiary implements java.io.Serializable {

    private final String name;
    private final String email;

    public Beneficiary(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return name + " (" + email + ")";
    }
}