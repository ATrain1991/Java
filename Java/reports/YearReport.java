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
 * A report for a single year of Fortune 500 data. 
 * Report includes the minimum, maximum, average, and standard deviation of revenues 
 * and profits for all ranked companies of the report's year.
 * 
 * 
 * 
 * @author alex
 *
 */
public class YearReport implements Report
{

	/**
	 * revenue for a given index in the file.
	 */
	public Double revenue;
	/**
	 * minimum revenue for a given year in the file.
	 */
	public Double minRevenue;
	/**
	 * maximum revenue for a given year in the file.
	 */
	public Double maxRevenue;
	/**
	 * average revenue for a given year in the file.
	 */
	public Double avgRevenue;
	/**
	 * standard deviation revenue for a given year in the file.
	 */
	public Double deviationRevenue;
	/**
	 * profit for a given index in the file.
	 */
	public Double profit;
	/**
	 * minimum revenue for a given year in the file.
	 */
	public Double minProfits;
	/**
	 * maximum revenue for a given year in the file.
	 */
	public Double maxProfits;
	/**
	 * average profit for a given year in the file.
	 */
	public Double avgProfits;
	/**
	 * standard deviation profit for a given year in the file.
	 */
	public Double deviationProfits;
	/**
	 * determines if the data has been processed.
	 */
	public boolean processed;
	/**
	 * year the file will look at.
	 */
	private int year;
	/**
	 * file that you will look for data in.
	 */
	private File input;
 /**
 * Creates new YearReport for given year; data to be read from given file.
 * 
 * 
 * @param inputFileIn File containing Fortune 500 data for this report.
 * @param yearIn Year to report Fortune 500 data.
 */
	public YearReport(File inputFileIn, int yearIn)
	{
		year = yearIn;
		input = inputFileIn;
	}
	/**
	 * Reads data from Fortune 500 data file; processes the data.
	 * 
	 * @return if the report was processed successfully.
	 * @throws YearNotFoundException used to determine if the user is trying to access data that doesn't exist.
	 */
	public boolean processReport() throws YearNotFoundException
	{
		processed = false;
		boolean check = false;
		try
		{
			Scanner in = new Scanner(input);
			int x = 0;
			Double[] revenueArr = new Double[NUMCOMPANIES];
			Double[] profitsArr = new Double[NUMCOMPANIES];
			in.nextLine();
			while (in.hasNextLine())
			{
				
				String record = in.nextLine();
				String[] tokens = record.split(",");
				if (Integer.parseInt(tokens[YEAR_LOC]) == year)
				{
					revenue = Double.parseDouble(tokens[REVENUE_LOC]);
					profit = Double.parseDouble(tokens[PROFIT_LOC]);
					revenueArr[x] = revenue;
					profitsArr[x] = profit;
					x++;
					check = true;
				}
			}
			if (check)
			{
				minRevenue = Data.minimum(revenueArr);
				maxRevenue = Data.maximum(revenueArr);
				avgRevenue = Data.average(revenueArr);
				deviationRevenue = Data.standardDeviation(revenueArr);
				minProfits = Data.minimum(profitsArr);
				maxProfits = Data.maximum(profitsArr);
				avgProfits = Data.average(profitsArr);
				deviationProfits = Data.standardDeviation(profitsArr);
			}
			else
			{
				minRevenue = null;
				maxRevenue = null;
				avgRevenue = null;
				deviationRevenue = null;
				minProfits = null;
				maxProfits = null;
				avgProfits = null;
				deviationProfits = null;
				
			}
			in.close();
			if (!check)
			{ 
				throw new YearNotFoundException();
			}
			processed = true;
		}
		catch (FileNotFoundException e)
		{
			
			System.out.println("no file");
			processed = false;
		}
		catch (YearNotFoundException e)
		{
			processed = false;	
		}
		return processed;
	}
 /**
 * Writes the processed report to the given file. File contents are based upon YearReport's toString.
 * 
 * 
 * @param outputFile  File to write report to.
 * @return true if write successful, false if file cannot be created.
 * @throws DataNotProcessedException used to determine if the data that is trying to be manipulated has been processed.
 */
	public boolean writeReport(File outputFile) throws DataNotProcessedException  
	{
		boolean ret = false;
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
			System.out.println("FNFE");
			ret = false;
		}
		catch (DataNotProcessedException e)
		{
			ret = false;
		} 
		return ret;
	}
 /**
 * Returns a formatted String.
 * 
 * @return a string representation of the companies in a given year.
 */
	public String toString()
	{
		return String.format("Fortune 500 Report for %d\n"
			+ "Revenue\nMin: %.3f Max: %.3f Avg: %.3f StD: %.3f\n"
			+ "Profit\nMin: %.3f Max: %.3f Avg: %.3f StD: %.3f",
			year, minRevenue, maxRevenue, avgRevenue, deviationRevenue,
			minProfits, maxProfits, avgProfits, deviationProfits);
	}
 /**
 * Returns the year of this report.
 * 
 * @return Year of this report.
 */
	public int getYear() 
	{
		return year;
	}
}

