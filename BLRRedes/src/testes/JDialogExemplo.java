package testes;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JDialogExemplo extends JDialog{
	public JDialogExemplo(String msg){
		JLabel texto = new JLabel(msg);
		JButton ok = new JButton("OK");
		//adiconando um evento ao componente JButton
		//os eventos serão explicados no decorrer do tutorial
		ok.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		JPanel pane1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel pane2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pane1.add(texto);
		pane2.add(ok);
		this.getContentPane().setLayout(new GridLayout(2, 1));
		this.getContentPane().add(pane1);
		this.getContentPane().add(pane2);

	}
	public static void main(String[] args){
		JDialogExemplo e = new JDialogExemplo("Testando o JDialog");
		e.pack(); //define um tamanho que exiba todos os componentes na
		e.setModal(true);
		e.setResizable(false);
		e.setVisible(true);
	}
}