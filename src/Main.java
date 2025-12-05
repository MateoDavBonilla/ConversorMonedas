import com_mateo_conversor.api.ExchangeRateApiClient;
import com_mateo_conversor.api.dto.ExchangeRateResponse;


public class Main {

    public static void main(String[] args) {
        ExchangeRateApiClient client = new ExchangeRateApiClient();
        ExchangeRateResponse response = client.fetchRates("USD");

        System.out.println("Result:" + response.getResult());
        System.out.println("Base:" + response.getBase_code());
        System.out.println("Eur rate:" + response.getConversion_rates().get("EUR"));
    }

}
