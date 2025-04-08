import java.util.Map;

/**
 * Payment subclass for digital wallet transactions.
 * Extends Payment and provides specific validation rules.
 */
public class DigitalWalletPayment extends Payment {

    public DigitalWalletPayment(double amount, String currency,
                                Map<String, String> customerInfo,
                                Map<String, String> paymentDetails) {
        super(amount, currency, customerInfo, paymentDetails);
    }

    /**
     * Validates digital wallet-specific fields like wallet id.
     * @return true if payment data is valid.
     */
    @Override
    public boolean validatePayment() {
        if (!basicValidation()) return false;
        return paymentDetails.containsKey("wallet_id");
    }
}
