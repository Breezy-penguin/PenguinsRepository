import java.util.*;


public class GameWorld{
    private int rows;
    private int cols;
    private int numShips;
    private int numBombs;
    private ArrayList<Ship> ships;
    private ArrayList<Bomb> bombs;
    private int HP;
    private int[][] Grid;
    private Random rand;

    public GameWorld(int rows,int cols,int numShips,int numBombs,int HP){
        rand = new Random();
        this.rows = rows;
        this.cols = cols;
        this.numShips = numShips;
        this.numBombs = numBombs;
        ships = new ArrayList<Ship>();
        bombs = new ArrayList<Bomb>();
        this.HP = HP;
        this.Grid = new int[rows][cols];
       
        for(int i = 0; i < getRows();i++){
            for(int j = 0; j < getCols(); j++){
                Grid[i][j] = Characters.SPACE.ordinal();
            }
        }
    }

    public void DisplayWorld(){
        for(int i = 0; i < getRows(); i++){
            for(int j = 0; j < getCols(); j++){

                if(Grid[i][j] == Characters.PLAYER.ordinal()){
                    System.out.print("\uD83E\uDDD1\u200D\uD83D\uDE80 " );
                }else if(Grid[i][j] == Characters.SHIP.ordinal()){
                     System.out.print("\uD83D\uDC7D " );
                }else if(Grid[i][j] == Characters.BOMB.ordinal()){
                     System.out.print("\uD83D\uDCA3 " );
                }else if(Grid[i][j] == Characters.SPACE.ordinal()){
                     System.out.print("\u2591  " );
                }else if(Grid[i][j] == Characters.DESTROYED_SHIP.ordinal()){
                     System.out.print("\u2620  " );
                }
                else{
                    System.out.print(Grid[i][j] + "  ");
                }
            }
            System.out.println();
        }
    }

    public void SetUpWorld(){

        int Prow = 0;
        int Pcol = 0;

        do{
            Prow = rand.nextInt((getRows()/2)+1,getRows() - 1);
            Pcol = rand.nextInt(0,getCols() - 1);
        }while(!isValid(Prow,Pcol));
        addChar(Prow, Pcol, Characters.PLAYER);

        int Srow = 0;
        int Scol = 0;
        int count = 0;
        while(isValid(Srow,Scol)&& count < numShips){
            Srow = rand.nextInt(0,(getRows()/2)-1);
            Scol = rand.nextInt(0,getCols()-1);
            addChar(Srow,Scol,Characters.SHIP);
            ships.add(new Ship(Srow,Scol,numBombs));
            count ++;
        }
       

    }

    public void GamePlay(Moves move){

        //1. player movements
        int PR = getPR();
        int PC = getPC();

        int y1 = PR - 1;
        int y2 = PR + 1;
        int x1 = PC - 1;
        int x2 = PC + 1;

        
         switch(move){
             case UP :
                    if(isValid(y1,PC) && y1 > (getRows()/2)){
                        if(Grid[y1][PC] == Characters.SPACE.ordinal()){
                            Grid[y1][PC] = Characters.PLAYER.ordinal();
                            Grid[PR][PC] = Characters.SPACE.ordinal();
                            PR = y1;
                        }else if(Grid[y1][PC] == Characters.BOMB.ordinal()){
                            Grid[y1][PC] = Characters.PLAYER.ordinal();
                            Grid[PR][PC] = Characters.SPACE.ordinal();
                            PR = y1;
                            HP --;
                            for(int i = 0; i < bombs.size();i++){
                                Bomb bomb = bombs.get(i);
                                if(bomb.getRow() == y1&& bomb.getCol() == PC){
                                    bombs.remove(i);
                                }
                            }
                        }
                    }
                    break;
            case DOWN :
                     if(isValid(y2,PC) && y2 > (getRows()/2)){
                        if(Grid[y2][PC] == Characters.SPACE.ordinal()){
                            Grid[y2][PC] = Characters.PLAYER.ordinal();
                            Grid[PR][PC] = Characters.SPACE.ordinal();
                            PR = y2;
                        }else if(Grid[y2][PC] == Characters.BOMB.ordinal()){
                            Grid[y2][PC] = Characters.PLAYER.ordinal();
                            Grid[PR][PC] = Characters.SPACE.ordinal();
                            PR = y2;
                            HP --;
                            for(int i = 0; i < bombs.size();i++){
                                Bomb bomb = bombs.get(i);
                                if(bomb.getRow() == y2&& bomb.getCol() == PC){
                                    bombs.remove(i);
                                }
                            }
                        }
                    }
                    break;
            case RIGHT :
                    if(isValid(PR,x2)){
                        if(Grid[PR][x2] == Characters.SPACE.ordinal()){
                            Grid[PR][x2] = Characters.PLAYER.ordinal();
                            Grid[PR][PC] = Characters.SPACE.ordinal();
                            PC = x2;
                        }else if(Grid[PR][x2] == Characters.BOMB.ordinal()){
                            Grid[PR][x2] = Characters.PLAYER.ordinal();
                            Grid[PR][PC] = Characters.SPACE.ordinal();
                            PC = x2;
                            HP --;
                            for(int i = 0; i < bombs.size();i++){
                                Bomb bomb = bombs.get(i);
                                if(bomb.getRow() == PR&& bomb.getCol() == x2){
                                    bombs.remove(i);
                                }
                            }
                        }
                    }
                    break;
            case LEFT :
                if(isValid(PR,x1)){
                        if(Grid[PR][x1] == Characters.SPACE.ordinal()){
                            Grid[PR][x1] = Characters.PLAYER.ordinal();
                            Grid[PR][PC] = Characters.SPACE.ordinal();
                            PC = x1;
                        }else if(Grid[PR][x1] == Characters.BOMB.ordinal()){
                            Grid[PR][x1] = Characters.PLAYER.ordinal();
                            Grid[PR][PC] = Characters.SPACE.ordinal();
                            PC = x1;
                            HP --;
                            for(int i = 0; i < bombs.size();i++){
                                Bomb bomb = bombs.get(i);
                                if(bomb.getRow() == PR && bomb.getCol() == x1){
                                    bombs.remove(i);
                                }
                            }
                        }
                    }
                    break;
            case SHOOT :
                for(int i = getRows() - 1;i >= 0; i--){
                    if(Grid[i][PC] == Characters.SPACE.ordinal()){
                        continue;
                    }else if(Grid[i][PC] == Characters.SHIP.ordinal()){
                        Grid[i][PC] = Characters.DESTROYED_SHIP.ordinal();
                        numShips --;
                        for(int j = 0; j < ships.size();j++){
                            Ship ship = ships.get(j);
                            if(ship.getRow() == i && ship.getCol() == PC){
                                ships.remove(ship);
                                break;
                            }
                        }
                    }
                    
                }
            break;
            default :
            break;

         }
        
         //Ship movement.
         //Every turn made by the player, the ship has a 60% chance of moving
         // into a single block LEFT,RIGHT,ABOVE OR BELOW IT
         //A ship also has a 25% chance it'll shoot a bomb
         for(Ship ship : ships){
            moveShip(ship);
            shootBomb(ship);
          }
          moveBmbs();

    }
    public int getHP(){
        return this.HP;
    }
    public int getShips(){
        return this.numShips;
    }
    public int getRows(){
        return this.rows;
    }
    public int getCols(){
        return this.cols;
    }
    public int getPR(){
        
        int PR = 0;
        for(int i = 0; i < getRows(); i++){
            for(int j = 0; j < getCols(); j++){
                if(Grid[i][j] == Characters.PLAYER.ordinal()){
                    if(isValid(i,j)){PR = i;}
                }
            }
        }
        return PR;
    }
    public int getPC(){
        
        int PC = 0;
        for(int i = 0; i < getRows(); i++){
            for(int j = 0; j < getCols(); j++){
                if(Grid[i][j] == Characters.PLAYER.ordinal()){
                    if(isValid(i,j)){PC = j;}
                }
            }
        }
        return PC;
    }
    public int getSR(){
        for(int i = 0; i < getRows(); i++){
            for(int j = 0; j < getCols(); j++){
                if(Grid[i][j] == Characters.SHIP.ordinal()){
                    return i;
                }
            }
        }
        return 0;
    }
    public int getSC(){
        for(int i = 0; i < getRows(); i++){
            for(int j = 0; j < getCols(); j++){
                if(Grid[i][j] == Characters.SHIP.ordinal()){
                    return j;
                }
            }
        }
        return 0;
    }
    private boolean isValid(int row,int col){
        return row >= 0 && row < getRows() && col >= 0 && col < getCols();
    }
   public boolean isLost(){
     if( HP <= 0){
        return true;
     }
     return false;
   }
    public boolean isWon(){
     
        if(numShips <= 0){
            return true;
        }
        return false;
    }
    private void addChar(int row,int col, Characters my){
        if(isValid(row, col)){
            Grid[row][col] = my.ordinal();
        }
    }
    private void moveShip(Ship ship){
        if(!(ship.getRow() < (getRows()/2))){
            return;
        }
       
        int direction = rand.nextInt(0,4);
        int newRow = ship.getRow();
        int newCol = ship.getCol();

        switch (direction) {
            case 0://UP
                newRow++;
                break;
            case 1://Down
                newRow--;
                break;
            case 2://LEFT
                newCol--;
                break;
            case 3://RIGHT
                newCol++;
            default:
                break;
        }

        if(isValid(newRow,newCol)&&Grid[newRow][newCol]==Characters.SPACE.ordinal()){
            Grid[newRow][newCol] = Characters.SHIP.ordinal();
            Grid[ship.getRow()][ship.getCol()] = Characters.SPACE.ordinal();
            ship.setRow(newRow);
            ship.setCol(newCol);
        }else if(isValid(newRow,newCol)&&Grid[newRow][newCol] == Characters.BOMB.ordinal()){
            Grid[newRow][newCol] = Characters.DESTROYED_SHIP.ordinal();
            Grid[ship.getRow()][ship.getCol()] = Characters.SPACE.ordinal();
            numShips--;
            ships.remove(ship);
        }else if(isValid(newRow,newCol)&&Grid[newRow][newCol] == Characters.SHIP.ordinal()){
            return;
        }
    }
    private void shootBomb(Ship ship){
        
        int brow = ship.getRow()+1;
        int bcol = ship.getCol();
        Bomb b = null;

        if(!isValid(brow,bcol)){return;}

        if(ship.getNumBomb() <= 0){
            ship.reload(numBombs);
        }

        int shootProp = rand.nextInt(0,100);
        
        if(shootProp < 25){   
             b = ship.shoot(); 
            if(b != null && Grid[brow][bcol] == Characters.SPACE.ordinal()){
                b.setRow(brow);
                b.setCol(bcol);
                bombs.add(b);
                Grid[brow][bcol] = Characters.BOMB.ordinal();    
            }
        }
    }
    private void moveBmbs(){
        for(int i = 0; i < bombs.size();i++){

            Bomb b = bombs.get(i);
            int newRow = b.getRow() + 1;
            int col = b.getCol();

            if(!isValid(newRow, col)){
                Grid[b.getRow()][col] = Characters.SPACE.ordinal();
                bombs.remove(i);
                continue;
            }

            if(isValid(newRow, col)&& Grid[newRow][col] == Characters.SPACE.ordinal()){
                Grid[newRow][col] = Characters.BOMB.ordinal();
                Grid[b.getRow()][b.getCol()] = Characters.SPACE.ordinal();
                b.setRow(newRow);
                b.setCol(col);
            }else if(isValid(newRow,col) && Grid[newRow][col] == Characters.PLAYER.ordinal()){
                HP --;
                Grid[b.getRow()][b.getCol()] = Characters.SPACE.ordinal();
                bombs.remove(i);
            }else if(isValid(newRow,col)&& Grid[newRow][col] == Characters.SHIP.ordinal()){
                Grid[newRow][col] = Characters.DESTROYED_SHIP.ordinal();
                Grid[b.getRow()][b.getCol()] = Characters.SPACE.ordinal();
                numShips--;
                for(Ship ship : ships){
                    if(ship.getRow() == newRow && ship.getCol() == col){
                        ships.remove(ship);
                    }
                }
            }
            if(b.getRow() == getRows()){
                bombs.remove(i);
            }
        }
    }
}