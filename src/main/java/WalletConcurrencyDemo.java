import java.math.BigDecimal;

public class WalletConcurrencyDemo {

    private BigDecimal balance;

    public WalletConcurrencyDemo(BigDecimal balance) {
        this.balance = balance;
    }

    public synchronized void addMoney(BigDecimal amount) {
        balance = balance.add(amount);
    }

    public synchronized boolean withdrawMoney(BigDecimal amount) {

        if (balance.compareTo(amount) < 0) {
            return false;
        }

        balance = balance.subtract(amount);
        return true;
    }

    public synchronized BigDecimal getBalance() {
        return balance;
    }

    public static void main(String[] args)
            throws InterruptedException {

        WalletConcurrencyDemo wallet =
                new WalletConcurrencyDemo(
                        new BigDecimal("1000")
                );

        Thread addThread = new Thread(() -> {

            for (int i = 0; i < 10; i++) {
                wallet.addMoney(
                        new BigDecimal("100")
                );
            }
        });

        Thread withdrawThread = new Thread(() -> {

            for (int i = 0; i < 5; i++) {
                wallet.withdrawMoney(
                        new BigDecimal("50")
                );
            }
        });

        addThread.start();
        withdrawThread.start();

        addThread.join();
        withdrawThread.join();

        System.out.println(
                "Final synchronized balance: Rs."
                        + wallet.getBalance()
        );
    }
}