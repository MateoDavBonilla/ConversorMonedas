import com_mateo_conversor.api.ExchangeRateApiClient;
import com_mateo_conversor.api.dto.ExchangeRateResponse;
import com_mateo_conversor.service.CurrencyConversionService;
import com_mateo_conversor.service.CurrencyFilterService;
import com_mateo_conversor.ui.ConsoleUI;


public class Main {

    public static void main(String[] args) {
        ExchangeRateApiClient apiClient = new ExchangeRateApiClient();
        CurrencyFilterService filter = new CurrencyFilterService();
        CurrencyConversionService converter = new CurrencyConversionService(filter);

        ConsoleUI ui = new ConsoleUI(apiClient, filter, converter);
        ui.start();
    }

}
