public class MobilePayment extends BasePayment {

    public MobilePayment() {
        super("Mobile");
    }

    @Override
    public boolean processPayment(double amount) {
        return amount > 0;
    }
}