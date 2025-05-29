
public interface ABC {
	
	default void display() {
		System.out.println("this is default method of interface");
	}

	void show();
}
