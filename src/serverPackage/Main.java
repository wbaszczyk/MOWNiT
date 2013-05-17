package serverPackage;
public class Main {

	public static void main(String[] args) {

		FileSystem system = new FileSystem();
		system.run();
		
		// podlacz klientow

		for (int i = 0; i < 100; ++i) {
			system.makeRequest(new Request(RequestType.Add, "stary_plik " + i));
		}
		//Logger.getInstance().log("pierwsza pula klientow");
		
		/*
		
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
		}
		
		for (int i = 10; i < 20; ++i) {
			system.makeRequest(new Request(RequestType.Add, "nowy_plik " + i));
		}
		Logger.getInstance().log("druga pula klientow");
		
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		
		for (int i = 0; i < 5; ++i) {
			system.makeRequest(new Request(RequestType.Read, i));
			Thread.yield();
		}
		
		for (int i = 10; i < 15; ++i) {
			system.makeRequest(new Request(RequestType.Read, i));
			Thread.yield();
		}
		*/
	}

}
