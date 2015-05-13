/*
 * @author      Yaotian He and Dau Ting Lam
 * @subject     CSCI 211 - Final Programming Project, Option#1
 * @time        April 23, 2013
 * 
 */
package informationretrievalsystems;

import javax.swing.JFrame;

public class InformationRetrievalSystems {
    
 public static void main(String[] args) {
     GUI frame = new GUI();
     frame.pack();
     frame.setTitle("Final Project: Option 1");
     frame.setSize(600,500);
     frame.setLocationRelativeTo(null);
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     frame.setVisible(true);}
}