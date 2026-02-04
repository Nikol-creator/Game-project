import java.util.ArrayList;

public class Entity {
    int x = 0;
    int y = 0;
    int speed = 0;
    int health = 0;
    int maxHealth = 0;
    int width = 0;
    int height = 0;

    public Entity() {
    }

    public boolean collision(Entity entity) {
        return (this.x + this.width > entity.x && entity.x + entity.width > this.x) && (this.y + this.height > entity.y && entity.y + entity.height > this.y);
    }

    public void moveTo(int x, int y, ArrayList<Wall> wall) {
        int speedX = 0;
        int speedY = 0;
        boolean canMove = true;
        speedX = (int) ((x - this.x) * speed / Math.sqrt((x - this.x)*(x - this.x) + (y - this.y)*(y - this.y)));
        speedY = (int) ((y - this.y) * speed / Math.sqrt((x - this.x)*(x - this.x) + (y - this.y)*(y - this.y)));
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

    public void moveTo(Entity entity, ArrayList<Wall> wall) {
        int speedX = 0;
        int speedY = 0;
        boolean canMove = true;
        speedX = (int) ((entity.x - this.x) * speed / Math.sqrt((entity.x - this.x)*(entity.x - this.x) + (entity.y - this.y)*(entity.y - this.y)));
        speedY = (int) ((entity.y - this.y) * speed / Math.sqrt((entity.x - this.x)*(entity.x - this.x) + (entity.y - this.y)*(entity.y - this.y)));
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

    public int distance(Entity entity) {
        return (int) Math.sqrt(Math.abs((this.x - entity.x) * (this.x - entity.x)) + Math.abs((this.y - entity.y) * (this.y - entity.y)));
    }

    public void combustion() {
        this.health -= 1;
    }

    public void dying() {
    }
}