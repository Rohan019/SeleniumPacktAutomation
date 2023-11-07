package packttestflow;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.Color;
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
import org.openqa.selenium.support.Color;

public class TestCase1 {
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
		String url = prop.getProperty("packturl");
		System.out.println(url);
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

	}
	
	 @Test(priority = 0)
	  public void TestCase001() throws InterruptedException {
	  
	  ExtentTest test = extent.createTest("Verify Positions/Color/text of the element");
	  
	  String url = driver.getCurrentUrl(); 
	  Thread.sleep(5000);
	  
	//********************************* Position Of The Element ****************************************
	  
			//Position 1 (With assert)
			  test.info("Start Free Trial Button X & Y Positions"); 
			  WebElement Start_Free_Trial_btn = driver.findElement(By.xpath("//a[@class='button']"));
			  Rectangle dimensions = Start_Free_Trial_btn.getRect();  
		
			  int expectedX = 594; // Specify the expected x-coordinate
	          int expectedY = 329; // Specify the expected y-coordinate
	          assert Start_Free_Trial_btn.getLocation().getX() == expectedX;
	          assert Start_Free_Trial_btn.getLocation().getY() == expectedY;
	          test.pass("Start Free Trial Button X Position :"+expectedX+
	        		 " Start Free Trial Button X Position :"+expectedY);
	  
	          System.out.println(" Positin passed!");
	          
	      	//Position 2 (With if else)
			  test.info("Advanced search Button X & Y Positions"); 
			  try {
			  WebElement Advanced_search_btn = driver.findElement(By.xpath("//button[@type='button' and @class='button--more-info btn btn-primary advanced-search-button']"));
			  Rectangle dimensions2 = Advanced_search_btn.getRect(); 
			  System.out.println(dimensions2);
			  
			  // Get the position of the element
	          Point elementLocation = Advanced_search_btn.getLocation();
	          int elementX = elementLocation.getX(); // X-coordinate of the element
	          int elementY = elementLocation.getY(); // Y-coordinate of the element
	          
	          System.out.println(elementX);
	          System.out.println(elementY);
	          

	          // Specify the expected X and Y coordinates
	          int expectedX1 = 325; 
	          int expectedY1 = 15;
	          
	          if (elementX == expectedX1 && elementY == expectedY1) {
	        	  test.info("Comparing Expected and actual Position");
	              System.out.println("Element position test passed. Actual position (X, Y): "
	              		+ "(" + elementX + ", " + elementY + ")");
	              test.pass("Position is Matched for Adavanced search");
	          } else {
	              System.out.println("Element position test failed. Expected (X, Y):"
	              		+ " (" + expectedX + ", " + expectedY + "), Actual (X, Y):"
	              				+ " (" + elementX + ", " + elementY + ")");
	              test.fail("Position is not matched");
	          }
	          
			  }
			  catch(Exception e) {
				  System.out.println("Test failed: " + e.getMessage());
				  test.fail("Position is not matched");
			  }
			      		
     //**************************** Color of the element ****************************************
			  
	  WebElement Color1 = driver.findElement(By.xpath("//h5[@class='text-white']/a"));
	  String backcolor = Color1.getCssValue("color");
	  System.out.println(backcolor);
	  String hexcolor = Color.fromString(backcolor).asHex();
	  System.out.println(hexcolor);
	  if(hexcolor.equals("#4ab9d5")) {
		  System.out.println("(edit your preference )color element is matched");
		  test.pass("(edit your preference) color element is matched");
	  }
	  else {
		  System.out.println("(edit your preference )color element is not matched");
		  test.fail("(edit your preference )color element is not matched");
		  
	  }
	  
	  
	  WebElement Color2 = driver.findElement(By.xpath("//a[@class='button']"));
	  String backcolor2 = Color2.getCssValue("color");
	  System.out.println(backcolor2);
	  String hexcolor2 = Color.fromString(backcolor2).asHex();
	  System.out.println(hexcolor2);
	  if(hexcolor.equals("##ffffff")) {
		  System.out.println("Start FREE trial button )color element is matched");
		  test.pass("(Start FREE trial button ) color element is matched");
	  }
	  else {
		  System.out.println("(Start FREE trial button  )color element is not matched");
		  test.fail("(Start FREE trial button  )color element is not matched");
		  
	  }
	  
	  
	  //******************************* Text of the element ************************************
	  
	//text1
	  test.info("Verify text (Advanced your knowldge in tech)");
	  try {
		    
            // Text Validation Test
            WebElement textElement = driver.findElement(By.xpath("//h2[contains(text(),'Advance your knowledge in tech')]")); // Replace with the actual element ID
            String expectedText = "Advance your knowledge in tech"; // Specify the expected text

            // Get the actual text from the element
            String actualText = textElement.getText();

            // Compare the actual text with the expected text
            if (actualText.equals(expectedText)) {
            	test.info("Comparing Expected and actual text");
                System.out.println("Text validation passed. Actual text: " + actualText);
                test.pass("Text is Matched :"+actualText);
            } else {
                System.out.println("Text validation failed. Expected: " + expectedText + ", Actual: " + actualText);
                test.fail("Text is not matched");
            }
        } catch (Exception e) {
            System.out.println("Test failed: " + e.getMessage());
            test.fail("Text is not matched");
        }
	  
	  
	//text2
	  test.info("Verify text (Your Suggested Titles)");
	  try {
		    
            // Text Validation Test
            WebElement textElement3 = driver.findElement(By.xpath("//h2[contains(text(),'Your Suggested Titles')]")); // Replace with the actual element ID
            String expectedText3 = "Your Suggested Titles"; // Specify the expected text

            // Get the actual text from the element
            Thread.sleep(3000);
            String actualText3 = textElement3.getText();

            // Compare the actual text with the expected text
            if (actualText3.equals(expectedText3)) {
            	test.info("Comparing Expected and actual text");
                System.out.println("Text validation passed. Actual text: " + actualText3);
                test.pass("Text is Matched :"+actualText3);
            } else {
                System.out.println("Text validation failed. Expected: " + expectedText3 + ", Actual: " + actualText3);
                test.fail("Text is not matched");
            }
        } catch (Exception e) {
            System.out.println("Test failed: " + e.getMessage());
            test.fail("Text is not matched");
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
