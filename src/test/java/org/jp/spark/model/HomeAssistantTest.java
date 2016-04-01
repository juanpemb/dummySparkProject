package org.jp.spark.model;

import org.jp.spark.App;
import org.json.JSONException;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.skyscreamer.jsonassert.JSONAssert;
import spark.Spark;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class HomeAssistantTest {

	private static final String HOME_ASSISTANT_PATH = "/home-assistant";
	private static final String EXPECTED_MESSAGES = "[{message:\"dummy message 1\"},{\"message\":\"dummy message 2\"},{\"message\":\"dummy message 3\"}]";
	private static final String EXPECTED_HOME_ASSISTANT = "[{name:\"name\",lastName:\"last name\",mail:\"mail\",phone:\"phone\",valoracion:\"2\",cp:\"30400\"}]";

	@BeforeClass
	public static void beforeClass() {
		App.main(null);
		waitStartServer();
	}

	@Test
	public void retrieveAllMessages() throws JSONException {
		TestResponse res = TestResponse.request("GET", "/messages");
		JSONAssert.assertEquals(EXPECTED_MESSAGES, res.body, false);
	}

	@Test
	public void retrieveAllHomeAssistant() throws JSONException {
		TestResponse response = TestResponse.request("GET", HOME_ASSISTANT_PATH);

		Assert.assertEquals("the status should be 200 ", 200, response.status);
		JSONAssert.assertEquals(EXPECTED_HOME_ASSISTANT, response.body, false);
	}
	
	
	@Test
	@Ignore
	public void saveHomeAssistant(){
		TestResponse res = TestResponse.request("POST", HOME_ASSISTANT_PATH);
		
		assertEquals(200, res.status);
	}

	private static void waitStartServer() {
		try {
			Thread.sleep(700);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public static void afterClass() {
		Spark.stop();
	}

}
