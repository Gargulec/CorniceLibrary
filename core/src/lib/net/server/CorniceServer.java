package lib.net.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class CorniceServer{

	static CorniceServer server;
	//Server socket
	ServerSocket serverSocket;
	//Clients list
	ArrayList<CorniceServerClient> clients = new ArrayList<CorniceServerClient>();
	
	//Starting server
	public void start(int port)
	{
		try
		{
			//Starting server with specified port
			System.out.println("[Server] Starting server...");
			serverSocket = new ServerSocket(port);
			System.out.println("[Server] Done!");
			server = this;
			//Starting thread
			System.out.println("[Server] Running clients thread...");
			new CorniceServerClients().start();
			System.out.println("[Server] Done!");
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	//Client connected
	public void clientConnected(CorniceServerClient client)
	{
		System.out.println("[Server] Client(" + client.getSocket().getInetAddress() + ") connected!");
		client.message("Hello!");
	}
	
	//Clients
	private class CorniceServerClients extends Thread
	{
		Socket client;
		
		public void run()
		{
			while(true)
			{
				try
				{
					System.err.println("[Server] Waiting for clients!");
					client = serverSocket.accept();
					clients.add(new CorniceServerClient(client));
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
	}
}