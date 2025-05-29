import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

import org.testng.Assert;

public class JiraTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI = "http://localhost:8080";

		// login method
		SessionFilter session = new SessionFilter();
		String response = given().header("Content-Type", "Application/json")
				.body("{ \r\n" + "    \"username\": \"aniketk67\", \r\n" + "    \"password\": \"Aniket123\" \r\n" + "}")
				.log().all().filter(session).when().post("/rest/auth/1/session").then().log().all().extract().response()
				.asString();

		// Using the above session in following api test which is adding comment to the
		// Jira
		String expectedComment = "My updated comment 2";

		String responseComment = given().pathParam("key", "10100").header("Content-Type", "Application/json")
				.body("{\r\n" + "    \"body\": \"" + expectedComment + "\",\r\n" + "    \"visibility\": {\r\n"
						+ "        \"type\": \"role\",\r\n" + "        \"value\": \"Administrators\"\r\n" + "    }\r\n"
						+ "}")
				.log().all().filter(session).when().post("/rest/api/2/issue/{key}/comment").then().log().all()
				.assertThat().statusCode(201).extract().asString();

		JsonPath js = new JsonPath(responseComment);
		String commentId = js.get("id");

		System.out.println("The commentID is as follows :" + " " + commentId);

		// adding the attachment to the Jira

		
		  given().header("X-Atlassian-Token","no-check").filter(session).
		  pathParam("key", "10100").header("Content-Type","multipart/form-data")
		  .multiPart("file",new
		  File("C:\\Users\\Aniket_Khandagale\\Documents\\My Learning\\APIAutomation_Demo\\SampleText.txt"
		  )). when().post("rest/api/2/issue/{key}/attachments")
		  .then().log().all().assertThat().statusCode(200);
		  
		 

		// get the Jira issue details using GET api

		String issueDetails = given().filter(session).pathParam("key", "10100").queryParam("fields", "comment").when()
				.get("/rest/api/2/issue/{key}").then().log().all().extract().response().asString();

		System.out
				.println("********************The response for issue details*****************************************");
		System.out.println(issueDetails);

		JsonPath js1 = new JsonPath(issueDetails);

		int commentCount = js1.getInt("fields.comment.comments.size()");

		for (int i = 0; i < commentCount; i++) {

			String commentIdIssue = js1.get("fields.comment.comments[" + i + "].id").toString();
			if (commentIdIssue.equalsIgnoreCase(commentId)) {

				String actualComment = js1.get("fields.comment.comments[" + i + "].body").toString();

				Assert.assertEquals(actualComment, expectedComment);

			}

		}

	}

}
