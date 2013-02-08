// We test getAppDetails : Returns the App's details given the appID
// Author : Chen Chen
package test.ws;

//We depend on Selenium jars (both webdriver and standalone server)
//and JUnit (3.8 here, but 4.x is completely okay as well).
//Configure the build path setting to suit your environment.

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import src.ws.App;
import src.ws.Categories;

import com.google.gson.Gson;

public class FourthTest {

	@Test

	public void TestgetAppDetail()
	{
		WebDriver driver = new HtmlUnitDriver();
		
		//Point the webdriver to the API URI
        driver.get("http://schoolware.cs.ucl.ac.uk:9999/aad-ws/api/application/1/");
            
        //The page source should be the returned JSON string
        String rawJson = driver.getPageSource();
        //which we parse using Gson; use whatever you want to :)
        Gson gson = new Gson();
        //You will also notice that I have written POJOs to store 
        //the data from JSON; if you already have corresponding
        //Java objects in your code base, you don't need to.
        //They are here just as an illustration
        App app = gson.fromJson(rawJson, App.class);
        
   
        Assert.assertTrue("app name match", app.getName().equals("BeautifulMaths"));
        Assert.assertTrue("category name match", app.getCategoryName().equals("Mathematics"));
        Assert.assertTrue("description match", app.getDescription().equals("BeautifulMaths_Description"));
        Assert.assertTrue("developer match", app.getDeveloper().equals("Marios"));
        Assert.assertTrue("ID match", app.getId().equals(1));
        
        //Assert.assertTrue("category id match", app.getCategories().get(0).getCategId() == 1);

	}
	
}
