package main;

import javax.swing.JFrame;

// Work on game started November 10 ,2016.

// Jarred a beta version of this game.(November 14 ,2016).
// Filled with bugs like
// * drop down method bug
// * The wahala dey bug.
// * the coupling rotation bug.

//This should all be fixed b4 game final release. (Date inconclusive).

public class Frame extends JFrame{
    
    public Frame(){
        
        super("Tetris");
        setContentPane(new Panel());      
        setResizable(false);        
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(3);
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new Frame();
    }
    
}
