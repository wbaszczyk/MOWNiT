
public class Main {
	
	public static void main(String[] args) {
		
		FileSystem system = new FileSystem();
		new Thread(system).start();
		
		
		// podlacz klientow
		
		for(int i=0; i<10; ++i) {
			system.makeRequest(new Request(RequestType.Add));
		}
		Logger.getInstance().log("pierwsza pula klientow");
		
		try { Thread.sleep(1000); }
		catch (InterruptedException e) { }
		
		for(int i=0; i<10; ++i) {
			system.makeRequest(new Request(RequestType.Read));
		}
		Logger.getInstance().log("druga pula klientow");
	}

}
