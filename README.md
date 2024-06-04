# simulate-investment-strategies
Programm loeb hinnad CSV-failist ja katsetab nelja erinevat investeerimisstrateegiat, investeerides algul 1000 ja edaspidi iga kuu 200 eurot trükkides välja iga strateegia tootluse. Kasutatavad strateegiad: Osta ja hoia, liikuv keskmine, sta madalama hinnaga kui eelmine ost, Dollar cost averaging.

## Kasutamine

1. Laadige alla S&P 500 ajaloolised andmed Yahoo Finance-st CSV-failina: https://finance.yahoo.com/quote/VOO/history/period1=1283990400&period2=1717372800&interval=1mo&filter=history&frequency=1mo&includeAdjustedClose=true või siit repositooriumist ja salvestage see oma projekti kausta, kust programm selle kätte saaks.
   
3. Kontrollige ja vajadusel muutke programmi csvFile muutuja vastavaks oma CSV-faili teega.

4. Määrake movingAveragePeriod, monthlyInvestment ja initialInvestment väärtused vastavalt vajadusele.
  
6. Käivitage programm.

   ![image](https://github.com/Paul-HenryP/simulate-investment-strategies/assets/104301931/e9553936-6a56-4950-a5b9-9d6667c6c5f1)

# simulate-investment-strategies Maven branch

## Usage

1.Make sure you open it in a maven project and the pom.xml file has the correct dependancies. Here I used:

---

(You need to click and reload the dependancies after editing the pom file.)

2.Activate the program.

![image](https://github.com/Paul-HenryP/simulate-investment-strategies/assets/104301931/485998be-556f-41b4-abc0-22de784972b7)
