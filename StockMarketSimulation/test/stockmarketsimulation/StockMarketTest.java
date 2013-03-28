package stockmarketsimulation;
import static org.junit.Assert.*;

import java.util.List;
import java.util.Random;

import org.junit.Test;

import stockmarketsimulation.StatisticsTools;
import stockmarketsimulation.StockMarket;


public class StockMarketTest {

	@Test
	public void test() 
	{
        Random random = new Random(10);
		
		int numberOfTradingPeriods = 1000;
		double buyAndSellVolatility = 0.01;
		double randomShockVolatility = 0.01;
		double startPrice = 100.0;
	    int numberOfStocks = 10;
	    int numberOfMomentumTraders = 80; 
	    double threshHold = 0.01;
	    int basePeriodLength = 30;
		
		StockMarket sm = new StockMarket(numberOfTradingPeriods,
				                         buyAndSellVolatility,
				                         randomShockVolatility,
				                         startPrice,
				                         numberOfStocks,				                         
				                         numberOfMomentumTraders,
				                         threshHold,
				                         basePeriodLength,
				                         random);
		
		List<Double> s = sm.getAllStocks().get(0).getHistory();
		
		double resultMean = StatisticsTools.getMean(s);
		double resultStdDev = StatisticsTools.getStdDev(s);
		
		System.out.println("mean : " + resultMean);
		System.out.println("standard deviation : " + resultStdDev);
		
		double expectedMean = 194.86708313238162;
		double expectedStdDev = 61.576906761225665;
		
		assertEquals(resultMean, expectedMean, 0.00001);
		assertEquals(resultStdDev, expectedStdDev, 0.00001);

	}

}
