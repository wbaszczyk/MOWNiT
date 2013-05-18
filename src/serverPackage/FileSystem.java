package serverPackage;

import java.util.*;

public class FileSystem {

	private SystemScheduler systemScheduler;
	private List<DataStorage> storages;

	public List<DataStorage> getStorages() {
		return storages;
	}

	public FileSystem() {

		systemScheduler = new SystemScheduler(this);

		storages = new ArrayList<>();
		storages.add(new DataStorage(1024*128));
		storages.add(new DataStorage(1024*32));
		storages.add(new DataStorage(1024*64));
		// reszta magazynow ...
	}

	public void run() {
		for (DataStorage storage : storages) {
			new Thread(storage).start();
		}

		Logger.getInstance().log("system uruchomiony!");
	}

	public void makeRequest(Request request) {

		systemScheduler.addRequest(request);
	}
}
