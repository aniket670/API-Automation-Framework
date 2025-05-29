
public class Test implements XYZ {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Test ob1 = new Test();
		XYZ.hello();
		//ob1.display();
		//ob1.show();
		
			
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		System.out.println("show method implmeneted in class");
		
	}
	
	public void display1() {
		System.out.println("method implemented in class");
		XYZ.super.display();
		
	}

}
