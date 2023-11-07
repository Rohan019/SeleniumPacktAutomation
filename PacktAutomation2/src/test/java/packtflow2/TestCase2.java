package packtflow2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
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

public class TestCase2 {
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
	
	
	@Test(priority = 0)
	  public void TestCase001() throws InterruptedException {

		  ExtentTest test = extent.createTest("check that the top nav bar, and their sub options go to the correct pages");
		  
		  String url = driver.getCurrentUrl(); 
		  Thread.sleep(5000);
		  
		  test.info("Go to browser library and verify user is landing on correct page");
		  WebElement topNavBar = driver.findElement(By.xpath("//a[@class='nav-link']"));
		  
		  topNavBar.click();
		  Thread.sleep(5000);
		  
		  String expectedURL = "https://subscription.packtpub.com/search";
		  String expectedtitle = "Search | Packt Subscription";
		  
		  test.info("Validating titles and url of pages");
		  
		  if (driver.getCurrentUrl().equals(expectedURL) && driver.getTitle().equals(expectedtitle)) {
              System.out.println("Navigated to the correct page: "+ "Browse library" );
              test.pass("Navigated to correct page for (Browse library)");
          } else {
              System.out.println("Navigation test failed for: " + "Browse library"  );
              test.fail("Unable to navigate at correct page");
          }
		   	
	}
	

	@Test(priority = 1)
	  public void TestCase002() throws InterruptedException {

		  ExtentTest test = extent.createTest("check that the top nav bar, and their sub options go to the correct pages");
		  
		  String url = driver.getCurrentUrl(); 
		  Thread.sleep(5000);
		  
		  test.info("Go to Advanced search and verify user is landing on correct page");
		  WebElement topNavBar = driver.findElement(By.xpath("//button[@type='button' and @class='button--more-info btn btn-primary advanced-search-button']"));
		  
		  topNavBar.click();
		  Thread.sleep(5000);
		  
		  String expectedURL = "https://subscription.packtpub.com/advanced-search";
		  String expectedtitle = "Advanced Search | Packt Subscription";
		  
		  test.info("Validating titles and url of pages");
		  
		  if (driver.getCurrentUrl().equals(expectedURL) && driver.getTitle().equals(expectedtitle)) {
              System.out.println("Navigated to the correct page: "+ "Advanced search" );
              test.pass("Navigated to correct page for (Advanced search)");
          } else {
              System.out.println("Navigation test failed for: " + "Advanced search"  );
              test.fail("Unable to navigate at correct page");
          }	  
		   	
	}
	
	@Test(priority = 2)
	  public void TestCase003() throws InterruptedException, IOException {
		
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
          
      
       // Find the dropdown containing anchor tags
          WebElement dropdown = driver.findElement(By.xpath("//a[@id='library-dropdown']"));
          
          dropdown.click();
          Thread.sleep(5000);
       
 
          
//*********************** Home Option *********************************
         
          
         try {
        	 test.info("Verify Home page is landing in correct page");
        	 WebElement dropdown1 = driver.findElement(By.xpath("//a[contains(.,'Home')]"));         
        	 dropdown1.click();
        	 Thread.sleep(5000);
        	 String expectedpageurl = "https://subscription.packtpub.com/";
        	 String expectedtitle = "Packt Subscription | Advance your knowledge in tech";
   		  
   		  test.info("Validating titles and url of pages");
   		  
   		  if (driver.getCurrentUrl().equals(expectedpageurl) && driver.getTitle().equals(expectedtitle)) {
                 System.out.println("Navigated to the correct page: "+ "Home Option" );
                 test.pass("Navigated to correct page for (Home Option)");
             } else {
                 System.out.println("Navigation test failed for: " + "Home Option"  );
                 test.fail("Unable to navigate at correct page");
             }	  
        	 
        	 
         }
         catch (Exception e) {
        	 System.out.println("Something went wrong unable to click the home element" );
             test.fail("Something went wrong unable to click the home element");
         } 
	
//************************* Playlists Option ******************************
         
         WebElement element = driver.findElement(By.xpath("//a[@id='library-dropdown']"));
         try {
             element.click();
         } catch (StaleElementReferenceException e) {
             element = driver.findElement(By.xpath("//a[@id='library-dropdown']"));
             element.click();
         }
       
         
         try {
        	 Thread.sleep(5000);
        	 test.info("Verify Playlist page is landing in correct page");
        	 WebElement dropdown2 = driver.findElement(By.xpath("//a[contains(.,'Playlists')]"));
        	 dropdown2.click();
        	 Thread.sleep(5000);
        	 String expectedpageurl = "https://subscription.packtpub.com/playlists";
        	 String expectedtitle = "Playlists | Packt Subscription";
   		  
   		  test.info("Validating titles and url of pages");
   		  
   		  if (driver.getCurrentUrl().equals(expectedpageurl) && driver.getTitle().equals(expectedtitle)) {
                 System.out.println("Navigated to the correct page: "+ "playlists option" );
                 test.pass("Navigated to correct page for (playlists option)");
             } else {
                 System.out.println("Navigation test failed for: " + "playlists option"  );
                 test.fail("Unable to navigate at correct page");
             }	  
        	 
        	 
         }
         catch (Exception e){
        	 System.out.println("Something went wrong unable to click the playlists option element" );
             test.fail("Something went wrong unable to click the playlists option element");
         } 
         
         // Navigate back to the first page
         driver.navigate().back();


         
 //************************* Book mark Option ******************************  
        
         try {
             element.click();
         } catch (StaleElementReferenceException e) {
             element = driver.findElement(By.xpath("//a[@id='library-dropdown']"));
             element.click();
         }
         
         try {
        	 Thread.sleep(5000);
        	 test.info("Verify Notes option page is landing in correct page");
        	 WebElement dropdown3 = driver.findElement(By.xpath("//a[contains(.,'Bookmarks')]"));
        	 dropdown3.click();
        	 Thread.sleep(5000);
        	 String expectedpageurl = "https://subscription.packtpub.com/bookmarks";
        	 String expectedtitle = "Bookmarks | Packt Subscription";
   		  
   		  test.info("Validating titles and url of pages");
   		  
   		  if (driver.getCurrentUrl().equals(expectedpageurl) && driver.getTitle().equals(expectedtitle)) {
                 System.out.println("Navigated to the correct page: "+ "Bookmarks Option" );
                 test.pass("Navigated to correct page for (Bookmarks Option)");
             } else {
                 System.out.println("Navigation test failed for: " + "Bookmarks Option"  );
                 test.fail("Unable to navigate at correct page");
             }	  
        	 
        	 
         }
         catch (Exception e) {
        	 System.out.println("Something went wrong unable to click the Bookmarks Option element" );
             test.fail("Something went wrong unable to click the Bookmarks Option element");
         } 
         
         // Navigate back to the first page
         driver.navigate().back();

         
       
         
 //************************* Notes Option ******************************  
        
         try {
        	 Thread.sleep(5000);
             element.click();
         } catch (StaleElementReferenceException e) {
             element = driver.findElement(By.xpath("//a[@id='library-dropdown']"));
             element.click();
         }
         
         try {
        	 Thread.sleep(5000);
        	 test.info("Verify Notes Option page is landing in correct page");
        	 WebElement dropdown4 = driver.findElement(By.xpath("//a[contains(.,'Notes')]"));
        	 dropdown4.click();
        	 Thread.sleep(5000);
        	 String expectedpageurl = "https://subscription.packtpub.com/notes";
        	 String expectedtitle = "Notes | Packt Subscription";
   		  
   		  test.info("Validating titles and url of pages");
   		  
   		  if (driver.getCurrentUrl().equals(expectedpageurl) && driver.getTitle().equals(expectedtitle)) {
                 System.out.println("Navigated to the correct page: "+ "Notes Option" );
                 test.pass("Navigated to correct page for (Notes Option)");
             } else {
                 System.out.println("Navigation test failed for: " + "Notes Option"  );
                 test.fail("Unable to navigate at correct page");
             }	  
        	 
        	 
         }
         catch (Exception e) {
        	 System.out.println("Something went wrong unable to click the Notes Option element" );
             test.fail("Something went wrong unable to click the Notes Option element");
         } 
         
         // Navigate back to the first page
         driver.navigate().back();

       
 //************************* Owned Option ******************************  
         
         try {
        	 Thread.sleep(5000);
             element.click();
         } catch (StaleElementReferenceException e) {
             element = driver.findElement(By.xpath("//a[@id='library-dropdown']"));
             element.click();
         }
         
         try {
        	 Thread.sleep(5000);
        	 test.info("Verify Owned Option page is landing in correct page");
        	 WebElement dropdown5 = driver.findElement(By.xpath("//a[contains(.,'Owned')]"));
        	 dropdown5.click();
        	 Thread.sleep(5000);
        	 String expectedpageurl = "https://subscription.packtpub.com/notes";
        	 String expectedtitle = "Notes | Packt Subscription";
   		  
   		  test.info("Validating titles and url of pages");
   		  
   		  if (driver.getCurrentUrl().equals(expectedpageurl) && driver.getTitle().equals(expectedtitle)) {
                 System.out.println("Navigated to the correct page: "+ "Owned Option" );
                 test.pass("Navigated to correct page for (Owned Option)");
             } else {
                 System.out.println("Navigation test failed for: " + "Owned Option"  );
                 test.fail("Unable to navigate at correct page");
             }	  
        	 
        	 
         }
         catch (Exception e) {
        	 System.out.println("Something went wrong unable to click the Owned Option element" );
             test.fail("Something went wrong unable to click the Owned Option element");
         } 
         
       
         // Navigate back to the first page
         driver.navigate().back();


 //************************* Credits Option ******************************  
                 
                 try {
                	 Thread.sleep(5000);
                     element.click();
                 } catch (StaleElementReferenceException e) {
                     element = driver.findElement(By.xpath("//a[@id='library-dropdown']"));
                     element.click();
                 }
                 
                 try {
                	 Thread.sleep(5000);
                	 test.info("Verify Credits Option page is landing in correct page");
                	 WebElement dropdown6 = driver.findElement(By.xpath("//a[contains(.,'Credits')]"));
                	 dropdown6.click();
                	 Thread.sleep(5000);
                	 String expectedpageurl = "https://subscription.packtpub.com/credits";
                	 String expectedtitle = "Credits | Packt Subscription";
           		  
           		  test.info("Validating titles and url of pages");
           		  
           		  if (driver.getCurrentUrl().equals(expectedpageurl) && driver.getTitle().equals(expectedtitle)) {
                         System.out.println("Navigated to the correct page: "+ "Credits Option" );
                         test.pass("Navigated to correct page for (Credits Option)");
                     } else {
                         System.out.println("Navigation test failed for: " + "Credits Option"  );
                         test.fail("Unable to navigate at correct page");
                     }	  
                	 
                	 
                 }
                 catch (Exception e) {
                	 System.out.println("Something went wrong unable to click the Credits Option element" );
                     test.fail("Something went wrong unable to click the Credits Option element");
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
