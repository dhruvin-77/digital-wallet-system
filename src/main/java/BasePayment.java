public abstract class BasePayment implements PaymentMethod {

    private final String paymentType;

    protected BasePayment(String paymentType) {
        this.paymentType = paymentType;
    }

    @Override
    public String getPaymentType() {
        return paymentType;
    }
}