import java.util.Map;

public class BankTransferPayment extends Payment {

    public BankTransferPayment(double amount, String currency,
                               Map<String, String> customerInfo,
                               Map<String, String> paymentDetails) {
        super(amount, currency, customerInfo, paymentDetails);
    }

    @Override
    public boolean validatePayment() {
        if (!basicValidation()) return false;
        return paymentDetails.containsKey("account_number");
    }
}
