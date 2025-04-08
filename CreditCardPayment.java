import java.util.Map;

/**
 * Payment subclass for credit card transactions.
 * Extends Payment and provides specific validation rules.
 */
public class CreditCardPayment extends Payment {

    public CreditCardPayment(double amount, String currency,
                             Map<String, String> customerInfo,
                             Map<String, String> paymentDetails) {
        super(amount, currency, customerInfo, paymentDetails);
    }

    /**
     * Validates credit card-specific fields like card number length.
     * @return true if payment data is valid for credit card.
     */
    @Override
    public boolean validatePayment() {
        if (!basicValidation()) return false;
        return paymentDetails.getOrDefault("card_number", "").length() >= 12;
    }
}
