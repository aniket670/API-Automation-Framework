import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import resources.Payload;

import static io.restassured.RestAssured.*;

public class DyanamicJsonAddBook {
	
	@Test(dataProvider="BooksData")
	public void addBook(String isbn, String aisle) {
		
		RestAssured.baseURI ="https://rahulshettyacademy.com";
		
	String response =	given().log().all().header("Content-type", "application/json")
		.body(Payload.addBookPayload(isbn,aisle)).when().post("Library/Addbook.php")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
		
		System.out.println(response);
		
	}
	
	@DataProvider(name="BooksData")
	public Object[][] getData() {
		
		return new Object[][]{{"oewaa", "38281"},{"ljgf","9832"},{"iuyyt","5156"}};
	}

}
