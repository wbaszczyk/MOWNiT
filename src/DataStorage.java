
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
		
		try {
			 switch (request.getType()) {
	            case Add:
	        		Logger.getInstance().log("processing request type: " + RequestType.Add);
	                break;
	            case Read:
	        		Logger.getInstance().log("processing request type: " + RequestType.Read);
	                break;
	            case Write:
	        		Logger.getInstance().log("processing request type: " + RequestType.Write);
	                break;
	            default:
	        		Logger.getInstance().log("no such type");
	                break;
	        }
			Thread.sleep(200);
		}
		catch (InterruptedException e) { }
		
		Logger.getInstance().log("zadanie gotowe: " + request);
	}
}
