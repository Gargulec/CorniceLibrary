package lib.net;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class CorniceNet {

	//Downloading file
	public static File download(String URL, String output)
	{
		try
		{
			URL website = new URL(URL);
			ReadableByteChannel rbc = Channels.newChannel(website.openStream());
			FileOutputStream fos = new FileOutputStream(output);
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			
			return new File(output);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
}
