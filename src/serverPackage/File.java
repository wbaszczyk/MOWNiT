package serverPackage;

import java.util.Date;

public class File {

	private static int nextId = 0;

	private int id;
	private String name;
	private Date lastAccess;
	private int size;

	private File(String name, int size) {
		this.id = nextId;
		nextId++;
		this.name = name;
		this.size = size;
		this.lastAccess = new Date();
	}

	public static synchronized File createFile(String name, int size) {
		return new File(name, size);
	}

	public int getID() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Date getLastAccess() {
		return lastAccess;
	}
	
	public int getSize() {
		return size;
	}

	public void use() {
		lastAccess = new Date();
	}

	@Override
	public String toString() {
		return "File: [fileID=" + id + "] [name='" + name + "']";
	}
}
