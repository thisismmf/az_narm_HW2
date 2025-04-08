import java.util.Date;
import java.util.Map;

/**
 * Service class for processing bank transfer payments via third-party APIs.
 */
public class BankTransferService implements PaymentService {
    private final String endpoint;

    public BankTransferService(String endpoint) {
        this.endpoint = endpoint;
    }

    /**
     * Processes the bank transfer payment by connecting to the external API.
     * @param payment The payment object containing necessary data.
     * @return A map with the processing result including status and transaction ID.
     */
    @Override
    public Map<String, String> process(Payment payment) {
        System.out.println("Connecting to Bank Transfer API at " + endpoint);
        String transactionId = "CC" + new Date().getTime();
        System.out.println("Processing bank transfer payment for " + payment.customerInfo.get("name"));
        return Map.of("status", "success", "transaction_id", transactionId);
    }
}
