import io.restassured.RestAssured;
import pojoserialization.AddPlace;
import pojoserialization.Location;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class SerializationAddPlace {

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
	
	String addPlaceResponse = given().queryParam("key", "qaclick123").log().all()
	 .body(p)
	 .when().post("/maps/api/place/add/json")
	 .then().statusCode(200).extract().asString();
	
	System.out.println(addPlaceResponse);

	}

}
