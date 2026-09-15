import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class WalletService implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Wallet wallet;
    private final List<Transaction> transactions = new ArrayList<>();
    private final List<Beneficiary> beneficiaries = new ArrayList<>();

    public WalletService(Wallet wallet) { this.wallet = wallet; }

    public Wallet getWallet() { return wallet; }
    public List<Transaction> getTransactions() { return transactions; }
    public List<Beneficiary> getBeneficiaries() { return beneficiaries; }

    public void addMoney(BigDecimal amount) {
        validateAmount(amount);
        checkActive();
        wallet.addMoney(amount);
        transactions.add(new Transaction("ADD_MONEY", amount, "Money added to wallet"));
    }

    public void withdrawMoney(BigDecimal amount) {
        validateAmount(amount);
        checkActive();
        if (!wallet.withdrawMoney(amount))
            throw new WalletException("Insufficient wallet balance.");
        transactions.add(new Transaction("WITHDRAW", amount, "Money withdrawn from wallet"));
    }

    public void sendMoney(WalletService receiver, BigDecimal amount) {
        validateAmount(amount);
        checkActive();
        if (receiver == null) throw new WalletException("Receiver not found.");
        if (receiver == this) throw new WalletException("You cannot send money to yourself.");
        if (!receiver.wallet.isActive()) throw new WalletException("Receiver wallet is blocked.");
        if (!wallet.withdrawMoney(amount))
            throw new WalletException("Insufficient wallet balance.");

        receiver.wallet.addMoney(amount);
        transactions.add(new Transaction("TRANSFER", amount, "Money sent"));
        receiver.transactions.add(new Transaction("RECEIVE", amount, "Money received"));
    }

    public void addBeneficiary(Beneficiary beneficiary) {
        if (beneficiary == null) throw new WalletException("Beneficiary cannot be null.");
        beneficiaries.add(beneficiary);
    }

    public void removeBeneficiary(int index) {
        if (index < 0 || index >= beneficiaries.size())
            throw new WalletException("Invalid beneficiary.");
        beneficiaries.remove(index);
    }

    public void payBill(String billType, String consumerNumber, BigDecimal amount) {
        validateAmount(amount);
        checkActive();
        if (billType == null || billType.isBlank())
            throw new WalletException("Bill type is required.");
        if (consumerNumber == null || consumerNumber.isBlank())
            throw new WalletException("Consumer number is required.");
        if (!wallet.withdrawMoney(amount))
            throw new WalletException("Insufficient wallet balance.");

        BillPayment payment = new BillPayment(billType, consumerNumber, amount);
        transactions.add(new Transaction("BILL_PAYMENT", amount, billType + " bill payment"));
        System.out.println("Payment details: " + payment);
    }

    public void exportStatement(String userName) {
        try {
            Path dir = Paths.get("statements");
            Files.createDirectories(dir);
            String safeName = userName.replaceAll("[^a-zA-Z0-9]", "_");
            Path file = dir.resolve("wallet_statement_" + safeName + ".txt");

            try (BufferedWriter writer = Files.newBufferedWriter(file, StandardCharsets.UTF_8)) {
                writer.write("DIGITAL WALLET STATEMENT"); writer.newLine();
                writer.write("Customer: " + userName); writer.newLine();
                writer.write("Balance: Rs." + wallet.getBalance()); writer.newLine();
                writer.newLine();
                writer.write("TRANSACTION HISTORY"); writer.newLine();
                writer.write("--------------------"); writer.newLine();

                if (transactions.isEmpty()) {
                    writer.write("No transactions found."); writer.newLine();
                } else {
                    for (Transaction transaction : transactions) {
                        writer.write(transaction.toString()); writer.newLine();
                    }
                }
            }
        } catch (IOException e) {
            throw new WalletException("Unable to export statement.");
        }
    }

    private void checkActive() {
        if (!wallet.isActive()) throw new WalletException("Wallet is blocked.");
    }

    private void validateAmount(BigDecimal amount) {
        if (amount == null) throw new WalletException("Amount cannot be null.");
        if (amount.compareTo(BigDecimal.ZERO) <= 0)
            throw new WalletException("Amount must be greater than zero.");
        if (amount.scale() > 2)
            throw new WalletException("Amount cannot have more than two decimal places.");
    }
}
