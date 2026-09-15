import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BillPayment implements java.io.Serializable{

    private final String billType;
    private final String consumerNumber;
    private final BigDecimal amount;
    private final LocalDateTime dateTime;

    public BillPayment(
            String billType,
            String consumerNumber,
            BigDecimal amount) {

        this.billType = billType;
        this.consumerNumber = consumerNumber;
        this.amount = amount;
        this.dateTime = LocalDateTime.now();
    }

    public String getBillType() {
        return billType;
    }

    public String getConsumerNumber() {
        return consumerNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return dateTime
                + " | "
                + billType
                + " | "
                + consumerNumber
                + " | Rs."
                + amount;
    }
}