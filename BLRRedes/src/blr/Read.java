package blr;

import java.awt.*;  
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;  


public class Read extends JFrame {  

	final static String caminho = "C://BLR//";
	final JButton playBtn = new JButton("Play");
	final JButton exitBtn = new JButton("Exit");

	public static void main(String args[]) {  
		new Read();		 
	}  

	public Read(){

		playBtn.setEnabled(true);
		exitBtn.setEnabled(true);

		playBtn.addActionListener(
				new ActionListener(){
					public void actionPerformed (ActionEvent e){
						playBtn.setEnabled(true);
						exitBtn.setEnabled(true);
						try {
							Sound.playSound(caminho+"somDaImagem.au");
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

		//carrega a imagem passando o nome da mesma  
		ImageIcon img = new ImageIcon(caminho+"Imagem.png");

		//adiciona a imagem em um label  
		JLabel label = new JLabel(img);  

		//cria o JPanel para adicionar os labels  
		JPanel panel = new JPanel();  
		panel.add(label, BorderLayout.NORTH);  

		//adiciona o panel no container  
		getContentPane().add(panel, BorderLayout.CENTER);

		setLocationRelativeTo(null);
		getContentPane().add(playBtn);
		getContentPane().add(exitBtn);
		getContentPane().setLayout(new FlowLayout());
		setTitle("Message translation");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		pack();  
	}
} 