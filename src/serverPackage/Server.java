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
				String requestType = request.substring(0, request.indexOf('/'));
				System.out.println(request);
				system.makeRequest(new Request(
						RequestType.getType(requestType), "plik"));

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
