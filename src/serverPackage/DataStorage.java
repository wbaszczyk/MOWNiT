package serverPackage;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class DataStorage implements Runnable {

	// parameters
	int totalSize;
	int usedSize;
	int freeSize;

	private StorageScheduler storageScheduler;
	private BlockingQueue<Request> requests;
	private Map<Integer, File> files;

	public File getFile(int fileID) {
		return files.get(fileID);
	}

	public synchronized boolean containsFile(int fileID) {
		return files.containsKey(fileID);
	}

	public DataStorage(int size) {

		totalSize = size;
		usedSize = 0;
		freeSize = totalSize;

		storageScheduler = new StorageScheduler(this);
		requests = new PriorityBlockingQueue<>(10, storageScheduler);
		files = new HashMap<>();
	}
	

	@Override
	public void run() {

		for (;;) {

			try {
				Request request = requests.take();
				processRequest(request);
			} catch (InterruptedException e) {
				Logger.getInstance().log("DataStorage.requests.take() failed");
				e.printStackTrace();
			}
		}
	}

	public void addRequest(Request request) {

		try {
			requests.put(request);
		} catch (InterruptedException e) {
			Logger.getInstance().log("DataStorage.requests.put() failed");
			e.printStackTrace();
		}
	}

	private void processRequest(Request request) {
		
		RequestType type = request.getType();
		
		Logger.getInstance().log(request.toString());

		switch (type) {

		case Add:
			handleAddRequest(request);
			break;
		case Read:
			handleReadRequest(request);
			break;
		case Write:
			handleWriteRequest(request);
			break;
		default:
			Logger.getInstance().log("unknown request: " + type.toString());
		}
	}
	
	private void handleAddRequest(Request request)
	{
		File newFile = File.createFile(request.getName());
		newFile.use();
		files.put(newFile.getId(), newFile);
	}
	
	private void handleReadRequest(Request request)
	{
		File file = getFile(request.getFileId());
		file.use();

		try { Thread.sleep(1000); }
		catch (InterruptedException e) { }
	}
	
	private void handleWriteRequest(Request request)
	{
		File file = getFile(request.getFileId());
		file.use();

		try { Thread.sleep(1000); }
		catch (InterruptedException e) { }
	}
}
