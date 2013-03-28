package stockmarketsimulation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;



class PairsTrader extends Investor
{
	/*
	 * Under consturction.
	 */
	private int stockOneId;
	private int stockTwoId;
	private final int TRADING_LENGTH;
	private double threshHold;
	private List<Stock> allStocksList;
	
	private boolean hasStock;
	private boolean pathsCrossed;
	
	PairsTrader(double buyAndSellVolatility,
			    int stockOneId,
			    int stockTwoId,
			    double threshHold,
			    final int TRADING_LENGTH,
			    List<Stock> allStocksList,
			    Random random)
	{
		super(buyAndSellVolatility, random);
		
		setStockOneId(stockOneId);
		setStockTwoId(stockTwoId);
		setHasStock(false);
		setPathsCrossed(true); // Assume we are starting from the position when pairs
		                       // paths are already crossed
		this.TRADING_LENGTH = TRADING_LENGTH;
		this.threshHold = threshHold;
		this.allStocksList = allStocksList;
	}
	
	public void setStockOneId(int stockOneId)
	{
		this.stockOneId = stockOneId;
	}
	
	public void setStockTwoId(int stockTwoId)
	{
		this.stockTwoId = stockTwoId;
	}
	
	public int getStockOneId()
	{
		return this.stockOneId;
	}
	
	public int getStockTwoId()
	{
		return this.stockTwoId;
	}
	
	public void setHasStock(boolean trueOrFalse)
	{
		this.hasStock = trueOrFalse;
	}
	
	public boolean getHasStock()
	{
		return this.hasStock;
	}
	
	public void setPathsCrossed( boolean trueOrFalse)
	{
		this.pathsCrossed = trueOrFalse;
	}
	
	public boolean getPathsCrossed()
	{
		return this.pathsCrossed;
	}
	
	@Override
	public void tradingMethod()
	{
		Stock stockOne = this.allStocksList.get(this.getStockOneId());
		Stock stockTwo = this.allStocksList.get(this.getStockTwoId());
							
		if (stockOne.getHistory().size() >= this.TRADING_LENGTH)
		{
			/*
			if(getMomentumStatus(stock) == "Hot" && this.myStocks.contains(stock) == false)
			{	
				// we buy an asset if don't already have it
				this.buyStock(stock);
				stock.addMomentumTrade();
				break;
			}
			if(getMomentumStatus(stock) == "No Momentum" && this.myStocks.contains(stock) == true)
			{
				// we sell an asset only if we have it
				this.sellStock(stock);
			}
			*/
			
			int n = stockOne.getHistory().size();			
			List<Double> recentPricesOne = stockOne.getHistory(n - this.TRADING_LENGTH, n);
			List<Double> recentPricesTwo = stockTwo.getHistory(n - this.TRADING_LENGTH, n);
			
			HashMap<String, Double> lastPeriodResults = this.getParms(recentPricesOne, recentPricesTwo);
			
			
			double priceOne = stockOne.getCurrentPrice();
			double priceTwo = stockTwo.getCurrentPrice();
			
			double difference  = Math.abs(priceOne - priceTwo);
			
			// buying and shorting the stocks
			if (lastPeriodResults.get("best spread") > difference && !this.getHasStock())
			{
				if(priceOne > priceTwo)
				{
					this.buyStock(stockTwo);
					this.sellStock(stockOne);
				}
				else
				{
					this.buyStock(stockOne);
				    this.sellStock(stockTwo);
				}
				this.setHasStock(true);
			}			
		}		
	}	
	
	public static HashMap<String, Double> getParms(List<Double> x, List<Double> y)
	{
		List<Double> chunks = getChunks(x, y);
		HashMap<String, Double> results = getMax(chunks);
		return results;
	}
	
	public static List<Double> getChunks(List<Double> x, List<Double> y)
	{
		List<Double> chunkSizes = new ArrayList<Double>();
		double prevSign = 0;
		double tempMax = 0;
		double diff;
		
		for(int i = 0; i < x.size(); i++)
		{
			diff = x.get(i) - y.get(i);
			if(Math.abs(diff) > tempMax)
			{
				tempMax = Math.abs(diff);
			}
			
			if ((diff * prevSign < 0 || diff == 0) && tempMax != 0 )
			{	
				chunkSizes.add(tempMax);
				tempMax = 0;
			}
			
			prevSign = diff;
		}
		return chunkSizes;
	}
	
	public static HashMap<String, Double> getMax(List<Double> chunks)
	{
		Collections.sort(chunks);
		double bestSpread = 0, maxProfit = 0, spread = 0, tempProfit;
		
		System.out.println(chunks); //debug
		
		int n = chunks.size();
		
	    for(int i = 0; i < chunks.size(); i++)
	    {
	    	spread = chunks.get(i);
	    	tempProfit = spread * (n - i);
	    	if(tempProfit > maxProfit)
	    	{
	    		maxProfit = tempProfit;
	    		bestSpread = spread;	    		
	    	}
	    }
	    
	    HashMap<String, Double> result = new HashMap<String, Double>();
	    result.put("max profit", maxProfit);
	    result.put("best spread", bestSpread);
	    
	    return result;
		
	}
}