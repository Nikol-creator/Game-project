public class EnemyBullet extends Bullet {
public EnemyBullet(int x, int y, int mouseX, int mouseY) {
    this.x = x;
    this.y = y;
    this.width = 15;
    this.height = 15;
    this.speed = 12;
    if (y > mouseY) this.angle = Math.acos((mouseX - x) / Math.sqrt((x - mouseX) * (x - mouseX) + (y - mouseY) * (y - mouseY)));
    else this.angle = Math.PI * 2 - Math.acos((mouseX - x) / Math.sqrt((x - mouseX) * (x - mouseX) + (y - mouseY) * (y - mouseY)));
    }
}