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
import graphic.DescricaoLivroJPanel;

// modelos
import model.Livro;

// suporte
import graphic.util.livro.JDadosLivro;

// java.util
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author giovani
 */
public class Main {
    
    private static JFrame frame;
    private static JPanel panel;
    
    public static void main(String[] args) {
        frame = new JFrame("Titulo");
        panel = new MainJPanel();
        
        Livro livro1 = new Livro(0, "Livro 1", "12-3456789ABCD", "Autor A",
            "Editora A", 1, "10/10/2000", "Categoria A", 1, 1, 42, 9.99, "um ótimo livro");
        Livro livro2 = new Livro(1, "Livro 2", "DC-BA987654321", "Autor B",
            "Editora B", 1, "11/11/2001", "Categoria B", 3, 1, 42, 9.99, "outro ótimo livro");
        Livro livro3 = new Livro(1, "Livro 3", "11-11111111111", "Autor C",
            "Editora C", 1, "12/11/2001", "Categoria B", 3, 1, 42, 9.99, "outro ótimo livro");
        Livro livro4 = new Livro(1, "Livro 3", "11-11111111111", "Autor C",
            "Editora C", 1, "12/11/2001", "Categoria B", 3, 1, 42, 9.99, "outro ótimo livro");
        
        List<Livro> livros = new ArrayList<Livro>(4);
        
        livros.add(livro1);
        // livros.add(livro2);
        // livros.add(livro3);
        // livros.add(livro4);
        
        MainJPanel mainPanel = (MainJPanel) panel;
        mainPanel.inserir_livros(livros);
        
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
    
    //Fica aqui por enquanto, já que várias classes precisam
    public static String formataData(String data){
        String[] pedacosData = data.split("/");

        return pedacosData[2]+"-"+pedacosData[1]+"-"+pedacosData[0];
    }
    
}
