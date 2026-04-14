package hooks;

import java.net.URI;
import java.time.Duration;

import org.openqa.selenium.OutputType;

import driver.DriverManager;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utilities.ConfigReader;
import utilities.LogUtil;

public class Hooks {

    @Before
    public void setUp(Scenario scenario) {
        LogUtil.info("Starting scenario: " + scenario.getName());

        try {
            UiAutomator2Options options = new UiAutomator2Options();

            options.setDeviceName(getRequiredProperty("deviceName"));
            options.setPlatformName(getRequiredProperty("platformName"));
            options.setPlatformVersion(getRequiredProperty("platformVersion"));
            options.setAutomationName(getRequiredProperty("automationName"));
            options.setAppPackage(getRequiredProperty("appPackage"));
            options.setAppActivity(getRequiredProperty("appActivity"));

            URI uri = new URI(getRequiredProperty("appiumServerUrl"));
            AndroidDriver driver = new AndroidDriver(uri.toURL(), options);

            long implicitWait = Long.parseLong(getRequiredProperty("implicitWait"));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));

            DriverManager.setDriver(driver);

            LogUtil.info("App launched successfully");
            LogUtil.info("Session ID: " + driver.getSessionId());

        } catch (Exception e) {
            LogUtil.error("Driver initialization failed: " + e.getMessage());
            throw new RuntimeException("Failed to initialize Appium driver", e);
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        AndroidDriver driver = DriverManager.getDriver();

        try {
            LogUtil.info("Scenario finished: " + scenario.getName());
            LogUtil.info("Scenario status: " + scenario.getStatus());

            if (driver != null && scenario.isFailed()) {
                byte[] screenshot = driver.getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", scenario.getName());
                LogUtil.info("Screenshot captured for failed scenario: " + scenario.getName());
            }

            if (driver != null) {
                LogUtil.info("Closing Appium session");
                driver.quit();
                LogUtil.info("Appium session closed successfully");
            }

        } catch (Exception e) {
            LogUtil.error("Error during teardown: " + e.getMessage());
        } finally {
            DriverManager.unload();
        }
    }

    private String getRequiredProperty(String key) {
        String value = ConfigReader.getProperty(key);

        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Missing required property in config file: " + key);
        }

        return value.trim();
    }
}


//package hooks;
//
//import java.net.URI;
//import java.time.Duration;
//
//import org.openqa.selenium.OutputType;
//
//import driver.DriverManager;
//import io.appium.java_client.android.AndroidDriver;
//import io.appium.java_client.android.options.UiAutomator2Options;
//import io.cucumber.java.After;
//import io.cucumber.java.Before;
//import io.cucumber.java.Scenario;
//import utilities.ConfigReader;
//import utilities.LogUtil;
//
//public class Hooks {
//	
//	@Before
//    //public void setUp() {
//    public void setUp(Scenario scenario) {
//		 LogUtil.info("Starting scenario: " + scenario.getName());
//        try {
//            UiAutomator2Options options = new UiAutomator2Options();
//
//            options.setDeviceName(getRequiredProperty("deviceName"));
//            options.setPlatformName(getRequiredProperty("platformName"));
//            options.setPlatformVersion(getRequiredProperty("platformVersion"));
//            options.setAutomationName(getRequiredProperty("automationName"));
//            options.setAppPackage(getRequiredProperty("appPackage"));
//            options.setAppActivity(getRequiredProperty("appActivity"));
//       
//
//            URI uri = new URI(getRequiredProperty("appiumServerUrl"));
//            AndroidDriver driver = new AndroidDriver(uri.toURL(), options);
//
//            long implicitWait = Long.parseLong(getRequiredProperty("implicitWait"));
//            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
//
//            DriverManager.setDriver(driver);
//
//            LogUtil.info("App launched successfully.");
//            LogUtil.info("Session ID: " + driver.getSessionId());
//
//        } catch (Exception e) {
//        	LogUtil.error("Driver initialization failed: " + e.getMessage());
//           // e.printStackTrace();
//            throw new RuntimeException("Failed to initialize Appium driver", e);
//        }
//    }
//
//    @After
//    public void tearDown(Scenario scenario) {
//        AndroidDriver driver = DriverManager.getDriver();
//
//        try {
//            if (driver != null && scenario.isFailed()) {
//                byte[] screenshot = driver.getScreenshotAs(OutputType.BYTES);
//                scenario.attach(screenshot, "image/png", scenario.getName());
//                LogUtil.info("Screenshot captured for failed scenario: " + scenario.getName());
//            }
//
//            if (driver != null) {
//            	LogUtil.info("Closing Appium session.");
//                driver.quit();
//                LogUtil.info("Appium session closed successfully.");
//            }
//        } catch (Exception e) {
//        	LogUtil.error("Error during teardown: " + e.getMessage());
//            e.printStackTrace();
//        } finally {
//            DriverManager.unload();
//        }
//    }
//
//    private String getRequiredProperty(String key) {
//        String value = ConfigReader.getProperty(key);
//
//        if (value == null || value.trim().isEmpty()) {
//            throw new IllegalArgumentException("Missing required property in config file: " + key);
//        }
//
//        return value.trim();
//    }
//}
//	
	

	
//	   @Before
//	    public void setUp() throws Exception {
//		   
//	       UiAutomator2Options options = new UiAutomator2Options();
//	        options.setDeviceName(ConfigReader.getProperty("deviceName"));
//	        options.setPlatformVersion(ConfigReader.getProperty("platformVersion"));
//	        options.setAutomationName(ConfigReader.getProperty("automationName"));
//	        options.setAppPackage(ConfigReader.getProperty("appPackage"));
//	        options.setAppActivity(ConfigReader.getProperty("appActivity"));
//
////	        AndroidDriver driver = new AndroidDriver(
////	                new URL(ConfigReader.getProperty("appiumServerUrl")), options);
//	        
//	        URI uri = new URI(ConfigReader.getProperty("appiumServerUrl"));
//	        AndroidDriver driver = new AndroidDriver(uri.toURL(), options);
//	  
//	        driver.manage().timeouts().implicitlyWait(
//	                Duration.ofSeconds(Long.parseLong(ConfigReader.getProperty("implicitWait")))
//	        );
//
//	        DriverManager.setDriver(driver);
//	    }
//
//	    @After
//	    public void tearDown() {
//	        AndroidDriver driver = DriverManager.getDriver();
//
//	        if (driver != null) {
//	            driver.quit();
//	        }
//
//	        DriverManager.unload();
//	    }
//	}
//	   
//    @Before
//    public void setUp() throws Exception {
//
//        UiAutomator2Options options = new UiAutomator2Options();
//        options.setDeviceName("emulator-5554");
//        options.setPlatformVersion("16");
//        options.setAutomationName("UiAutomator2");
//        options.setAppPackage("free.rm.skytube.oss");
//        options.setAppActivity("free.rm.skytube.gui.activities.MainActivity");
//
//        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//
//        DriverManager.setDriver(driver);
//    }
//
//    @After
//    public void tearDown() {
//        AndroidDriver driver = DriverManager.getDriver();
//        if (driver != null) {
//            driver.quit();
//        }
//        DriverManager.unload();
//    }
//}