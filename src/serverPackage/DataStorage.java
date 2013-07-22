package serverPackage;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class DataStorage extends Thread /*implements Runnable*/ {

	// storage parameters
	private int totalSpace;
	private int usedSpace;
	private int freeSpace;

	public synchronized int getFreeSpace() {
		return freeSpace;
	}

	public synchronized double getFillFactor() {
		return (double) usedSpace / totalSpace;
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
				Logger.getInstance().log(
						"DataStorage.requests.take() interrupted");
				e.printStackTrace();
			}
		}
	}

	public void addRequest(Request request) {

		try {
			request.createTime = System.currentTimeMillis();
			requests.put(request);
		} catch (InterruptedException e) {
			Logger.getInstance().log("DataStorage.requests.put() interrupted");
			e.printStackTrace();
		}
	}

	private void processRequest(Request request) {
		
		clientsPackage.GlobalList.get().put(System.currentTimeMillis()-request.createTime);
		
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
			break;
		default:
			Logger.getInstance().log("unknown request: " + type.toString());
		}
	}

	private void handleAddRequest(Request request) {
		int fileSize = request.getFileID();

		File newFile = File.createFile(request.getName(), fileSize);
		newFile.use();

		synchronized (this) {
			files.put(newFile.getID(), newFile);
			usedSpace += newFile.getSize();
			freeSpace -= newFile.getSize();
		}

		Logger.getInstance().log("New file: " + newFile);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
		}
	}

	private void handleReadRequest(Request request) {
		File file = getFile(request.getFileID());
		file.use();

		Logger.getInstance().log("Reading file: " + file);
		try {
			Thread.sleep(file.getSize()/10);
		} catch (InterruptedException e) {
		}
	}

	private void handleWriteRequest(Request request) {
		File file = getFile(request.getFileID());
		file.use();

		Logger.getInstance().log("Writing file: " + file);
		try {
			Thread.sleep(file.getSize()/10);
		} catch (InterruptedException e) {
		}
	}

	private void handleDeleteRequest(Request request) {
		File file = getFile(request.getFileID());
		synchronized (this) {
			files.remove(file.getID());
			usedSpace -= file.getSize();
			freeSpace += file.getSize();
		}

		Logger.getInstance().log("Deleted file: " + file);
		try {
			Thread.sleep(file.getSize()/10);
		} catch (InterruptedException e) {
		}
	}
}
