import java.util.*;

public class FileSystem implements Runnable {

	private RequestQueue requests;
	private Scheduler scheduler;
	List<DataStorage> storages;

	public FileSystem() {

		requests = new RequestQueue();
		scheduler = new Scheduler(this);

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

			if (requests.isEmpty())
				try {
					this.wait();
				} catch (InterruptedException e) {
					continue;
				}

			Request next = requests.pop();

			DataStorage randomStorage = storages.get(Random.nextInt(storages
					.size()));
			randomStorage.addRequest(next);
		}
	}

	public synchronized void makeRequest(Request request) {

		requests.push(request);

		// tutaj scheduler powinien jakos kolejkowac requesty

		this.notifyAll();
	}
}
