package driver;

import io.appium.java_client.android.AndroidDriver;

public class DriverManager {

    private static final ThreadLocal<AndroidDriver> driver = new ThreadLocal<>();

    public static void setDriver(AndroidDriver androidDriver) {
        driver.set(androidDriver);
        System.out.println("[DriverManager] Driver set for thread: " + Thread.currentThread().getId());
    }

    public static AndroidDriver getDriver() {
        if (driver.get() == null) {
            throw new IllegalStateException("Driver is not initialized. Call setDriver() first.");
        }
        return driver.get();
    }

    public static String getSessionId() {
        return getDriver().getSessionId().toString();
    }

    public static void unload() {
        driver.remove();
        System.out.println("[DriverManager] Driver removed for thread: " + Thread.currentThread().getId());
    }
}


//package driver;
//
//import io.appium.java_client.android.AndroidDriver;
//
//public class DriverManager {
//
//		private static final ThreadLocal<AndroidDriver> driver = new ThreadLocal<>();
//
//	    public static void setDriver(AndroidDriver androidDriver) {
//	        driver.set(androidDriver);
//	    }
//
//	    public static AndroidDriver getDriver() {
//	        return driver.get();
//	    }
//
//	    public static void unload() {
//	        driver.remove();
//	    }
//	}