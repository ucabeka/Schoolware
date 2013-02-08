//Author : Kazuo Kobori
package test.ws;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import src.ws.App;
import src.ws.Categories;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class ThirdWSTest_koborik {

		@Test
		public void test() {
			//Instantiate Selenium webdriver
			WebDriver driver = new HtmlUnitDriver();
			String rawJson = null;
			
			//input value is set for an non-existing appID (e.g. 999)
	        driver.get("http://schoolware.cs.ucl.ac.uk:9999/aad-ws/api/application/999/");
			System.out.println("input value is set for a non-existing appID[999]");
	        rawJson = driver.getPageSource();
	        System.out.println(rawJson);
	        Gson gson = new Gson();
	
	        // checking whether it is not app data
	        try{
	        	App app = gson.fromJson(rawJson, App.class);
	        	Assert.fail();
	        }catch(JsonSyntaxException e){
	        	
	        }
	        
			//input value has been changed to an existing appID (e.g. 132)
	        driver.get("http://schoolware.cs.ucl.ac.uk:9999/aad-ws/api/application/132/");
	        System.out.println("input value has been changed to an existing appID[132]");
	        rawJson = driver.getPageSource();
	        System.out.println(rawJson);
	        
	        // checking whether it is app data
	        try{
	        	App app = gson.fromJson(rawJson, App.class);
	        }catch(JsonSyntaxException e){
	        	Assert.fail();       	
	        }
	        
			//input value has been changed to an non-existing appID (e.g. 999) again
	        driver.get("http://schoolware.cs.ucl.ac.uk:9999/aad-ws/api/application/999/");
	        System.out.println("input value has been changed to the non-existing appID[999] again");
	        //The page source should be the returned JSON string
	        rawJson = driver.getPageSource();
	        System.out.println(rawJson);
	        
	        // checking whether it is not app data
	        try{
	        	App app = gson.fromJson(rawJson, App.class);
	        	Assert.fail();
	        }catch(JsonSyntaxException e){
	        	
	        }
	        
	        
	        //which we parse using Gson; use whatever you want to :)
	
	        //You will also notice that I have written POJOs to store 
	        //the data from JSON; if you already have corresponding
	        //Java objects in your code base, you don't need to.
	        //They are here just as an illustration
	        

	        //We assume that the first category in the returned JSON data
	        //is always Mathematics, so we check the type and id
	        //Assert.assertTrue("category title match", categories.getCategories().get(0).getCategType().equals("Mathematics"));
	        //Assert.assertTrue("category id match", categories.getCategories().get(0).getCategId() == 1);
		}

	}
