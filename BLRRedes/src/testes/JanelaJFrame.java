package testes;

import java.awt.*;
import javax.swing.*;

public class JanelaJFrame extends JFrame {
	JLabel label1;
	JLabel label2;
	JLabel label3;
	JLabel label4 = new JLabel("R�tulo 4", JLabel.CENTER);

	public JanelaJFrame(){

		label1 = new JLabel("Testando r�tulos com JAVA"); //texto no construtor

		//texto com posi��o definida
		label2 = new JLabel("Texto Centralizado", JLabel.CENTER);

		/*getClass().getResource(arg) procura por um arquivo na pasta onde
		 * o aplicativo � chamado. Geralmente esse local � onde cont�m os
		 * arquivos .class que s�o os arquivo compilados para bytecode
		 * ImageIcon � um objeto que armazena uma imagem*/
		//ImageIcon img = new ImageIcon(this.getClass().getResource("JAVA.png"));
		ImageIcon img = new ImageIcon("C://Users//InteliMed02//800x600lovewu4.jpg");

		//texto com imagem
		label3 = new JLabel("JAVA - Interface Gr�fica", img, JLabel.LEFT);

		Container pane = this.getContentPane(); //captura o painel do JFrame
		pane.setLayout(new GridLayout(4, 1)); //define o layout do painel
		pane.add(label1);
		pane.add(label2);
		pane.add(label3);
		pane.add(label4);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(300, 200);
		this.setVisible(true);
	}

	public static void main(String[] args){
		JanelaJFrame janela = new JanelaJFrame();
	}
}


