public class ElectricityPayment extends BasePayment {

    public ElectricityPayment() {
        super("Electricity");
    }

    @Override
    public boolean processPayment(double amount) {
        return amount > 0;
    }
}