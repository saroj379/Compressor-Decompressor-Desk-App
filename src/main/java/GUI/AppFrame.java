/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import comp_decompress.compressor;
import comp_decompress.decompressor;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author princ
 */
public class AppFrame implements ActionListener{
    
    JFrame jFrame;
    
    JButton comp;
    JButton decomp;
    
    
    AppFrame(){
        //The main frame or a small window for compression and decompression as well
        jFrame = new JFrame("Compressor <-> Decompressor!");
        
        // This is the compression button to compressor file
        comp = new JButton("Select File to compress");
        comp.setBounds(50, 130, 150, 30);
        comp.addActionListener(this);
      
        // This is the decompression button to decompress file
        decomp = new JButton("Select File to Decompress");
        decomp.setBounds(300, 130, 150, 30);
        decomp.addActionListener(this);
        
        jFrame.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                int opt = JOptionPane.showConfirmDialog(jFrame, "Do you want to exit?");
                if(opt == JOptionPane.YES_OPTION) jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });

        
        jFrame.add(comp);
        jFrame.add(decomp);
        
        jFrame.setLayout(null);
        jFrame.setSize(500, 300);
        jFrame.setLocation(400, 150);
        jFrame.getContentPane().setBackground(Color.orange);
        jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        jFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == comp){
            JFileChooser filechooser = new JFileChooser();
            int resp = filechooser.showSaveDialog(null);
            // if there will not file selection then resp will be 1, so for 1 this will show the message
            if(resp == 1) JOptionPane.showMessageDialog(jFrame,"Select file to compress");
            else{
                if(resp == JFileChooser.APPROVE_OPTION){
                    File file = new File(filechooser.getSelectedFile().getAbsolutePath());
                    System.out.print(file);
                    try{
                        //if able to do compression then it will show the succes msg 
                        compressor.method(file);
                        JOptionPane.showMessageDialog(null, "Compression is successfully done!");
                    }
                    catch(Exception ee){
                        JOptionPane.showMessageDialog(null, ee.toString());
                    }
                }
            }
        }
        
        if(e.getSource() == decomp){
            JFileChooser filechooser = new JFileChooser();
            int resp = filechooser.showSaveDialog(null);
            // if no file selection then it will show message
            if(resp == 1) JOptionPane.showMessageDialog(jFrame,"Select file to decompress");
            else{
                if(resp == JFileChooser.APPROVE_OPTION){
                    File file = new File(filechooser.getSelectedFile().getAbsolutePath());
                    System.out.print(file);
                    try{
                        decompressor.method(file);
                        JOptionPane.showMessageDialog(null, "Decompression is successfully done!");
                    }
                    catch(Exception ee){
                        JOptionPane.showMessageDialog(null, ee.toString());
                    }
                }
            }
        }
    }
}
