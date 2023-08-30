package Macys.com.macysSelenium;

import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.Iterator;
import java.time.Duration;
import java.util.Set;

public class MacysAutomateTest {
   
    final static String macysUrl = "https://www.macys.com/shop/for-the-home?id=22672&cm_sp=intl_hdr-_-home-_-\r\n" + 
    		"22672_home";
    final static String productName = "Men Watches";
    WebDriver driver;
    
    @BeforeSuite
    public void setUpBrowser() {
        try {
            System.out.println("Setting Up browser...");

            WebDriverManager.edgedriver().setup();
            EdgeOptions options = new EdgeOptions();

            options.addArguments("--disable-blink-features=AutomationControlled");
            String[] arr = {"enable-automation"};
            options.setExperimentalOption("excludeSwitches", arr);
            options.setExperimentalOption("useAutomationExtension", false);


            driver = new EdgeDriver(options);

            System.out.println("Browser started successfully...");
        } catch (Exception e) {
            System.out.println("Error in MacysAutomateTest>>setUpBrowser: " + e.getMessage());
            e.printStackTrace(); // Print the full stack trace for debugging
        }
    }

    
    @Test
    public void macyTestCases() throws InterruptedException {
        try {
            /***
                Navigate to macys.com
             ***/
        	
        	System.out.println("Navigating to macys's homepage...");
            
            driver.get(macysUrl);

            //Maximize the window size
            driver.manage().window().maximize();

            

            //Close pop-up
            
        System.out.println("Closing PopUp Window...");
        Utility.getElement(driver,"id","bx-close-inside-2047250",true).click();
         Thread.sleep(1000);
         System.out.println("Popup closed...");
         
            

  
            /*
                Search Product
             */

            System.out.println("Searching for searchbox...");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Wait for up to 10 seconds
            WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.id("globalSearchInputField")));
            System.out.println("Searching for searchbox2...");
            searchBox.click();
            System.out.println("Search box clicked...");


            searchBox.sendKeys(productName);

            searchBox.submit();

            String expectedTitle = productName+" - Macy's";
            String actualTitle = driver.getTitle();

            
              //  Add product to Bag
             
            //Scroll window

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scroll(0,1000)");


            //select the product
            System.out.println("Selecting the product...");
            
         // Add WebDriverWait code here
            WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement productLink = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"13463032\"]/div[2]/div/a")));
            productLink.click();


            String expectedResult = "Men's Black-Tone Stainless Steel Bracelet Watch 46mm Gift Set";
            String actualResult = driver.getTitle();
            System.out.println("Product selected title...");
            
         
            
            
         // Add to cart
            System.out.println("Adding Product to Bag.........");

            Set<String> ids = driver.getWindowHandles();
            Iterator<String> it = ids.iterator();
            String parentId = it.next();	
            String childId = it.next();
            driver.switchTo().window(childId);
  
            Thread.sleep(5000);

//            WebElement e=driver.findElement(By.xpath("//*[@id=\"bag-add-13463032\"]"));
    
         // Scroll down to twice of windows inner height to make add to bag button visible
            Long scrollHeight = (Long) js.executeScript("return window.innerHeight;");
            js.executeScript("window.scrollTo(0,"+(scrollHeight*2)+");");
            System.out.println("Scrolled down");

            WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(80));
            
            Actions actions=new Actions(driver);
            actions.click(driver.findElement(By.id("bag-add-13463032"))).perform();;
           
           
            System.out.println("Got bag id");


            //Verify if add to bag successful by finding checkout button
            Thread.sleep(8000);
//            WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(100)); // Increase the timeout to 10 seconds
            System.out.println("Flag");
            WebElement element = Utility.getElement(driver,"id","atbIntCheckout",true);
            System.out.println("Flag2");
            element.click();
            
            System.out.println("Flag 2....");
            
          if(element.getText().equals("checkout")){
        	  System.out.println("Add Product to Bag Test Passed!...");
          

          }else{
        	  System.out.println("Add Product to Bag Failed!...");


            Thread.sleep(4000);
          }}
            catch (Exception e){
                System.out.println("Error in MacysAutomateTest>>macyTestCases: "+e);
                Thread.sleep(1000);
            }

    }

    @AfterSuite
    public void close(){
        try {
            driver.close();
            driver.quit();
            System.out.println("Closed the browser!...");
            System.out.println("Test Completed!...");
            

        }
        catch (Exception e){
            System.out.println("Error in MacysAutomateTest>>close: "+e);
        }
    }
    

   

}