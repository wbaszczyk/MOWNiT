public class SystemScheduler {

	private FileSystem system;

	// dodaæ interfejs

	public SystemScheduler(FileSystem system) {

		this.system = system;
	}

	public void addRequest(Request request) {

		switch (request.getType()) {
		case Add:
			system.getStorages().get(
					Random.nextInt(system.getStorages().size()));
			break;
		case Read:
		case Write:
			for (DataStorage storage : system.getStorages())
				for (File file : storage.getFiles())
					if (file.getId() == request.getFileId()) {
						storage.addRequest(request);
						return;
					}
		}

		Logger.getInstance().log("nie ma pliku!");
	}
}
