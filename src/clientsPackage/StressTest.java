package clientsPackage;

import java.util.*;

import serverPackage.FileSystem;
import serverPackage.Request;
import serverPackage.RequestType;
import serverPackage.ServerMonitor;

public class StressTest {

	public static void main(String[] args) {
		
		FileSystem system = new FileSystem();
		system.run();
		
		ServerMonitor monitor = new ServerMonitor(system);
		new Thread(monitor).start();
		
		
		int testSize = 300;
		int iter = 0;
		List<Integer> files = new ArrayList<>();
		
		for(int i=0; i<testSize; ++i) {
			
			files.add(iter);
			iter++;
			
			system.makeRequest(new Request(RequestType.Add, 0, "plik " + iter));
			
			try { Thread.sleep(30); }
			catch (InterruptedException e) { }
		}
		
		
		for(int i=0; i<testSize; ++i) {
			
			int action = serverPackage.Random.nextInt(4);
			
			if(action == 3) {
				files.add(iter);
				iter++;
				system.makeRequest(new Request(RequestType.Add, 0, "plik " + iter));
			}
			
			int randIndex = serverPackage.Random.nextInt(files.size());
			int actionIndex = files.remove(randIndex);
			
			switch(action)
			{
			case 0:
				system.makeRequest(new Request(RequestType.Read, actionIndex, ""));
				break;
			case 1:
				system.makeRequest(new Request(RequestType.Write, actionIndex, ""));
				break;
			case 2:
				system.makeRequest(new Request(RequestType.Delete, actionIndex, ""));
				break;
			}
		}
	}

}
