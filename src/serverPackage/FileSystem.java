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
		
		for(int i=0; i<20; ++i) {
			int storageSize = 1024 * (100 + Random.nextInt(50));
			storages.add(new DataStorage(storageSize));
		}
	}

	public void run() {
		
		for (DataStorage storage : storages) {
			//new Thread(storage).start();
			storage.start();
		}

		Logger.getInstance().log("system uruchomiony!");
	}

	public void makeRequest(Request request) {

		systemScheduler.addRequest(request);
	}
}
