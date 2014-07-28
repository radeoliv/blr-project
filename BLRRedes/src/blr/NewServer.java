package blr;

import java.io.*;
import java.net.*;

public class NewServer {
	
	static int [] image;
	static byte[] audio;
	Object aux;
	
	public NewServer() throws Exception{
		ImageServer is = new ImageServer();
		
		try{
			ServerSocket serv = new ServerSocket(7000);
		    System.out.println("Server running!");
	        
	            
	        while(true){           
				Socket s = serv.accept();
				
				ObjectInputStream inAudio = new ObjectInputStream (s.getInputStream());
				ObjectOutputStream outMix = new ObjectOutputStream (s.getOutputStream());
				ObjectOutputStream outMix2 = new ObjectOutputStream (s.getOutputStream());

				aux = inAudio.readObject();
				
				image = is.convertionAudioImage((byte[])aux);
				audio = is.convertionImageAudio(image);
			
				outMix.writeObject(image);
				outMix.writeObject(audio);
				
	        }
		} catch(IOException e){	           
            System.out.println("Problema ¬¬");   
        }	            
	}
	
	public static void main(String[] args) throws Exception{
		new NewServer();
	}
	
}