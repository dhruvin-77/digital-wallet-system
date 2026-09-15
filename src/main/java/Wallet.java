import java.math.BigDecimal;

public class Wallet implements java.io.Serializable {

    private BigDecimal balance;
    private boolean active;

    public Wallet() {
        balance = BigDecimal.ZERO;
        active = true;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public boolean isActive() {
        return active;
    }

    public void addMoney(BigDecimal amount) {
        balance = balance.add(amount);
    }

    public boolean withdrawMoney(BigDecimal amount) {

        if (amount.compareTo(balance) > 0) {
            return false;
        }

        balance = balance.subtract(amount);
        return true;
    }

    public void block() {
        active = false;
    }
}