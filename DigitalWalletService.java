import java.util.Date;
import java.util.Map;

public class DigitalWalletService implements PaymentService {
    private final String endpoint;

    public DigitalWalletService(String endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public Map<String, String> process(Payment payment) {
        System.out.println("Connecting to Digital Wallet API at " + endpoint);
        String transactionId = "CC" + new Date().getTime();
        System.out.println("Processing digital wallet payment for " + payment.customerInfo.get("name"));
        return Map.of("status", "success", "transaction_id", transactionId);
    }
}
