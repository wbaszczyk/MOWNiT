import java.util.*;

public class DataStorage implements Runnable {

	private StorageScheduler storageScheduler;
	private RequestQueue requests;

	private List<File> files;
	
	public List<File> getFiles() {
		return files;
	}

	public DataStorage() {

		storageScheduler=new StorageScheduler(this);
		requests = new RequestQueue();
		files = new ArrayList<>();
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

			processRequest(requests.pop());
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

		Logger.getInstance().log("przetwarzam zadanie: " + request);

		switch (request.getType()) {
		case Add:
			files.add(File.createFile(request.toString()));
			Logger.getInstance().log(
					"processing request type: " + RequestType.Add + ", " + request.getName());
			break;
		case Read:
			Logger.getInstance().log(
					"processing request type: " + RequestType.Read + ", " + request.getFileId());
			break;
		case Write:
			Logger.getInstance().log(
					"processing request type: " + RequestType.Write + ", " + request.getFileId());
			break;
		default:
			Logger.getInstance().log("no such type");
			break;
		}
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
		}

		Logger.getInstance().log("zadanie gotowe: " + request);
	}
}
