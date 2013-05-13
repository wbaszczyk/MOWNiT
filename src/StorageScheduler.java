import java.util.Map;


public class StorageScheduler {

	private DataStorage storage;

	public StorageScheduler(DataStorage storage) {
		this.storage = storage;
	}

	public void addRequest(Request request) {
		
		RequestQueue rq = storage.getRequests();
		Map<Integer, File> files = storage.getFiles();
		
		
		File reqFile = files.get(request.getFileId());
		
		int iter = 0;
		
		while(iter < rq.size())
		{
			if(rq.get(iter).getType() == RequestType.Add) {
				iter++;
				continue;
			}
			
			if(request.getType() == RequestType.Add)
				break;
			
			int fileId = rq.get(iter).getFileId();
			File file = files.get(fileId);
			
			if(file.getLastAccess().before(reqFile.getLastAccess())) {
				// najstarsze na poczatek
				iter++;
				continue;
			}
			
			break;
		}
		
		storage.getRequests().insert(request, iter);
	}	
}
