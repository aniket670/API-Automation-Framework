import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import resources.Payload;

public class SumValidation {
	
	//Verify if Sum of all Course prices matches with Purchase Amount
	@Test
	public void sumOfCourses() {
		
		JsonPath js = new JsonPath(Payload.complexJsonMockResponse());
		int sum = 0;
		
		int count = js.getInt("courses.size()");
		
		for (int i =0; i<count; i++) {
		 int price = js.getInt("courses["+i+"].price");
		 int copies = js.getInt("courses["+i+"].copies");
		 
		 int amount = price *copies;
		 sum = sum +amount ;
		}
		System.out.println("The total sum amount :"+sum);
		
	 int expectedAmount = js.getInt("dashboard.purchaseAmount");
	 Assert.assertEquals(sum, expectedAmount);
	}

}
