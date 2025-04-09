import java.util.Date;
import java.util.Map;

public class PaymentProcessor {
    private final Map<String, String> config;

    public PaymentProcessor(Map<String, String> config) {
        this.config = config;
    }

    /**
     * Processes a payment end-to-end using the existing PaymentService mechanism.
     */
    public Map<String, String> processPayment(String paymentTypeStr, double amount, String currency,
                                              Map<String, String> customerInfo, Map<String, String> paymentDetails) {
        PaymentType paymentType;
        try {
            paymentType = PaymentType.fromString(paymentTypeStr);
        } catch (IllegalArgumentException e) {
            return Map.of("status", "failed", "message", "Unknown payment type");
        }

        Payment payment = paymentType.createPayment(amount, currency, customerInfo, paymentDetails);
        if (!payment.validatePayment()) {
            return Map.of("status", "failed", "message", "Validation error");
        }

        PaymentService service = paymentType.createService(config);
        Map<String, String> result = service.process(payment);

        logTransaction(paymentTypeStr, amount, currency, customerInfo, paymentDetails, result);
        return result;
    }

    private void logTransaction(String paymentType, double amount, String currency,
                                Map<String, String> customerInfo, Map<String, String> paymentDetails,
                                Map<String, String> result) {
        String logEntry = String.format("%s - %s payment of %.2f %s for %s: %s",
                new Date(), paymentType, amount, currency, customerInfo.get("name"), result);
        System.out.println("LOG: " + logEntry);
    }

    public static void main(String[] args) {
        Map<String, String> config = Map.of(
                "credit_card_endpoint", "https://api.creditcard.com/process",
                "digital_wallet_endpoint", "https://api.digitalwallet.com/process",
                "bank_transfer_endpoint", "https://api.banktransfer.com/process",
                "stripe_endpoint", "https://api.stripe.com/process",
                "paypal_endpoint", "https://api.paypal.com/process"
        );

        PaymentProcessor processor = new PaymentProcessor(config);
        Map<String, String> customer = Map.of("name", "John Doe", "email", "john@example.com");
        Map<String, String> paymentDetails = Map.of("card_number", "123456789012", "expiry", "12/25", "cvv", "123");

        // Process payment using the existing PaymentService mechanism.
        Map<String, String> result = processor.processPayment("credit_card", 100, "USD", customer, paymentDetails);
        System.out.println("Final Payment Result: " + result);

        // Demonstrate polymorphism with PaymentGateway implementations
        Payment payment = PaymentType.CREDIT_CARD.createPayment(150, "USD", customer, paymentDetails);
        PaymentGateway stripeGateway = new StripeGateway(config.get("stripe_endpoint"));
        PaymentGateway paypalGateway = new PayPalGateway(config.get("paypal_endpoint"));

        // Process payment using StripeGateway
        Map<String, String> stripeResult = stripeGateway.processPayment(payment);
        System.out.println("Stripe Result: " + stripeResult);

        // Switch to PayPalGateway at runtime to process payment
        Map<String, String> paypalResult = paypalGateway.processPayment(payment);
        System.out.println("PayPal Result: " + paypalResult);
    }
}
