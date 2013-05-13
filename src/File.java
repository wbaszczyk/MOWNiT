import java.util.Date;

public class File {

	private static int nextId = 0;

	private int id;
	private String name;
	private Date lastAccess;

	private File(String name) {
		this.id = nextId;
		nextId++;
		this.name = name;
		this.lastAccess = new Date();
	}

	public static synchronized File createFile(String name) {
		return new File(name);
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public Date getLastAccess() {
		return lastAccess;
	}
	
	public void use()
	{
		lastAccess = new Date();
		Logger.getInstance().log("u¿ywany plik: '" + getName() + "', (id " + getId() + ")");
	}
}
