package blr;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.sound.sampled.*;


public class Write extends JFrame{

	AudioFormat audioFormat;
	TargetDataLine targetDataLine;
	public static final String caminho = "C://BLR//";

	final JButton captureBtn = new JButton("Record");
	final JButton stopBtn = new JButton("Stop");
	final JButton playBtn = new JButton("Play");
	final JButton convertBtn = new JButton("Convert");
	final JButton exitBtn = new JButton("Exit");

	public static void main(String args[]){
		new Write();
	}

	public Write(){
		File pasta = new File (caminho);
		if(!pasta.exists()){
			pasta.mkdir();
		}
		
		//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		//Pra botar ÍCONE nos botões é assim! Fácil!
		//playBtn.setIcon(new ImageIcon (imagenzinhaAque));
		//oooou só é jogar quando cria o botão
		//JButton playBtn = new JButton("Play", imagenzinhaAque);
		//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		
		captureBtn.setEnabled(true);
		stopBtn.setEnabled(false);
		playBtn.setEnabled(false);
		convertBtn.setEnabled(false);
		exitBtn.setEnabled(true);

		captureBtn.addActionListener(
				new ActionListener(){
					public void actionPerformed(
							ActionEvent e){
						captureBtn.setEnabled(false);
						stopBtn.setEnabled(true);
						playBtn.setEnabled(false);
						convertBtn.setEnabled(false);
						exitBtn.setEnabled(false);
					
						captureAudio();
					}
				}
		);

		stopBtn.addActionListener(
				new ActionListener(){
					public void actionPerformed(
							ActionEvent e){
						captureBtn.setEnabled(false);
						stopBtn.setEnabled(false);
						playBtn.setEnabled(true);
						convertBtn.setEnabled(true);
						exitBtn.setEnabled(true);
						// Terminou de capturar
						targetDataLine.stop();
						targetDataLine.close();
					}
				}
		);
		
		playBtn.addActionListener(
				new ActionListener(){
					public void actionPerformed (ActionEvent e){
						captureBtn.setEnabled(false);
						stopBtn.setEnabled(false);
						playBtn.setEnabled(true);
						convertBtn.setEnabled(true);
						exitBtn.setEnabled(true);
						try {
							Sound.playSound(caminho+"Som.au");
						} catch (Exception e1) {
							e1.printStackTrace();
						}


					}
				}
		);
		
		convertBtn.addActionListener(
				new ActionListener(){
					public void actionPerformed(
							ActionEvent e){
						// Lançar janela de aviso dizendo que a conversão foi concluída.
						captureBtn.setEnabled(false);
						stopBtn.setEnabled(false);
						playBtn.setEnabled(false);
						convertBtn.setEnabled(false);
						exitBtn.setEnabled(true);
						
							try {
								//new NewCliente("127.0.0.1");
								Testing test = new Testing();
								//buttonMensagemActionPerformed(e);
								dispose();
							} catch (Exception e1) {
								e1.printStackTrace();
							}
					}
				}
		);
		
		exitBtn.addActionListener(
				new ActionListener(){
					public void actionPerformed(
							ActionEvent e){
						dispose();			
					}
				}
		);

		//Colocar os botões no JFrame
		getContentPane().add(captureBtn);
		getContentPane().add(stopBtn);
		getContentPane().add(playBtn);
		getContentPane().add(convertBtn);
		getContentPane().add(exitBtn);
		
		getContentPane().setLayout(new FlowLayout());
		setTitle("Message record");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(300,120);
		setLocationRelativeTo(null); 
		setVisible(true);
	}

	
	private void captureAudio(){
		try{
			
			audioFormat = getAudioFormat();
			DataLine.Info dataLineInfo =
				new DataLine.Info(
						TargetDataLine.class,
						audioFormat);
			targetDataLine = (TargetDataLine)
			AudioSystem.getLine(dataLineInfo);

			
			new CaptureThread().start();
		}catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	private AudioFormat getAudioFormat(){
		float sampleRate = 44100.0F;
		//8000,11025,16000,22050,44100
		int sampleSizeInBits = 16;
		//8,16
		int channels = 2;
		//1,2
		boolean signed = true;
		//true,false
		boolean bigEndian = false;
		//true,false
		return new AudioFormat(sampleRate,
				sampleSizeInBits,
				channels,
				signed,
				bigEndian);
	}
	
	class CaptureThread extends Thread{
		public void run(){
			AudioFileFormat.Type fileType = null;
			File audioFile = null;

			fileType = AudioFileFormat.Type.AU;
			
			audioFile = new File(caminho+"Som.au");

			try{
				targetDataLine.open(audioFormat);
				targetDataLine.start();
				AudioSystem.write(
						new AudioInputStream(targetDataLine),
						fileType,
						audioFile);
			}catch (Exception e){
				e.printStackTrace();
			}

		}
	}
	
	/*private void buttonMensagemActionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(this, "Sound converted successfully!",
				"Convertion", JOptionPane.INFORMATION_MESSAGE);
	}*/
	
	public static String getCaminho(){
		return caminho;
	}
}//end outer class AudioRecorder02.java