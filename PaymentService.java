/**
 * Service interface for processing payments through external APIs.
 */
public interface PaymentService {
    /**
     * Process the given payment.
     * @param payment The payment object to be processed.
     * @return A result map including status and transaction information.
     */
    Map<String, String> process(Payment payment);
}
