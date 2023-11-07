package packtflow4;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ExtentSparkReporterConfig;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class TestCase4 {
	
	private static final TimeUnit SECONDS = null;

	// Web Driver
	public static WebDriver driver;

	// Extent report
	ExtentReports extent;
	ExtentSparkReporter Reporter;

	@BeforeClass
	public void beforeClass() throws IOException {

		// current date and time
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
		LocalDateTime now = LocalDateTime.now();
		String date = dtf.format(now);
		String filename;

		FileReader reader = new FileReader("UserData.properties");
		Properties prop = new Properties();
		prop.load(reader);

		String integration = prop.getProperty("Packt");
		String testername = prop.getProperty("tester");
		filename = integration + "PacktTestCase" + date + "_" + testername + ".html";

		// Extent report
		extent = new ExtentReports();
		Reporter = new ExtentSparkReporter("Reports//" + filename);

		// ReportInitiator
		extent.attachReporter(Reporter);
		Reporter.config(
				ExtentSparkReporterConfig.builder().theme(Theme.DARK).documentTitle("Packt Report for " + integration)
						.reportName("Integration: " + integration + "   /   Flow: Packt Flows").build());
	}

	@BeforeMethod
	public void beforeTest() throws IOException {

		FileReader reader = new FileReader("UserData.properties");
		Properties prop = new Properties();
		prop.load(reader);

		// ChromeInitiator
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "\\chromedriver\\chromedriver.exe"); // driver location
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		DesiredCapabilities cp = new DesiredCapabilities();
		cp.setCapability(ChromeOptions.CAPABILITY, options);
		options.merge(cp);
		options.setHeadless(false); // flase - with browser / true - without browser
		driver = new ChromeDriver(options);
		String url = prop.getProperty("packtturl");
		System.out.println(url);
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

	}
	
//*********************************************************************************************	
	
	@Test(priority = 0)
	  public void TestCase001() throws InterruptedException, IOException {
		
      FileReader reader=new FileReader("UserData.properties"); 
      Properties prop=new Properties(); 
      prop.load(reader);
      
      String ValidUsername = prop.getProperty("UserName");
      String ValidPassword = prop.getProperty("PassworD");


		  ExtentTest test = extent.createTest("check that the top nav bar, and their sub options go to the correct pages for (Library dropdown)");
		  
		  String url = driver.getCurrentUrl();
		  Thread.sleep(5000);
		  
		  test.info("User signin the application");
		  
		  WebElement Signin = driver.findElement(By.xpath("//a[@class='style-1 nav-link']"));
		  try {
			  test.info("User click on signin link");
			  Signin.click();
			  test.pass("Able to click on signin link");
			  
		  }
        catch(Exception e) {
      	  System.out.println("Unable to click the signin link");
      	  test.fail("Able to click on signin link");
        }
		  
		WebElement Username = driver.findElement(By.xpath("//input[@id='inline-form-input-username']"));
        WebElement password = driver.findElement(By.xpath("//input[@id='inline-form-input-password']"));
        WebElement Sigin_btn = driver.findElement(By.xpath("//button[@class='login-page__main__container__login__form__button__login']"));
        
        try {
      	  test.info("User enter username and pass and click on signing button");
      	  test.info("Credentials used in this scenarios : "+ValidUsername+"/"+ValidPassword);
      	  System.out.println("Credentials used in this scenarios : "+ValidUsername+"/"+ValidPassword);
      	  test.info("User eneter username and password: "+ValidUsername+"/"+ValidPassword);
      	  Username.sendKeys(ValidUsername);
      	  Thread.sleep(3000);
      	  password.sendKeys(ValidPassword);
      	  Thread.sleep(3000);
      	  Sigin_btn.click();
      	  
      	  Thread.sleep(20000);
      	  String expectedtitle="Packt Subscription | Advance your knowledge in tech";
      	  test.info("Validating correct of the page");
  		  
  		  if ( driver.getTitle().equals(expectedtitle)) {
                System.out.println("Navigated to the correct page: Home page" );
                test.pass("Navigated to correct page");
            } else {
                System.out.println("Navigation test failed " );
                test.fail("Unable to navigate at correct page");
            }	 
      	  
     	  
        }
        
        catch(Exception e) {
      	  System.out.println("Something went wrong in testcase03");
 		      test.fail("Something went wrong in TestCase03, unable to login");
        }
        
        test.info("that will click browse In the top nav, then click view all books. This should take you to the browse page. Click to clear any filters that are already set, and then click to set the 2021 filter for publication date.");
        WebElement P1 = driver.findElement(By.xpath("//a[@class='nav-link']"));
        Thread.sleep(2000);
        P1.click();
        //P1.click();)
        
       String currenturl = driver.getCurrentUrl();
       System.out.println(currenturl);
       
       //reset filter
       Thread.sleep(2000);
       WebElement resetfilter = driver.findElement(By.xpath("//button[@class='reset-button']"));
       resetfilter.click();
       

  
        //CLicking on publish year
       Thread.sleep(2000);
        WebElement publishyear = driver.findElement(By.xpath("//div[contains(.,'Published Year') and @class='header']"));
        publishyear.click();
        
        //set 2021 filter
        Thread.sleep(2000);
        WebElement filter = driver.findElement(By.xpath("//div[@class='value' and contains(.,'2021')]"));
        filter.click();
        
        Thread.sleep(2000);
        String currenturlfilter = driver.getCurrentUrl();
        System.out.println(currenturlfilter);
  
//*************************** Python ************************************
        String INPUT1 = prop.getProperty("input1");
        
        //enter input in search bar
        Thread.sleep(2000);
        WebElement searchbar = driver.findElement(By.xpath("//input[@class='mr-sm-2 form-control']"));
        Thread.sleep(5000);   
        Actions actions = new Actions(driver);
        
       //Type text, press Enter, and then release Enter
        actions.sendKeys(searchbar, INPUT1).sendKeys(Keys.ENTER).perform();
        Thread.sleep(5000);
        
     // Verify that all titles found include the search text
        java.util.List<WebElement> titles = driver.findElements(By.xpath("//div[@class='product-title mb-3']"));
        
        for (WebElement title : titles) {
            if (!title.getText().contains("Python")) {
                System.out.println("FAIL: The title \"" + title.getText() + "\" does not contain the search text \"Python\"");
            }
            else {
            	System.out.println("Python titles is present");
            	test.pass("Python is present in search text");
            }
            
        }
        
//**************************** Paint ***************************************
        
System.out.println("**************************************************************************");        
        
        String INPUT2 = prop.getProperty("input2");
        
        //enter input in search bar
        Thread.sleep(2000);
        WebElement searchbar2 = driver.findElement(By.xpath("//input[@class='mr-sm-2 form-control']"));
        Thread.sleep(5000);   
       
        
      //Type text, press Enter, and then release Enter
        actions.sendKeys(searchbar2, INPUT2).sendKeys(Keys.ENTER).perform();
        Thread.sleep(5000);
        
        
        
        // Verify that all titles found include the search text
           java.util.List<WebElement> titles2 = driver.findElements(By.xpath("//div[@class='product-title mb-3']"));
           
           for (WebElement title : titles2) {
               if (!title.getText().contains("Paint")) {
                   System.out.println("FAIL: The title \"" + title.getText() + "\" does not contain the search text \"Paint\"");
               }
               else {
               	System.out.println("Paint titles is present");
               	test.pass("Paint is present in search text");
               }
           }
        
    
//**************************** Secure ***************************************
           
System.out.println("**************************************************************************");        
    
    String INPUT3 = prop.getProperty("input3");
    
    //enter input in search bar
    Thread.sleep(2000);
    WebElement searchbar3 = driver.findElement(By.xpath("//input[@class='mr-sm-2 form-control']"));
    Thread.sleep(5000);   
   
    
  //Type text, press Enter, and then release Enter
    actions.sendKeys(searchbar3, INPUT3).sendKeys(Keys.ENTER).perform();
    Thread.sleep(5000);
    
    
    
    // Verify that all titles found include the search text
       java.util.List<WebElement> titles3 = driver.findElements(By.xpath("//div[@class='product-title mb-3']"));
       
       for (WebElement title : titles3) {
           if (!title.getText().contains("Secure")) {
               System.out.println("FAIL: The title \"" + title.getText() + "\" does not contain the search text \"Secure\"");
           }
           else {
           	System.out.println("Secure titles is present");
           	test.pass("Secure is present in search text");
           }
       }
	
	
//**************************** Secure ***************************************
       
	System.out.println("**************************************************************************");        
	    
	    String INPUT4 = prop.getProperty("input4");
	    
	    //enter input in search bar
	    Thread.sleep(2000);
	    WebElement searchbar4 = driver.findElement(By.xpath("//input[@class='mr-sm-2 form-control']"));
	    Thread.sleep(5000);   
	   
	    
	  //Type text, press Enter, and then release Enter
	    actions.sendKeys(searchbar4, INPUT4).sendKeys(Keys.ENTER).perform();
	    Thread.sleep(5000);
	    
	    
	    
	    // Verify that all titles found include the search text
	       java.util.List<WebElement> titles4 = driver.findElements(By.xpath("//div[@class='product-title mb-3']"));
	       
	       for (WebElement title : titles4) {
	           if (!title.getText().contains("Tableau")) {
	               System.out.println("FAIL: The title \"" + title.getText() + "\" does not contain the search text \"Tableau\"");
	           }
	           else {
	           	System.out.println("Tableau titles is present");
	           	test.pass("Tableau is present in search text");
	           }
	       }
		}
	
        
        
        	
        
        
        @AfterMethod
    	public void afterTest() {
    		// CloseBrowser
    		driver.quit();
    	}

    	@AfterClass
    	public void afterClass() {

    		// ReportTerminator
    		extent.flush();

    	}

}
