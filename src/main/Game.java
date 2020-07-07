package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;

public class Game {
   
    private Data data;    
    private Manager manager;    
    private int bs;
    
    // globals for any brick currently being handled by the game
      //brick object.
    private Brick b;
      //type
    private int t;
      //number
    private int n;
       // initial pixel loc.
    private int ipx,ipy;
        //final pixel position of a brick before given to manager
    private int px,py;
        // brick dimension
    private Point dim;
       // stores how far the brick was moved horizontally and vertically.
    private int horizMove=0;
    private int vertMove=0;
       //stores the way the brick is drawn
    private int[][] block;
       //stores how far brick is from x=0 when first loaded into scene.
    private int xdist;
    
    private Random r=new Random();
    
    public Game(Data d,Manager m,int s){
        
        data=d;    
        manager=m;
        bs=s;
        
         // first brick.
        b=new Brick(r.nextInt(7)+1,1);   
        b.setColor(brickColor);
       
    }
    
     // used to control speed of movement.
    private int count=0;
    
    public void update(){
        
        count++;
        
        if(count%500==0){
            //if brick can move, move brick
            // else init new brick
            if(brickCanMove(DOWN) && vertMove<((18*bs-bs)-(bs*dim.x))){
             
                vertMove+=bs;                
            }
            else{               
                initNewBrick();             
            }           
        }
    }
    
    private final int STILL=0;
    private final int DOWN=1;
    private final int LEFT=2;
    private final int RIGHT=3;
    
    private Rectangle re;
    private int colx,coly;
    
    private boolean brickCanMove(int choice){
        
        switch(choice){
            case 0:
                colx=px;
                coly=py;
            break;
            case 1:
                colx=px;
                coly=py+bs;
            break;
            case 2:
                colx=px-bs;
                coly=py;
            break;
            case 3:
                colx=px+bs;
                coly=py;
            break;
        }      
        
        for(int i=0;i<dim.x;i++){
            for(int j=0;j<dim.y;j++){
                                
                if(block[i][j]==1) {

                    re=new Rectangle(colx,coly,bs,bs);
                    if(!manager.checkCollision(re)){
                        return false;
                    }
                    
                }                
                colx+=bs;
            }  
            switch(choice){
                case 0:
                case 1:
                    colx=px;break;
                case 2:
                    colx=px-bs;break;
                case 3:
                    colx=px+bs;break;
            }   
            coly+=bs;
        }
        return true;
    }
    
    private Color brickColor=randBrickColor();
     
    private void initNewBrick(){   
        
        b.setPixel(new Point(px,py));
        //System.out.println(px+" "+py);
        
          //brick given to manager to handle.
        manager.addBrickData(b);
        
          //variables reset
        vertMove=0;
        horizMove=0;
        brickColor=randBrickColor();
          //another brick initialized.
        b=new Brick(r.nextInt(7)+1,1);
        b.setColor(brickColor);
    }

    public void draw(Graphics g){  
        
        // get necassary data to draw active brick.
       t=b.getType();
       n=b.getNumber();
       
       xdist=bs*data.getDistData(t,n);
          
       ipx=xdist;
       ipy=bs;
          
       px=ipx+horizMove;
       py=ipy+vertMove;
       
       block = data.getBlockData(t,n);
       dim = data.getDimensionData(t,n);

        for(int i=0;i<dim.x;i++){
            for(int j=0;j<dim.y;j++){
                                
                if(block[i][j]==1) {
                   // System.out.println(px+" "+py);
                    g.setColor(brickColor);
                    g.fillRect(ipx+horizMove,ipy+vertMove, bs, bs);
                    g.setColor(Color.black);
                    g.drawRect(ipx+horizMove,ipy+vertMove, bs, bs);
                    
                }
                ipx+=bs;
            }            
            ipx=xdist;
            ipy+=bs;
        }        
        
    }
    
    private int dist;
    private Point d;
    
    public void rotBrick(){
        
        b.rot();
       // if(!brickCanMove(STILL)) b.revRot();
        
           // if rotating brick will cause array index out of bounds, adjustDist.
           // get data for quick pixel correction.
        dist=data.getDistData(b.getType(),b.getNumber());
        d=data.getDimensionData(b.getType(),b.getNumber());
           
        adjustDist();
    }
    
    private void adjustDist(){
        
        if((dist*bs)+horizMove < 0){
            //System.out.println("Wahala dey.");
            horizMove=-(dist*bs);
        }
        if(bs+vertMove+(bs*d.x)>18*bs){
            //System.out.println("Wahala show");
            vertMove=(18*bs)-(bs*d.x)-bs;
        }
        if((dist*bs)+horizMove>bs*(12-d.y)){
            //System.out.println("Wahala form");
            horizMove=(bs*(12-d.y))-(dist*bs);
        }
    }
    
    public void dropBrick(){
               
        while(true){
            
            // i think the problem is due to recursive calls to brickCanMove.
            
            if(brickCanMove(DOWN) && vertMove<((18*bs-bs)-(bs*dim.x))){
                
                vertMove+=bs;     
                py=ipy+vertMove;
                System.out.println(vertMove);
            }
            else break;
        }
          
    }
    
    public void moveBrickLeft(){
        
       if(brickCanMove(LEFT)){
           if(ipx+horizMove>0) horizMove-=bs;
       }
    }
    public void moveBrickRight(){
        
        if(brickCanMove(RIGHT)){
            if(ipx+horizMove<bs*(12-dim.y)) horizMove+=bs;
        }
    }
    
    private Color randBrickColor(){
        
        int num=r.nextInt(4);
        
        switch(num){
            case 0:
                return Color.RED;
            case 1:
                return Color.YELLOW;
            case 2:
                return Color.GREEN;
            case 3:
                return Color.ORANGE;
            case 4:
                return Color.BLUE;
            case 5:
                return Color.CYAN;
            case 6:
                return Color.PINK;
            case 7:
                return Color.WHITE;
            case 8:
                return Color.MAGENTA;
            default:
                return Color.BLACK;
        }
        
    }
}
