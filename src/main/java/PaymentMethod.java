public interface PaymentMethod {

    String getPaymentType();

    boolean processPayment(double amount);
}