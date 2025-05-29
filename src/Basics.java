
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import resources.Payload;
import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.testng.Assert;

public class Basics {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//End to end test case, first adding the place, updating the address and then retrieving the latest address updation using POST, PUT and GET calls resp.
		
		//POST call
		RestAssured.baseURI="https://rahulshettyacademy.com";
		//RestAssured.baseURI ="https://google.com";
		
		String response =	given().log().all().queryParam("key", "qaclick123").header("Content-type","Application/json")
		.body(new String (Files.readAllBytes(Path.of("C:\\Users\\Aniket_Khandagale\\Documents\\My Learning\\APIAutomation_Demo\\src\\resources\\AddPlace.json"))))
			.when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).extract().asString();
		
		System.out.println(response);
		JsonPath js = new JsonPath(response);
		String placeId = js.getString("place_id");
		
		

		/*
		 * JsonPath js = new JsonPath(response);
		 * 
		 * String placeId= js.getString("place_id");
		 * 
		 * //PUT call String newAddress ="70 Lane, South Wales";
		 * given().queryParam("key",
		 * "qaclick123").header("Content-type","Application/json") .body("{\r\n" +
		 * "\"place_id\":\""+placeId+"\",\r\n" + "\"address\":\""+newAddress+"\",\r\n" +
		 * "\"key\":\"qaclick123\"\r\n" + "}").when().put("maps/api/place/update/json")
		 * .then().assertThat().log().all().statusCode(200).body("msg",
		 * equalTo("Address successfully updated"));
		 * 
		 * //get call String getResponse= given().queryParam("key",
		 * "qaclick123").queryParam("place_id", placeId)
		 * .when().get("maps/api/place/get/json")
		 * .then().log().all().statusCode(200).extract().asString();
		 * 
		 * JsonPath js1 = new JsonPath(getResponse); String actualAddress=
		 * js1.getString("address");
		 * 
		 * System.out.println(actualAddress);
		 * 
		 * Assert.assertEquals(actualAddress, newAddress);
		 */
	}

}

