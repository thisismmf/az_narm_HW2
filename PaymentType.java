/**
 * Enum representing supported payment types in the system.
 * Provides factory methods to create matching Payment and PaymentService instances.
 */
public enum PaymentType {
    CREDIT_CARD {
        @Override
        public Payment createPayment(double amount, String currency,
                                     Map<String, String> customerInfo, Map<String, String> paymentDetails) {
            return new CreditCardPayment(amount, currency, customerInfo, paymentDetails);
        }

        @Override
        public PaymentService createService(Map<String, String> config) {
            return new CreditCardService(config.get("credit_card_endpoint"));
        }
    },
    DIGITAL_WALLET {
        @Override
        public Payment createPayment(double amount, String currency,
                                     Map<String, String> customerInfo, Map<String, String> paymentDetails) {
            return new DigitalWalletPayment(amount, currency, customerInfo, paymentDetails);
        }

        @Override
        public PaymentService createService(Map<String, String> config) {
            return new DigitalWalletService(config.get("digital_wallet_endpoint"));
        }
    },
    BANK_TRANSFER {
        @Override
        public Payment createPayment(double amount, String currency,
                                     Map<String, String> customerInfo, Map<String, String> paymentDetails) {
            return new BankTransferPayment(amount, currency, customerInfo, paymentDetails);
        }

        @Override
        public PaymentService createService(Map<String, String> config) {
            return new BankTransferService(config.get("bank_transfer_endpoint"));
        }
    };

    /**
     * Creates a payment instance with corresponding fields.
     * @return A new Payment subclass instance.
     */
    public abstract Payment createPayment(double amount, String currency,
                                          Map<String, String> customerInfo, Map<String, String> paymentDetails);

    /**
     * Creates a corresponding PaymentService for the payment type.
     * @return A concrete implementation of PaymentService.
     */
    public abstract PaymentService createService(Map<String, String> config);

    /**
     * Parses a string value into a PaymentType enum.
     * @param value The string representation.
     * @return Matching enum value.
     * @throws IllegalArgumentException if value is unknown.
     */
    public static PaymentType fromString(String value) {
        switch (value.toLowerCase()) {
            case "credit_card":
                return CREDIT_CARD;
            case "digital_wallet":
                return DIGITAL_WALLET;
            case "bank_transfer":
                return BANK_TRANSFER;
            default:
                throw new IllegalArgumentException("Unsupported payment type: " + value);
        }
    }
}
