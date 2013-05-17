package serverPackage;
import java.io.*;

public class Logger {
	
	private static Logger instance = null;
	
	private PrintStream logFile;
	
	private Logger() {
		logFile = System.out;
	};
	
	public static synchronized Logger getInstance() {
		
		if(instance == null)
			instance = new Logger();
		
		return instance;
	}
	
	public synchronized void log(String message) {
		
		logFile.println(Thread.currentThread() + " : " + message);
	}
}
