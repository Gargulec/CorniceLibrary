package lib.net.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class CorniceClient{

	//Socket
	private Socket socket;
	//In
	private DataInputStream in;
	//Out
	private DataOutputStream out;
	
	//Conecting to server
	public void connect(String adress, int port) 
	{
		try 
		{
			System.out.println("[Client] Connecting to " + adress + "...");
			socket = new Socket(adress, port);
			System.out.println("[Client] Done! Connected to " + adress + "!");
			//Creating output & input streams
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
			//Starting data sending thread
			new CorniceClientData().start();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	//Sending data
	public boolean send(String prefix, String data)
	{
		try 
		{
			this.out.writeUTF(prefix + "|" + data);
			this.out.flush();
			return true;
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	//Data acquired
	public void dataAcquired(String prefix, String data)
	{	
		System.out.println("[Data] (" + prefix + ")" + data);
	}
	
	//Getters
	public Socket getSocket() 
	{
		return socket;
	}
	
	/************************************************************/
	//Data sending thread
	private class CorniceClientData extends Thread
	{
		public void run()
		{
			while(true)
			{
				try
				{
					String s = in.readUTF();
					dataAcquired(s.split("|")[0], s.substring(2, s.length()));
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
	}
}
