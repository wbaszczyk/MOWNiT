package serverPackage;

public enum RequestType {
	Add, Read, Write;

	public static RequestType getType(String type) {
		switch(type){
			case "Add": return Add;
			case "Read": return Read;
			case "Write": return Write;
		}
		return null;
	}
}
