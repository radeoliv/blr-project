package testes;

// Este exemplo mostra como exibir um JFileChooser
// e escolher um arquivo a partir de uma aplicação
// console

import javax.swing.*;
import java.io.*;

public class Estudos{
    public static void main(String[] args) {
        final JFileChooser fc = new JFileChooser();
        int res = fc.showOpenDialog(null);
        
        if(res == JFileChooser.APPROVE_OPTION){
          File arquivo = fc.getSelectedFile();  
          System.out.println("Voce escolheu o arquivo: " + arquivo.getName());
        }
        else
          System.out.println("Voce nao selecionou nenhum arquivo.");
    }
}
