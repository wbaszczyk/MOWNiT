
public class FileSystem implements Runnable {
	
	// wstrzyknac w konstruktorze?
	private RequestQueue requestQueue; // + jakis listener na queue
	private Scheduler scheduler;
	
	@Override
	public void run() {
		
		for(;;) {
			if(queue.empty()) wait
			
			processRequest(queue.removeFirst()) // w nowym watku?
		}
	}
	
	// handler, uzytkownik chce dostepu do pliku
	void makeRequest(Request req) {
		requestQueue.add(req);
		scheduler.sort(requestQueue); // uaktualnij kolejke
		
	}
	
	void processRequest(Request req) {
		// przetwarza zadanie 
	}
}
