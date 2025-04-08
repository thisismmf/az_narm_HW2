import java.util.Date;
import java.util.Map;

public class CreditCardService implements PaymentService {
    private final String endpoint;

    public CreditCardService(String endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public Map<String, String> process(Payment payment) {
        System.out.println("Connecting to Credit Card API at " + endpoint);
        String transactionId = "CC" + new Date().getTime();
        System.out.println("Processing credit card payment for " + payment.customerInfo.get("name"));
        return Map.of("status", "success", "transaction_id", transactionId);
    }
}
