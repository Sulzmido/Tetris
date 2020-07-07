package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Manager {
    
    private Data data; 
    private int bs;
    private ArrayList blocks;
    
    private int[][] blocksData=new int[18][12];
            
    public Manager(Data d,int s){
        
        data=d;
        bs=s;
        blocks=new ArrayList();
        
        initArray();
        
    }
    
      // Array of 1 and 0 representing the blocks.
       // This is done for easy data manipulation.
       // Highly useful in checking for complete rows.
    private void initArray(){
        
        for(int i=0;i<18;i++){
            for(int j=0;j<12;j++){
                blocksData[i][j]=0;
            }
        }
    }
    
    private Block bl;
    
        // data for bricks
    private Color brickColor;
    private Point px;
    private int[][] block;
    private int temp;
    private Point dim;
    private int x,y;
    
    public void addBrickData(Brick b){
        
        px=b.getPixel();
  
        x=px.x;
        y=px.y;
       
        temp=px.x;
        
        brickColor=b.getColor();
        dim=data.getDimensionData(b.getType(),b.getNumber());
        block=data.getBlockData(b.getType(),b.getNumber());
        
        for(int i=0;i<dim.x;i++){
            for(int j=0;j<dim.y;j++){
                
                if(block[i][j]==1) {
                    bl=new Block(x,y,brickColor); 
                    //System.out.println(why+" "+ex);
                    blocksData[y/bs][x/bs]=1;
                    blocks.add(bl);
                }
                x+=bs;
            }            
            x=temp;
            y+=bs;
        }      
    }
    
    int count=0;
    
    private void printArray(){
        
        for(int i=0;i<18;i++){
            for(int j=0;j<12;j++){
                System.out.print(blocksData[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
    
    public void update(){
        
        clearCompleteRows();
        
        count++;
        
        if(count%1500==0){
            //printArray();
        }       
    }
    
    public void draw(Graphics g){
        for(int i=0;i<blocks.size();i++){
            
            //System.out.println(i);
            bl=(Block)blocks.get(i);
            drawBlock(bl,g);
        }
    }
   
    
    private void drawBlock(Block bl,Graphics g){
           
        px=bl.getPixel();
        
        brickColor=bl.getColor();
        
        g.setColor(brickColor);
        g.fillRect(px.x,px.y, bs, bs);
        g.setColor(Color.black);
        g.drawRect(px.x,px.y, bs, bs);
                           
    }
       //collision checking.
    private Rectangle r;
    
    public boolean checkCollision(Rectangle re){
        
        for(int i=0;i<blocks.size();i++){
            
            bl=(Block)blocks.get(i);
            
            px=bl.getPixel();

            r=new Rectangle(px.x,px.y,bs,bs);
            
            if(re.intersects(r)){
                return false;
            }
        }
        return true;          
    }
    
    private int total;
    
    private void clearCompleteRows(){
        
        for(int i=0;i<18;i++){
            
            total=0;
            for(int j=0;j<12;j++){
                total+=blocksData[i][j];                
            }
            if(total==12){
                
                rearrangeRows(i);
              
                //System.out.println("bingo");
                purgeRow(i*30);
            }
        }
    }
     
    private void rearrangeRows(int index){
        
        for(int i=index;i>0;i--){
            System.arraycopy(blocksData[i-1], 0, blocksData[i], 0, 12);
        }   
        
        for(int j=0;j<12;j++) blocksData[0][j]=0;    
    }
  
      //remove all blocks with yPixel
    
    private void purgeRow(int yPixel){
        
        //System.out.println(yPixel);       
        for(int i=0;i<blocks.size();i++){
            
            bl=(Block)blocks.get(i);
            
                // increase ypixel value of all bricks above the row purged by bs.
            if(bl.getPixel().y<yPixel){ 
                bl.getPixel().y+=bs;
                continue;
            }
            
                // purge all bricks with ypixel           
            if(bl.getPixel().y==yPixel) {
                blocks.remove(i);
                i--;
            }      
        }
    }   
}
