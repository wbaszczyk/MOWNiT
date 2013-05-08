import java.util.*;

public class FileSystem implements Runnable {

	//private RequestQueue requests;
	private SystemScheduler systemScheduler;
	private List<DataStorage> storages;
	
	public List<DataStorage> getStorages() {
		return storages;
	}

	public FileSystem() {

		//requests = new RequestQueue();
		systemScheduler = new SystemScheduler(this);

		storages = new ArrayList<>();
		storages.add(new DataStorage());
		storages.add(new DataStorage());
		storages.add(new DataStorage());
		// reszta magazynow ...

		for (DataStorage storage : storages) {
			new Thread(storage).start();
		}

		Logger.getInstance().log("system uruchomiony!");
	}

	@Override
	public synchronized void run() {
		for (;;) {
			try { Thread.sleep(1000); }
			catch (InterruptedException e) { }
		}
		/*
		for (;;) {

			//if (requests.isEmpty())
				try {
					this.wait();
				} catch (InterruptedException e) {
					continue;
				}

			//Request next = requests.pop();

			DataStorage randomStorage = storages.get(Random.nextInt(storages
					.size()));
			//randomStorage.addRequest(next);
		}
		*/
	}

	public synchronized void makeRequest(Request request) {

		systemScheduler.addRequest(request);

		this.notifyAll();
	}
}
