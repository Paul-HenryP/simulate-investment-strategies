# simulate-investment-strategies
Programm loeb hinnad CSV-failist ja katsetab nelja erinevat investeerimisstrateegiat, investeerides algul 1000 ja edaspidi iga kuu 200 eurot trükkides välja iga strateegia tootluse. Kasutatavad strateegiad: Osta ja hoia, liikuv keskmine, sta madalama hinnaga kui eelmine ost, Dollar cost averaging.

## Kasutamine

1. Laadige alla S&P 500 ajaloolised andmed Yahoo Finance-st CSV-failina: https://finance.yahoo.com/quote/VOO/history/period1=1283990400&period2=1717372800&interval=1mo&filter=history&frequency=1mo&includeAdjustedClose=true või siit repositooriumist ja salvestage see oma projekti kausta, kust programm selle kätte saaks.
   
3. Kontrollige ja vajadusel muutke programmi csvFile muutuja vastavaks oma CSV-faili teega.

4. Määrake movingAveragePeriod ja monthlyInvestment ja investeeringute väärtused vastavalt vajadusele.
  
6. Käivitage programm.
