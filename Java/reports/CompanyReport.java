// COURSE: CSCI1620
// TERM: Spring 2018
//
// NAME: Alex Schulte
// RESOURCES: I used StackOverflow in order to help with writing my junit tests.
package reports;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import analytics.Data;
/**
 * A report for a single company of Fortune 500 data. 
 * Report includes the minimum, maximum, average, and standard deviation of revenues, profits, and rank
 * for all years in which the company was ranked in the Fortune 500.
 * 
 * @author alex
 *
 */
public class CompanyReport implements Report
{

	/**
	 * these hold all the calculated values for revenue and profits.
	 */
	private Double revenue, minRevenue, maxRevenue, avgRevenue, deviationRevenue,
			profits, minProfits, maxProfits, avgProfits, deviationProfits;
	/**
	 * lowest and highest the company has placed on the fortune 500.
	 */
	private Integer minRank, maxRank;
	/**
	 * average and standard deviation rank values. 
	 */
	private Double deviationRank, avgRank;
	/**
	 * file that data is pulled from.
	 */
	private File inputFile;
	/**
	 * keeps track of number of times the company has been in the data set.
	 */
	private int rankCount = 0;
	/**
	 * name of the company.
	 */
	private String company;
	/**
	 * determines if the data has been processed.
	 */
	private boolean processed;
	
 /**
 * Creates new CompanyReport for given company; data to be read from given file.
 * 
 * 
 * @param inputFileIn file the information is in.
 * @param companyIn company that you are looking for information on.
 */
	public CompanyReport(File inputFileIn, String companyIn)
	{
		company = companyIn;
		inputFile = inputFileIn;
	}
		
	/**
	 * Reads data from Fortune 500 data file; processes the data.
	 * 
	 * 
	 * @return boolean based on if the report was successfully generated.
	 */
	public boolean processReport()
	{
		try
		{
			Scanner in = new Scanner(inputFile); 
			String companyRecord = in.nextLine();
			while (in.hasNextLine())
			{
				//put every instance of companyIn into another file.
				companyRecord = in.nextLine();
				//Breaks apart the line.
				String[] tokens = companyRecord.split(",");
				//Gets the company name from the split line.
				String lineCompany = tokens[COMPANY_LOC];
				//adds company information if the names match.
				if (lineCompany.equals(company))
				{
					rankCount++;
				}
			} 
			in.close();
			if (rankCount > 0) 
			{
				int x = 0;
				Double[] revenueArr = new Double[rankCount];
				Double[] profitsArr = new Double[rankCount];
				Integer[] rankArr = new Integer[rankCount];
				Scanner inAgain = new Scanner(inputFile);
				String record = inAgain.nextLine();
				while (inAgain.hasNextLine())
				{
					
					record = inAgain.nextLine();
					String[] tokens = record.split(",");
					Integer rank = Integer.parseInt(tokens[RANK_LOC]);
					revenue = Double.parseDouble(tokens[REVENUE_LOC]);
					profits = Double.parseDouble(tokens[PROFIT_LOC]);
					if (tokens[COMPANY_LOC].equals(company))
					{
						revenueArr[x] = revenue;
						profitsArr[x] = profits;
						rankArr[x] = rank;
						x++;
					}
				}
				minRank = Data.minimum(rankArr);
				maxRank = Data.maximum(rankArr);
				avgRank = Data.average(rankArr);
				deviationRank = Data.standardDeviation(rankArr);
				minRevenue = Data.minimum(revenueArr);
				maxRevenue = Data.maximum(revenueArr);
				avgRevenue = Data.average(revenueArr);
				deviationRevenue = Data.standardDeviation(revenueArr);
				minProfits = Data.minimum(profitsArr);
				maxProfits = Data.maximum(profitsArr);
				avgProfits = Data.average(profitsArr);
				deviationProfits = Data.standardDeviation(profitsArr);
				inAgain.close();
				processed = true;
			}
		}
		catch (RuntimeException e)
		{
			e.printStackTrace();
			processed = false;
		}
		catch (FileNotFoundException e)
		{
			
			System.out.println("no file");
			processed = false;
		}
		return processed;
	}
	/**
	 * Writes the processed report to the given file.
	 * 
	 * 
	 * @param outputFile file to write report to
	 * @return whether the report was successfully written.
	 */
	public boolean writeReport(File outputFile)
	{
		boolean ret;
		FileOutputStream fileOut;
		try 
		{
			if (!processed)
			{
				throw new DataNotProcessedException();
			}
			fileOut = new FileOutputStream(outputFile, false);
			PrintWriter writer = new PrintWriter(fileOut);
			writer.println(toString());
			writer.toString();
			writer.close();
			ret = true;
		} 
		catch (FileNotFoundException e) 
		{
			ret = false;
		}
		catch (DataNotProcessedException e)
		{
			ret = false;
		} 
		return ret;
	}
	/**
	 * Returns a formatted String of this report.
	 * 
	 * @return string report.
	 */
	public String toString()
	{
		return String.format("Fortune 500 Report for %s ranked %d times\n"
				+ "Revenue\nMin: %.3f Max: %.3f Avg: %.3f StD: %.3f\n"
				+ "Profit\nMin: %.3f Max: %.3f Avg: %.3f StD: %.3f\n"
				+ "Rank\nMin: %d Max: %d Avg: %.3f StD: %.3f",
				company, rankCount, minRevenue, maxRevenue, avgRevenue, deviationRevenue,
				minProfits, maxProfits, avgProfits,
				deviationProfits, minRank, maxRank, avgRank, deviationRank);
	}
	/**
	 * Returns the company of this report.
	 * 
	 * @return the company name.
	 */
	public String getCompany()
	{
		return company;
	}
	/**
	 * Returns the company of this report.
	 * 
	 * @return the company name.
	 */
	public Double getMinProfit()
	{
		return minProfits;
	}
	/**
	 * Returns the company of this report.
	 * 
	 * @return the company name.
	 */
	public Double getMinRevenue()
	{
		return minRevenue;
	}
	/**
	 * Returns the company of this report.
	 * 
	 * @return the company name.
	 */
	public Double getMaxProfit()
	{
		return maxProfits;
	}
	/**
	 * Returns the company of this report.
	 * 
	 * @return the company name.
	 */
	public Double getMaxRevenue()
	{
		return maxRevenue;
	}
	/**
	 * Returns the company of this report.
	 * 
	 * @return the company name.
	 */
	public Double getAvgProfit()
	{
		return avgProfits;
	}
	/**
	 * Returns the company of this report.
	 * 
	 * @return the company name.
	 */
	public Double getAvgRevenue()
	{
		return avgRevenue;
	}
	/**
	 * Returns the company of this report.
	 * 
	 * @return the company name.
	 */
	public Double getDeviationProfit()
	{
		return deviationProfits;
	}
	/**
	 * Returns the company of this report.
	 * 
	 * @return the company name.
	 */
	public Double getDeviationRevenue()
	{
		return deviationRevenue;
	}
}
