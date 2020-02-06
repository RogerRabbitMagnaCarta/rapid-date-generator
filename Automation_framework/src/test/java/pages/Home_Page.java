package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class Home_Page {
	
	WebDriver driver;
	
	By dates_generated = By.xpath("//textarea[@class='data randomjson']");
	
	By dates_to_generate = By.xpath("//input[@id='option-count-147fe348']");
	
	By date_output_format = By.xpath("//select[@id='option-format-147fe348']");
	
	By custom_date_format = By.xpath("//input[@id='option-custom-format-147fe348']");
	
	By start_date = By.xpath("//input[@id='option-start-147fe348']");
	
	By end_date = By.xpath("//input[@id='option-end-147fe348']");
	
	By generate_random_dates = By.xpath("//button[@class='btn1 btn-large generatejson']");
	
	public static final String dateformat1 = "YYYY-MM-DD hh:mm:ss";
	public static final String date1= "MM-dd-yyyy HH:mm:ss";
	public static final String dateformat2 = "YYYY-DD-MM hh:mm:ss";
	public static final String date2 = "yyyy-dd-MM HH:mm:ss";
	public static final String dateformat3 = "MM-DD-YYYY hh:mm:ss";
	public static final String date3="MM-dd-yyyy HH:mm:ss";
	public static final String dateformat4 = "ISO 8601";
	public static final String dateformat5 = "Year Month Date hh:mm:ss";
	public static final String date5="yyyy MMMMM d HH:mm:ss";
	public static final String dateformat6 = "Year Date Month hh:mm:ss";
	public static final String date6="yyyy d MMMMM  HH:mm:ss";
	public static final String dateformat7 = "Month Date Year hh:mm:ss";
	public static final String dateformat8 = "Custom date format";
	//TC_05
	public static final String customdateformat1="yyyy-MMMMM";
	public static final String custom_date1="YYYY-month";
	//TC_06
	public static final String customdateformat2="yyyy-dd-MMMMM";
	public static final String custom_date2="YYYY-d-month";
	//Tc_07
	public static final String customdateformat3="MMMMM";
	public static final String custom_date3="month";
	//TC_08
	public static final String customdateformat4="yy-MMMMM";
	public static final String custom_date4="YY-month";
	//TC_09
	public static final String customdateformat5="yy-MMMMM-d";
	public static final String custom_date5="YY-month-d";
	//YYYY-DD-month
	
	/*
	 * 1 = YYYY-MM-DD hh:mm:ss 
	 * 2=  YYYY-DD-MM hh:mm:ss
	 * 3 = MM-DD-YYYY hh:mm:ss
	 * 4 = ISO 8601
	 * 5= Year Month Date hh:mm:ss 
	 * 6= Year Date Month hh:mm:ss 
	 * 7 = Month Date Year hh:mm:ss 
	 * 8 = 
	 */
	
	public Home_Page(WebDriver _driver) {
		driver = _driver;
	}
	
	public void enter_date_to_generate(String count) {
		driver.findElement(dates_to_generate).clear();
		driver.findElement(dates_to_generate).sendKeys(count);
	}
	
	
	
	public void select_date_output_format(int i) {
		
		Select dropdown = new Select(driver.findElement(date_output_format));
		switch(i) {
		case 1:
			dropdown.selectByVisibleText(dateformat1);
			break;
		case 2:
			dropdown.selectByVisibleText(dateformat2);
			break;
		case 3:
			dropdown.selectByVisibleText(dateformat3);
			break;
		case 4:
			dropdown.selectByVisibleText(dateformat4);
			break;
		case 5:
			dropdown.selectByVisibleText(dateformat5);
			break;
		case 6:
			dropdown.selectByVisibleText(dateformat6);
			break;
		case 7:
			dropdown.selectByVisibleText(dateformat7);
			break;
		case 8:
			dropdown.selectByVisibleText(dateformat8);
			break;
			
		}
	}	
		
	public String get_Dates() {
		String getdate = driver.findElement(dates_generated).getAttribute("value");
		return getdate;
	}
	
	public  void enter_start_date(String date_to_enter) {
		driver.findElement(start_date).clear();
		driver.findElement(start_date).sendKeys(date_to_enter);
	}
	
	public  void enter_end_date(String date_to_enter) {
		driver.findElement(end_date).clear();
		driver.findElement(end_date).sendKeys(date_to_enter);
	}
	
	public void enter_custom_date_format(String format_to_enter) {
		driver.findElement(custom_date_format).clear();
		driver.findElement(custom_date_format).sendKeys(format_to_enter);
	}
}















