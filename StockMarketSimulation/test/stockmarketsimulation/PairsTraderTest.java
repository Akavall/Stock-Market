package stockmarketsimulation;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.HashMap;

import org.junit.Test;

import stockmarketsimulation.PairsTrader;


public class PairsTraderTest {

	@Test
	public void testGetChunks1() {
		List<Double> x = Arrays.asList(1.0, 3.0, 7.0, 2.0, 3.0, 6.0, 2.0);
		List<Double> y = Arrays.asList(1.0, 5.0, 9.0, 3.0, 3.0, 1.0, 2.0);
		
		List<Double> result = PairsTrader.getChunks(x, y);
		List<Double> expect = Arrays.asList(2.0, 5.0);
		
		assertEquals(result, expect);
	}
	
	@Test
	public void testGetChunks2(){
		List<Double> x = Arrays.asList(1.0,2.0,3.0,6.0,7.0,8.0,9.0,9.0,2.0,3.0,2.0,4.0,5.0,6.0,7.0,8.0,9.0,6.0,4.0,5.0,6.0);
	    List<Double> y = Arrays.asList(1.0,3.0,4.0,5.0,6.0,7.0,4.0,5.0,6.0,7.0,2.0,3.0,5.0,6.0,7.0,8.0,3.0,4.0,5.0,6.0,7.0);
	    
	    List<Double> result = PairsTrader.getChunks(x, y);
	    List<Double> expect = Arrays.asList(1.0, 5.0, 4.0, 1.0, 6.0);
	    
	    assertEquals(result, expect);
	}
	
	@Test
	public void testGetMax(){
		List<Double> l = Arrays.asList(1.0, 5.0, 8.0, 2.0, 6.0);
		
		HashMap<String, Double> result = PairsTrader.getMax(l);
		
		double resultMaxProfit = result.get("max profit");
		double resultBestSpread = result.get("best spread");
		
		double expectedMaxProfit = 15.0;
		double expectedBestSpread = 5.0;
		
		assertEquals(resultMaxProfit, expectedMaxProfit, 0.000001);
		assertEquals(resultBestSpread, expectedBestSpread, 0.000001);
		
	}
	
	@Test
	public void testGetParms(){
		List<Double> z = Arrays.asList(1.0,2.0,3.0,6.0,7.0,8.0,9.0,9.0,2.0,3.0,2.0,4.0,5.0,6.0,7.0,8.0,9.0,6.0,4.0,5.0,6.0);
	    List<Double> a = Arrays.asList(1.0,3.0,4.0,5.0,6.0,7.0,4.0,5.0,6.0,7.0,2.0,3.0,5.0,6.0,7.0,8.0,3.0,4.0,5.0,6.0,7.0);
		
		HashMap<String, Double> result = PairsTrader.getParms(a, z);
		
		double resultMaxProfit = result.get("max profit");
		double resultBestSpread = result.get("best spread");
		
		double expectedMaxProfit = 12.0;
		double expectedBestSpread = 4.0;
		
		assertEquals(resultMaxProfit, expectedMaxProfit, 0.000001);
		assertEquals(resultBestSpread, expectedBestSpread, 0.000001);
	}

}
