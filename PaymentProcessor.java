import java.util.Map;

/**
 * Main payment orchestrator class.
 * Responsible for validating the payment,
 * delegating processing to the appropriate service,
 * and logging the result.
 */
public class PaymentProcessor {
    private final Map<String, String> config;

    /**
     * Constructs a PaymentProcessor with the given configuration map.
     *
     * @param config A map containing API endpoint values.
     */
    public PaymentProcessor(Map<String, String> config) {
        this.config = config;
    }

    /**
     * Processes a payment end-to-end: creation, validation, execution, and logging.
     *
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
     *
     * @param paymentType Type of the payment.
     * @param amount Amount of the payment.
     * @param currency Currency used.
     * @param customerInfo Customer information.
     * @param paymentDetails Payment-specific information.
     * @param result Result of the transaction.
     */
    private void logTransaction(String paymentType, double amount, String currency,
                                Map<String, String> customerInfo, Map<String, String> paymentDetails,
                                Map<String, String> result) {
        String logEntry = String.format("%s - %s payment of %.2f %s for %s: %s",
                new java.util.Date(), paymentType, amount, currency,
                customerInfo.get("name"), result);
        System.out.println("LOG: " + logEntry);
    }
}
