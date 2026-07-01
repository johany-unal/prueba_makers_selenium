package com.makers.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/com/makers/features",
        glue = "com.makers.steps",
        plugin = {
                "pretty",
                "html:target/cucumber-report/report.html",
                "json:target/cucumber-report/report.json"
        },
        monochrome = true
)
public class CucumberRunner {
}