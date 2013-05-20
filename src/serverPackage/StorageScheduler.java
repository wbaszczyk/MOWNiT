package serverPackage;

import java.util.Comparator;

public class StorageScheduler implements Comparator<Request> {

	private DataStorage storage;

	public StorageScheduler(DataStorage storage) {
		this.storage = storage;
	}

	@Override
	public int compare(Request o1, Request o2) {

		// o1 < o2 -> -1
		// o1 = 02 -> 0
		// o1 > o2 -> 1

		if (o1.getType() == RequestType.Add)
			return 1; // Add has highest priority

		if (o2.getType() == RequestType.Add)
			return -1; // Add has highest priority

		File f1 = storage.getFile(o1.getFileID());
		File f2 = storage.getFile(o2.getFileID());
		
		int sizeDiff = f2.getSize() - f2.getSize();
		
		if(Math.abs(sizeDiff) < 0.2*f2.getSize())
			return f2.getLastAccess().compareTo(f1.getLastAccess());
		else return sizeDiff/Math.abs(sizeDiff);
	}
}
