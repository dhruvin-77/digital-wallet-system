public class PaymentProcessor {

    public boolean process(PaymentMethod paymentMethod, double amount) {

        if (paymentMethod == null) {
            return false;
        }

        boolean successful = paymentMethod.processPayment(amount);

        if (successful) {
            System.out.println(
                    paymentMethod.getPaymentType()
                    + " payment processed successfully."
            );
        } else {
            System.out.println(
                    paymentMethod.getPaymentType()
                    + " payment failed."
            );
        }

        return successful;
    }
}