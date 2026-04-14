package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import driver.DriverManager;
import io.appium.java_client.android.AndroidDriver;

public class BasePage {

    protected AndroidDriver driver;
    protected WebDriverWait wait;

    
    public BasePage() {
        this.driver = DriverManager.getDriver();
        if (this.driver == null) {
            throw new IllegalStateException("Driver is not initialized. Make sure Hooks set the driver before page object creation.");
        }
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    protected WebElement find(By locator) {
        return driver.findElement(locator);
    }

    protected WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForClickability(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected boolean waitForVisibility(By locator, int seconds) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(seconds))
                    .until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    protected void click(By locator) {
        waitForClickability(locator).click();
    }

    protected void type(By locator, String text) {
        WebElement element = waitForVisibility(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected boolean isDisplayed(By locator) {
        try {
            return waitForVisibility(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}