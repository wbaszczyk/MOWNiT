package serverPackage;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SystemScheduler {

	private FileSystem system;

	public SystemScheduler(FileSystem system) {

		this.system = system;
	}

	public void addRequest(Request request) {

		RequestType type = request.getType();

		switch (type) {

		case Add:
			handleAddRequest(request);
			break;
		case Read:
		case Write:
		case Delete:
			handleAccessRequest(request);
			break;
		default:
			Logger.getInstance().log("unknown request: " + type.toString());
		}
	}

	private void handleAddRequest(Request request) {

		List<DataStorage> storages = system.getStorages();
		
		try {
		Collections.sort(storages, new Comparator<DataStorage>() {

			@Override
			public int compare(DataStorage o1, DataStorage o2) {
				
				return (int) ((int) 100*(o1.getFillFactor() - o2.getFillFactor()));
			}
		});
		} catch(Exception ex) {
			//
		}
		
		
		/*
		 * Request.fileID przechowuje rozmiar w przypadku Add
		 */
		
		int randomSize = 1024*(1 + Random.nextInt(10));
		request.setFileID(randomSize);
		
		for(DataStorage ds : storages)
			if(ds.getFreeSpace() >= randomSize) {
				ds.addRequest(request);
				return;
			}
		
		Logger.getInstance().log("All storages full!");
	}

	private void handleAccessRequest(Request request) {
		for (DataStorage storage : system.getStorages())
			if (storage.containsFile(request.getFileID())) {
				storage.addRequest(request);
				return;
			}

		Logger.getInstance().log(
				"file [id=" + request.getFileID() + "] not found");
	}
}
