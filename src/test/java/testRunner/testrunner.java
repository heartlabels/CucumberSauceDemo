package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"pretty", "me.jvt.cucumber.report.PrettyReports:target/cucumber"},
		features = "src/test/resources"
		,glue={"stepdefinations"}, 
		monochrome = true,
		tags = "@testcase2"
)


public class testrunner {

}
