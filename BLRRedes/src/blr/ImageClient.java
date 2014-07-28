package blr;

import java.awt.Image;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class ImageClient {
	static final String caminho = "C://BLR//";
	static final String caminhoCore = "C://BLR//Core//";
	
	public ImageClient(){
		File pasta = new File (caminhoCore);
		if(!pasta.exists()){
			pasta.mkdir();
		}

		try{
			Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/imagemAuxiliar.jpg"));
			BufferedImage cpimg = bufferImage(image);
			File f1 = new File(caminhoCore+"imagemAuxiliar.png");
			ImageIO.write(cpimg,"png",f1);
		}catch(Exception e){
			System.out.println(e);
		}
	}

	public static byte[] sendAudio() throws Exception{
		byte[] musicaVetor = Sound.fileToBytes(new File(caminho+"Som.au"));
		return musicaVetor;
	}
	
	public static BufferedImage sendImage() throws IOException{
		BufferedImage image = ImageIO.read(new File(caminho+"Imagem.png"));
		return image;
	}
	
	public static void createAudio(byte[] musica) throws IOException{
		File file = new File(caminho+"somDaImagem.au");
		file.createNewFile();
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(musica);
	}
	
	public static void createImage(int[] pixels) throws Exception{
		BufferedImage image = ImageIO.read(new File(caminhoCore+"redimensionado.png"));
		double tam = pixels.length;
		int w = (int) Math.sqrt(tam);
		int h = w;
		image.setRGB(0, 0, w, h, pixels, 0, w);
		ImageIO.write(image, "PNG", new File(caminho+"Imagem.png"));
	}

	
	public static BufferedImage bufferImage(Image image) {
		return bufferImage(image,BufferedImage.TYPE_INT_RGB);
	}	

	public static BufferedImage bufferImage(Image image, int type) {
		BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
		Graphics2D g = bufferedImage.createGraphics();
		g.drawImage(image, null, null);
		return bufferedImage;
	}

}
