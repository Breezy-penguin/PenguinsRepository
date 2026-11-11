public class SuperShip extends Ship{

    int hp;
    public SuperShip(int row, int col, int numBombs) {
        super(row, col, numBombs);
        
        this.hp = 100;
    }
    public int getHP(){
        return hp;
    }
    public void setHP(int hp){
        this.hp = hp;
    }
    public void takeDamage(){
        if(this.hp > 20){
            this.hp -= 20;
        }
    }
}