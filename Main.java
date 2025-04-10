public class Main {
    public static void main(String[] args) {
        ConfigurationManager configManager = new ConfigurationManager();
        Map<String, String> config = configManager.load();

        PaymentProcessor processor = new PaymentProcessor(config);

        Map<String, String> customer = Map.of("name", "Ali", "email", "ali@example.com");
        Map<String, String> paymentDetails = Map.of("card_number", "123456789012", "expiry", "12/26", "cvv", "123");

        Map<String, String> result = processor.processPayment("credit_card", 250, "USD", customer, paymentDetails);
        System.out.println("Final Result: " + result);
    }
}
