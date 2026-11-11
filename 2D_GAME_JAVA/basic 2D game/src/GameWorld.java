import java.util.*;

public class GameWorld{

    private int rows;
    private int cols;
    private int PR;
    private int PC;
    private int FR;
    private int FC;
    private int WR;
    private int WC;
    private int numWall;
    private int numFood;
    Random rand;
    private int[][] World;


    public GameWorld(int rows,int cols,int numWall,int numFood){
        this.rand = new Random();
        this.rows = rows;
        this.cols = cols;
        this.numWall = numWall;
        this.numFood = numFood;
        
        this.World = new int[rows][cols];
        for(int i = 0; i < this.rows; i++){
            for(int j = 0; j < this.cols; j++){
                this.World[i][j] = Characters.SPACE.ordinal();
            }
        }
    }

    public void DisplayWorld(){
        for(int i = 0; i < this.rows; i++){
            for(int j =0; j < this.cols; j++){
                if(this.World[i][j] == Characters.SPACE.ordinal()){
                    System.out.print(".");
                   // System.out.print(this.World[i][j]);   
                }else if(this.World[i][j] == Characters.PLAYER.ordinal()){
                    System.out.print("P");
                }else if(this.World[i][j] == Characters.FOOD.ordinal()){
                    System.out.print("x");
                }else if(this.World[i][j] == Characters.WALL.ordinal()){
                    System.out.print("#");
                }
                System.out.print(' ');
            }
            System.out.println();
        }
    }

    public void setUpWorld(){
        
        for(int i = 0; i < this.numWall; i++){
            this.WR = rand.nextInt(0,this.rows - 1);
            this.WC = rand.nextInt(0,this.cols - 1);
            if(isInbounds(this.WR, this.WC)){
            this.World[this.WR][this.WC] = Characters.WALL.ordinal();
            }
        }
        for(int i = 0; i < this.numFood; i++){
            this.FR = rand.nextInt(0,this.rows - 1);
            this.FC = rand.nextInt(0,this.cols - 1);
            if(isInbounds(this.FR, this.FC)
                && (this.FR != this.WR || this.FC != this.WC)
                && (this.FR != this.PR || this.FC != this.PC)){

            this.World[this.FR][this.FC] = Characters.FOOD.ordinal();

            }
        }
       do{
            this.PR = rand.nextInt(0,this.rows - 1);
            this.PC = rand.nextInt(0,this.cols - 1);
       }while(!isInbounds(this.PR, this.PC) 
                && (this.PR == this.WR || this.PC == this.WC )
                && (this.PR == this.FR || this.PC == this.FC));

       this.World[this.PR][this.PC] = Characters.PLAYER.ordinal();
    }

    public void GamePlay(Moves actions){
       
        int pr = getPRow();
        int pc = getPCol();

        int x1 = pc + 1;
        int x2 = pc - 1;
        int y1 = pr - 1;
        int y2 = pr + 1;

        switch (actions) {
            case UP:
                if(isInbounds(y1, pc)){
                    if(this.World[y1][pc] == Characters.SPACE.ordinal()){
                        this.World[y1][pc] = Characters.PLAYER.ordinal();
                        this.World[pr][pc] = Characters.SPACE.ordinal();
                        this.PR = y1;
                        this.PC = pc;
                    }else if(this.World[y1][pc] == Characters.WALL.ordinal()){
                        System.err.println("You hit a wall!");
                    }else if(this.World[y1][pc] == Characters.FOOD.ordinal()){
                        this.numFood -= 1;
                        this.World[y1][pc] = Characters.PLAYER.ordinal();
                        this.World[pr][pc] = Characters.SPACE.ordinal();
                        this.PR = y1;
                        this.PC = pc;
                    }
                    
                }
                break;
            case DOWN:
                if(isInbounds(y2, pc)){
                    if(this.World[y2][pc] == Characters.SPACE.ordinal()){
                        this.World[y2][pc] = Characters.PLAYER.ordinal();
                        this.World[pr][pc] = Characters.SPACE.ordinal();
                        this.PR = y2;
                        this.PC = pc;
                    }else if(this.World[y2][pc] == Characters.WALL.ordinal()){
                        System.err.println("You hit a wall!");
                    }else if(this.World[y2][pc] == Characters.FOOD.ordinal()){
                        this.World[y2][pc] = Characters.PLAYER.ordinal();
                        this.World[pr][pc] = Characters.SPACE.ordinal();
                        this.PR = y2;
                        this.PC = pc;
                        this.numFood -= 1;
                    }
                }
                break;
            case LEFT:
                if(isInbounds(pr, x2)){
                    if(this.World[pr][x2] == Characters.SPACE.ordinal()){
                        this.World[pr][x2] = Characters.PLAYER.ordinal();
                        this.World[pr][pc] = Characters.SPACE.ordinal();
                        this.PR = pr;
                        this.PC = x2;
                    }else if(this.World[pr][x2] == Characters.WALL.ordinal()){
                        System.err.println("You hit a wall!");
                    }else if(this.World[pr][x2] == Characters.FOOD.ordinal()){
                        this.numFood -= 1;
                        this.World[pr][x2] = Characters.PLAYER.ordinal();
                        this.World[pr][pc] = Characters.SPACE.ordinal();
                        this.PR = pr;
                        this.PC = x2;
                    }
                }
                break;
            case RIGHT:
                 if(isInbounds(pr, x1)){
                    if(this.World[pr][x1] == Characters.SPACE.ordinal()){
                        this.World[pr][x1] = Characters.PLAYER.ordinal();
                        this.World[pr][pc] = Characters.SPACE.ordinal();
                        this.PR = pr;
                         this.PC = x1;
                    }else if(this.World[pr][x1] == Characters.WALL.ordinal()){
                        System.err.println("You hit a wall!");
                    }else if(this.World[pr][x1] == Characters.FOOD.ordinal()){
                        this.numFood -= 1;
                        this.World[pr][x1] = Characters.PLAYER.ordinal();
                        this.World[pr][pc] = Characters.SPACE.ordinal();
                        this.PR = pr;
                        this.PC = x1;
                    }
                }
                break;
            default:
                break;
        }
        //DisplayWorld();
       
    }

    private int getPRow(){
        return PR;
    }
    private int getPCol(){
        return PC;
    }
    public int getRows(){
        return this.rows;
    }
    public void setRows(int rows){
        this.rows = rows;
    }
    public int getCols(){
        return this.cols;
    }
    public void setCols(int cols){
        this.cols = cols;
    }
    public int getNumFood(){
        return this.numFood;
    }
    private boolean isInbounds(int x,int y){
        
        if(x < 0 || x > (this.rows - 1) || y < 0 || y > (this.cols - 1) ){
            return false;
        }
        
        return true;
    }

    public boolean isWon(){    
        if(this.numFood == 0){ 
               System.out.print("Congratulations! \nYou won!!");
            return true;}
        return false;
    }
}