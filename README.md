# Investment Strategies Simulation

This Java program simulates different investment strategies using historical stock market data. It allows users to test various strategies such as buy and hold, moving average, buying when the stock price is lower than the last purchase, and dollar-cost averaging.

## Features

- Simulates investment strategies using historical stock market data.
- Supports multiple investment strategies: buy and hold, moving average, buy lower than last purchase, and dollar-cost averaging.
- Generates graphical representations of investment results over time.
- Allows users to adjust initial investment, monthly investment amount, and moving average period.

## Getting Started

Follow these steps to run the program:

1. **Clone the repository:**


2. **Navigate to the project directory:**


3. **Open the project in IntelliJ IDEA (or your preferred Java IDE).**

4. **Ensure that the required dependencies are installed:** The project uses Maven for dependency management. IntelliJ IDEA should automatically download the dependencies specified in the `pom.xml` file. If not, you may need to manually trigger a Maven reimport.

5. **Prepare the historical stock market data:** You need to provide historical stock market data in CSV format. Replace the placeholder CSV file `VOO.csv` in the project directory with your own CSV file. Ensure that the CSV file contains the following columns: Date, Open, High, Low, Close, Adj Close, Volume.

6. **Run the `InvestmentStrategiesChart` class:** This is the main class of the program. Running this class will simulate the investment strategies and generate graphical representations of the results.

## Customization

You can customize the simulation by adjusting the following parameters:

- `initialInvestment`: The initial amount of money to invest.
- `monthlyInvestment`: The amount of money to invest each month.
- `movingAveragePeriod`: The period (in months) for calculating the moving average in the moving average strategy.

## Example Usage

Here's an example of how to use the program:

1. Clone the repository and navigate to the project directory.
2. Replace the `VOO.csv` file with your own historical stock market data.
3. Open the project in IntelliJ IDEA.
4. Run the `InvestmentStrategiesChart` class.
5. View the graphical representations of the investment results.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

- This project utilizes the [JFreeChart](http://www.jfree.org/jfreechart/) library for generating charts.

----


# Simuleeri investeerimisstrateegiaid:

Programm loeb hinnad CSV-failist ja katsetab nelja erinevat investeerimisstrateegiat, investeerides algul 1000 ja edaspidi iga kuu 200 eurot (Muudetav) trükkides välja või Maven variandis kuvades iga strateegia tootluse. Kasutatavad strateegiad: Osta ja hoia, liikuv keskmine, sta madalama hinnaga kui eelmine ost, Dollar cost averaging.

## Kasutamine

1. Laadige alla S&P 500 ajaloolised andmed Yahoo Finance-st CSV-failina: https://finance.yahoo.com/quote/VOO/history/period1=1283990400&period2=1717372800&interval=1mo&filter=history&frequency=1mo&includeAdjustedClose=true või siit repositooriumist ja salvestage see oma projekti kausta, kust programm selle kätte saaks.
   
3. Kontrollige ja vajadusel muutke programmi csvFile muutuja vastavaks oma CSV-faili teega.

4. Määrake movingAveragePeriod, monthlyInvestment ja initialInvestment väärtused vastavalt vajadusele.
   
6. Kontrolli pom.xml faili sobivust kui kasutada Maven varianti. Minu pom faili sisu:
```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.example</groupId>
    <artifactId>investment-strategies</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.jfree</groupId>
            <artifactId>jfreechart</artifactId>
            <version>1.5.3</version>
        </dependency>
        <dependency>
            <groupId>org.jfree</groupId>
            <artifactId>jcommon</artifactId>
            <version>1.0.24</version>
        </dependency>
    </dependencies>
</project>
```
  
8. Käivitage programm.

   ![image](https://github.com/Paul-HenryP/simulate-investment-strategies/assets/104301931/e9553936-6a56-4950-a5b9-9d6667c6c5f1)

