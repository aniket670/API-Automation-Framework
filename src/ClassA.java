
public class ClassA {
	
	
	public void add () {
		System.out.println("Add method from classA");
	}

public static void main(String[]args) {
		
		ClassA o = new ClassB();
		o.add();
		
		ClassA x = new ClassA();
		x.add();
	}
}


class ClassB extends ClassA {
	
	
	public void add () {
		System.out.println("Add method from classB");
	}
	
	
}