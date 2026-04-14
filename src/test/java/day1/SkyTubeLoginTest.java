package day1;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.options.UiAutomator2Options;

public class SkyTubeLoginTest {

	public static void main(String[] args) {
		//1. Define the w3c driver Options (Replaces Desired Capabilities) 
		
				UiAutomator2Options options = new UiAutomator2Options();
				
				options.setDeviceName("emulator-5554");  
				options.setPlatformVersion("16"); 
				options.setAutomationName("UiAutomator2");
				
				options.setAppPackage("free.rm.skytube.oss");
		        
		        options.setAppActivity("free.rm.skytube.gui.activities.MainActivity");
		       		        
		        AndroidDriver driver = null;
		        
		        try {
		        	
		        	// 2. Connect to your running Appium 3.2 Server
		        	URL serverUrl = new URL("http://127.0.0.1:4723");
		        	driver = new AndroidDriver(serverUrl, options);
		        	
		        	// Set a global wait time to let the emulator catch up
		        	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		        	System.out.println("SkyTube launched successfully");
		        	
		        	  Thread.sleep(3000);
		        	  
		        	// Handle popup first
		              try {
		                  WebElement okButton = driver.findElement(
		                      AppiumBy.androidUIAutomator("new UiSelector().text(\"OK\")")
		                  );
		                  okButton.click();
		                  System.out.println("Popup handled");
		              } catch (Exception e) {
		                  System.out.println("No popup displayed");
		              }
		              
		              // Click search icon
		              WebElement searchButton = driver.findElement(
		                  AppiumBy.id("free.rm.skytube.oss:id/menu_search")
		              );
		              searchButton.click();
		              System.out.println("Clicked search button");

		        	
		           // Enter search text
		              WebElement searchField = driver.findElement(AppiumBy.id("free.rm.skytube.oss:id/search_src_text"));
		              searchField.click();
		              Thread.sleep(1000);
		              searchField.sendKeys("appium");
		              //searchField.setValue("appium");
		              System.out.println("Entered text: appium");

		              Thread.sleep(2000);

		              // Press Enter key
		              driver.pressKey(new KeyEvent(AndroidKey.ENTER));
		              System.out.println("Pressed Enter key");

		              Thread.sleep(4000);

		           
		           // Validate Appium videos/results are displayed
		           int resultCount = driver.findElements(
		               AppiumBy.xpath("//android.widget.TextView[contains(@text,'Appium') or contains(@text,'appium')]")
		           ).size();

		           System.out.println("Matching results count: " + resultCount);

		           if (resultCount > 0) {
		               System.out.println("Search functionality is working - Appium videos are displayed");
		           } else {
		               System.out.println("Search failed - no matching videos displayed");
		           }
		         		             
		        } catch (Exception e) {
		            e.printStackTrace();
		        } finally {
		            if (driver != null) {
		               driver.quit();
		                System.out.println("Test session closed");
		            }
		        }
		    }
		}
		        	 
		        	 
		        	 