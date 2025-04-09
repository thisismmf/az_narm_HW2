import java.util.Map;

/**
 * Abstract base class implementing the PaymentGateway interface.
 * Provides common logic shared by concrete payment gateway implementations.
 */
public abstract class BaseGateway implements PaymentGateway {
    protected final String endpoint;
    
    public BaseGateway(String endpoint) {
        this.endpoint = endpoint;
    }
    
    @Override
    public Map<String, String> getTransactionStatus(String transactionId) {
        System.out.println("Retrieving transaction status for " + transactionId + " from " + endpoint);
        // Dummy implementation: in a real scenario, an API call would be made here.
        return Map.of("transaction_id", transactionId, "status", "unknown");
    }
}
