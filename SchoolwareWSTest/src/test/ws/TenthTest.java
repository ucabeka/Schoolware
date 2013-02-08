// We test getTestQuestions API : Returns an array of question IDs given the testID
// Author : Kaprou Emilia
package test.ws;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import src.ws.Questions;
import src.ws.Question;
import com.google.gson.Gson;

public class TenthTest {
	
	@Test
	public void test() {

	Integer testId = 1;

	//Instantiate Selenium webdriver
	WebDriver driver = new HtmlUnitDriver();

	//Point the webdriver to the API URI
	driver.get("http://schoolware.cs.ucl.ac.uk:9999/aad-ws/api/questions/1");

	//The page source should be the returned JSON string
	String rawJson = driver.getPageSource();
	System.out.println(rawJson);
	Gson gson = new Gson();

	Questions questions = gson.fromJson(rawJson, Questions.class);

	
	//System.out.println(questions.getQuestions().get(0).getQuesID());
	
	Assert.assertTrue("question id match", questions.getQuestions().get(1).getQuesID() == 2);
	//Assert.assertEquals((int)7, (int)questions.getQuestions().get(0).getQuesID());
	}
	
	
}
