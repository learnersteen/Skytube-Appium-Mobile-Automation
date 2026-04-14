package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"stepdefinitions", "hooks"},
    plugin = {
        "pretty",
        "html:target/cucumber-report.html",
        "json:target/cucumber.json",
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
       // "com.aventstack.chaintest.plugins.ChainTestCucumberListener:"
    },
    monochrome = true,
    //publish = true   //Maven is trying to publish the Cucumber report online.
    publish = false 
)
public class TestRunner extends AbstractTestNGCucumberTests {
}