package automationpilot;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ResourceBundle;


@CucumberOptions(
        features = "src/test/resources/automationpilot",
        glue = {"automationpilot.steps"},
        tags = {"~@Ignore"},
        format = {
                "pretty",
                "html:target/cucumber-reports/cucumber-pretty",
                "json:target/cucumber-reports/CucumberTestReport.json",
                "rerun:target/cucumber-reports/rerun.txt"
        })
public class RunCucumberTest {
        private TestNGCucumberRunner testNGCucumberRunner;

        @BeforeClass(alwaysRun = true)
        public void setUpClass() throws Exception {
                testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
                //ResourceBundle rb = ResourceBundle.getBundle("config");
        }

        @Test(groups = {}, description = "Runs Cucumber Feature", dataProvider = "features")
        public void feature(CucumberFeatureWrapper cucumberFeature) {
                testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
        }

        @DataProvider
        public Object[][] features() {
                return testNGCucumberRunner.provideFeatures();
        }

        @AfterClass(alwaysRun = true)
        public void tearDownClass() throws Exception {
                testNGCucumberRunner.finish();
        }
}