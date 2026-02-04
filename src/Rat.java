public class Rat extends Entity {
    public Rat(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 20;
        this.height = 20;
        this.speed = 7;
    }

    public void damage(int damage) {
        this.health -= damage;
    }
}