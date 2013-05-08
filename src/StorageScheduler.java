
public class StorageScheduler {

	private DataStorage storage;

	public StorageScheduler(DataStorage storage) {
		this.storage = storage;
	}

	public void addRequest(Request request) {
		
		storage.getRequests().pushBack(request);
	}	
}
