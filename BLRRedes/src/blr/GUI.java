package blr;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GUI extends JFrame{

	private static JMenuItem menuItemExit = new JMenuItem("Exit");
	private static JMenuItem menuItemRead = new JMenuItem("Read");
	private static JMenuItem menuItemWrite = new JMenuItem("Write");
	private static JMenuItem menuItemAbout = new JMenuItem("About");
	private static JMenuItem menuItemSupport = new JMenuItem("Support");
	
	private JDesktopPane desktop;

	private static JMenu menuFile = new JMenu("File");
	private static JMenu menuMessage = new JMenu("Message");
	private static JMenu menuHelp = new JMenu("Help");
	private JMenuBar menuBar = new JMenuBar();

	public GUI(){
		super("BLR Program");
		
		desktop = new JDesktopPane(){
			//adiciona uma imagem de fundo para o desktop
			public void paintComponent(Graphics g){
				try{
					super.paintComponents(g);
					
					Image img = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/logo.jpg"));
					
					if (img != null){
						g.drawImage(img, 0, 0, 800, 600, this);
					}

				}catch(Exception e){
					e.printStackTrace();
				}
			}
		};
		
		desktop.setBackground(Color.BLACK);
		
		menuFile.add(menuItemExit);
		menuMessage.add(menuItemRead);
		menuMessage.add(menuItemWrite);
		menuHelp.add(menuItemAbout);
		menuHelp.add(menuItemSupport);

		menuBar.add(menuFile);
		menuBar.add(menuMessage);
		menuBar.add(menuHelp);
		
		menuItemRead.setEnabled(false);
		
		getContentPane().add(desktop);
		this.setJMenuBar(menuBar);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 600);
		setLocationRelativeTo(null); 
		this.setVisible(true);

		menuItemExit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				buttonConfirmActionPerformed(e); // Confirmar o fechar
			}
		});

		menuItemRead.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new Read();
			}
		});

		menuItemWrite.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new Write();
			}
		});

		menuItemAbout.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				aboutActionPerformed(e);
			}
		});
		
		menuItemSupport.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				supportActionPerformed(e);
			}
		});
	}
	
	private void supportActionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(this,"To record a message, choose the option *Write*.\n"+
				"To visualize the image made from the audio, choose the option *Read*.\n"+
				"You can find all of your created files at the directory *C:\\BLR\\*",
				"Support", JOptionPane.INFORMATION_MESSAGE);
	}
	
	private void aboutActionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(this, "Created and developed by:\n"+ "       - Bruno Yamashita\n"+"       - Larissa Soares\n"+"       - Rodrigo Silva",
				"Development", JOptionPane.PLAIN_MESSAGE);
	}
	
	private void buttonConfirmActionPerformed(ActionEvent e){
		int ret = JOptionPane.showConfirmDialog(this,"Are you sure?","Exit",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);

		if (ret == JOptionPane.OK_OPTION){
			System.exit(0);
		}
	}
	
	public static void activateMessage(){
		menuMessage.setEnabled(true);
	}
	
	public static void activateRead(){
		menuItemRead.setEnabled(true);
	}
	
	public static void main(String[] args){
		new GUI();
	}
}