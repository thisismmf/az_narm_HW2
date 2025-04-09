import java.util.Map;

/**
 * PaymentGateway interface for processing payments through external APIs.
 * Provides methods for processing, refunding, and checking transaction status.
 */
public interface PaymentGateway {
    /**
     * Processes the given payment.
     * @param payment The payment object containing necessary data.
     * @return A map with the processing result including status and transaction ID.
     */
    Map<String, String> processPayment(Payment payment);
    
    /**
     * Initiates a refund for the given payment.
     * @param payment The payment object for which the refund is to be initiated.
     * @return A map with the refund result details.
     */
    Map<String, String> refundPayment(Payment payment);
    
    /**
     * Retrieves the status of a transaction.
     * @param transactionId The unique transaction identifier.
     * @return A map containing transaction status information.
     */
    Map<String, String> getTransactionStatus(String transactionId);
}
