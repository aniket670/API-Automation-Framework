package basicauthentication;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import pojoimplementation.Api;
import pojoimplementation.GetCourses;
import pojoimplementation.Mobile;
import pojoimplementation.webAutomation;

public class AuthenticationImplementation {

	public static void main(String[] args) {

		// TODO Auto-generated method stub

		// getting the code value from the GetCode API endpoint

		String[] courseTitles= { "Selenium Webdriver Java","Cypress","Protractor"};
		
		  String url
		  ="https://rahulshettyacademy.com/getCourse.php?code=4%2F0AX4XfWiVAjWclo4QDy5NsmOOG3Onx_bVJNuf8F4_VPsYOTSkSxKtFT6V_ynXjIV05tOVNA&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none#";
		  
		  String partial_code = url.split("code=")[1]; 
		  String code =partial_code.split("&scope")[0];
		  
		  System.out.println(code);
		  
		  System.out.println(
		  "****************************************************************");
		  
		  // getting the value of access token 
		  String response1 =
		  given().urlEncodingEnabled(false) .queryParams("code",code)
		  .queryParams("client_id",
		  "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		  .queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
		  .queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
		  .queryParams("grant_type","authorization_code") .when().log().all()
		  .post("https://www.googleapis.com/oauth2/v4/token") .asString();
		  
		  JsonPath js = new JsonPath(response1); 
		  String access_token =
		  js.getString("äccess_token");
		 
		String access_token1 = "ya29.a0ARrdaM_6c4JIJ6CAnGNUNQNwxNQtRdtGG7nxHHe_LnmBpRB80UZXu1rtiREv6KJJp9H6UWb4SykiS904xuwr"
				+ "7OBjDopwQWAjGJvajAvv0JgDMaX7lWPlIwFZaKPE5Fhg7ahDpOo9-iGDgFSPc-ruMl8ewtKykg";

		// hitting actual request API endpoint URL
		GetCourses gc = given().contentType("application/json").queryParam("access_token", access_token1).expect()
				.defaultParser(Parser.JSON).when().get("https://rahulshettyacademy.com/getCourse.php").then().extract()
				.response().as(GetCourses.class);
		
		 System.out.println(gc.getLinkedIn());
		 
		  List <Api> apiCourses=gc.getCourses().getApi();
		   //System.out.println(apiCourses.size());
		   
		   for (int i =0 ; i<apiCourses.size(); i++) {
			   
			String c = apiCourses.get(i).getCourseTitle();
			 	if (c.equalsIgnoreCase("SoapUI Webservices testing")) {
			 		System.out.println(apiCourses.get(i).getPrice());
			 	}
		   }
		
		   //Get the names of WebAutomation  
		   ArrayList <String> a = new ArrayList<>();  
		   List <webAutomation> w = gc.getCourses().getWebAutomation();
		   
		   for(int i =0 ; i<w.size(); i++) {
			   
			  a.add(w.get(i).getCourseTitle());
		   }
		
		   List<String> expectedList = Arrays.asList(courseTitles);
		   
		   Assert.assertTrue(a.equals(expectedList));
	}

}
