package clientsPackage;

import java.io.IOException;
import java.util.*;

import serverPackage.Config;
import serverPackage.FileSystem;
import serverPackage.Request;
import serverPackage.RequestType;
import serverPackage.ServerMonitor;

public class StressTest {

	public static void main(String[] args) {
		
		Config.getInstance().storages = 15;
		
		// THREAD - SAFE instantiate
		GlobalList.get();
		
		FileSystem system = new FileSystem();
		
		system.run();
		
		ServerMonitor monitor = new ServerMonitor(system);
		new Thread(monitor).start();
		         
		
		int testSize = Config.getInstance().storages*20;
		int iter = 0;
		List<Integer> files = new ArrayList<>();
		
		for(int i=0; i<testSize; ++i) {
			
			files.add(iter);
			iter++;
			
			system.makeRequest(new Request(RequestType.Add, 0, "plik " + iter));
			
			try { Thread.sleep(30); }
			catch (InterruptedException e) { }
		}

		
		try { Thread.sleep(1000); }
		catch (InterruptedException e) { }
		
		GlobalList.get().clear();
		
		long startTime = System.currentTimeMillis();
		
		for(int i=0; i<testSize; ++i) {
			
			int action = serverPackage.Random.nextInt(4);
			
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
			case 3:
				files.add(iter);
				iter++;
				system.makeRequest(new Request(RequestType.Add, 0, "plik " + iter));
				break;
			}
			
			try { Thread.sleep(3); }
			catch (InterruptedException e) { }
			
			System.out.println("main loop");
		}
		
		System.out.println("main done at: " + (System.currentTimeMillis()-startTime)/1000.0);
		
		// pause ...
		
		try {
			int x = System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		long endTime = System.currentTimeMillis();
		
		GlobalList.get().dump();
		
		System.out.println("size: " + GlobalList.get().size() + "! lol.");
		//System.out.println("workTime: " + (endTime-startTime)/1000.0);
		//System.out.println("avg request time: " + GlobalList.get().avg()/1000.0);
		System.out.println("{ " + testSize/20 + ", " + (endTime-startTime)/1000.0 + ", " + GlobalList.get().avg()/1000.0 + " }");
	}

}
