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
		storages.add(new DataStorage());
		//storages.add(new DataStorage());
		//storages.add(new DataStorage());
		// reszta magazynow ...
	}
	
	public void run()
	{
		for (DataStorage storage : storages) {
			new Thread(storage).start();
		}

		Logger.getInstance().log("system uruchomiony!");
	}

	public synchronized void makeRequest(Request request) {

		systemScheduler.addRequest(request);

		this.notifyAll();
	}
}
