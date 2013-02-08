//We test Compatability of the webiste
//Author: Hee San Kim
package test.ws;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.*;

import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestCompatability {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    baseUrl = "http://schoolware.cs.ucl.ac.uk";
  }

  @Test
  public void TestCompatability() throws Exception {
	/* Firefox Test */
	driver = new FirefoxDriver();
	driver.manage().window().setPosition(new Point(0, 0));
	driver.manage().window().setSize(new Dimension(1920, 1200));
	checkIsDisplaytedByBrowser();
	  
	/* Chrome Test  */
	//You should download chromedriver.exe first and change the file path where you located it.
	File file = new File("C:\\Program Files (non-install)\\Eclipse\\chromedriver.exe");
	System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
  	DesiredCapabilities capabilities = DesiredCapabilities.chrome();
  	List<String> arguments = Arrays.asList("--disable-web-security", "--low-file-access-from-files");
  	capabilities.setCapability("chrome.switches", arguments);
	driver = new ChromeDriver(capabilities);
	checkIsDisplaytedByBrowser();
  }
  
  private void checkIsDisplaytedByBrowser() throws Exception{
	    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.get(baseUrl + "/schoolware/web/");
		int[][] resolution = {{1920,1200},{1920,1080},{1280,1024},{1280,800},{1366,768},{1024,768},{1024,600},{800,600},{640,480}};

		for(int i=0; i<resolution.length; i++)
			this.checkIsDisplayedByResolution(resolution[i][0], resolution[i][1]);
  }

  private void checkIsDisplayedByResolution(int width, int height) throws Exception {
		driver.manage().window().setSize(new Dimension(width, height));
		Assert.assertTrue("width = " +width +" hight = " +height, driver.findElement(By.id("name")).isDisplayed());
		Assert.assertTrue("width = " +width +" hight = " +height, driver.findElement(By.id("description")).isDisplayed());
		Assert.assertTrue("width = " +width +" hight = " +height, driver.findElement(By.id("developer")).isDisplayed());
		Assert.assertTrue("width = " +width +" hight = " +height, driver.findElement(By.id("btnUpload")).isDisplayed());
		Assert.assertTrue("width = " +width +" hight = " +height, driver.findElement(By.id("file")).isDisplayed());
		Assert.assertTrue("width = " +width +" hight = " +height, driver.findElement(By.id("appCategory")).isDisplayed());
		Assert.assertTrue("width = " +width +" hight = " +height, driver.findElement(By.id("appType")).isDisplayed());
		//Thread.sleep(500);
}
  
  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }
}
