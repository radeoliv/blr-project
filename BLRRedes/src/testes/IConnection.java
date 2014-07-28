package testes;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import blr.FixedLengthDocument;
import blr.NewCliente;



public class IConnection extends JFrame {

	static String IP;
	
	public IConnection(){
		final JTextField text = new JTextField(15);
		JLabel label = new JLabel("Enter server IP:", JLabel.LEFT);
		JButton OKBtn = new JButton("Connect");
		JButton CancelBtn = new JButton("Cancel");
		
		Container pane = this.getContentPane();
		pane.setLayout(new FlowLayout(FlowLayout.TRAILING));
		text.setDocument(new FixedLengthDocument(15));
		
		pane.add(label);
		pane.add(text);
		pane.add(OKBtn);
		pane.add(CancelBtn);
		
		CancelBtn.addActionListener(
				new ActionListener(){
					public void actionPerformed(
							ActionEvent e){
						dispose();
					}
				}
		);
		
		OKBtn.addActionListener(
				new ActionListener(){
					public void actionPerformed(
							ActionEvent e){
						setIP(text.getText());
						//TODO: Falta mostrar janela de confirmação!
						try {
							new NewCliente(IP);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						connectionActionPerformed(e);
						dispose();
					}
				}
		);
		
		this.setSize(285, 100);
		this.setVisible(true);
		setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void connectionActionPerformed(ActionEvent e){
		JOptionPane.showMessageDialog(this, "Connection established!", "Server", JOptionPane.PLAIN_MESSAGE);
	}
	
	public void setIP(String address){
		IP = address;
	}
	
	public static void main(String[] args){
		IConnection janela = new IConnection();
	}
}

