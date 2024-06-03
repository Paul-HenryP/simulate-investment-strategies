import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InvestmentStrategiesFromCSV {

    public static void main(String[] args) {
        String csvFile = "VOO.csv"; // failitee vastavalt oma CSV failile.
        List<Double> stockPrices = readPricesFromCSV(csvFile);

        if (stockPrices == null || stockPrices.isEmpty()) {
            System.out.println("Failed to read stock prices from CSV.");
            return;
        }

        double initialInvestment = 1000.0;
        double monthlyInvestment = 200.0;
        int movingAveragePeriod = 3; // Liikuva keskmise periood kuudes

        double buyAndHoldReturn = buyAndHoldStrategy(stockPrices, initialInvestment, monthlyInvestment);
        double movingAverageReturn = movingAverageStrategy(stockPrices, initialInvestment, monthlyInvestment, movingAveragePeriod);
        double buyLowerThanLastPurchaseReturn = buyLowerThanLastPurchaseStrategy(stockPrices, initialInvestment, monthlyInvestment);
        double dollarCostAveragingReturn = dollarCostAveragingStrategy(stockPrices, initialInvestment, monthlyInvestment);

        System.out.printf("Osta-ja-hoia strateegia tootlus: %.2f%n", buyAndHoldReturn);
        System.out.printf("Liikuva keskmise strateegia tootlus: %.2f%n", movingAverageReturn);
        System.out.printf("Osta madalama hinnaga kui eelmine ost strateegia tootlus: %.2f%n", buyLowerThanLastPurchaseReturn);
        System.out.printf("Dollar cost averaging strateegia tootlus: %.2f%n", dollarCostAveragingReturn);
    }

    /**
     * Loeb CSV failist aktsia hinnad
     * @param csvFile name in the project folder.
     * @return ArrayList of prices.
     */
    public static List<Double> readPricesFromCSV(String csvFile) {
        List<Double> prices = new ArrayList<>();
        String line;
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            br.readLine(); // Skip header line
            while ((line = br.readLine()) != null) {
                String[] data = line.split(cvsSplitBy);
                if (data.length > 5 && !data[5].equals("null")) {
                    prices.add(Double.parseDouble(data[5])); // Assume 'Adj Close' is the 6th column
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return prices;
    }

    // Osta-ja-hoia strateegia
    public static double buyAndHoldStrategy(List<Double> prices, double initialInvestment, double monthlyInvestment) {
        double shares = initialInvestment / prices.get(0);
        for (int i = 1; i < prices.size(); i++) {
            shares += monthlyInvestment / prices.get(i);
        }
        double finalValue = shares * prices.get(prices.size() - 1);
        return finalValue;
    }

    // Liikuva keskmise strateegia
    public static double movingAverageStrategy(List<Double> prices, double initialInvestment, double monthlyInvestment, int period) {
        double cash = initialInvestment;
        double shares = 0.0;

        for (int i = 0; i < prices.size(); i++) {
            if (i >= period) {
                double movingAverage = calculateMovingAverage(prices, i - period, i);
                if (prices.get(i) > movingAverage && cash > 0) {
                    shares = cash / prices.get(i);
                    cash = 0;
                } else if (prices.get(i) < movingAverage && shares > 0) {
                    cash = shares * prices.get(i);
                    shares = 0;
                }
            }
            cash += monthlyInvestment;
            shares += monthlyInvestment / prices.get(i);
        }

        double finalValue = cash + shares * prices.get(prices.size() - 1);
        return finalValue;
    }

    // Osta madalama hinnaga kui eelmine ost strateegia
    public static double buyLowerThanLastPurchaseStrategy(List<Double> prices, double initialInvestment, double monthlyInvestment) {
        double cash = initialInvestment;
        double shares = 0.0;
        double lastPurchasePrice = Double.MAX_VALUE;

        for (int i = 0; i < prices.size(); i++) {
            if (prices.get(i) < lastPurchasePrice && cash > 0) {
                shares = cash / prices.get(i);
                cash = 0;
                lastPurchasePrice = prices.get(i);
            }
            cash += monthlyInvestment;
            if (prices.get(i) < lastPurchasePrice) {
                shares += monthlyInvestment / prices.get(i);
                lastPurchasePrice = prices.get(i);
            }
        }

        double finalValue = cash + shares * prices.get(prices.size() - 1);
        return finalValue;
    }

    // Dollar Cost Averaging strateegia
    public static double dollarCostAveragingStrategy(List<Double> prices, double initialInvestment, double monthlyInvestment) {
        double cash = initialInvestment;
        double shares = 0.0;

        for (int i = 0; i < prices.size(); i++) {
            if (cash >= monthlyInvestment) {
                shares += monthlyInvestment / prices.get(i);
                cash -= monthlyInvestment;
            }
        }

        double finalValue = cash + shares * prices.get(prices.size() - 1);
        return finalValue;
    }

    private static double calculateMovingAverage(List<Double> prices, int start, int end) {
        double sum = 0.0;
        for (int i = start; i < end; i++) {
            sum += prices.get(i);
        }
        return sum / (end - start);
    }
}
