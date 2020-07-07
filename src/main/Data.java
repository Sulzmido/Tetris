package main;

import java.awt.Point;

public class Data {
    
        //B Type 1
    private final int [][] B={{1,1},
                              {1,1}};
    
        //L1-L4 Type 2
    private final int [][] L1={{1,0,0},
                               {1,1,1}};
    
    private final int [][] L2={{1,1},
                               {1,0},
                               {1,0}};
    
    private final int [][] L3={{1,1,1},
                               {0,0,1}};
    
    private final int [][] L4={{0,1},
                               {0,1},
                               {1,1}};
    
       //T1-T4 Type 3
    private final int [][] T1={{0,1,0},
                               {1,1,1}};
    
    private final int [][] T2={{1,0},
                               {1,1},
                               {1,0}};
    
    private final int [][] T3={{1,1,1},
                               {0,1,0}};
    
    private final int [][] T4={{0,1},
                               {1,1},
                               {0,1}};
    
       //R1-R4 Type 4
    private final int [][] R1={{0,0,1},
                               {1,1,1}};
    
    private final int [][] R2={{1,0},
                               {1,0},
                               {1,1}};
    
    private final int [][] R3={{1,1,1},
                               {1,0,0}};
    
    private final int [][] R4={{1,1},
                               {0,1},
                               {0,1}};
    
       //S1-S2 Type 5
    private final int [][] S1={{1},
                               {1},
                               {1},
                               {1}};
    
    private final int [][] S2={{1,1,1,1}};
    
      //W1-W2 Type 6
    private final int [][] W1={{1,1,0},
                               {0,1,1}};
    
    private final int [][] W2={{0,1},
                               {1,1},
                               {1,0}};    
    
      //Q1-Q2  Type 7
    private final int [][] Q1={{0,1,1},
                               {1,1,0}};
    
    private final int [][] Q2={{1,0},
                               {1,1},
                               {0,1}};
    
       // number of row data
    private final int[][] row={{2,2,2,2,4,2,2},
                               {0,3,3,3,1,3,3},
                               {0,2,2,2,0,0,0},
                               {0,3,3,3,0,0,0}};
    
      // number of col data
    private final int[][] col={{2,3,3,3,1,3,3},
                               {0,2,2,2,4,2,2},
                               {0,3,3,3,0,0,0},
                               {0,2,2,2,0,0,0}};
    
      // initial distance from edge of screen.
    private final int[][] dist={{6,5,5,5,6,5,5},
                                {0,6,6,6,5,6,5},
                                {0,5,5,5,0,0,0},
                                {0,5,5,5,0,0,0}};
    
    
    public Data(){}
    
    public int[][] getBlockData(int type,int number){
        
        if(type==1){
            
            switch(number){
                case 1:
                    return B;                
            }
           
        }
        
        if(type==2){
            
            switch(number){
                case 1:
                    return L1;    
                case 2:
                    return L2; 
                case 3:
                    return L3; 
                case 4:
                    return L4; 
            }
           
        }
        
        if(type==3){
            
            switch(number){
                case 1:
                    return T1;    
                case 2:
                    return T2; 
                case 3:
                    return T3; 
                case 4:
                    return T4; 
            }
           
        }
        
        if(type==4){
            
            switch(number){
                case 1:
                    return R1;    
                case 2:
                    return R2; 
                case 3:
                    return R3; 
                case 4:
                    return R4; 
            }
           
        }
        
        if(type==5){
            
            switch(number){
                case 1:
                    return S1; 
                case 2:
                    return S2; 
            }
           
        }
        
        if(type==6){
            
            switch(number){
                case 1:
                    return W1; 
                case 2:
                    return W2; 
            }
           
        }
        
        if(type==7){
            
            switch(number){
                case 1:
                    return Q1; 
                case 2:
                    return Q2; 
            }
           
        }
            
        else{
            System.out.println("Error while getting block data");
        }
        return null;
    }
    
    public Point getDimensionData(int type,int number){
        
        type--;
        number--;
        return new Point(row[number][type],col[number][type]);
    }
    
     public int getDistData(int type,int number){
        
        type--;
        number--;
        return dist[number][type];
    }
    
}
