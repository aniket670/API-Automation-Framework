
public interface XYZ {
	
	default void display() {
		System.out.println("this is default method of interface");
	}

	void show();
	
	static void hello(){
		System.out.println("hello static");
	}
}
