import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;


public class StorageScheduler {

	private DataStorage storage;

	public StorageScheduler(DataStorage storage) {
		this.storage = storage;
	}

	public void addRequest(Request request) {
		
		RequestQueue rq = storage.getRequests();
		
		storage.getRequests().pushBack(request);
	}
}


