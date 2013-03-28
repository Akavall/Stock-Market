package stockmarketsimulation;
import static org.junit.Assert.*;

import org.junit.Test;

import stockmarketsimulation.Stock;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class StockTest {

	@Test
	public void testUpdatePrice() 
	{
		Random r = new Random();
		
		Stock s = new Stock(0.01, 100.0, r);
		s.updatePrice(100.13);
		
	    double expect  = 100.13;
	    double result = s.getCurrentPrice();
	    
	    assertEquals(expect, result, 0.00001);
	}
	
   @Test
   public void testRecordPrice()
   {
	   Random r = new Random();
	   
	   Stock s = new Stock(0.01, 100.0, r);
	   s.updatePrice(100.23);
	   s.updatePrice(102.24);
	   s.recordPrice();
	   s.updatePrice(101.23);
	   
	   List<Double> result = s.getHistory();
	   
	   List<Double> expect = new ArrayList<Double>();
	   expect.add(100.0);
	   expect.add(102.24);
	     
	   assertEquals(expect, result);	   	   
   }
   
   @Test 
   public void testRandomEffect()
   {
	    Random r = new Random(10);		
		Stock s = new Stock(0.01, 100, r);	
		s.randomEffect();

		double result = s.getCurrentPrice();
		
		double expect = 100.87467889664622;
		
		assertEquals(expect, result, 0.00001);
   }
}
