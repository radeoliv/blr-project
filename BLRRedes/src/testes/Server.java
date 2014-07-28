package testes;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;

public class Server {
	
	static Socket s;
	static ServerSocket serv;
	static BufferedImage image;
	static byte[] audio;
	
	public Server() throws IOException{
		serv = new ServerSocket(7000);
        System.out.println("Server running!");
        s = serv.accept();
	}
	
	public Server (int choice) throws Exception{

		while(true){           
			s = serv.accept();
			System.out.println("Conection established!");
			
			/*
			Caso 1: Converte audio em imagem e envia ao cliente
			Caso 2: Converte imagem em audio e envia ao cliente
			*/
			
			switch(choice){
				case(1):
					ObjectInputStream inAudio = new ObjectInputStream (s.getInputStream());
					//image = ImageServer.convertionAudioImage((byte[])inAudio.readObject());
					ObjectOutputStream outImage = new ObjectOutputStream (s.getOutputStream());
					outImage.writeObject(image);
					inAudio.close();
					outImage.close();
					break;
				case(2):
					ObjectInputStream inImage = new ObjectInputStream (s.getInputStream());
					//audio = ImageServer.convertionImageAudio((BufferedImage)inImage.readObject());
					ObjectOutputStream outAudio = new ObjectOutputStream (s.getOutputStream());
					outAudio.writeObject(audio);
					inImage.close();
					outAudio.close();
					break;
				case(3):
					serv.close();
					System.exit(0);
				default:
					throw new Exception("What I do?");
			}
		}
		
	}
	
	public static void main(String[] args) throws ClassNotFoundException, IOException{
		new Server();
	}
	
}