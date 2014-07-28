package testes;

// Este exemplo mostra como selecionar diretório usando
// JFileChooser a partir de uma JFrame

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Ler extends JFrame{
	
	static String caminho;
	final JButton browse = new JButton("Browse");
	final JButton record = new JButton("Record");
	final JButton stop = new JButton("Stop");
	final JButton play = new JButton("Play");
	final JButton transform = new JButton("Transform");
	
	public Ler(){
        super("Escolher um diretório usando JFileChooser");
    
        browse.setEnabled(true);
        record.setEnabled(true);
        stop.setEnabled(false);
        play.setEnabled(false);
        transform.setEnabled(false);
		
        Container c = getContentPane();
        FlowLayout layout = new FlowLayout(FlowLayout.LEFT);
        c.setLayout(layout);
        
        browse.addActionListener(
            new ActionListener(){
                //public void actionPerformed(ActionEvent e){
            	public void actionPerformed(ActionEvent e){
                    JFileChooser fc = new JFileChooser();
                    
                    // restringe a amostra a diretorios apenas
                    fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    
                    int res = fc.showOpenDialog(null);
                    
                    if(res == JFileChooser.APPROVE_OPTION){
                        File diretorio = fc.getSelectedFile();   
                        
                        //Esse é o que importa!
                        caminho = diretorio.getAbsolutePath().replace('\\', '/');
                        
                        JOptionPane.showMessageDialog(null, "Voce escolheu o diretório: " + diretorio.getName());
                    }
                    else
                        JOptionPane.showMessageDialog(null, "Voce nao selecionou nenhum diretorio."); 
                    
            	}
            }   
        );
        
        record.addActionListener(
        		new ActionListener(){
        			//public void actionPerformed(ActionEvent e){
        			public void actionPerformed(ActionEvent e){
        				browse.setEnabled(false);
        				record.setEnabled(false);
        				stop.setEnabled(true);
        		        play.setEnabled(false);
        		        transform.setEnabled(false);
        		        
        				 
        			}
        		}   
        );

        stop.addActionListener(
        		new ActionListener(){
        			//public void actionPerformed(ActionEvent e){
        			public void actionPerformed(ActionEvent e){
        				browse.setEnabled(false);
        				stop.setEnabled(false);
        				record.setEnabled(false);
        		        play.setEnabled(true);
        		        transform.setEnabled(true);
        				System.out.println("LARISSA É LEGAL!"); 
        			}
        		}   
        );
        
        play.addActionListener(
        		new ActionListener(){
        			//public void actionPerformed(ActionEvent e){
        			public void actionPerformed(ActionEvent e){
        				System.out.println("LARISSA É safada!"); 
        			}
        		}   
        );
        
        transform.addActionListener(
        		new ActionListener(){
        			//public void actionPerformed(ActionEvent e){
        			public void actionPerformed(ActionEvent e){
        				record.setEnabled(false);
        				browse.setEnabled(false);
        				stop.setEnabled(false);
        		        play.setEnabled(false);
        		        transform.setEnabled(false);
        				System.out.println("LARISSA É nojenta!"); 
        			}
        		}   
        );
        
        
        c.add(browse);
        c.add(record);
        c.add(stop);
        c.add(play);
        c.add(transform);
        
        setSize(400, 200);
        setVisible(true);
    }
    
    public static void main(String[] args) {
        Ler app = new Ler();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}