// We test storeApp API : Used to stores apps details in the DB
//Author :  Gao Zheung

package test.ws;

import java.util.Iterator;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.Select;

import src.ws.AppDescription;
import src.ws.Categories;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.google.gson.Gson;

public class TestUpload {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new HtmlUnitDriver(BrowserVersion.FIREFOX_10);
    ((HtmlUnitDriver) driver).setJavascriptEnabled(true); 
//	driver = new FirefoxDriver();
    baseUrl = "http://schoolware.cs.ucl.ac.uk/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testUpload() throws Exception {
	
	  driver.get(baseUrl + "/schoolware/web/");
	  driver.findElement(By.id("name")).clear();
	  driver.findElement(By.id("name")).sendKeys("App");
	  new Select(driver.findElement(By.id("appCategory"))).selectByVisibleText("History");
	  new Select(driver.findElement(By.id("appType"))).selectByVisibleText(".jar");
	  driver.findElement(By.id("description")).clear();
	  driver.findElement(By.id("description")).sendKeys("App");
	  driver.findElement(By.id("developer")).clear();
	  driver.findElement(By.id("developer")).sendKeys("Simon");
	  driver.findElement(By.id("file")).clear();
	  driver.findElement(By.id("file")).sendKeys("D:\\Program Files (x86)\\Alloy\\test.zip");
//	  driver.findElement(By.id("analytics_modified")).click();
	  driver.findElement(By.id("btnUpload")).click();
	    
    String mwh = driver.getWindowHandle();
    Set handles = driver.getWindowHandles();

    Iterator<String> it = handles.iterator();
    while(it.hasNext())
    {
    	String h = it.next().toString();
		if(!h.contains(mwh))
		{
			driver.switchTo().window(h);
			String rawJson = driver.getPageSource();
			System.out.println(rawJson);
	        
	        Gson gson = new Gson();
	        AppDescription appDescription = gson.fromJson(rawJson, AppDescription.class);
	        
			System.out.println(appDescription.getDescription());
			System.out.println(appDescription.getAppCategId());
	        Assert.assertEquals("App", appDescription.getDescription());
	        Assert.assertEquals(4, appDescription.getAppCategId());
	        
			driver.switchTo().window(mwh);
		}
    }
}

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alert.getText();
    } finally {
      acceptNextAlert = true;
    }
  }
}
