import java.util.ArrayList;


public class FileSystem implements Runnable {
	
	private RequestQueue requestQueue;
	private Scheduler scheduler;
	Collection<DataStorage> dataStorage;
	
	public FileSystem(RequestQueue requestQueue, Scheduler scheduler) {
		this.requestQueue = requestQueue;
		this.scheduler = scheduler;
		this.scheduler.setSystem(this);
		
		dataStorage = new ArrayList<>();
		dataStorage.add(new DataStorage(/* rozmiar, ... */));
	}
	
	@Override
	public void run() {
		
		for(;;) {
			if(requestQueue.empty()) this.wait(); // Object.wait()
			
			processRequest(requestQueue.removeFirst());
		}
	}
	
	void makeRequest(Request request) {
		// tu wchodzi sterowanie innego watku:
		requestQueue.add(request);
		scheduler.sort(requestQueue); // uaktualnij kolejke
		this.notifyAll(); // for kontynuuje
	}
	
	void processRequest(Request req) {
		// to powinno byc w nowym watku??
	}
}
