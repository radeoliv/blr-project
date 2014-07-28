package blr;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ServerGUI extends JFrame{
	
	public ServerGUI(){
		JLabel texto = new JLabel("Server ON!");
		JButton ok = new JButton("OK");
		//adiconando um evento ao componente JButton
		//os eventos serão explicados no decorrer do tutorial
		ok.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try {
					dispose();
					new NewServer();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		JPanel pane1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel pane2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pane1.add(texto);
		pane2.add(ok);
		this.getContentPane().setLayout(new GridLayout(2, 1));
		this.getContentPane().add(pane1);
		this.getContentPane().add(pane2);
		setLocationRelativeTo(null); 

	}
	
	public static void main(String[] args){
		ServerGUI e = new ServerGUI();
		e.pack(); //define um tamanho que exiba todos os componentes na
		e.setResizable(false);
		e.setVisible(true);
	}

}
