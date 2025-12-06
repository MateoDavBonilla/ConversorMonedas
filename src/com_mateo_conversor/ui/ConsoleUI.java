package com_mateo_conversor.ui;

import com_mateo_conversor.api.ExchangeRateApiClient;
import com_mateo_conversor.api.dto.ExchangeRateResponse;
import com_mateo_conversor.domain.ConversionRecord;
import com_mateo_conversor.domain.PopularCurrencies;
import com_mateo_conversor.service.CurrencyConversionService;
import com_mateo_conversor.service.CurrencyFilterService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class ConsoleUI {
    private final ExchangeRateApiClient apiClient;
    private final CurrencyFilterService filterService;
    private final CurrencyConversionService conversionService;
    private final List<ConversionRecord> history = new ArrayList<>();
    private Language language = Language.ES;
    private final Scanner scanner;

    // Base fija
    private static final String BASE = "USD";

    public ConsoleUI(ExchangeRateApiClient apiClient,
                     CurrencyFilterService filterService,
                     CurrencyConversionService conversionService) {
        this.apiClient = apiClient;
        this.filterService = filterService;
        this.conversionService = conversionService;
        this.scanner = new Scanner(System.in);
    }

    public void start() {

        System.out.println("===================================");
        printLine("title");
        System.out.println("===================================");
        System.out.println("Base currency: " + BASE + " (fixed)");
        System.out.println();

        // Obtener tasas una sola vez
        ExchangeRateResponse response = apiClient.fetchRates(BASE);

        boolean running = true;

        while (running) {
            printMenu();
            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    convertCurrency(response);
                    break;
                case "2":
                    showAvailableCurrencies(response);
                    break;
                case "3":
                    showPopularCurrencies();
                    break;
                case "4":
                    showHistory();
                    break;
                case "5":
                    exportHistoryToFile();
                    break;
                case "6":
                    changeLanguage();
                    break;
                case "7":
                    printLine("exit");
                    running = false;
                    break;
                default:
                    printLine("invalidOption");
            }
        }


    }

    private void printMenu() {
        if (language == Language.ES) {
            System.out.println("\nElige una opción:");
            System.out.println("1. Convertir moneda");
            System.out.println("2. Ver monedas disponibles");
            System.out.println("3. Ver monedas más usadas");
            System.out.println("4. Ver historial");
            System.out.println("5. Exportar historial a archivo");
            System.out.println("6. Cambiar idioma");
            System.out.println("7. Salir");
            System.out.print("Opción: ");
        } else {
            System.out.println("\nChoose an option:");
            System.out.println("1. Convert currency");
            System.out.println("2. Show available currencies");
            System.out.println("3. Show popular currencies");
            System.out.println("4. Show conversion history");
            System.out.println("5. Export history to file");
            System.out.println("6. Change language");
            System.out.println("7. Exit");
            System.out.print("Option: ");
        }
    }

    private void convertCurrency(ExchangeRateResponse response) {

        System.out.println("\n--- Convert Currency ---");
        System.out.println("Examples: USD, EUR, MXN, JPY, GBP\n");

        System.out.print("From currency: ");
        String from = scanner.nextLine().trim().toUpperCase();

        System.out.print("To currency: ");
        String to = scanner.nextLine().trim().toUpperCase();

        // Validaciones
        try {
            filterService.validateCurrency(from, response);
            filterService.validateCurrency(to, response);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }

        System.out.print("Amount: ");
        double amount;

        try {
            amount = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number. Try again.");
            return;
        }

        double result = conversionService.convert(from, to, amount, response);
        history.add(new ConversionRecord(from, to, amount, result));
        System.out.println("\nResult: " + amount + " " + from + " = " + result + " " + to);
    }

    private void showAvailableCurrencies(ExchangeRateResponse response) {
        System.out.println("\n--- Available Currencies ---");

        Set<String> currencies = filterService.getAvailableCurrencies(response);

        System.out.println(currencies); // simple display
    }

    private void showPopularCurrencies() {
        System.out.println("\n--- Popular Currencies ---");

        PopularCurrencies.getPopularCurrencies().forEach((code, name) -> {
            System.out.println(code + " - " + name);
        });
    }

    private void showHistory() {
        System.out.println("\n--- Conversion History ---");

        if (history.isEmpty()) {
            System.out.println("No conversions yet.");
            return;
        }

        history.forEach(System.out::println);
    }

    private void changeLanguage() {
        System.out.println("\n=== Language / Idioma ===");
        System.out.println("1. Español");
        System.out.println("2. English");
        System.out.print("Choose / Elige: ");

        String choice = scanner.nextLine().trim();

        switch (choice) {
            case "1":
                language = Language.ES;
                System.out.println("Idioma cambiado a Español.");
                break;
            case "2":
                language = Language.EN;
                System.out.println("Language changed to English.");
                break;
            default:
                System.out.println("Opción inválida / Invalid option.");
        }
    }

    private void printLine(String key) {
        switch (key) {
            case "title":
                if (language == Language.ES) {
                    System.out.println("Conversor de Monedas - Java");
                } else {
                    System.out.println("Currency Converter - Java");
                }
                break;
            case "exit":
                if (language == Language.ES) {
                    System.out.println("Saliendo del programa...");
                } else {
                    System.out.println("Exiting program...");
                }
                break;
            case "invalidOption":
                if (language == Language.ES) {
                    System.out.println("Opción inválida. Intenta de nuevo.");
                } else {
                    System.out.println("Invalid option. Try again.");
                }
                break;
            default:

        }
    }

    private void exportHistoryToFile() {
        System.out.println("\n--- Export History / Exportar Historial ---");

        if (history.isEmpty()) {
            if (language == Language.ES) {
                System.out.println("No hay conversiones para exportar.");
            } else {
                System.out.println("There are no conversions to export.");
            }
            return;
        }

        StringBuilder sb = new StringBuilder();

        sb.append("Conversion History / Historial de Conversiones\n");
        sb.append("---------------------------------------------\n");

        for (ConversionRecord record : history) {
            sb.append(record.toString()).append(System.lineSeparator());
        }

        try {
            Path filePath = Path.of("conversion-history.txt");

            Files.writeString(
                    filePath,
                    sb.toString(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );

            if (language == Language.ES) {
                System.out.println("Historial exportado a: " + filePath.toAbsolutePath());
            } else {
                System.out.println("History exported to: " + filePath.toAbsolutePath());
            }

        } catch (IOException e) {
            if (language == Language.ES) {
                System.out.println("Error al exportar el historial: " + e.getMessage());
            } else {
                System.out.println("Error exporting history: " + e.getMessage());
            }
        }
    }


}
