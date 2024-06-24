package com.lexisnexis.cucumber.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/com/lexisnexis/cucumber/features",
        glue = "com/lexisnexis/cucumber/features/stepDefinitions",
        publish = true,
        monochrome = true,
        //dryRun = true,
        tags = "@ALL",
        //name = "reg",
        plugin = {
                "pretty",
                "json:target/json_output/cucumber.json",
                "junit:target/junit_xml_output/cucumber.xml",
                "html:cucumberReport/cucumber.html",
                "html:target/html_output/cucumber.html"
        })

public class TestRunner extends AbstractTestNGCucumberTests {


}
