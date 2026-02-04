import java.awt.*;

public class Button {
    int x = 0;
    int y = 0;
    int width = 0;
    int height = 0;
    Image img;

    public Button(int x, int y, int width, int height, Image img) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.img = img;
    }

    public boolean click(double mouseX, double mouseY) {
        return mouseX >= this.x && mouseX <= this.x + this.width && mouseY >= this.y && mouseY <= this.y + this.height;
    }

    public void paint(Graphics g) {
        g.drawImage(this.img, this.x, this.y, this.width, this.height, null);
    }
}