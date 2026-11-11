import java.util.*;

public class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GameWorld world = new GameWorld(7,7,4,2);
        String move = " ";
       
        world.setUpWorld();
        System.out.println("Welcome to X-chaser!\n" + 
        "Your objective is to get all the X's whilst avoiding the walls.\n" + 
        "Goodluck!\n");
        
        
          System.out.println("Moves:\n UP: W \t DOWN: S \t LEFT: A \t RIGHT: D \t Quit: Q\n "
                            +" Treasures left: "+ world.getNumFood());
           world.DisplayWorld();
       
        do{
            move = sc.next();

            switch (move.toUpperCase()) {
                case "W":
                    world.GamePlay(Moves.UP);
                    break;
                case "S":
                    world.GamePlay(Moves.DOWN);
                    break;
                case "A":
                    world.GamePlay(Moves.LEFT);
                    break;
                case "D":
                    world.GamePlay(Moves.RIGHT);
                    break;
                case "Q":
                    System.out.println("GoodBye!");
                    break;
                default:
                    System.err.println("Invalid move!");
                    sc.next();
                    break;
            }

            System.out.print("\033[H\033[2J");//clears screen
            System.out.flush();//clears screen
            System.out.println("Moves:\n UP: W \t DOWN: S \t LEFT: A \t RIGHT: D \t Quit: Q \n"+
                                " Treasures left: "+ world.getNumFood());
            world.DisplayWorld();
        }while(!world.isWon());
        sc.close();
            
        
       
    }
}