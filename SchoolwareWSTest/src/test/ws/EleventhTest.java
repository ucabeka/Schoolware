// We test getQuestionsDetails API : Returns the number of clicks and time given the questionID
// Author : Kaprou Emilia
package test.ws;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import src.ws.QuestionDetails;
import src.ws.QuestionsDetails;
import com.google.gson.Gson;


public class EleventhTest {

	@Test
	public void test() {

	Integer questionId = 1;

	//Instantiate Selenium webdriver
	WebDriver driver = new HtmlUnitDriver();

	//Point the webdriver to the API URI
	driver.get("http://schoolware.cs.ucl.ac.uk:9999/aad-ws/api/question/1");

	//The page source should be the returned JSON string
	String rawJson = driver.getPageSource();
	//System.out.println(rawJson);
	Gson gson = new Gson();

	QuestionsDetails questions = gson.fromJson(rawJson, QuestionsDetails.class);

	
	//System.out.println(questions.getQuestions().get(0).getQuesID());
	
	Assert.assertTrue("question time matches", questions.getDetails().get(0).getTime() == 0);
	
	
	}	
	
}
