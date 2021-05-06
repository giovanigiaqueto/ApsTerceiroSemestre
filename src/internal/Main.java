/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package internal;

// swing
import javax.swing.JFrame;
import javax.swing.JPanel;

// janelas
import graphic.MainJPanel;

/**
 *
 * @author giovani
 */
public class Main {
    
    private static JFrame frame;
    private static JPanel panel;
    
    public static void main(String[] args) {
        // cria o JFrame e a janela principal
        frame = new JFrame("Titulo");
        panel = new MainJPanel();
        
        // adiciona a janela ao frame
        frame.add(panel);
        
        // ajusta o tamanho do frame
        // para conter a janela
        frame.pack();
        
        // deixa o frame visivel
        frame.setVisible(true);
    }
}
