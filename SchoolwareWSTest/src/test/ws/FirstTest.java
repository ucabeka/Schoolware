// We test getCategories API: Returns the Categories
// Author: Shim Yoo
package test.ws;
//
//We depend on Selenium jars (both webdriver and standalone server)
//and JUnit (3.8 here, but 4.x is completely okay as well).
//Configure the build path setting to suit your environment.

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import src.ws.Categories;

import com.google.gson.Gson;

public class FirstTest {

	@Test
	public void test() {
		//Instantiate Selenium webdriver
		WebDriver driver = new HtmlUnitDriver();
		
		//Point the webdriver to the API URI
        driver.get("http://schoolware.cs.ucl.ac.uk:9999/aad-ws/api/categories");
        
        //The page source should be the returned JSON string
        String rawJson = driver.getPageSource();
        //which we parse using Gson; use whatever you want to :)
        Gson gson = new Gson();
        //You will also notice that I have written POJOs to store 
        //the data from JSON; if you already have corresponding
        //Java objects in your code base, you don't need to.
        //They are here just as an illustration
        Categories categories = gson.fromJson(rawJson, Categories.class);
        
        //We assume that the first category in the returned JSON data
        //is always Mathematics, so we check the type and id
        Assert.assertTrue("category title match", categories.getCategories().get(0).getCategType().equals("Mathematics"));
        Assert.assertTrue("category id match", categories.getCategories().get(0).getCategId() == 1);
	}

}
