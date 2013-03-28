package stockmarketsimulation;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import stockmarketsimulation.StatisticsTools;


public class StatisticsToolsTest {

	@Test
	public void test() {
		List<Double> l = new ArrayList<Double>();
		l.add(1.0);
		l.add(3.0);
		l.add(5.0);
	    l.add(4.0);
	    l.add(3.0);
	    l.add(8.3);
	    l.add(12.2);
	    
	    double resultMean = StatisticsTools.getMean(l);
	    double resultStdDev = StatisticsTools.getStdDev(l);
	    
	    double expectMean = 5.2142857142857144;
	    double expectStdDev = 3.5336862870818346;
	    
	    assertEquals(resultMean, expectMean, 0.00001);
	    assertEquals(resultStdDev, expectStdDev, 0.00001);
	}

}
