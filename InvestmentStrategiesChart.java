import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Paul-Henry Paltmann
 * 2024
 * No guarantees of the program working.
 */

public class InvestmentStrategiesChart extends ApplicationFrame {

    public InvestmentStrategiesChart(String title,
                                     List<Double> investedAmounts,
                                     List<Double> buyAndHoldValues,
                                     List<Double> movingAverageValues,
                                     List<Double> buyLowerThanLastPurchaseValues,
                                     List<Double> dollarCostAveragingValues) {
        super(title);
        JFreeChart xylineChart = ChartFactory.createXYLineChart(
                "Investment Strategies Over Time",
                "Time (months)",
                "Value",
                createDataset(investedAmounts, buyAndHoldValues, movingAverageValues, buyLowerThanLastPurchaseValues, dollarCostAveragingValues),
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(xylineChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        final XYPlot plot = xylineChart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        plot.setRenderer(renderer);
        setContentPane(chartPanel);
    }

    /**
     * Creates the data lists of prices to work with.
     *
     * @param investedAmounts
     * @param buyAndHoldValues
     * @param movingAverageValues
     * @param buyLowerThanLastPurchaseValues
     * @param dollarCostAveragingValues
     * @return
     */
    private XYSeriesCollection createDataset(List<Double> investedAmounts,
                                             List<Double> buyAndHoldValues,
                                             List<Double> movingAverageValues,
                                             List<Double> buyLowerThanLastPurchaseValues,
                                             List<Double> dollarCostAveragingValues) {
        final XYSeries investedSeries = new XYSeries("Invested Amount");
        final XYSeries buyAndHoldSeries = new XYSeries("Buy and Hold");
        final XYSeries movingAverageSeries = new XYSeries("Moving Average");
        final XYSeries buyLowerThanLastPurchaseSeries = new XYSeries("Buy Lower Than Last Purchase");
        final XYSeries dollarCostAveragingSeries = new XYSeries("Dollar Cost Averaging");

        for (int i = 0; i < buyAndHoldValues.size(); i++) {
            investedSeries.add(i, investedAmounts.get(i));
            buyAndHoldSeries.add(i, buyAndHoldValues.get(i));
            movingAverageSeries.add(i, movingAverageValues.get(i));
            buyLowerThanLastPurchaseSeries.add(i, buyLowerThanLastPurchaseValues.get(i));
            dollarCostAveragingSeries.add(i, dollarCostAveragingValues.get(i));
        }

        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(investedSeries);
        dataset.addSeries(buyAndHoldSeries);
        dataset.addSeries(movingAverageSeries);
        dataset.addSeries(buyLowerThanLastPurchaseSeries);
        dataset.addSeries(dollarCostAveragingSeries);

        return dataset;
    }

    public static void main(String[] args) {
        String csvFile = "VOO.csv"; // Muuda see failitee vastavalt oma CSV failile
        List<Double> stockPrices = readPricesFromCSV(csvFile);

        if (stockPrices == null || stockPrices.isEmpty()) {
            System.out.println("Failed to read stock prices from CSV.");
            return;
        }

        double initialInvestment = 1000.0;
        double monthlyInvestment = 200.0;
        int movingAveragePeriod = 3; // Liikuva keskmise periood kuudes

        List<Double> buyAndHoldValues = simulateBuyAndHold(stockPrices, initialInvestment, monthlyInvestment);
        List<Double> movingAverageValues = simulateMovingAverage(stockPrices, initialInvestment, monthlyInvestment, movingAveragePeriod);
        List<Double> buyLowerThanLastPurchaseValues = simulateBuyLowerThanLastPurchase(stockPrices, initialInvestment, monthlyInvestment);
        List<Double> dollarCostAveragingValues = simulateDollarCostAveraging(stockPrices, initialInvestment, monthlyInvestment);

        List<Double> investedAmounts = calculateInvestedAmounts(buyAndHoldValues.size(), initialInvestment, monthlyInvestment);

        InvestmentStrategiesChart chart = new InvestmentStrategiesChart("Investment Strategies",
                investedAmounts, buyAndHoldValues, movingAverageValues, buyLowerThanLastPurchaseValues, dollarCostAveragingValues);
        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);
    }

    public static List<Double> calculateInvestedAmounts(int periods, double initialInvestment, double monthlyInvestment) {
        List<Double> investedAmounts = new ArrayList<>();
        for (int i = 0; i < periods; i++) {
            investedAmounts.add(initialInvestment + (i + 1) * monthlyInvestment);
        }
        return investedAmounts;
    }

    public static List<Double> simulateBuyAndHold(List<Double> prices, double initialInvestment, double monthlyInvestment) {
        List<Double> values = new ArrayList<>();
        double shares = initialInvestment / prices.get(0);
        for (int i = 1; i < prices.size(); i++) {
            shares += monthlyInvestment / prices.get(i);
            values.add(shares * prices.get(i));
        }
        return values;
    }

    public static List<Double> simulateMovingAverage(List<Double> prices, double initialInvestment, double monthlyInvestment, int period) {
        List<Double> values = new ArrayList<>();
        double cash = initialInvestment;
        double shares = 0.0;

        for (int i = 0; i < prices.size(); i++) {
            if (i >= period) {
                double movingAverage = calculateMovingAverage(prices, i - period, i);
                if (prices.get(i) > movingAverage && cash > 0) {
                    shares += cash / prices.get(i);
                    cash = 0;
                } else if (prices.get(i) < movingAverage && shares > 0) {
                    cash += shares * prices.get(i);
                    shares = 0;
                }
            }
            cash += monthlyInvestment;
            shares += monthlyInvestment / prices.get(i);
            values.add(cash + shares * prices.get(i));
        }

        return values;
    }

    public static List<Double> simulateBuyLowerThanLastPurchase(List<Double> prices, double initialInvestment, double monthlyInvestment) {
        List<Double> values = new ArrayList<>();
        double cash = initialInvestment;
        double shares = 0.0;
        double lastPurchasePrice = Double.MAX_VALUE;

        for (int i = 0; i < prices.size(); i++) {
            if (prices.get(i) < lastPurchasePrice && cash > 0) {
                shares += cash / prices.get(i);
                cash = 0;
                lastPurchasePrice = prices.get(i);
            }
            cash += monthlyInvestment;
            if (prices.get(i) < lastPurchasePrice) {
                shares += monthlyInvestment / prices.get(i);
                lastPurchasePrice = prices.get(i);
            }
            values.add(cash + shares * prices.get(i));
        }

        return values;
    }

    public static List<Double> simulateDollarCostAveraging(List<Double> prices, double initialInvestment, double monthlyInvestment) {
        List<Double> values = new ArrayList<>();
        double cash = initialInvestment;
        double shares = 0.0;

        for (int i = 0; i < prices.size(); i++) {
            cash += monthlyInvestment;
            shares += monthlyInvestment / prices.get(i);
            values.add(cash + shares * prices.get(i));
        }

        return values;
    }

    public static List<Double> readPricesFromCSV(String csvFile) {
        List<Double> prices = new ArrayList<>();
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            br.readLine(); // Skip header line
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length > 4 && fields[4] != null && !fields[4].isEmpty()) {
                    try {
                        prices.add(Double.parseDouble(fields[4])); // Adj Close price
                    } catch (NumberFormatException e){
                        e.printStackTrace();
                        System.out.println("NumberFormatException found: "+ fields[4]);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prices;
    }

    public static double calculateMovingAverage(List<Double> prices, int start, int end) {
        double sum = 0.0;
        for (int i = start; i < end; i++) {
            sum += prices.get(i);
        }
        return sum / (end - start);
    }
}

