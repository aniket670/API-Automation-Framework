import org.testng.Assert;

import io.restassured.path.json.JsonPath;
import resources.Payload;

public class ComplexJsonParse {
	
	/* 
	 * 1. Print No of courses returned by API
	   2.Print Purchase Amount
	   3. Print Title of the first course
	   4. Print All course titles and their respective Prices
	   5. Print no of copies sold by RPA Course
	   6. Verify if Sum of all Course prices matches with Purchase Amount
	 * 
	 * 
	 * 
	 * 
	 * 
	 * */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JsonPath js = new JsonPath(Payload.complexJsonMockResponse());
		//Print No of courses returned by API
		int noOfCourse = js.getInt("courses.size()");
		System.out.println("Total no. of courses: " +noOfCourse);
		
		//Print Purchase Amount
		int purchaseAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println("The total purchase amount :"+purchaseAmount);
		
		//Print Title of the first course
		String title = js.getString("courses[0].title");
		System.out.println("Title of first course is :" +title);
		
		//Print All course titles and their respective Prices
		System.out.println("****************************************************");
		
		for (int i = 0; i<noOfCourse; i++) {
			
			System.out.println(js.getString("courses["+i+"].title").toString());
			System.out.println(js.getString("courses["+i+"].price").toString());
		}
		
		System.out.println("Print no of copies sold by RPA Course");
		
			for (int i = 0; i<noOfCourse; i++) {
				
				String titles = js.getString("courses["+i+"].title");
				if(titles.equalsIgnoreCase("RPA")) {
					System.out.println(js.getString("courses["+i+"].copies").toString());
					break;
				}
		}
		
			System.out.println("Verify if Sum of all Course prices matches with Purchase Amount");
			int expectedPurchaseAmnt = js.getInt("dashboard.purchaseAmount");
			System.out.println("The expected amount :"+expectedPurchaseAmnt);
			
			int tempAmount =0;
			for(int i =0; i<noOfCourse; i++) {
				int price =js.getInt("courses["+i+"].price");
				int copies = js.getInt("courses["+i+"].copies");
				int amount = price*copies;
				tempAmount=tempAmount+amount;
			}
			System.out.println("The actual amount :"+tempAmount);
			Assert.assertEquals(expectedPurchaseAmnt, tempAmount);
			

	}

}
