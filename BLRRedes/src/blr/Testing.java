package blr;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import blr.FixedLengthDocument;
import blr.NewCliente;

public class Testing extends JFrame {
	
	public Testing(){
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
						String temp = text.getText();
						//TODO: Falta mostrar janela de confirmação!
						try {
							new NewCliente(temp);
							connectionActionPerformed(e);
							buttonMensagemActionPerformed(e);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						GUI.activateRead();
						dispose();
					}
				}
		);
		
		this.setSize(285, 100);
		this.setVisible(true);
		setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void buttonMensagemActionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(this, "Sound converted successfully!",
				"Convertion", JOptionPane.INFORMATION_MESSAGE);
	}
	
	private void connectionActionPerformed(ActionEvent e){
		JOptionPane.showMessageDialog(this, "Connection established!", "Server", JOptionPane.INFORMATION_MESSAGE);
	}
	
}
