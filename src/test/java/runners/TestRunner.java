//package runners;
//
//import io.cucumber.testng.AbstractTestNGCucumberTests;
//import io.cucumber.testng.CucumberOptions;
//
//@CucumberOptions(
//     features = "src/test/resources/features",
//     glue = "com/globalsqa/banking/stepdefinitions",
//     plugin = {"pretty", "html:target/cucumber-reports.html"},
//     tags = "not @ignore"
//)
//public class TestRunner extends AbstractTestNGCucumberTests {
//}


package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
     features = "src/test/resources/features",
     // 👇 This is the critical change.
     // It now points to both packages.
     glue = {"stepdefinitions", "hooks"},
     plugin = {"pretty", "html:target/cucumber-reports.html"},
     tags = "not @ignore"
)
public class TestRunner extends AbstractTestNGCucumberTests {
}