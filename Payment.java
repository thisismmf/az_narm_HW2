import java.util.Date;
import java.util.Map;

/**
 * Abstract class representing a generic payment in the system.
 * Encapsulates common payment attributes and validation behavior.
 */
public abstract class Payment {
    protected double amount;
    protected String currency;
    protected Date timestamp;
    protected Map<String, String> customerInfo;
    protected Map<String, String> paymentDetails;

    public Payment(double amount, String currency,
                   Map<String, String> customerInfo,
                   Map<String, String> paymentDetails) {
        this.amount = amount;
        this.currency = currency;
        this.customerInfo = customerInfo;
        this.paymentDetails = paymentDetails;
        this.timestamp = new Date();
    }

    /**
     * Performs general validation common to all payment types.
     * Checks for positive amount, supported currency, and presence of customer email.
     * @return true if basic rules are satisfied, false otherwise.
     */
    protected boolean basicValidation() {
        if (amount <= 0) return false;
        if (!currency.equals("USD") && !currency.equals("EUR") && !currency.equals("GBP")) return false;
        if (!customerInfo.containsKey("email")) return false;
        return true;
    }

    /**
     * Performs validation logic specific to the payment type.
     * @return true if the payment is valid, false otherwise.
     */
    public abstract boolean validatePayment();
}
