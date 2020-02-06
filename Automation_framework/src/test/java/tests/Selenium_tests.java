package tests;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import pages.Home_Page;
import utils.dates_helper;

public class Selenium_tests extends dates_helper {

	HashMap<String, Boolean> results;

	WebDriver driver;

	Home_Page home;

	// builds a new report using the html template
	ExtentHtmlReporter htmlReporter;

	ExtentReports extent;
	// helps to generate the logs in test report.
	ExtentTest test;

	com.aventstack.extentreports.model.Test extentTest;

	@BeforeClass
	public void setUp() {
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/testReport.html");

		// initialize ExtentReports and attach the HtmlReporter
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		// To add system or environment info by using the setSystemInfo method.
		extent.setSystemInfo("OS", "Windows");
		extent.setSystemInfo("Browser", "Chrome");

		// configuration items to change the look and feel
		// add content, manage tests etc
		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setDocumentTitle("Extent Report Demo");
		htmlReporter.config().setReportName("Test Report");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");

		System.setProperty("webdriver.gecko.driver",
				new File(System.getProperty("user.home"), "chromedriver.exe").getAbsolutePath());
		driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://codebeautify.org/generate-random-date");

	}

	@Test(priority = 0)
	public void test_case1() throws IOException {
		test = extent.createTest("Test Case 1");
		home = new Home_Page(driver);
		test.log(Status.INFO, "Rapid Date generator Home Page loaded");
		home.enter_date_to_generate("0");
		test.log(Status.INFO, "Rapid Date generator Home Page loaded");
		String dates1 = home.get_Dates();

		Assert.assertEquals(dates1, "");
		test.log(Status.PASS, "Test Case 1  is Passed ");

		MediaEntityModelProvider screenshot = MediaEntityBuilder.createScreenCaptureFromPath("testcase1.png").build();

		test.log(Status.INFO, "Screenshot on test PASS", screenshot);

	}

	@Test(priority = 1)
	public void test_case2() throws IOException {
		test = extent.createTest("Test Case 2");
		test.log(Status.INFO, "Rapid Date generator Home Page loaded");
		home = new Home_Page(driver);
		home.enter_date_to_generate("0.1");
		test.log(Status.INFO, "dates to generated was set as 0.1");
		String dates1 = home.get_Dates();

		Assert.assertEquals(dates1, "");
		test.log(Status.FAIL, "Test Case 2  is Failed ");

		MediaEntityModelProvider screenshot = MediaEntityBuilder.createScreenCaptureFromPath("testcase2.png").build();

		test.log(Status.INFO, "Screenshot on test FAIL", screenshot);
	}

	@Test(priority = 2)
	public void test_case3() throws IOException {
		test = extent.createTest("Test Case 3");
		test.log(Status.INFO, "Rapid Date generator Home Page loaded");
		home = new Home_Page(driver);
		home.enter_date_to_generate("");
		test.log(Status.INFO, "dates to generated was set as null");

		String dates1 = home.get_Dates();

		Assert.assertEquals(dates1, "");
		test.log(Status.PASS, "Test Case 3  is Passed ");

		MediaEntityModelProvider screenshot = MediaEntityBuilder.createScreenCaptureFromPath("testcase3.png").build();

		test.log(Status.INFO, "Screenshot on test PASS", screenshot);
	}

	@Test(priority = 3)
	public void test_case4() throws IOException {
		test = extent.createTest("Test Case 4");
		test.log(Status.INFO, "Rapid Date generator Home Page loaded");
		home = new Home_Page(driver);
		home.enter_date_to_generate("A");
		test.log(Status.INFO, "dates to generated was set as 'A' ");

		String dates1 = home.get_Dates();

		Assert.assertEquals(dates1, "");
		test.log(Status.PASS, "Test Case 4  is Passed ");

		MediaEntityModelProvider screenshot = MediaEntityBuilder.createScreenCaptureFromPath("testcase4.png").build();

		test.log(Status.INFO, "Screenshot on test PASS", screenshot);
	}

	@Test(priority = 4)
	public void test_case5() throws IOException {
		test = extent.createTest("Test Case 5");
		test.log(Status.INFO, "Rapid Date generator Home Page loaded");
		home = new Home_Page(driver);
		home.enter_date_to_generate("10");
		test.log(Status.INFO, "dates to generated was set as '10' ");
		home.select_date_output_format(8);
		test.log(Status.INFO, "Custom Date format was selected ");
		home.enter_custom_date_format(home.custom_date1);
		test.log(Status.INFO, "Custom Date format was entered ");
		results = check_dates(get_date_as_list(home.get_Dates()), home.customdateformat1);

		Assert.assertTrue(check_HashMap(results));
		test.log(Status.FAIL, "Test Case 5  is failed ");

		MediaEntityModelProvider screenshot = MediaEntityBuilder.createScreenCaptureFromPath("testcase5.png").build();

		test.log(Status.INFO, "Screenshot on test FAIL", screenshot);
	}

	@Test(priority = 5)
	public void test_case6() throws IOException {
		test = extent.createTest("Test Case 6");
		test.log(Status.INFO, "Rapid Date generator Home Page loaded");
		home = new Home_Page(driver);
		home.enter_date_to_generate("10");
		test.log(Status.INFO, "Custom Date format was selected ");
		home.select_date_output_format(8);
		home.enter_custom_date_format(home.custom_date2);
		test.log(Status.INFO, "Custom Date format was entered ");

		results = check_dates(get_date_as_list(home.get_Dates()), home.customdateformat2);

		Assert.assertTrue(check_HashMap(results));
		test.log(Status.FAIL, "Test Case 6  is failed ");

		MediaEntityModelProvider screenshot = MediaEntityBuilder.createScreenCaptureFromPath("testcase6.png").build();

		test.log(Status.INFO, "Screenshot on test FAIL", screenshot);
	}

	@Test(priority = 6)
	public void test_case7() throws IOException {
		test = extent.createTest("Test Case 7");
		test.log(Status.INFO, "Rapid Date generator Home Page loaded");
		home = new Home_Page(driver);
		home.enter_date_to_generate("10");
		test.log(Status.INFO, "Dates to generate was set to '10'");

		home.select_date_output_format(8);
		test.log(Status.INFO, "Dates output format was selected");
		home.enter_custom_date_format(home.custom_date3);
		test.log(Status.INFO, "Dates output format was entered");
		results = check_dates(get_date_as_list(home.get_Dates()), home.customdateformat3);

		Assert.assertTrue(check_HashMap(results));
		test.log(Status.FAIL, "Test Case 7  is failed ");

		MediaEntityModelProvider screenshot = MediaEntityBuilder.createScreenCaptureFromPath("testcase7.png").build();

		test.log(Status.INFO, "Screenshot on test FAIL", screenshot);
	
	}

	@Test(priority = 7)
	public void test_case8() throws IOException {
		test = extent.createTest("Test Case 8");
		test.log(Status.INFO, "Rapid Date generator Home Page loaded");
		home = new Home_Page(driver);
		home.enter_date_to_generate("10");
		test.log(Status.INFO, "dates to generated was set to '10'");
		home.select_date_output_format(8);
		test.log(Status.INFO, "Custom Date Format was Selected");
		home.enter_custom_date_format(home.custom_date4);
		test.log(Status.INFO, "Custom Date Format was entered");
		results = check_dates(get_date_as_list(home.get_Dates()), home.customdateformat4);

		Assert.assertTrue(check_HashMap(results));
		test.log(Status.FAIL, "Test Case 8  is failed ");

		MediaEntityModelProvider screenshot = MediaEntityBuilder.createScreenCaptureFromPath("testcase8.png").build();

		test.log(Status.INFO, "Screenshot on test FAIL", screenshot);

	}

	@Test(priority = 8)
	public void test_case9() throws IOException {
		test = extent.createTest("Test Case 9");
		test.log(Status.INFO, "Rapid Date generator Home Page loaded");
		home = new Home_Page(driver);
		home.enter_date_to_generate("10");
		test.log(Status.INFO, "dates to generated was set to '10'");

		home.select_date_output_format(8);
		test.log(Status.INFO, "Custom Date Format was Selected");

		home.enter_custom_date_format(home.custom_date5);
		test.log(Status.INFO, "Custom Date Format was entered");
		results = check_dates(get_date_as_list(home.get_Dates()), home.customdateformat5);

		Assert.assertTrue(check_HashMap(results));
		test.log(Status.FAIL, "Test Case 9  is failed ");

		MediaEntityModelProvider screenshot = MediaEntityBuilder.createScreenCaptureFromPath("testcase9.png").build();

		test.log(Status.INFO, "Screenshot on test FAIL", screenshot);
	}

	@Test(priority = 9)
	public void test_case10() throws IOException {
		test = extent.createTest("Test Case 10");
		test.log(Status.INFO, "Rapid Date generator Home Page loaded");
		home = new Home_Page(driver);
		home.enter_date_to_generate("10");
		test.log(Status.INFO, "dates to generated was set to '10'");

		home.select_date_output_format(8);
		test.log(Status.INFO, "Custom Date Format was Selected");

		home.enter_custom_date_format("YYY");
		test.log(Status.INFO, "Custom Date Format was entered");

		String dates1 = home.get_Dates();

		Assert.assertEquals(dates1, "");
		test.log(Status.FAIL, "Test Case 10  is failed ");

		MediaEntityModelProvider screenshot = MediaEntityBuilder.createScreenCaptureFromPath("testcase10.png").build();

		test.log(Status.INFO, "Screenshot on test FAIL", screenshot);
	}

	@Test(priority = 10)
	public void test_case11() throws IOException {
		test = extent.createTest("Test Case 11");
		test.log(Status.INFO, "Rapid Date generator Home Page loaded");
		
		home = new Home_Page(driver);
		home.enter_date_to_generate("10");
		test.log(Status.INFO, "dates to generated was set to '10'");

		home.select_date_output_format(1);
		test.log(Status.INFO, "dates output format was selected");

		home.enter_start_date("2000-01-01 00:00:00");
		test.log(Status.INFO, "Start date was entered");

		home.enter_end_date("2000-01-31 00:00:10");
		test.log(Status.INFO, "End date was entered");

		home.enter_date_to_generate("0.1");
		test.log(Status.INFO, "dates to generated was set to '0.1'");

		String dates1 = home.get_Dates();

		Assert.assertEquals(dates1, "");
		test.log(Status.FAIL, "Test Case 11  is failed ");

		MediaEntityModelProvider screenshot = MediaEntityBuilder.createScreenCaptureFromPath("testcase11.png").build();

		test.log(Status.INFO, "Screenshot on test FAIL", screenshot);
	}

	@Test(priority = 11)
	public void test_case12() throws IOException {
		test = extent.createTest("Test Case 12");
		test.log(Status.INFO, "Rapid Date generator Home Page loaded");
		home = new Home_Page(driver);
		home.enter_date_to_generate("10");
		test.log(Status.INFO, "dates to generate was set to 10 ");
		home.select_date_output_format(8);
		test.log(Status.INFO, "date output format was selected");
		home.enter_custom_date_format(home.dateformat1);
		test.log(Status.INFO, "date output format was entered");
		home.enter_start_date("2000-01-01 00:00:00");
		test.log(Status.INFO, "start date was entered");
		home.enter_end_date("2000-01-31 00:00:10");
		test.log(Status.INFO, "End date was entered");
		home.enter_end_date("a");
		test.log(Status.INFO, "start date was set to A ");
		home.enter_start_date("a");
		test.log(Status.INFO, "end date was set to A ");
		String dates1 = home.get_Dates();

		Assert.assertEquals(dates1, "");
		test.log(Status.FAIL, "Test Case 12  is failed ");

		MediaEntityModelProvider screenshot = MediaEntityBuilder.createScreenCaptureFromPath("testcase12.png").build();

		test.log(Status.INFO, "Screenshot on test FAIL", screenshot);
	}

	@Test(priority = 13)
	public void test_case13() throws IOException {
		test = extent.createTest("Test Case 13");
		test.log(Status.INFO, "Rapid Date generator Home Page loaded");
		home = new Home_Page(driver);
		home.enter_date_to_generate("10");
		test.log(Status.INFO, "dates to generate was set to 10 ");

		home.select_date_output_format(5);
		test.log(Status.INFO, "dates output format was selected ");

		home.enter_start_date("2000-01-01 25:00:00");
		test.log(Status.INFO, "Start date was entered");

		home.enter_end_date("2000-01-31 00:00:10");
		test.log(Status.INFO, "End date was entered ");

		String dates1 = home.get_Dates();

		Assert.assertEquals(dates1, "");
		test.log(Status.FAIL, "Test Case 13  is failed ");

		MediaEntityModelProvider screenshot = MediaEntityBuilder.createScreenCaptureFromPath("testcase13.png").build();

		test.log(Status.INFO, "Screenshot on test FAIL", screenshot);
	}

	@Test(priority = 13)
	public void test_case14() throws IOException {
		test = extent.createTest("Test Case 14");
		test.log(Status.INFO, "Rapid Date generator Home Page loaded");
		home = new Home_Page(driver);
		home.enter_date_to_generate("10");
		test.log(Status.INFO, "dates to generate was set to 10");
		home.select_date_output_format(5);
		test.log(Status.INFO, "date output format was selected");
		home.enter_start_date("2000-01-01 00:00:00");
		test.log(Status.INFO, "start date was selected");
		home.enter_end_date("2000-01-31 00:00:10");
		test.log(Status.INFO, "end date was selected");
		home.enter_end_date("");
		test.log(Status.INFO, "end date was set to null");
		home.enter_start_date("");
		test.log(Status.INFO, "start date was set to null");
		String dates1 = home.get_Dates();

		Assert.assertEquals(dates1, "");
		test.log(Status.FAIL, "Test Case 14  is failed ");

		MediaEntityModelProvider screenshot = MediaEntityBuilder.createScreenCaptureFromPath("testcase14.png").build();

		test.log(Status.INFO, "Screenshot on test FAIL", screenshot);
	}

	@Test(priority = 14)
	public void test_case15() throws IOException {
		test = extent.createTest("Test Case 15");
		test.log(Status.INFO, "Rapid Date generator Home Page loaded");
		home = new Home_Page(driver);
		home.enter_date_to_generate("10");
		test.log(Status.INFO, "dates to generate was set to 10 ");

		home.select_date_output_format(6);
		test.log(Status.INFO, "date output format was selected ");

		results = check_dates(get_date_as_list(home.get_Dates()), home.dateformat6);

		Assert.assertTrue(check_HashMap(results));
		test.log(Status.FAIL, "Test Case 15  is failed ");

		MediaEntityModelProvider screenshot = MediaEntityBuilder.createScreenCaptureFromPath("testcase15.png").build();

		test.log(Status.INFO, "Screenshot on test FAIL", screenshot);
	}

	@Test(priority = 15)
	public void test_case16() throws IOException {
		test = extent.createTest("Test Case 16");
		test.log(Status.INFO, "Rapid Date generator Home Page loaded");
		home = new Home_Page(driver);
		home.enter_date_to_generate("2");
		test.log(Status.INFO, "dates to generate was set to 2");
		home.select_date_output_format(2);
		test.log(Status.INFO, "date output format was selected");
		home.enter_start_date("31-01-1999");
		test.log(Status.INFO, "start date was selectee");
		home.enter_end_date("01-01-2002");
		test.log(Status.INFO, "end date was selected");
		home.enter_date_to_generate("20");
		test.log(Status.INFO, "date to generate was changed to 20");
		int count = get_results_count(home.get_Dates());
		System.out.println("number of dates " + count);
		Assert.assertTrue((count > 3));
		test.log(Status.FAIL, "Test Case 16  is failed ");

		MediaEntityModelProvider screenshot = MediaEntityBuilder.createScreenCaptureFromPath("testcase16.png").build();

		test.log(Status.INFO, "Screenshot on test FAIL", screenshot);

	}

	@Test(priority = 16)
	public void test_case17() throws IOException {
		test = extent.createTest("Test Case 17");
		test.log(Status.INFO, "Rapid Date generator Home Page loaded");
		home = new Home_Page(driver);
		test.log(Status.INFO, "dates to genrate was set to 10");
		home.enter_date_to_generate("10");
		test.log(Status.INFO, "date output format was selected");
		home.select_date_output_format(2);
		test.log(Status.INFO, "date output format was entered");
		home.enter_start_date("31-01-1999");
		test.log(Status.INFO, "start date was entered");
		home.enter_end_date("01-01-2002");
		test.log(Status.INFO, "end date was entered");
		home.enter_date_to_generate("0");
		test.log(Status.INFO, "dates to generate was changed to 0");
		String dates1 = home.get_Dates();

		Assert.assertEquals(dates1, "");
		test.log(Status.FAIL, "Test Case 17  is failed ");

		MediaEntityModelProvider screenshot = MediaEntityBuilder.createScreenCaptureFromPath("testcase17.png").build();

		test.log(Status.INFO, "Screenshot on test FAIL", screenshot);
	}

	@Test(priority = 17)
	public void test_case18() throws IOException {
		test = extent.createTest("Test Case 18");
		test.log(Status.INFO, "Rapid Date generator Home Page loaded");
		home = new Home_Page(driver);
		test.log(Status.INFO, "dates to generate was set to 10");
		home.enter_date_to_generate("10");
		test.log(Status.INFO, "date output format was selected");
		home.select_date_output_format(4);
		test.log(Status.INFO, "start date was set");
		home.enter_start_date("2000-02-31 00:00:00");
		test.log(Status.INFO, "end date was set");
		home.enter_end_date("000-04-31 00:00:00");
		test.log(Status.INFO, "end date was changed");
		home.enter_end_date("300-04-31 00:00:00");

		String dates1 = home.get_Dates();

		Assert.assertEquals(dates1, "");
		test.log(Status.FAIL, "Test Case 18  is failed ");

		MediaEntityModelProvider screenshot = MediaEntityBuilder.createScreenCaptureFromPath("testcase18.png").build();

		test.log(Status.INFO, "Screenshot on test FAIL", screenshot);

	}

	@Test(priority = 18)
	public void test_case19() throws IOException {
		test = extent.createTest("Test Case 19");
		test.log(Status.INFO, "Rapid Date generator Home Page loaded");
		home = new Home_Page(driver);
		test.log(Status.INFO, "dates to generate was set to 10");
		home.enter_date_to_generate("10");
		test.log(Status.INFO, "date output format was selected");
		home.select_date_output_format(4);
		test.log(Status.INFO, "date output format was entered");
		home.enter_start_date("2000-02-31 00:00:00");
		test.log(Status.INFO, "start date was entered");
		home.enter_end_date("000-04-31 00:00:00");
		test.log(Status.INFO, "end date was entered");
		home.enter_end_date("");
		test.log(Status.INFO, "end date was set to null");
		home.enter_start_date("");
		test.log(Status.INFO, "start date was set to null ");
		String dates1 = home.get_Dates();

		Assert.assertEquals(dates1, "");
		test.log(Status.FAIL, "Test Case 18  is failed ");

		MediaEntityModelProvider screenshot = MediaEntityBuilder.createScreenCaptureFromPath("testcase19.png").build();

		test.log(Status.INFO, "Screenshot on test FAIL", screenshot);
	}

	public String captureScreen() throws IOException {
		TakesScreenshot screen = (TakesScreenshot) driver;
		File src = screen.getScreenshotAs(OutputType.FILE);
		String dest = "D://Automation_Reports//screenshots//" + getcurrentdateandtime() + ".png";
		File target = new File(dest);
		FileUtils.copyFile(src, target);
		return dest;
	}

	public String getcurrentdateandtime() {
		String str = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss:SSS");
			Date date = new Date();
			str = dateFormat.format(date);
			str = str.replace(" ", "").replaceAll("/", "").replaceAll(":", "");
		} catch (Exception e) {

		}
		return str;
	}

	@AfterClass
	public void tear_down() {

		extent.flush();

		if (driver != null) {
			driver.close();
			driver.quit();
		}

	}

}
