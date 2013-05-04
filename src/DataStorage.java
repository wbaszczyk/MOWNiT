
public class DataStorage implements Runnable {
	
	private RequestQueue requests;
	
	public DataStorage() {
		
		requests = new RequestQueue();
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
	
	public synchronized void addRequest(Request request) {
		
		requests.push(request);
		this.notifyAll();
	}
	
	private synchronized void processRequest(Request request) {

		Logger.getInstance().log("przetwarzam zadanie: " + request);
		
		try { Thread.sleep(200); }
		catch (InterruptedException e) { }
		
		Logger.getInstance().log("zadanie gotowe: " + request);
	}
}
