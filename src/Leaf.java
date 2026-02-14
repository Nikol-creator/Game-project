import java.util.Random;

public class Leaf {
    int x = 0;
    int y = 0;
    int width = 0;
    int height = 0;
    Leaf leftChild = null;
    Leaf rightChild = null;
    int minSize = 60;
    Room room = null;

    int minWidth = 3;
    int minHeight = 3;
    int maxWidth = 9;
    int maxHeight = 9;

    public Leaf(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean canSplit() {
        if (leftChild != null || rightChild != null) return false;
        else if (width <= maxWidth && height <= maxHeight) return false;
        else if (width <= 2 * minWidth && height <= 2 * minHeight) return false;
        else return true;
    }

    public void split() {
        if (!canSplit()) return;

        boolean splitH;
        if (width / height > 1) splitH = false;
        else if (height / width > 1) splitH = true;
        else splitH = Math.random() > 0.5;

        int split;
        Random rand = new Random();

        if (splitH) {
            split = rand.nextInt(height - 2 * minWidth + 1) + minWidth;
            leftChild = new Leaf(x, y, width, split);
            rightChild = new Leaf(x, y + split, width, height - split);
        }
        else {
            split = rand.nextInt(width - 2 * minHeight + 1) + minHeight;
            leftChild = new Leaf(x, y, split, height);
            rightChild = new Leaf(x + split, y, width - split, height);
        }
    }

    public void createRooms() {
        if (leftChild != null || rightChild != null) {
            if (leftChild != null) leftChild.createRooms();
            if (rightChild != null) rightChild.createRooms();
        }
        else {
            Random random = new Random();
            room = new Room(x, y, width, height);
        }
    }

    public Room getRoom() {
        if (room != null) return room;
        else {
            Room leftRoom = null;
            Room rightRoom = null;

            if (leftChild != null) leftRoom = leftChild.getRoom();
            if (rightChild != null) rightRoom = rightChild.getRoom();

            if (leftRoom == null && rightRoom == null) return null;
            else if (leftRoom == null) return rightRoom;
            else if (rightRoom == null) return leftRoom;
            else if (Math.random() > 0.5) return leftRoom;
            else return rightRoom;
        }
    }
}