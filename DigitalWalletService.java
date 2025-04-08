import java.util.Date;
import java.util.Map;

/**
 * Service class for processing digital wallet payments via third-party APIs.
 */
public class DigitalWalletService implements PaymentService {
    private final String endpoint;

    public DigitalWalletService(String endpoint) {
        this.endpoint = endpoint;
    }

    /**
     * Processes the digital wallet payment by connecting to the external API.
     * @param payment The payment object containing necessary data.
     * @return A map with the processing result including status and transaction ID.
     */
    @Override
    public Map<String, String> process(Payment payment) {
        System.out.println("Connecting to Digital Wallet API at " + endpoint);
        String transactionId = "CC" + new Date().getTime();
        System.out.println("Processing digital wallet payment for " + payment.customerInfo.get("name"));
        return Map.of("status", "success", "transaction_id", transactionId);
    }
}
