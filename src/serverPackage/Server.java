package serverPackage;

import java.net.*;
import java.io.*;

public class Server extends Thread {

	private FileSystem system;
	private ServerSocket serverSocket;
	private Socket server;

	public Server(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		serverSocket.setSoTimeout(10000);
		system = new FileSystem();
		system.run();
		ServerMonitor monitor = new ServerMonitor(system);
		new Thread(monitor).start();
	}

	public void run() {
		while (true) {
			try {
				System.out.println("Waiting for client on port "
						+ serverSocket.getLocalPort() + "...");
				server = serverSocket.accept();
				System.out.println("Just connected to "
						+ server.getRemoteSocketAddress());
				DataInputStream in = new DataInputStream(
						server.getInputStream());

				String request = in.readUTF();
				String requestTypeStr = request.substring(0,
						request.indexOf(':'));
				String requestFileId = request.substring(
						request.indexOf(':') + 1, request.indexOf('/'));
				System.out.println(request);

				int fileId = 0;
				RequestType requestType = RequestType.getType(requestTypeStr);
				if (requestType == RequestType.Add)
					system.makeRequest(new Request(requestType, 0, requestFileId));
				else {
					try {
						fileId = Integer.parseInt(requestFileId);
						system.makeRequest(new Request(requestType, fileId, ""));
					} catch (NumberFormatException e) {
						Logger.getInstance().log("Can't parse ID file");
					}
				}

				DataOutputStream out = new DataOutputStream(
						server.getOutputStream());
				out.writeUTF("Thank you for connecting to "
						+ server.getLocalSocketAddress() + "\nGoodbye!");
				server.close();

			} catch (SocketTimeoutException s) {
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}

		}
	}

	public static void main(String[] args) {

		int port = 6066;
		try {
			Thread t = new Server(port);
			t.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
