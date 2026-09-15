import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction implements java.io.Serializable{

    private final String type;
    private final BigDecimal amount;
    private final String description;
    private final LocalDateTime dateTime;

    public Transaction(
            String type,
            BigDecimal amount,
            String description) {

        this.type = type;
        this.amount = amount;
        this.description = description;
        this.dateTime = LocalDateTime.now();
    }

    public String getType() {
        return type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return dateTime
                + " | "
                + type
                + " | Rs."
                + amount
                + " | "
                + description;
    }
}