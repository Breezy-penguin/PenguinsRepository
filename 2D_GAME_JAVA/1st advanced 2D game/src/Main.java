import java.util.Scanner;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
public class Main{
    public static void main(String[] args) throws Exception{
        //to use emojis and non-ascii encoding stuff
        System.setOut(new PrintStream(System.out, true, "UTF-8"));
        //System.out.println("\u2605 \u2620 \u2665");//test chars

        Scanner sc;
        GameWorld game = new GameWorld(7,7,3,5,3);

        game.SetUpWorld();
        System.out.println("===ALIEN SHOOTER===");
        System.out.println("HP: "+game.getHP()+"\tAliens: "+game.getShips());
        game.DisplayWorld();

        System.out.println("MOVES : \nW : UP\tS : DOWN\tA : LEFT\tD : RIGHT\nE : SHOOT\nQ : QUIT");
        String input = " ";
         sc = new Scanner(System.in);
        do{
           input = sc.next();
            switch(input.toUpperCase()){
                case "W":
                    game.GamePlay(Moves.UP);
                    break;
                case "S":
                    game.GamePlay(Moves.DOWN);
                    break;
                case "A":
                    game.GamePlay(Moves.LEFT);
                    break;
                case "D":
                    game.GamePlay(Moves.RIGHT);
                    break;
                case "E":
                    game.GamePlay(Moves.SHOOT);
                    break;
                case "Q":
                    System.out.println("Are you sure you want to quit?\nCHOOSE: Y: YES\tN: NO");
                    String ans = sc.next();
                    if(ans.toUpperCase().equals("Y")){
                        System.out.println("Goodbye! press any key to exit...");
                        sc.next();
                        return;
                    }else if(ans.toUpperCase().equals("N")){
                        break;
                    }
                    return;
                default :
                    System.err.println("Invalid move!");

            }        
            System.out.print("\033[H\033[2J");//clears screen
            System.out.flush();//clears screen
            System.out.println("===ALIEN SHOOTER===");
            System.out.println("HP: "+game.getHP()+"\tAliens: "+game.getShips());
           game.DisplayWorld();
           System.out.println("MOVES : \nW : UP\tS : DOWN\tA : LEFT\tD : RIGHT\nE : SHOOT\nQ : QUIT");

           if(game.isWon()){
            System.out.println("=== YOU WIN! ===");
           }else if(game.isLost()){
            System.out.println("=== YOU LOSE! ===");
           }
        }while(!game.isLost());
         sc.close();
    }
}