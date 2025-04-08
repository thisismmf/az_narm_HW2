import java.util.Date;
import java.util.Map;

/**
 * Service class for processing credit card payments via third-party APIs.
 */
public class CreditCardService implements PaymentService {
    private final String endpoint;

    public CreditCardService(String endpoint) {
        this.endpoint = endpoint;
    }

    /**
     * Processes the credit card payment by connecting to the external API.
     * @param payment The payment object containing necessary data.
     * @return A map with the processing result including status and transaction ID.
     */
    @Override
    public Map<String, String> process(Payment payment) {
        System.out.println("Connecting to Credit Card API at " + endpoint);
        String transactionId = "CC" + new Date().getTime();
        System.out.println("Processing credit card payment for " + payment.customerInfo.get("name"));
        return Map.of("status", "success", "transaction_id", transactionId);
    }
}
