public class Rat extends Entity {
    public Rat(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 30;
        this.height = 30;
        this.speed = 7;
        this.health = 10;
    }

    public void damage(int damage) {
        this.health -= damage;
    }
}