package hooks;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import utils.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import reports.ExtentReportManager;

public class Hooks {
    
    private static ExtentReports extent;
    private static ExtentTest scenarioTest;

    @BeforeAll
    public static void before_all() {
        extent = ExtentReportManager.getInstance();
    }

    @Before
    public void setup(Scenario scenario) {
        scenarioTest = extent.createTest(scenario.getName());
        DriverManager.getDriver();
    }

    @AfterStep
    public void afterStep(Scenario scenario) {
        // Determine the step's actual status
        Status stepStatus = scenario.isFailed() ? Status.FAIL : Status.PASS;
        String logMessage = scenario.isFailed() ? "Step failed" : "Step passed";

        // Condition: Take a screenshot if the scenario has failed OR if it is a @Negative test case
        if (scenario.isFailed() || scenario.getSourceTagNames().contains("@Negative")) {
            try {
                WebDriver driver = DriverManager.getDriver();
                final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                String screenshotBase64 = new String(org.apache.commons.codec.binary.Base64.encodeBase64(screenshot));

                // Attach screenshot to the native Cucumber report
                scenario.attach(screenshot, "image/png", "Screenshot");

                // Log status and attach screenshot to the Extent Report
                scenarioTest.log(stepStatus, logMessage)
                            .addScreenCaptureFromBase64String(screenshotBase64, "Screenshot");

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // For positive scenarios that pass, just log the status without a screenshot
            scenarioTest.log(stepStatus, logMessage);
        }
    }

    // This single @After hook now handles everything
    @After
    public void teardown(Scenario scenario) {
        // Log the final status of the scenario
        if (scenario.isFailed()) {
            scenarioTest.log(Status.FAIL, "Scenario failed: " + scenario.getName());
        } else {
            scenarioTest.log(Status.PASS, "Scenario passed: " + scenario.getName());
        }
        
        // This is now the last action, ensuring the browser closes correctly
        DriverManager.quitDriver();
    }
    
    @AfterAll
    public static void after_all() {
        extent.flush();
    }
}