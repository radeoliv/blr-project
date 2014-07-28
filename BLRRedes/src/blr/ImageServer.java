package blr;

import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.util.Random;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

import blr.Write;

public class ImageServer {
	
	static final String caminhoCore = "C://BLR//Core//";

	
	public static int menorQuad (int valor){
		double raiz = Math.sqrt((double)valor);
		raiz = Math.ceil(raiz);
		return (int)raiz;
	}

	public static Color preencheRGB(byte valor){
		Random r = new Random();
		int red = 0;
		int green = 0;
		int blue = r.nextInt(256);
		if (valor < 0){
			red = r.nextInt(127);
		} else {
			red = 128 + r.nextInt(128);
		}
		green = Math.abs(valor);

		return new Color(red,green,blue);
	}

	public static int[] convertionAudioImage(byte[] audioVector) throws Exception{

		int tam = audioVector.length;

		int w = menorQuad(tam); 		
		int h = w;
		int posicaoMusica = 0;

		Random r = new Random();

		redimensionaImagem(h,w,caminhoCore);
		BufferedImage image = ImageIO.read(new File(caminhoCore+"redimensionado.png"));
		int[] pixels = image.getRGB(0, 0, w, h, null, 0, w);
		for (int lin = 0; lin < h; lin++){
			for (int col = 0; col < w; col++, posicaoMusica++){
				if (posicaoMusica < tam){
					pixels[w*lin+col] = preencheRGB(audioVector[posicaoMusica]).getRGB();
				} else if (posicaoMusica == tam){
					pixels[w*lin+col] = new Color (127,127,127).getRGB();
				} else {
					pixels[w*lin+col] = new Color (r.nextInt(256),r.nextInt(256),r.nextInt(256)).getRGB();
				}
			}
		}
				
		//image.setRGB(0, 0, w, h, pixels, 0, w);
		
		return pixels;
	}

	public static byte[] convertionImageAudio(int [] pixels) throws IOException{
		
		int w = (int) Math.sqrt(pixels.length);
		int h = w;
		int[] red = getRed(pixels);
		int[] green = getGreen(pixels);

		int[] sinais = getSinais(red);

		byte[] musica = new byte[w*h];
		for(int i=0; i<w*h; i++){
			musica[i] = (byte)(green[i]*sinais[i]);
		}
		
		return musica;
	}

	public static int[] getSinais(int red[]){
		int tam = red.length;
		int[] sinais = new int [tam];
		for(int i=0; i<tam; i++){
			if(red[i]<=126){
				sinais[i] = -1;
			} else {
				sinais[i] = 1;
			}
		}
		return sinais;
	}

	public static int[] getRed(int[]pixels){
		//imagem quadrada! w=h
		ColorModel model = ColorModel.getRGBdefault();
		int tam = pixels.length;
		int[] red = new int [tam];

		for(int i=0; i<tam; i++){
			red[i] = model.getRed(pixels[i]);
		}
		return red;
	}

	public static int[] getGreen(int[]pixels){
		//imagem quadrada! w=h
		ColorModel model = ColorModel.getRGBdefault();
		int tam = pixels.length;
		int[] green = new int [tam];

		for(int i=0; i<tam; i++){
			green[i] = model.getGreen(pixels[i]);
		}
		return green;
	}

	public static void main(String arg[]){
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

	public static void redimensionaImagem(int height, int width, String caminho) throws IOException{
		BufferedImage imagem = ImageIO.read(new File(caminho+"imagemAuxiliar.png"));
		int new_w = width, new_h = height;
		BufferedImage new_img = new BufferedImage(new_w, new_h, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = new_img.createGraphics();
		g.drawImage(imagem, 0, 0, new_w, new_h, null);
		ImageIO.write(new_img, "PNG", new File(caminho+"redimensionado.png"));
	}

}
