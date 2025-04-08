/**
 * Factory class for creating instances of PaymentService
 * based on the payment type and external configuration.
 */
public class PaymentServiceFactory {
    private Map<String, String> config;

    public PaymentServiceFactory(Map<String, String> config) {
        this.config = config;
    }

    /**
     * Returns a PaymentService implementation suitable for the given type.
     * @param paymentType The string identifier of the payment type.
     * @return A PaymentService instance.
     * @throws IllegalArgumentException for unknown types.
     */
    public PaymentService getService(String paymentType) {
        switch (paymentType) {
            case "credit_card":
                return new CreditCardService(config.get("credit_card_endpoint"));
            case "digital_wallet":
                return new DigitalWalletService(config.get("digital_wallet_endpoint"));
            case "bank_transfer":
                return new BankTransferService(config.get("bank_transfer_endpoint"));
            default:
                throw new IllegalArgumentException("Unknown payment type: " + paymentType);
        }
    }
}
