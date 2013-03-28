package stockmarketsimulation;
import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import org.junit.Test;

import stockmarketsimulation.Stock;
import stockmarketsimulation.StockTools;


public class StockToolsTest {

	@Test
	public void TestgetLatestPart()
	{
		   Random r = new Random();
		   
		   Stock s = new Stock(0.01, 100.0, r);
		   s.updatePrice(100.23);
		   s.updatePrice(102.24);
		   s.recordPrice();
		   s.updatePrice(100.8);
		   s.recordPrice();
		   s.updatePrice(104.8);
		   s.recordPrice();
		   
		   System.out.println(s.getHistory());
		   System.out.println(s.getCurrentPrice());
		   
		   List<Double> result = StockTools.getLatestHistory(s, 2);
		   
		   List<Double> expect = new ArrayList<Double>();
		   expect.add(100.8);
		   expect.add(104.8);
		   		   
		   assertEquals(expect, result);
	}
	
	@Test
	public void TestgetLatestPart2()
	{
		   Random r = new Random();
		   
		   Stock s = new Stock(0.01, 100.0, r);
		   s.updatePrice(100.23);
		   s.updatePrice(102.24);
		   s.recordPrice();
		   s.updatePrice(100.8);
		   s.recordPrice();
		   s.updatePrice(104.8);
		   s.recordPrice();
		   s.updatePrice(107.2);
		   
		   System.out.println(s.getHistory());
		   System.out.println(s.getCurrentPrice());
		   
		   List<Double> result = StockTools.getLatestHistory(s, 2);
		   
		   List<Double> expect = new ArrayList<Double>();
		   expect.add(104.8);
		   expect.add(107.2);
		   		   
		   assertEquals(expect, result);
	}
}
