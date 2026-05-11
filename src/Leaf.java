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
    Room corridor1;
    Room corridor2;

    int minWidth = 10;
    int minHeight = 10;
    int maxWidth = 40;
    int maxHeight = 40;

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
            if (leftChild != null && rightChild != null) {
                createCorridor(leftChild.getRoom(), rightChild.getRoom());
            }
        }
        else {
            Random random = new Random();
            room = new Room(x, y, width - 5, height - 5);


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

    public void createCorridor(Room room1, Room room2) {
        Random random = new Random();
        int AX = room1.x + random.nextInt(room1.width);
        int AY = room1.y + random.nextInt(room1.height);
        int BX = room2.x + random.nextInt(room2.width);
        int BY = room2.y + random.nextInt(room2.height);

        int dX = BX - AX;
        int dY = BY - AY;

        if (dX > 0) {
            if (dY > 0) {
                if (Math.random() > 0.5) {
                    corridor1 = new Room(AX, BY, Math.abs(dX), 1);
                    corridor2 = new Room(AX, AY, 1, Math.abs(dY));
                }
                else {
                    corridor1 = new Room(BX, AY, 1, Math.abs(dY));
                    corridor2 = new Room(AX, AY, Math.abs(dX), 1);
                }
            }
            else if (dY < 0) {
                if (Math.random() > 0.5) {
                    corridor1 = new Room(AX, BY, Math.abs(dX), 1);
                    corridor2 = new Room(AX, BY, 1, Math.abs(dY));
                }
                else {
                    corridor1 = new Room(AX, AY, Math.abs(dX), 1);
                    corridor2 = new Room(BX, BY, 1, Math.abs(dY));
                }
            }
            else {
                corridor1 = new Room(AX, AY, Math.abs(dX), 1);
                corridor2 = new Room(AX, AY, Math.abs(dX), 1);
            }
        }
        else if (dX < 0) {
            if (dY > 0) {
                if (Math.random() > 0.5) {
                    corridor1 = new Room(BX, AY, Math.abs(dX), 1);
                    corridor2 = new Room(BX, AY, 1, Math.abs(dY));
                }
                else {
                    corridor1 = new Room(AX, AY, 1, Math.abs(dY));
                    corridor2 = new Room(BX, BY, Math.abs(dX), 1);
                }
            }
            else if (dY < 0) {
                if (Math.random() > 0.5) {
                    corridor1 = new Room(BX, BY, Math.abs(dX), 1);
                    corridor2 = new Room(AX, BY, 1, Math.abs(dY));
                }
                else {
                    corridor1 = new Room(BX, AY, Math.abs(dX), 1);
                    corridor2 = new Room(BX, BY, 1, Math.abs(dY));
                }
            }
            else {
                corridor1 = new Room(BX, BY, Math.abs(dX), 1);
                corridor2 = new Room(BX, BY, Math.abs(dX), 1);
            }
        }
        else {
            if (dY > 0) {
                corridor1 = new Room(AX, AY, 1, Math.abs(dY));
                corridor2 = new Room(AX, AY, 1, Math.abs(dY));
            }
            else if (dY < 0) {
                corridor1 = new Room(BX, BY, 1, Math.abs(dY));
                corridor2 = new Room(BX, BY, 1, Math.abs(dY));
            }
            else {
                corridor1 = new Room(BX, BY, 1, 1);
                corridor2 = new Room(BX, BY, 1, 1);
            }
        }
    }
}