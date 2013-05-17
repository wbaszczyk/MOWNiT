import java.util.*;

public class DataStorage implements Runnable {

	private StorageScheduler storageScheduler;
	private RequestQueue requests;

	private Map<Integer, File> files;
	
	public Map<Integer, File> getFiles() {
		return files;
	}

	public DataStorage() {

		storageScheduler = new StorageScheduler(this);
		requests = new RequestQueue();
		files = new HashMap<>();
	}

	@Override
	public synchronized void run() {

		for (;;) {

			if (requests.isEmpty())
				try {
					this.wait();
				} catch (InterruptedException e) {
					continue;
				}

			processRequest(requests.popFront());
		}
	}

	public synchronized void addRequest(Request request) {

		storageScheduler.addRequest(request);
		this.notifyAll();
	}

	public RequestQueue getRequests() {
		return requests;
	}

	private synchronized void processRequest(Request request) {

		Logger.getInstance().log("przetwarzam zadanie: " + request.getType() + ", plik '" + request.getName() + "', (id " + request.getFileId() + ")");

		switch (request.getType()) {
		
		case Add:
			File newFile = File.createFile(request.getName());
			newFile.use();
			files.put(newFile.getId(), newFile);
			break;
			
		case Read:
		case Write:
			File file = files.get(request.getFileId());
			file.use();
			break;
			
		default:
			Logger.getInstance().log("no such type");
			break;
		}
		
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			//
		}

		Logger.getInstance().log("zadanie gotowe");
	}
}
