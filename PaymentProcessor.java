/**
 * Main payment orchestrator class.
 * Responsible for creating payment instances, validating them,
 * delegating processing to the appropriate service,
 * and logging the result.
 */
public class PaymentProcessor {
    private final Map<String, String> config;

    public PaymentProcessor(Map<String, String> config) {
        this.config = config;
    }

    /**
     * Processes a payment end-to-end: creation, validation, execution, and logging.
     * @param paymentTypeStr The payment type as a string.
     * @param amount The transaction amount.
     * @param currency The currency used (USD, EUR, GBP).
     * @param customerInfo A map containing customer details.
     * @param paymentDetails A map containing payment-specific fields.
     * @return A map indicating the result of the payment process.
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

    /**
     * Logs the transaction result in a structured format.
     */
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
    "bank_transfer_endpoint", "https://api.banktransfer.com/process"
    );

    PaymentProcessor processor = new PaymentProcessor(config);
    Map<String, String> customer = Map.of("name", "John Doe", "email", "john@example.com");
    Map<String, String> paymentDetails = Map.of("card_number", "123456789012", "expiry", "12/25", "cvv", "123");

    Map<String, String> result = processor.processPayment("credit_card", 100, "USD", customer, paymentDetails);
    System.out.println("Final Result: " + result);
    }
}
