package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = {

			    "src/test/resources/features/SearchChannel.feature",
			    "src/test/resources/features/BookmarkFunctionality.feature",

			    "src/test/resources/features/DownloadFunctionality.feature",
			    "src/test/resources/features/SubscribeAndUnsubscribe.feature",
			    "src/test/resources/features/PreferencesOnlyUIValidation.feature",
			    "src/test/resources/features/VideoBlockerSetup.feature",		  
			    "src/test/resources/features/WatchedVideoCleaner.feature"
			  
	},
	
    glue = {"stepdefinitions", "hooks"},
    plugin = {
        "pretty",
        "html:target/cucumber-report.html",
        "json:target/cucumber.json",
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
       
    },
    monochrome = true,
    //publish = true   //Maven is trying to publish the Cucumber report online.
    publish = false 
)
public class TestRunner extends AbstractTestNGCucumberTests {
}