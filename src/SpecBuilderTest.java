import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojoserialization.AddPlace;
import pojoserialization.Location;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class SpecBuilderTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	AddPlace p = new AddPlace();
	List<String> mylist = new ArrayList<>();
	
	mylist.add("shoe park");
	mylist.add("shop");
	
	p.setTypes(mylist);
	
	Location loc = new Location();
	loc.setLat(-38.383494);
	loc.setLng(33.427362);
	
	p.setLocation(loc);
	p.setAccuracy(50);
	p.setAddress("29, side layout, cohen 09");
	p.setName("White House");
	p.setPhone_number("(+91) 983 893 3937");
	p.setWebsite("https://google.com");
	p.setLanguage("United Kingdom - En");
	
	RestAssured.baseURI ="https://rahulshettyacademy.com";
	
	RequestSpecification reqspec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").
			addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
	
   ResponseSpecification resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
	
   	RequestSpecification req = given().spec(reqspec).body(p);
	
   	Response response = req.when().post("/maps/api/place/add/json").then().spec(resspec).extract().response();
   	
	String addPlaceResponse = response.asString();
	
	System.out.println(addPlaceResponse);

	}

}
