// We test getTypes : Returns the Categories' Types
// Author : Kaprou Emilia

package test.ws;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.google.gson.Gson;

import src.ws.Apps;

public class SecondTest {

@Test
public void test() {

String categoryId = "1";

//Instantiate Selenium webdriver
WebDriver driver = new HtmlUnitDriver();

//Point the webdriver to the API URI
driver.get("http://schoolware.cs.ucl.ac.uk:9999/aad-ws/api/applications/" + categoryId);

//The page source should be the returned JSON string
String rawJson = driver.getPageSource();

Gson gson = new Gson();

Apps apps = gson.fromJson(rawJson, Apps.class);

System.out.println(apps.getApps().get(0).getName());

Assert.assertTrue("app name match", apps.getApps().get(0).getName().equals("BeautifulMaths"));
Assert.assertTrue("app id match", apps.getApps().get(0).getId() == 1);

}
}