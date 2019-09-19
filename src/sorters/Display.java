package sorters;

public class Display {
	private String message;
	public Display(String message) {
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	public static void show(String m){
		System.out.println(m);
	}
}
