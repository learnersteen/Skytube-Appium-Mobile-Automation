package hooks;

import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

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
            String runMode = getProperty("runMode").toLowerCase();

            AndroidDriver driver;

            if ("browserstack".equals(runMode)) {
                driver = createBrowserStackDriver(scenario);
            } else {
                driver = createLocalDriver();
            }

            driver.manage().timeouts().implicitlyWait(
                    Duration.ofSeconds(Long.parseLong(getProperty("implicitWait")))
            );

            DriverManager.setDriver(driver);

            LogUtil.info("Driver initialized successfully");
            LogUtil.info("Session ID: " + driver.getSessionId());

        } catch (Exception e) {
            LogUtil.error("Driver initialization failed: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // ================= LOCAL =================
    private AndroidDriver createLocalDriver() throws Exception {

        LogUtil.info("Running in LOCAL mode");

        UiAutomator2Options options = new UiAutomator2Options();

        options.setDeviceName(getProperty("deviceName"));
        options.setPlatformName(getProperty("platformName"));
        options.setPlatformVersion(getProperty("platformVersion"));
        options.setAutomationName(getProperty("automationName"));
        options.setAppPackage(getProperty("appPackage"));
        options.setAppActivity(getProperty("appActivity"));

        URI uri = new URI(getProperty("appiumServerUrl"));

        return new AndroidDriver(uri.toURL(), options);
    }

    // ================= BROWSERSTACK =================
    
    
    
    private AndroidDriver createBrowserStackDriver(Scenario scenario) throws Exception {

        LogUtil.info("Running in BROWSERSTACK mode");

        String username = getEnvOrProperty("BROWSERSTACK_USERNAME", "browserstackUserName");
        String accessKey = getEnvOrProperty("BROWSERSTACK_ACCESS_KEY", "browserstackAccessKey");

        UiAutomator2Options options = new UiAutomator2Options();

        options.setPlatformName("Android");
        options.setAutomationName("UiAutomator2");
        options.setDeviceName(getProperty("browserstackDeviceName"));
        options.setPlatformVersion(getProperty("browserstackPlatformVersion"));
        options.setApp(getProperty("browserstackApp"));

        Map<String, Object> bstackOptions = new HashMap<>();
        bstackOptions.put("userName", username);
        bstackOptions.put("accessKey", accessKey);
        bstackOptions.put("projectName", "SkyTube Automation");
        bstackOptions.put("buildName", "BrowserStack Build");
        bstackOptions.put("sessionName", scenario.getName());
        bstackOptions.put("debug", true);
        bstackOptions.put("networkLogs", true);

        options.setCapability("bstack:options", bstackOptions);

        return new AndroidDriver(
                new URL("https://hub-cloud.browserstack.com/wd/hub"),
                options
        );
    }
//    private AndroidDriver createBrowserStackDriver(Scenario scenario) throws Exception {
//
//        LogUtil.info("Running in BROWSERSTACK mode");
//
//        String username = getEnvOrProperty("BROWSERSTACK_USERNAME", "browserstackUserName");
//        String accessKey = getEnvOrProperty("BROWSERSTACK_ACCESS_KEY", "browserstackAccessKey");
//
//        UiAutomator2Options options = new UiAutomator2Options();
//
//        options.setPlatformName("Android");
//        options.setAutomationName("UiAutomator2");
//        options.setDeviceName(getProperty("browserstackDeviceName"));
//        options.setPlatformVersion(getProperty("browserstackPlatformVersion"));
//
//        // IMPORTANT
//        options.setApp(getProperty("browserstackApp"));
//
//        // BrowserStack auth
//        options.setCapability("browserstack.user", username);
//        options.setCapability("browserstack.key", accessKey);
//
//        // Advanced options
//        Map<String, Object> bstackOptions = new HashMap<>();
//        bstackOptions.put("projectName", "SkyTube Automation");
//        bstackOptions.put("buildName", "BrowserStack Build");
//        bstackOptions.put("sessionName", scenario.getName());
//        bstackOptions.put("debug", true);
//        bstackOptions.put("networkLogs", true);
//
//        options.setCapability("bstack:options", bstackOptions);
//
//        return new AndroidDriver(
//                new URL("https://hub-cloud.browserstack.com/wd/hub"),
//                options
//        );
//    }

    // ================= TEARDOWN =================
    @After
    public void tearDown(Scenario scenario) {

        AndroidDriver driver = null;

        try {
            driver = DriverManager.getDriver();

            LogUtil.info("Scenario finished: " + scenario.getName());
            LogUtil.info("Status: " + scenario.getStatus());

            if (scenario.isFailed() && driver != null) {
                byte[] screenshot = driver.getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", scenario.getName());
            }

            if (driver != null) {
                driver.quit();
            }

        } catch (Exception e) {
            LogUtil.error("Teardown error: " + e.getMessage());
        } finally {
            DriverManager.unload();
        }
    }

    // ================= UTIL =================
    private String getProperty(String key) {
        String value = ConfigReader.getProperty(key);

        if (value == null || value.isEmpty()) {
            throw new RuntimeException("Missing property: " + key);
        }
        return value.trim();
    }

    private String getEnvOrProperty(String envKey, String propKey) {
        String value = System.getenv(envKey);

        if (value != null && !value.isEmpty()) {
            return value;
        }
        return getProperty(propKey);
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
//    @Before
//    public void setUp(Scenario scenario) {
//        LogUtil.info("Starting scenario: " + scenario.getName());
//
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
//            URI uri = new URI(getRequiredProperty("appiumServerUrl"));
//            AndroidDriver driver = new AndroidDriver(uri.toURL(), options);
//
//            long implicitWait = Long.parseLong(getRequiredProperty("implicitWait"));
//            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
//
//            DriverManager.setDriver(driver);
//
//            LogUtil.info("App launched successfully");
//            LogUtil.info("Session ID: " + driver.getSessionId());
//
//        } catch (Exception e) {
//            LogUtil.error("Driver initialization failed: " + e.getMessage());
//            throw new RuntimeException("Failed to initialize Appium driver", e);
//        }
//    }
//
//    @After
//    public void tearDown(Scenario scenario) {
//        AndroidDriver driver = DriverManager.getDriver();
//
//        try {
//            LogUtil.info("Scenario finished: " + scenario.getName());
//            LogUtil.info("Scenario status: " + scenario.getStatus());
//
//            if (driver != null && scenario.isFailed()) {
//                byte[] screenshot = driver.getScreenshotAs(OutputType.BYTES);
//                scenario.attach(screenshot, "image/png", scenario.getName());
//                LogUtil.info("Screenshot captured for failed scenario: " + scenario.getName());
//            }
//
//            if (driver != null) {
//                LogUtil.info("Closing Appium session");
//                driver.quit();
//                LogUtil.info("Appium session closed successfully");
//            }
//
//        } catch (Exception e) {
//            LogUtil.error("Error during teardown: " + e.getMessage());
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


