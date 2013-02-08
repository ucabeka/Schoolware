// We test getTests : Returns the tests of a specific app given the appID
// Author : Zheng Gao

package test.ws;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import src.ws.Tests;

import com.google.gson.Gson;


public class EighthTest {

	@Test
	public void test() {
		
		String appId = "1";
		
		//Instantiate Selenium webdriver
		WebDriver driver = new HtmlUnitDriver();
		
		//Point the webdriver to the API URI
        driver.get("http://schoolware.cs.ucl.ac.uk:9999/aad-ws/api/tests/" + appId);
        
        //The page source should be the returned JSON string
        String rawJson = driver.getPageSource();
        System.out.println(rawJson);        
        
        //which we parse using Gson; use whatever you want to :)
        Gson gson = new Gson();
        //You will also notice that I have written POJOs to store 
        //the data from JSON; if you already have corresponding
        //Java objects in your code base, you don't need to.
        //They are here just as an illustration
        Tests emilyTest = gson.fromJson(rawJson, Tests.class);
        
        System.out.println(emilyTest.getTest().get(0).getTestId());
        
       
        Assert.assertTrue("tests name match", emilyTest.getTest().get(0).getTestName().equals("BeautifulTest1"));
        Assert.assertTrue("tests id match", emilyTest.getTest().get(0).getTestId() == 1);
	}

}
