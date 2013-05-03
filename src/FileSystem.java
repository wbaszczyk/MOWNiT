import java.util.*;


public class FileSystem implements Runnable {
	
	private RequestQueue requests;
	private Scheduler scheduler;
	Collection<DataStorage> storages;
	
	public FileSystem() {
		
		requests = new RequestQueue();
		scheduler = new Scheduler(this);
		
		storages = new ArrayList<>();
		storages.add(new DataStorage());
		
		Logger.getInstance().log("system uruchomiony!");
	}
	
	@Override
	public synchronized void run() {
		
		for(;;) {
			
			if(requests.isEmpty())
				try { this.wait(); }
				catch (InterruptedException e) { continue; }
			
			processRequest(requests.pop());
		}
	}
	
	public synchronized void makeRequest(Request request) {
		
		requests.push(request);
		
		// tutaj scheduler powinien jakos kolejkowac requesty
		
		this.notifyAll();
	}
	
	private void processRequest(Request request) {
		
		// blokuje system
		// to powinno byc w watku DataStorage
		
		Logger.getInstance().log("przetwarzam zadanie: " + request);
		
		try { Thread.sleep(200); }
		catch (InterruptedException e) { }
		
		Logger.getInstance().log("zadanie gotowe: " + request);
	}
}
