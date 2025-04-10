import java.util.HashMap;
import java.util.Map;

/**
 * Loads external configuration from environment variables or default values.
 */
public class ConfigurationManager {

    public Map<String, String> load() {
        Map<String, String> config = new HashMap<>();

        config.put("credit_card_endpoint",
                System.getenv().getOrDefault("CREDIT_CARD_ENDPOINT", "https://api.defaultcredit.com/process"));
        config.put("digital_wallet_endpoint",
                System.getenv().getOrDefault("DIGITAL_WALLET_ENDPOINT", "https://api.defaultwallet.com/process"));
        config.put("bank_transfer_endpoint",
                System.getenv().getOrDefault("BANK_TRANSFER_ENDPOINT", "https://api.defaultbank.com/process"));

        return config;
    }
}
