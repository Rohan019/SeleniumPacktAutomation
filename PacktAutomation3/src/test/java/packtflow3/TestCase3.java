package packtflow3;

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

public class TestCase3 {
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
        
        
//********************************** Causal Inference and Discovery in Python **************************************************************       
        
        test.info("Validating Causal Inference and Discovery in Python page is redirecting on correct page ");
        WebElement P1 = driver.findElement(By.xpath("//h2[contains(.,'Causal Inference and Discovery in Python')]"));
        Thread.sleep(6000);
        Actions actions = new Actions(driver);
        actions.moveToElement(P1).perform();
       


        WebElement ReadNow = driver.findElement(By.xpath("//a[@class='readNowButton btn-lg btn-block']"));
        Thread.sleep(2000);
        ReadNow.click();
        Thread.sleep(5000);
        
        Set<String> windwohandles = driver.getWindowHandles();
        Iterator<String> iterator= windwohandles.iterator();
        String parentwindow = iterator.next();
        String childwindow = iterator.next();  
        driver.switchTo().window(childwindow);  
        Thread.sleep(5000);
        
        
		
	
		  WebElement Tab = driver.findElement(By.xpath("//li[@id='tab-overview']")); 
		  Tab.click();
		  Thread.sleep(2000);
		  WebElement pagetitle = driver.findElement(By.xpath("//h4[@class='title pl-4']"));
		  System.out.println(pagetitle); 
		  Thread.sleep(10000);
		  String expectedtitle = "Preface | Causal Inference and Discovery in Python";
		  String actualTitle = driver.getTitle(); 
		  System.out.println(actualTitle);
		  
		  test.info("Comparing actual vs expected title ");
		  //w.r.t using assert
		  Assert.assertEquals(actualTitle, expectedtitle, "Page title does not match the expected title.");
		  
		  //w.r.t using if else
		  if ( actualTitle.equals(expectedtitle)) {
	            System.out.println("Displayed correct title: "+"Causal Inference and Discovery in Python" );
	            test.pass("Displayed correct title");
	        } else {
	            System.out.println("Displayed Wrong title " );
	            test.fail("Unable to Displayed correct title");
	        }
		 
		  // Close current window     
	         driver.close();
	         
//********************************** Modern Generative AI with ChatGPT and OpenAI Models **************************************************************       
	         
	         driver.switchTo().window(parentwindow);
	         Thread.sleep(5000);
	         test.info("Validating Modern Generative AI with ChatGPT and OpenAI Model page is redirecting on correct page ");
	         WebElement P2 = driver.findElement(By.xpath("//h5[contains(.,'Modern Generative AI with ChatGPT and OpenAI Model...')]"));
	         Thread.sleep(6000);
	         Actions actions2 = new Actions(driver);
	         actions2.moveToElement(P2).perform();
	         P2.click();
	       
	         Thread.sleep(2000);
	         Actions actionsread2 = new Actions(driver);
	         actionsread2.moveToElement(ReadNow).perform();
	         Thread.sleep(2000);
	         ReadNow.click();
	         Thread.sleep(10000);
	         
	         Set<String> windwohandles2 = driver.getWindowHandles();
	         Iterator<String> iterator2= windwohandles2.iterator();
	         String parentwindow2 = iterator2.next();
	         String childwindow2 = iterator2.next();  
	         driver.switchTo().window(childwindow2);  
	         Thread.sleep(7000);		
	 	
	 		  WebElement Tab2 = driver.findElement(By.xpath("//li[@id='tab-overview']")); 
	 		  Tab2.click();
	 		   		 
	 		  Thread.sleep(2000);
	 		  String expectedtitle2 = "Preface | Modern Generative AI with ChatGPT and OpenAI Models";
	 		  String actualTitle2 = driver.getTitle(); 
	 		  System.out.println(actualTitle2);
	 		  
	 		 test.info("Comparing actual vs expected title ");
	 		  //w.r.t using assert
	 		  Assert.assertEquals(actualTitle2, expectedtitle2, "Page title does not match the expected title.");
	 		  
	 		  //w.r.t using if else
	 		  if ( actualTitle2.equals(expectedtitle2)) {
	 	            System.out.println("Displayed correct title: "+"Modern Generative AI with ChatGPT and OpenAI Models" );
	 	            test.pass("Displayed correct title");
	 	        } else {
	 	            System.out.println("Displayed Wrong title " );
	 	            test.fail("Unable to Displayed correct title");
	 	        }
	 		 
	 		// Close current window     
		         driver.close(); 	         
        
		         
    
//********************************** Layered Design for Ruby on Rails Applications **************************************************************       

		         driver.switchTo().window(parentwindow);
		         Thread.sleep(5000);
		         test.info("Validating Layered Design for Ruby on Rails Applications page is redirecting on correct page ");
		         WebElement P3 = driver.findElement(By.xpath("//h5[contains(.,'Layered Design for Ruby on Rails Applications')]"));
		         Thread.sleep(6000);
		         Actions actions3 = new Actions(driver);
		         actions3.moveToElement(P3).perform();
		         P3.click();
		       
		         Thread.sleep(2000);
		         Actions actionsread3 = new Actions(driver);
		         actionsread3.moveToElement(ReadNow).perform();
		         Thread.sleep(2000);
		         ReadNow.click();
		         Thread.sleep(10000);
		         
		         Set<String> windwohandles3 = driver.getWindowHandles();
		         Iterator<String> iterator3= windwohandles3.iterator();
		         String parentwindow3 = iterator3.next();
		         String childwindow3 = iterator3.next();  
		         driver.switchTo().window(childwindow3);  
		         Thread.sleep(7000);		

		     	  WebElement Tab3 = driver.findElement(By.xpath("//li[@id='tab-overview']")); 
		     	  Tab3.click();
		     	   		 
		     	  Thread.sleep(2000);
		     	  String expectedtitle3 = "Preface | Layered Design for Ruby on Rails Applications";
		     	  String actualTitle3 = driver.getTitle(); 
		     	  System.out.println(actualTitle3);
		     	  
		     	 test.info("Comparing actual vs expected title ");
		     	  //w.r.t using assert
		     	  Assert.assertEquals(actualTitle3, expectedtitle3, "Page title does not match the expected title.");
		     	  
		     	  //w.r.t using if else
		     	  if ( actualTitle3.equals(expectedtitle3)) {
		                 System.out.println("Displayed correct title: "+"Layered Design for Ruby on Rails Applications" );
		                 test.pass("Displayed correct title");
		             } else {
		                 System.out.println("Displayed Wrong title " );
		                 test.fail("Unable to Displayed correct title");
		             }
		     	 
		     	// Close current window     
		             driver.close(); 	         
		     	
	
    
//********************************** Machine Learning Engineering with Python **************************************************************       
    
		             driver.switchTo().window(parentwindow);
		             Thread.sleep(5000);
		             test.info("Validating Machine Learning Engineering with Python page is redirecting on correct page ");
		             WebElement P4 = driver.findElement(By.xpath("//h5[contains(.,'Machine Learning Engineering with Python')]"));
		             Thread.sleep(6000);
		             Actions actions4 = new Actions(driver);
		             actions4.moveToElement(P4).perform();
		             P4.click();
		           
		             Thread.sleep(2000);
		             Actions actionsread4 = new Actions(driver);
		             actionsread4.moveToElement(ReadNow).perform();
		             Thread.sleep(2000);
		             ReadNow.click();
		             Thread.sleep(10000);
		             
		             Set<String> windwohandles4 = driver.getWindowHandles();
		             Iterator<String> iterator4= windwohandles4.iterator();
		             String parentwindow4 = iterator4.next();
		             String childwindow4 = iterator4.next();  
		             driver.switchTo().window(childwindow4);  
		             Thread.sleep(7000);		

		         	  WebElement Tab4 = driver.findElement(By.xpath("//li[@id='tab-overview']")); 
		         	  Tab4.click();
		         	   		 
		         	  Thread.sleep(2000);
		         	  String expectedtitle4 = "Preface | Machine Learning Engineering with Python";
		         	  String actualTitle4 = driver.getTitle(); 
		         	  System.out.println(actualTitle4);
		         	  
		         	 test.info("Comparing actual vs expected title ");
		         	  //w.r.t using assert
		         	  Assert.assertEquals(actualTitle4, expectedtitle4, "Page title does not match the expected title.");
		         	  
		         	  //w.r.t using if else
		         	  if ( actualTitle4.equals(expectedtitle4)) {
		                     System.out.println("Displayed correct title: "+"Machine Learning Engineering with Python" );
		                     test.pass("Displayed correct title");
		                 } else {
		                     System.out.println("Displayed Wrong title " );
		                     test.fail("Unable to Displayed correct title");
		                 }
		         	 
		         	// Close current window     
		                 driver.close(); 	         
		         	
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
