import java.util.Date;
import java.util.Map;

/**
 * Concrete PaymentGateway implementation for Stripe.
 */
public class StripeGateway extends BaseGateway {
    public StripeGateway(String endpoint) {
        super(endpoint);
    }
    
    @Override
    public Map<String, String> processPayment(Payment payment) {
        System.out.println("Connecting to Stripe API at " + endpoint);
        String transactionId = "STRIPE" + new Date().getTime();
        System.out.println("Processing Stripe payment for " + payment.customerInfo.get("name"));
        return Map.of("status", "success", "transaction_id", transactionId);
    }
    
    @Override
    public Map<String, String> refundPayment(Payment payment) {
        System.out.println("Initiating refund through Stripe API at " + endpoint);
        String refundId = "REFUND_STRIPE" + new Date().getTime();
        return Map.of("status", "success", "refund_id", refundId);
    }
}
