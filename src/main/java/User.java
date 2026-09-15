import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String name;
    private final String email;
    private final String phone;
    private final String pin;
    private WalletService walletService;

    public User(
            String name,
            String email,
            String phone,
            String pin) {

        this.name = name;
        this.email = email;
        this.phone = phone;
        this.pin = pin;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPin() {
        return pin;
    }

    public WalletService getWalletService() {
        return walletService;
    }

    public void createWallet() {

        if (walletService == null) {
            walletService =
                    new WalletService(new Wallet());
        }
    }

    @Override
    public String toString() {

        return "Name: " + name
                + ", Email: " + email
                + ", Phone: " + phone;
    }
}