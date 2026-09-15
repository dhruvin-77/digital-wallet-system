public class InternetPayment extends BasePayment {

    public InternetPayment() {
        super("Internet");
    }

    @Override
    public boolean processPayment(double amount) {
        return amount > 0;
    }
}