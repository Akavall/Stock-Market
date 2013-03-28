package stockmarketsimulation;
import static org.junit.Assert.*;

import org.junit.Test;

import stockmarketsimulation.MomentumTrader;
import stockmarketsimulation.Stock;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class MomentumTraderTest {

	@Test
	public void testGetMomentumStatus() 
	{
        Random r = new Random(10);
		
		Stock s = new Stock(0.01, 100, r);
		s.updatePrice(102.2);
		s.recordPrice();
		s.updatePrice(102.1);
		s.recordPrice();
		s.updatePrice(104.5);
		s.recordPrice();
		s.updatePrice(105.2);
		s.recordPrice();
		
		List<Stock> allStocksList = new ArrayList<Stock>();
		
		allStocksList.add(s);
		
        MomentumTrader mt = new MomentumTrader(0.01, 2, 4, 0.01, allStocksList, r);
		
		String result = mt.getMomentumStatus(s);
		
		String expect = "Hot";
		
		assertEquals(expect, result);
	}
	
	@Test
	public void testGetMomentumStatus2() 
	{
        Random r = new Random(10);
		
		Stock s = new Stock(0.01, 100, r);
		s.updatePrice(102.2);
		s.recordPrice();
		s.updatePrice(102.1);
		s.recordPrice();
		s.updatePrice(104.5);
		s.recordPrice();
		s.updatePrice(102.2); // This is different from the previous test
		s.recordPrice();
		
		List<Stock> allStocksList = new ArrayList<Stock>();
		
		allStocksList.add(s);
		
        MomentumTrader mt = new MomentumTrader(0.01, 2, 4, 0.01, allStocksList, r);
		
		String result = mt.getMomentumStatus(s);
		
		String expect = "Some Momentum";
		
		assertEquals(expect, result);
	}
	
	@Test
	public void testTradingMethod()
	{
	Random r = new Random(10);
		
		Stock s = new Stock(0.01, 100, r);
		s.updatePrice(102.2);
		s.recordPrice();
		s.updatePrice(102.1);
		s.recordPrice();
		s.updatePrice(104.5);
		s.recordPrice();
		s.updatePrice(105.2);
		s.recordPrice();
		
		Stock s1 = new Stock(0.01, 100, r);
		s1.updatePrice(102.2);
		s1.recordPrice();
		s1.updatePrice(102.1);
		s1.recordPrice();
		s1.updatePrice(104.5);
		s1.recordPrice();
		s1.updatePrice(101.2);
		s1.recordPrice();
		
		System.out.println(s.getHistory());
		
		List<Stock> allStocksList = new ArrayList<Stock>();
		
		allStocksList.add(s);
		allStocksList.add(s1);
		
		MomentumTrader mt = new MomentumTrader(0.01, 2, 4, 0.01, allStocksList, r);
		
		mt.tradingMethod();
		
		double result1 = s.getCurrentPrice();
		double result2 = s1.getCurrentPrice();
		
		double expect1 = 106.12016219927182;
		double expect2 = 101.2;
		
		assertEquals(result1, expect1, 0.00001);
		assertEquals(result2, expect2, 0.00001);
	}
}
