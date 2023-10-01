package com.aanand.shooter.window;

import java.awt.Dimension;
import javax.swing.*;

public class Window {
  
	JLabel scoreLabel = new JLabel("Score: 0");
  public Window (int w, int h, String title, Game game){
    game.setPreferredSize(new Dimension(w,h));
    game.setMaximumSize(new Dimension(w,h));
    game.setMinimumSize(new Dimension(w,h));
    
    //Jframe stuff
    JFrame frame = new JFrame(title);    
    frame.add(game);
    frame.pack();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    frame.setLocationRelativeTo(null);//center
    frame.setVisible(true);
    
    
   game.start();
  }
  
}
