
public class File {
	
	private static int nextId = 0;
	
	private int id;
	private String name;
	
	
	private File(String name) {
		this.id = nextId;
		nextId++;
		this.name = name;
	}
	
	public static synchronized File createFile(String name) {
		return new File(name);
	}
}
