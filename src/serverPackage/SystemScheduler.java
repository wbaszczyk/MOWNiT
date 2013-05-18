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
			handleAccessRequest(request);
			break;
		case Write:
			handleAccessRequest(request);
			break;
		default:
			Logger.getInstance().log("unknown request: " + type.toString());
		}
	}

	private void handleAddRequest(Request request) {

		List<DataStorage> storages = system.getStorages();

		Collections.sort(storages, new Comparator<DataStorage>() {

			@Override
			public int compare(DataStorage o1, DataStorage o2) {
				return (int) Math.abs(o1.getFillFactor() - o2.getFillFactor());
			}
		});
		
		

		int randomStorageId = Random.nextInt(system.getStorages().size());
		DataStorage randomStorage = system.getStorages().get(randomStorageId);
		randomStorage.addRequest(request);
	}

	private void handleAccessRequest(Request request) {
		for (DataStorage storage : system.getStorages())
			if (storage.containsFile(request.getFileId())) {
				storage.addRequest(request);
				return;
			}

		Logger.getInstance().log(
				"file [id=" + request.getFileId() + "] not found");
	}
}
