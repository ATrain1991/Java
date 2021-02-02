package tests;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import reports.CompanyReport;

public class CompanyReportTest {

	@Test
	public void unprocessedToStringTest()
	{
		CompanyReport c = new CompanyReport(new File("fortune500.csv"), "Nike");
		String expected = "Fortune 500 Report for Nike ranked 0 times\n" + 
				"Revenue\n" + 
				"Min: nul Max: nul Avg: nul StD: nul\n" + 
				"Profit\n" + 
				"Min: nul Max: nul Avg: nul StD: nul\n" + 
				"Rank\n" + 
				"Min: null Max: null Avg: nul StD: nul";
		
		assertEquals("Problem in CompanyReport basic toString format,"
				+ " check spelling, capitalization, spacing, and format",
				expected, c.toString());
	}
	@Test
	public void noCompany()
	{
		CompanyReport company = new CompanyReport(new File("fortune500.csv"), "xyz");
		company.processReport();
		//min
		assertEquals(company.getMinRevenue(), null);
		assertEquals(company.getMinProfit(), null);
		//max
		assertEquals(company.getMaxRevenue(), null);
		assertEquals(company.getMaxProfit(), null);
		//avg
		assertEquals(company.getAvgRevenue(), null);
		assertEquals(company.getAvgProfit(), null);
		//standard deviation
		assertEquals(company.getDeviationRevenue(), null);
		assertEquals(company.getDeviationProfit(), null);
	}
	@Test
	public void noFile()
	{
		CompanyReport company = new CompanyReport(new File("fortune5000.csv"), "Skelly Oil");		
		assertFalse(company.writeReport(new File("")));
	}
	/**@Test
	public void Mismatch()
	{
		CompanyReport company = new CompanyReport(new File("fortune500.xxx"), "Skelly Oil");
	}*/
	@Test
	public void MinTest()
	{
		CompanyReport company = new CompanyReport(new File("fortune500.csv"), "Skelly Oil");
		company.processReport();
		assertEquals(company.getMinRevenue(), 211.100, 0.001);
		assertEquals(company.getMinProfit(), 21.200, 0.001);
	}
	@Test
	public void MaxTest()
	{
		CompanyReport company = new CompanyReport(new File("fortune500.csv"), "Skelly Oil");
		company.processReport();
		assertEquals(company.getMaxRevenue(), 332.400, 0.001);
		assertEquals(company.getMaxProfit(), 53.200, 0.001);
	}
	@Test
	public void AvgTest()
	{
		CompanyReport company = new CompanyReport(new File("fortune500.csv"), "Skelly Oil");
		company.processReport();
		assertEquals(company.getAvgRevenue(), 256.377, 0.001);
		assertEquals(company.getAvgProfit(), 30.369, 0.001);
	}
	@Test
	public void StDTest()
	{
		CompanyReport company = new CompanyReport(new File("fortune500.csv"), "Skelly Oil");
		company.processReport();
		assertEquals(company.getDeviationRevenue(), 26.611, 0.001);
		assertEquals(company.getDeviationProfit(), 7.985, 0.001);
	}
	@Test
	public void getCompanyTest()
	{
		CompanyReport company = new CompanyReport(new File("fortune500.csv"), "Skelly Oil");
		assertEquals(company.getCompany(),"Skelly Oil");
	}
	@Test
	public void validWriteTest()
	{
		CompanyReport company = new CompanyReport(new File("fortune500.csv"), "Skelly Oil");
		company.processReport();
		assertTrue(company.writeReport(new File("output")));
	}
}
