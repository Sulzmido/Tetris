package main;

import java.awt.Color;
import java.awt.Point;

public class Block {
    
    private Color blockColor;
    private Point pixel;
    
    public Block(int x,int y,Color c){
        
        pixel=new Point();
        pixel.x=x;
        pixel.y=y;
        blockColor=c;
        
    }
    
    public Color getColor() { return blockColor; }
    
    public Point getPixel() { return pixel; }  
}
