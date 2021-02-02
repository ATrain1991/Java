package tests;

import static org.junit.Assert.*;
import java.io.File;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import junit.framework.Assert;
import reports.CompanyReport;
import reports.DataNotProcessedException;
import reports.YearNotFoundException;
import reports.YearReport;

public class YearReportTest {
	//Create an ExpectedException Rule, this rule can be used
	//for all Exception tests
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void NoFile()
	{
			CompanyReport company = new CompanyReport(new File("fortune5000.csv"), "Skelly Oil");		
			assertFalse(company.writeReport(new File("")));
	}
	
	@Test
	public void writeTest() throws DataNotProcessedException 
	{
		YearReport yr = new YearReport(new File("fortune500.csv"), 1955);
		try {
			yr.processReport();
			assertTrue(yr.writeReport(new File("HereIsAFile.txt")));
		} catch (YearNotFoundException e) {
			e.printStackTrace();
		}
		assertTrue(yr.writeReport(new File("HereIsAFile.txt")));
		
	}
	@Test
	public void notProcessedTest() throws DataNotProcessedException
	{
		YearReport yr = new YearReport(new File("fortune500.csv"), 1955);
		assertFalse(yr.writeReport(new File("HereIsAFile.txt")));
		/**try 
		{
		yr.writeReport(new File("HereIsAFile.txt"));
		}
		catch(DataNotProcessedException e)
		{
			assertTrue(true);
		}*/
	}
	@Test
	public void noYear() 
	{
		YearReport yr = new YearReport(new File("fortune500.csv"), 1900);
		try {
			yr.processReport();
			assertEquals(yr.minRevenue, null);
			assertEquals(yr.minProfits, null);
			assertEquals(yr.maxRevenue, null);
			assertEquals(yr.maxProfits, null);
			assertEquals(yr.avgRevenue, null);
			assertEquals(yr.avgProfits, null);
			assertEquals(yr.deviationRevenue, null);
			assertEquals(yr.deviationProfits, null);
		} catch (YearNotFoundException e) {
			// TODO Auto-generated catch block
		}

	}
	@Test
	public void unprocessedToStringTest()
	{
		YearReport y = new YearReport(new File("fortune500.csv"), 2020);
		String expected = "Fortune 500 Report for 2020\n" + 
				"Revenue\n" + 
				"Min: nul Max: nul Avg: nul StD: nul\n" + 
				"Profit\n" + 
				"Min: nul Max: nul Avg: nul StD: nul";
		
		assertEquals("Problem in YearReport basic toString format, check spelling, capitalization, spacing, and format",
				expected, y.toString());
	}
	@Test
	public void MinTest()
	{
		YearReport yr = new YearReport(new File("fortune500.csv"), 1955);
		try {
			yr.processReport();
		} catch (YearNotFoundException e) {
			// TODO Auto-generated catch block
		}
		assertEquals(yr.minRevenue, 49.700, 0.001);
		assertEquals(yr.minProfits, -35.500, 0.001);
	}
	@Test
	public void MaxTest()
	{
		YearReport yr = new YearReport(new File("fortune500.csv"), 1955);
		try {
			yr.processReport();
		} catch (YearNotFoundException e) {
			// TODO Auto-generated catch block
		}
		assertEquals(yr.maxRevenue, 9823.500, 0.001);
		assertEquals(yr.maxProfits, 806.000, 0.001);
	}
	@Test
	public void AvgTest()
	{
		YearReport yr = new YearReport(new File("fortune500.csv"), 1955);
		try {
			yr.processReport();
		} catch (YearNotFoundException e) {
			// TODO Auto-generated catch block
		}
		assertEquals(yr.avgRevenue, 273.566, 0.001);
		assertEquals(yr.avgProfits, 16.531, 0.001);
	}
	@Test
	public void StDTest()
	{
		YearReport yr = new YearReport(new File("fortune500.csv"), 1955);
		try {
			yr.processReport();
		} catch (YearNotFoundException e) {
			// TODO Auto-generated catch block
		}
		assertEquals(yr.deviationRevenue, 610.058, 0.001);
		assertEquals(yr.deviationProfits, 53.148, 0.001);
	}
	@Test
	public void getYearTest()
	{
		YearReport yr = new YearReport(new File("fortune500.csv"), 1955);
		assertEquals(yr.getYear(),1955);
	}
	@Test
	public void validWriteTest()
	{
		YearReport company = new YearReport(new File("fortune500.csv"), 1955);
		company.processReport();
		assertTrue(company.writeReport(new File("output")));
	}
}

