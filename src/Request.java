public class Request {

	private RequestType type;
	private int fileId;
	private String name;

	private Request(RequestType type) {
		this.type = type;
	}

	public Request(RequestType type, int fileId) {
		this(type);
		this.fileId = fileId;
	}

	public Request(RequestType type, String name) {
		this(type);
		this.name = name;
	}

	public RequestType getType() {
		return type;
	}

	public int getFileId() {
		return fileId;
	}

	public String getName() {
		return name;
	}

}
