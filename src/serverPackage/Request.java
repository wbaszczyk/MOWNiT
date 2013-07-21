package serverPackage;

public class Request {

	private RequestType type;
	private int fileID;
	private String name;
	
	public double createTime;

	public Request(RequestType type, int fileID, String name) {
		this.type = type;
		this.fileID = fileID;
		this.name = name;
		
		createTime = System.currentTimeMillis();
	}

	public RequestType getType() {
		return type;
	}
	
	public void setFileID(int val) {
		fileID = val;
	}

	public int getFileID() {
		return fileID;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Request: [type=" + type + "] [fileID=" + fileID + "] [name='"
				+ name + "']";
	}
}
