public class PlayerBullet extends Bullet{
    public PlayerBullet(int x, int y, int mouseX, int mouseY) {
        this.x = x;
        this.y = y;
        this.width = 10;
        this.height = 10;
        this.speed = 15;
        if (y > mouseY) this.angle = Math.acos((mouseX - x) / Math.sqrt((x - mouseX) * (x - mouseX) + (y - mouseY) * (y - mouseY)));
        else this.angle = Math.PI * 2 - Math.acos((mouseX - x) / Math.sqrt((x - mouseX) * (x - mouseX) + (y - mouseY) * (y - mouseY)));
    }
}