public class Shooter extends Entity{
    int timeShot = 0;
    public Shooter(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 40;
        this.height = 40;
        this.speed = 5;
        this.timeShot = 100;
        this.health = 15;
    }

    public void damage(int damage) {
        this.health -= damage;
    }
}
