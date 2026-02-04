import java.util.ArrayList;

public class Bullet extends Entity{
    double angle = 0;

    public Bullet(int x, int y, int width, int height, int speed, int mouseX, int mouseY) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
        if (y > mouseY) this.angle = Math.acos((mouseX - x) / Math.sqrt((x - mouseX) * (x - mouseX) + (y - mouseY) * (y - mouseY)));
        else this.angle = Math.PI * 2 - (Math.acos((mouseX - x) / Math.sqrt((x - mouseX) * (x - mouseX) + (y - mouseY) * (y - mouseY))));
    }

    public Bullet() {
    }

    public void move(ArrayList<Wall> wall) {
        int speedX = 0;
        int speedY = 0;
        boolean canMove = true;
        speedX = (int) (this.speed * Math.cos(this.angle));
        speedY = (int) (this.speed * Math.sin(this.angle) * -1);
        for (int i = 0; i < Math.abs(speedX); i++) {
            for (int j = 0; j < wall.size(); j++) {
                if ((this.x + this.width + 1 == wall.get(j).x && speedX > 0 || this.x + 1 == wall.get(j).x + wall.get(j).width && speedX < 0) && this.y + this.height > wall.get(j).y && this.y < wall.get(j).y + wall.get(j).height) {
                    canMove = false;
                    break;
                }
                else canMove = true;
            }
            if (canMove) this.x += (speedX / Math.abs(speedX));
        }
        for (int i = 0; i < Math.abs(speedY); i++) {
            for (int j = 0; j < wall.size(); j++) {
                if ((this.y + this.height + 1 == wall.get(j).y && speedY > 0 || this.y + 1 == wall.get(j).y + wall.get(j).height && speedY < 0) && this.x + this.width > wall.get(j).x && this.x < wall.get(j).x + wall.get(j).width) {
                    canMove = false;
                    break;
                }
                else canMove = true;
            }
            if (canMove) this.y += (speedY / Math.abs(speedY));
        }
    }
}