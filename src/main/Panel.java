package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Panel extends JPanel implements Runnable{
    
       // bricks length and breadth.
    private int bs=30;
       //panels dimensions
    private final int PWIDTH=bs*12;
    private final int PHEIGHT=bs*18;
    
    private Data data;
    private Game game;
    private Manager manager;
    
    public Panel(){
        
        super();
        setPreferredSize(new Dimension(PWIDTH,PHEIGHT));
        setFocusable(true);
        requestFocus();
        setUpMouse();
        setUpKey();
        
        data=new Data();
        manager=new Manager(data,bs);
        game=new Game(data,manager,bs);
    }
    
    @Override
    public void addNotify(){
        
        super.addNotify();
        Thread thread=new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        
        while(true){
            gameUpdate();
            gameRender();
            paintScreen();
        } 
                       
    }
    
    private void gameUpdate(){
        
        game.update();
        manager.update();

    }
    
    private Image img;
    
    private void gameRender(){
        
        img=createImage(PWIDTH,PHEIGHT);
        
        Graphics g=img.getGraphics();
        g.setColor(Color.blue);
        g.fillRect(0, 0, PWIDTH, PHEIGHT);
        
        game.draw(g);
        manager.draw(g);
        
        g.dispose();
    }
    
    private void paintScreen(){
        
        Graphics g=this.getGraphics();
        g.drawImage(img, 0, 0, null);
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
        
    }
    
    private void setUpMouse() {
        
        addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                JOptionPane.showMessageDialog(null,e.getX()+" : "+e.getY());
            }
        });
        
    }
    
    private void setUpKey() {
        
        addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e){
                
                if(e.getKeyCode()==KeyEvent.VK_UP){
                    
                    game.rotBrick();
                }
                
                if(e.getKeyCode()==KeyEvent.VK_DOWN){
                    
                    game.dropBrick();
                }
                
                if(e.getKeyCode()==KeyEvent.VK_LEFT){
                    
                    game.moveBrickLeft();
                }
                
                if(e.getKeyCode()==KeyEvent.VK_RIGHT){
                    
                    game.moveBrickRight();
                }          
            }
        });
    }
    
}
