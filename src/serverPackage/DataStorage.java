package serverPackage;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class DataStorage implements Runnable {

	// storage parameters
	private int totalSpace;
	private int usedSpace;
	private int freeSpace;
	
	public synchronized int getFreeSpace() {
		return freeSpace;
	}
	
	public synchronized double getFillFactor() {
		return (double)usedSpace/totalSpace;
	}
	
	private StorageScheduler storageScheduler;
	private BlockingQueue<Request> requests;
	private Map<Integer, File> files;

	public synchronized File getFile(int fileID) {
		return files.get(fileID);
	}

	public synchronized boolean containsFile(int fileID) {
		return files.containsKey(fileID);
	}

	public DataStorage(int size) {

		totalSpace = size;
		usedSpace = 0;
		freeSpace = totalSpace;

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
				Logger.getInstance().log("DataStorage.requests.take() interrupted");
				e.printStackTrace();
			}
		}
	}

	public void addRequest(Request request) {

		try {
			requests.put(request);
		} catch (InterruptedException e) {
			Logger.getInstance().log("DataStorage.requests.put() interrupted");
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
		case Delete:
			handleDeleteRequest(request);
		default:
			Logger.getInstance().log("unknown request: " + type.toString());
		}
	}
	
	private void handleAddRequest(Request request)
	{
		File newFile = File.createFile(request.getName());
		newFile.use();
		
		synchronized(this) {
			files.put(newFile.getId(), newFile);
			usedSpace += newFile.getSize();
			freeSpace -= newFile.getSize();
		}
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
	
	private void handleDeleteRequest(Request request) {
		synchronized(this) {
			files.remove(request.getFileId());
		}
	}
}
