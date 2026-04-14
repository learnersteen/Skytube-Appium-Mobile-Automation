package day1;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class SauceDemoLoginTest {

	public static void main(String[] args) {
		//1. Define the w3c driver Options (Replaces Desired Capabilities) 
		
		UiAutomator2Options options = new UiAutomator2Options();
		
		options.setDeviceName("emulator-5554");  //adb devices gives emulator name 
		options.setPlatformVersion("16"); //adb shell getprop ro.build.version.release  gives version number 
		
		
		 //if u freshly install an app and test it
        //options.setApp("path of the apk file");
		
		// Assuming the app is already installed from your Inspector session
        options.setAppPackage("com.swaglabsmobileapp");
        
        options.setAppActivity(".MainActivity");
        //options.setAppActivity("*");
        
        AndroidDriver driver = null;
        
        try {
        	
        	// 2. Connect to your running Appium 3.2 Server
        	URL serverUrl = new URL("http://127.0.0.1:4723");
        	driver = new AndroidDriver(serverUrl, options);
        	
        	// Set a global wait time to let the emulator catch up
        	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        	System.out.println("Success! Connected to Pixel 9a and launched SauceDemo.");
        	
        	// ... inside your try block after the driver initializes ...
        	// 1. Find and interact with the Username field
            WebElement usernameField = driver.findElement(AppiumBy.accessibilityId("test-Username"));
            usernameField.sendKeys("standard_user");

            // 2. Find and interact with the Password field
            WebElement passwordField = driver.findElement(AppiumBy.accessibilityId("test-Password"));
            passwordField.sendKeys("secret_sauce");

            // 3. Click the Login button
            WebElement loginButton = driver.findElement(AppiumBy.accessibilityId("test-LOGIN"));
            loginButton.click();

            System.out.println("Login executed successfully!");
               
           } catch (MalformedURLException e) {
               System.out.println("Invalid Appium server URL.");
           } finally {
               // 3. Clean up the session
               if (driver != null) {
                   //driver.quit();
                   System.out.println("Test session closed.");
               }
           }
       }
   }



//UiAutomator2Options options = new UiAutomator2Options();

	//This creates a capabilities object. It tells Appium:“Which device? Which app? How to launch?”

//options.setDeviceName -> This is the device ID from ADB -> adb devices ==> your Android emulator name
//options.setPlatformVersion("16");  // command gives android version ==> adb shell getprop ro.build.version.release

//if app is not installed -> options.setApp("C:\\Apps\\myapp.apk");

// if app is already installed , use package ==>  options.setAppPackage("com.swaglabsmobileapp"); -> similar to Currently Active App ID in Appium Inspector 

// AndroidDriver driver = null; ==> Declaring a variable + assigning it as null (empty)


