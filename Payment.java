import java.util.Date;
import java.util.Map;

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

    protected boolean basicValidation() {
        if (amount <= 0) return false;
        if (!currency.equals("USD") && !currency.equals("EUR") && !currency.equals("GBP")) return false;
        if (!customerInfo.containsKey("email")) return false;
        return true;
    }

    public abstract boolean validatePayment();
}
