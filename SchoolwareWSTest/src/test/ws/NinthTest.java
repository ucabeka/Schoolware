// We test getTestDetails : Returns the score, time and questions answered given testID
// Author : Zheng Gao
package test.ws;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.google.gson.Gson;

import src.ws.UserTests;

public class NinthTest {

	@Test
	public void test() {
		
		String testId = "1";
		
		//Instantiate Selenium webdriver
		WebDriver driver = new HtmlUnitDriver();	
		
		//Point the webdriver to the API URI
        driver.get("http://schoolware.cs.ucl.ac.uk:9999/aad-ws/api/test/" + testId);
        
        //The page source should be the returned JSON string
        String rawJson = driver.getPageSource();
        
        Gson gson = new Gson();
        
        UserTests userTests = gson.fromJson(rawJson, UserTests.class);

        System.out.println(userTests.getUserTests().get(0).getQuesAttented());
        
        Assert.assertTrue("question attented match", userTests.getUserTests().get(0).getQuesAttented() == 3);
        Assert.assertTrue("test id match", userTests.getUserTests().get(0).getTestId() == 1);

	}

}
