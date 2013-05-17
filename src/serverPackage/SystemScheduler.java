package serverPackage;
public class SystemScheduler {

	private FileSystem system;

	public SystemScheduler(FileSystem system) {

		this.system = system;
	}

	public void addRequest(Request request) {
		
		switch (request.getType()) {
		
		case Add:
			int randomStorageId = Random.nextInt(system.getStorages().size());
			DataStorage randomStorage = system.getStorages().get(randomStorageId);
			randomStorage.addRequest(request);
			return;
			
		case Read:
		case Write:
			for (DataStorage storage : system.getStorages())
				if(storage.getFiles().containsKey(request.getFileId())) {
					storage.addRequest(request);
					return;
				}
			Logger.getInstance().log("file id " + request.getFileId() + " not found");
			break;
		}
	}
}
