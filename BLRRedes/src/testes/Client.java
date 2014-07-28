package testes;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;

import blr.GUI;
import blr.ImageClient;




public class Client {
	
	static String address;
	static Socket s;
	
	public Client (String ip) throws Exception {
		address = ip;
		s = new Socket(address, 7000);
		if(s.isConnected()){
			GUI.activateMessage();
		}
	}
	
	public Client(int choice) throws Exception{
		/*
		Caso 1: Envia som para servidor / recebe imagem e grava no disco
		Caso 2: Envia imagem e traduz
		*/
		
		switch(choice){
			case(1):
				ObjectOutputStream outAudio = new ObjectOutputStream (s.getOutputStream());
				outAudio.writeObject(ImageClient.sendAudio());
				ObjectInputStream inImage = new ObjectInputStream (s.getInputStream());
				ImageClient.createImage((int [])inImage.readObject());
				break;
			case(2):
				ObjectOutputStream outImage = new ObjectOutputStream (s.getOutputStream());
				outImage.writeObject(ImageClient.sendImage());
				ObjectInputStream inAudio = new ObjectInputStream (s.getInputStream());
				ImageClient.createAudio((byte[])inAudio.readObject());
				break;
			case(3):
				s.close();
				break;
			default:
				throw new Exception("What I do?");
		}
	}
	
	public static void main(String[] args) throws ClassNotFoundException, IOException{
		try {
			new Client("127.0.0.1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
