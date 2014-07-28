package blr;

import java.io.*;
import java.net.*;

public class NewCliente {
	Object aux;
	Object aux2;
	ImageClient ic = new ImageClient();
	
	public NewCliente (String address) throws Exception {
		Socket s = new Socket(address, 7000);
		
		ObjectOutputStream outAudio = new ObjectOutputStream (s.getOutputStream());
		ObjectInputStream inImage = new ObjectInputStream (s.getInputStream());
		ObjectInputStream inAudio = new ObjectInputStream (s.getInputStream());
		
		outAudio.writeObject(ic.sendAudio());
		
		aux = inImage.readObject();
		aux2 = inAudio.readObject();

		ic.createImage((int [])aux);
		ic.createAudio((byte[])aux2);
		
		s.close();
		
	}
	
	/*public static void main(String[] args) throws ClassNotFoundException, IOException{
		try {
			new NewCliente(host);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
}
