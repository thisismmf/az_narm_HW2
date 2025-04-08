import java.util.Date;
import java.util.Map;

public class BankTransferService implements PaymentService {
    private final String endpoint;

    public BankTransferService(String endpoint) {
        this.endpoint = endpoint;
    }

    @Override
    public Map<String, String> process(Payment payment) {
        System.out.println("Connecting to Bank Transfer API at " + endpoint);
        String transactionId = "CC" + new Date().getTime();
        System.out.println("Processing bank transfer payment for " + payment.customerInfo.get("name"));
        return Map.of("status", "success", "transaction_id", transactionId);
    }
}
