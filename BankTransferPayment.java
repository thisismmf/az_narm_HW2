import java.util.Map;

/**
 * Payment subclass for bank transfer transactions.
 * Extends Payment and provides specific validation rules.
 */
public class BankTransferPayment extends Payment {

    public BankTransferPayment(double amount, String currency,
                               Map<String, String> customerInfo,
                               Map<String, String> paymentDetails) {
        super(amount, currency, customerInfo, paymentDetails);
    }

    /**
     * Validates bank transfer-specific fields like account number.
     * @return true if payment data is valid.
     */
    @Override
    public boolean validatePayment() {
        if (!basicValidation()) return false;
        return paymentDetails.containsKey("account_number");
    }
}
