package lib.net.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class CorniceServerClient extends Thread{

	private Socket socket;
	//In
	private DataInputStream in;
	//Out
	private DataOutputStream out;
	//Constructor
	public CorniceServerClient(Socket socket)
	{
		try
		{
			this.socket = socket;
			//
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
			//Server event
			CorniceServer.server.clientConnected(this);
		}
		catch(Exception e)
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
	//Sending message
	public boolean message(String message)
	{
		try 
		{
			this.out.writeUTF("m|" + message);
			this.out.flush();
			return true;
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		return false;
	}
	
	//Getters
	public Socket getSocket() 
	{
		return socket;
	}
//	
//	public PrintWriter getOut() 
//	{
//		return out;
//	}
//	
//	public BufferedReader getIn() 
//	{
//		return in;
//	}
	
}
