/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventana;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class Prueba {

    public static void main(String[] args) {

        JFileChooser fr = new JFileChooser();
        FileSystemView fw = fr.getFileSystemView();
        System.out.println((new JFileChooser()).getFileSystemView().getDefaultDirectory().toString());
        /*  Runnable r = new Runnable() {

            @Override
            public void run() {
                new Prueba().createUI();
            }
        };

        EventQueue.invokeLater(r);
    }

    private void createUI() {
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());

        JButton saveBtn = new JButton("Save");
        JButton openBtn = new JButton("Open");

        saveBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                JFileChooser saveFile = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("FICHEROS TXT(.txt)", "txt", "text");
                saveFile.setFileFilter(filter);
                saveFile.showSaveDialog(null);
                System.out.println(saveFile.getSelectedFile());
            }
        });

        openBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                JFileChooser openFile = new JFileChooser();
                openFile.showOpenDialog(null);
            }
        });

        frame.add(new JLabel("File Chooser"), BorderLayout.NORTH);
        frame.add(saveBtn, BorderLayout.CENTER);
        frame.add(openBtn, BorderLayout.SOUTH);
        frame.setTitle("File Chooser");
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }*/
    }
}
