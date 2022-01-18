package com.belk.feature.definition;

import org.testng.annotations.Test;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(features="FeatureFile", glue= {"com.belk.feature.definition"}, plugin= {"pretty","html:files/Report.html"})


public class ReportBelk extends AbstractTestNGCucumberTests{
	
	@Test
	public void test1() {
		
	}
	
	


}
