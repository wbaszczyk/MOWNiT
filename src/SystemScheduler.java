public class SystemScheduler {

	private FileSystem system;

	// dodaæ interfejs

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
				for (File file : storage.getFiles())
					if (file.getId() == request.getFileId()) {
						storage.addRequest(request);
						return;
					}
		default:
			Logger.getInstance().log("no such request type");
		}
		Logger.getInstance().log("no such file!");
	}
}
