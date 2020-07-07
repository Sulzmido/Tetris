package main;

import java.awt.Color;
import java.awt.Point;

public class Brick {
    
       // brick data.
    private Point pixel;
    private final int type;
    private int number;
    private Color color;
    
    public Brick(int t,int n){
        
        type=t;
        number=n;
        color=Color.BLACK;
        pixel=new Point();
    }  
   
    
    public void rot(){
        
        switch (type) {
            case 2:
            case 3:
            case 4:
                number=(number%4)+1;
                //System.out.println(number);
                break;
            case 5:
            case 6:
            case 7:
                number=(number%2)+1;
                break;
            default:
                number=1;
                break;
        }
    }
    
    public void revRot(){
        
        switch (type) {
            case 2:
            case 3:
            case 4:
                number=(number-1);
                if(number==0) number=4;
                //System.out.println(number);
                break;
            case 5:
            case 6:
            case 7:
                number=(number-1);
                if(number==0) number=2;
                break;
            default:
                number=1;
                break;
        }
        
    }
    
       //getters and setters
    
    public void setColor(Color c) { color=c; }
    
    public void setPixel(Point p) { pixel=p; }
    
    public Point getPixel() { return pixel; }
   
    public Color getColor() { return color; }
    
    public int getNumber() { return number; }
    
    public int getType() { return type; }
}
