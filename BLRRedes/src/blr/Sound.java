package blr;

import sun.audio.*;
import java.io.*;

public class Sound{

	public static void main(String[] args) throws Exception {
	}
	
	public static void playSound(String address) throws Exception{
		AudioStream as = new AudioStream(getIS(address));
		AudioPlayer.player.start(as);
	}
	
	
	public static byte[] fileToBytes(File file) throws Exception{

		InputStream is = new FileInputStream(file);
		
		long length = file.length();
		
		if(length > Integer.MAX_VALUE){
			throw new Exception("File too large");
		} else {
			//Criando vetor de bytes para guardar informacao
			byte[] bytes = new byte[(int)length];
			
			//Passando informacao para vetor
			int offset = 0;
			int numRead = 0;
			while(offset < bytes.length
					&& (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0){
				offset += numRead;
			}
			
			//Garantindo que todos os bytes foram lidos
			if(offset < bytes.length){
				throw new IOException("Could not completely read file "+file.getName());
			}
			
			//Fechando arquivo
			is.close();
			return bytes;
		}
	}
	
	
	public static InputStream getIS(String address) throws FileNotFoundException{
		return new FileInputStream(getFile(address));
	}
	
	public static File getFile(String address){
		return new File(address);
	}
	
}
