import java.util.ArrayList;

public class Ship{
    
    private int row;
    private int col;
    private int numBombs;
    private ArrayList<Bomb> bombs;

    public Ship(int row,int col,int numBombs){
        this.row = row;
        this.col = col;
        this.numBombs = numBombs;
        bombs = new ArrayList<Bomb>();
        for(int i = 0; i < numBombs; i++){
            bombs.add(new Bomb(row,col));
        }
    }

    public int getRow(){
        return this.row;
    }
    public void setRow(int row){
         this.row = row;
    }
    public int getCol(){
        return this.col;
    }
    public void setCol(int col){
        this.col = col;
    } 
    public int getNumBomb(){
        return this.numBombs;
    }
    public void reload(int amount){
        this.numBombs = amount;
        for(int i = 0; i < numBombs;i++){
            bombs.add(new Bomb(getRow(),getCol()));
        }
    }
    public Bomb shoot(){
        Bomb bomb = null;
        if(this.numBombs > 0){
            this.numBombs --;
           bomb =  bombs.removeLast();
        }
        return bomb;
    }
}