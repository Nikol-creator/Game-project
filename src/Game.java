import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class Game extends JPanel implements ActionListener, KeyListener, MouseListener {

    static ArrayList<Leaf> leaves = new ArrayList<>();
    static boolean allSplit = false;
    static ArrayList<Room> corridors = new ArrayList<>();

    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension screenSize = toolkit.getScreenSize();
    private final int screenWidth = screenSize.width;
    private final int screenHeight = screenSize.height;

    private int cameraX = 0;
    private int cameraY = 0;

    Player player = new Player(1, 0, 48, 48, 17);

    ArrayList<Wall> wall = new ArrayList<>();
    ArrayList<PlayerBullet> playerBullet = new ArrayList<>();
    ArrayList<EnemyBullet> enemyBullet = new ArrayList<>();

    boolean inGame = false;
    boolean mainMenu = true;
    boolean settingsMain = false;

    Button button1 = new Button((screenWidth - 192) / 2, (screenHeight - 108) / 2, 192, 108, new ImageIcon(".idea/images/igraPressed.png").getImage());

    boolean directionLeft = true;

    ArrayList<Rat> rat = new ArrayList<>();
    ArrayList<Shooter> shooter = new ArrayList<>();

    Timer timer;
    boolean gameOver = false;
    private boolean pause = false;
    private boolean map = false;
    private boolean moveW = false;
    private boolean moveA = false;
    private boolean moveS = false;
    private boolean moveD = false;
    int cooldown = 0;
    private static double mouseX = 0;
    private static double mouseY = 0;
    private boolean newFloor = true;
    static JFrame frame = new JFrame("Simple Game");

    public Game() {
        addKeyListener(this);
        addMouseListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(20, this);
        timer.start();
    }

    public static void main(String[] args) {
        //   JFrame frame = new JFrame("Simple Game");
        Game game = new Game();
        frame.add(game);
        //    frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        cameraX = player.x - 800;
        cameraY = player.y - 450;

/*
        if (inGame) {


            if (!map) {

                for (int i = -2; i < 2; i++) {
                    for (int j = -2; j < 2; j++) {
                        Image img1 = new ImageIcon(".idea/images/floar.png").getImage();
                        g.drawImage(img1, -cameraX + i * 1920, -cameraY + j * 1080, 1920, 1080, null);
                    }
                }

                if (directionLeft) {
                    Image img = new ImageIcon(".idea/images/dinozaur.png").getImage();
                    g.drawImage(img, player.x - cameraX, player.y - cameraY, 48, 48, null);
                } else {
                    Image img = new ImageIcon(".idea/images/dinozaur2.png").getImage();
                    g.drawImage(img, player.x - cameraX, player.y - cameraY, 48, 48, null);
                }

                //   g.drawImage(op.filter(unicornImage, null), 100, 100, null);


                for (Rat value : rat) {
                    g.setColor(Color.RED);
                    g.fillOval(value.x - cameraX, value.y - cameraY, value.width, value.height);
                }
                for (Shooter value : shooter) {
                    g.setColor(Color.RED);
                    g.fillOval(value.x - cameraX, value.y - cameraY, value.width, value.height);
                }

                for (PlayerBullet bullet : playerBullet) {
                    g.setColor(Color.GREEN);
                    g.fillOval(bullet.x - cameraX, bullet.y - cameraY, bullet.width, bullet.height);
                }
                for (EnemyBullet bullet : enemyBullet) {
                    g.setColor(Color.BLUE);
                    g.fillOval(bullet.x - cameraX, bullet.y - cameraY, bullet.width, bullet.height);
                }

                for (Wall value : wall) {
                    g.setColor(Color.CYAN);
                    g.fillRect(value.x - cameraX, value.y - cameraY, value.width, value.height);
                }

                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial", Font.PLAIN, 40));
                g.drawString("Счет: " + score, 10, 40);
                g.drawString("Здоровье: " + player.health + "/" + player.maxHealth, 220, 40);

                if (gameOver) {
                    g.setFont(new Font("Arial", Font.PLAIN, 100));
                    g.drawString("Конец игры", 770, 500);
                }

            }
        }

        if (mainMenu) {
            Image img = new ImageIcon(".idea/images/main_manu.png").getImage();
            g.drawImage(img, 0, 0, 1920, 1080, null);

         /*   if (button1.click(mouseX, mouseY)) {
                Image img1 = new ImageIcon(".idea/images/igraPressed.png").getImage();
                g.drawImage(img1, button1.x, button1.y, button1.width, button1.height, null);
            } else {
                Image img1 = new ImageIcon(".idea/images/igra.png").getImage();
                g.drawImage(img1, button1.x, button1.y, button1.width, button1.height, null);
            }*/

        /*

            button1.paint(g);

            if (mouseX > 1000 && mouseX < 1192 && mouseY > 700 && mouseY < 808) {
                Image img2 = new ImageIcon(".idea/images/buttonSettingsPressed.png").getImage();
                g.drawImage(img2, 1000, 700, 192, 108, null);
            } else {
                Image img2 = new ImageIcon(".idea/images/buttonSettings.png").getImage();
                g.drawImage(img2, 1000, 700, 192, 108, null);
            }

            if (mouseX > 700 && mouseX < 892 && mouseY > 700 && mouseY < 808) {
                Image img2 = new ImageIcon(".idea/images/buttonExitPressed.png").getImage();
                g.drawImage(img2, 700, 700, 192, 108, null);
            } else {
                Image img2 = new ImageIcon(".idea/images/buttonExit.png").getImage();
                g.drawImage(img2, 700, 700, 192, 108, null);
            }
        }

        if (settingsMain) {
            Image img = new ImageIcon(".idea/images/settings.png").getImage();
            g.drawImage(img, 0, 0, 1920, 1080, null);

            if (mouseX > 1000 && mouseX < 1192 && mouseY > 500 && mouseY < 608) {
                Image img1 = new ImageIcon(".idea/images/buttonMainMenuPressed.png").getImage();
                g.drawImage(img1, 1000, 500, 192, 108, null);
            } else {
                Image img1 = new ImageIcon(".idea/images/buttonMainMenu.png").getImage();
                g.drawImage(img1, 1000, 500, 192, 108, null);
            }
        }

        */

        for (int i = -2; i < 2; i++) {
            for (int j = -2; j < 2; j++) {
                g.setColor(Color.DARK_GRAY);
//                g.fillRect(-cameraX + i * 1920, -cameraY + j * 1080, 1920, 1080);
            }
        }
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(-1920, -1080, 19200, 10800);


        for (Wall value : wall) {
            g.setColor(Color.GRAY);
            g.fillRect(value.x - cameraX, value.y - cameraY, value.width, value.height);
        }

//        g.setColor(Color.GREEN);
//        g.fillRect(player.x - cameraX, player.y - cameraY, player.width, player.height);
        Image imgp = new ImageIcon(".idea/images/player.png").getImage();
        g.drawImage(imgp, player.x - cameraX, player.y - cameraY, player.width, player.height, null);



        for (Rat value : rat) {
//            g.setColor(Color.RED);
//            g.fillRect(value.x - cameraX, value.y - cameraY, value.width, value.height);
            Image img = new ImageIcon(".idea/images/slime.png").getImage();
            g.drawImage(img, value.x - cameraX, value.y - cameraY, value.width, value.height, null);
        }


        for (Shooter value : shooter) {
//            g.setColor(Color.ORANGE);
//            g.fillRect(value.x - cameraX, value.y - cameraY, value.width, value.height);
            Image img = new ImageIcon(".idea/images/shooter.png").getImage();
            g.drawImage(img, value.x - cameraX, value.y - cameraY, value.width, value.height, null);
        }


        for (PlayerBullet bullet : playerBullet) {
            g.setColor(Color.BLACK);
            g.fillOval(bullet.x - cameraX, bullet.y - cameraY, bullet.width, bullet.height);
        }

        for (EnemyBullet bullet : enemyBullet) {
            g.setColor(Color.RED);
            g.fillOval(bullet.x - cameraX, bullet.y - cameraY, bullet.width, bullet.height);
        }


     /*   for (int k = 0; k < leaves.size(); k++) {
//                    if (i >= leaves.get(k).room.x && i <= leaves.get(k).room.x + leaves.get(k).room.width && j >= leaves.get(k).room.y && j <= leaves.get(k).room.y + leaves.get(k).room.height) break;
            if (leaves.get(k).room != null) {
                g.setColor(Color.getHSBColor((float) (Math.random() * 250), (float) (Math.random() * 250), (float) (Math.random() * 250)));
                g.fillRect(leaves.get(k).room.x * 10, leaves.get(k).room.y * 10, leaves.get(k).room.width * 10, leaves.get(k).room.height * 10);
            }
        }*/



    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Point location = MouseInfo.getPointerInfo().getLocation();

        if (inGame) {
            mouseX = location.getX() + cameraX;
            mouseY = location.getY() + cameraY;
        } else {
            mouseX = location.getX();
            mouseY = location.getY();
        }

        int toX = 0;
        int toY = 0;

        if (moveW) toY -= 100;
        if (moveS) toY += 100;
        if (moveA) toX -= 100;
        if (moveD) toX += 100;

        player.moveTo(player.x + toX, player.y + toY, wall);

        for (Rat value : rat) {
            if (value.distance(player) < 500) value.moveTo(player, wall);
        }

        for (Shooter value : shooter) {
            if (value.distance(player) < 750 && value.distance(player) > 300) {
                value.moveTo(player, wall);
            }
            if (value.distance(player) < 450) {
                if (value.timeShot > 30) {
                    enemyBullet.add(new EnemyBullet(value.x + value.width/2, value.y + value.height/2, player.x + player.width/2, player.y + player.height/2));
                    value.timeShot = 0;
                }
            }
            value.timeShot ++;
        }

        for (int i = 0; i < playerBullet.size(); i++) {
            if(playerBullet.get(i).move(wall)) playerBullet.remove(i);
        }
        for (int i = 0; i < enemyBullet.size(); i++) {
            if(enemyBullet.get(i).move(wall)) enemyBullet.remove(i);
        }

        for (int i = 0; i < enemyBullet.size(); i++) {
            if (enemyBullet.get(i).collision(player)) enemyBullet.remove(i);
        }

        for (int i = 0; i < playerBullet.size(); i++) {
            for (Rat value : rat) {
                if (value.collision(playerBullet.get(i))) {
                    value.damage(3);
                    playerBullet.remove(i);
                    break;
                }
            }
        }
        for (int i = 0; i < playerBullet.size(); i++) {
            for (Shooter value : shooter) {
                if (value.collision(playerBullet.get(i))) {
                    value.damage(3);
                    playerBullet.remove(i);
                    break;
                }
            }
        }

        for (int i = 0; i < rat.size(); i++) {
            if (rat.get(i).health <= 0) {
                rat.remove(i);
            }
        }
        for (int i = 0; i < shooter.size(); i++) {
            if (shooter.get(i).health <= 0) {
                shooter.remove(i);
            }
        }

        cooldown --;



        if (!gameOver) {


            repaint();


            if (newFloor) {
                walls();
//                rat.add(new Rat(30, 90));
//                rat.add(new Rat(300, 900));
//                rat.add(new Rat(3000, 90));
//                shooter.add(new Shooter(100, 300));
                newFloor = false;
            }
        }
    }

    public void walls() {
        level();

//        Random rand = new Random();
//        for (int i = 0; i < 200; i++) {
//            int x = rand.nextInt(210);
//            int y = rand.nextInt(140);
//            wall.add(new Wall(x * 50, y * 50, 50, 50));
//        }


//        for (int i = 0; i < leaves.size(); i++) {
//            for (int j = 0; j < leaves.get(i).room.x; j++) {
//                for (int k = 0; k < leaves.get(i).room.y; k++) {
//                    wall.add(new Wall(j * 50, k * 50, 50, 50));
//                }
//            }
//        }

//        for (int k = 0; k < leaves.size(); k++) {
//            if (leaves.get(k).room != null) rat.add(new Rat(leaves.get(k).room.height - 5, leaves.get(k).room.y + 2));
//        }



        for (int k = 0; k < leaves.size(); k++) {
//                    if (i >= leaves.get(k).room.x && i <= leaves.get(k).room.x + leaves.get(k).room.width && j >= leaves.get(k).room.y && j <= leaves.get(k).room.y + leaves.get(k).room.height) break;
            if (leaves.get(k).room != null) {
                System.out.println(leaves.get(k).room.x + " " + leaves.get(k).room.y + " " + leaves.get(k).room.width + " " + leaves.get(k).room.height);
            }
        }


        boolean wal = false;
        wall.add(new Wall(-5 * 50, -5 * 50, 50, 50));
        for (int i = -5; i < 200; i++) {
            for (int j = -5; j < 200; j++) {
                for (int k = 0; k < leaves.size(); k++) {
                    if (leaves.get(k).corridor1 != null) if ((i >= leaves.get(k).corridor1.x && i < leaves.get(k).corridor1.x + leaves.get(k).corridor1.width && j >= leaves.get(k).corridor1.y && j < leaves.get(k).corridor1.y + leaves.get(k).corridor1.height) || (i >= leaves.get(k).corridor2.x && i < leaves.get(k).corridor2.x + leaves.get(k).corridor2.width && j >= leaves.get(k).corridor2.y && j < leaves.get(k).corridor2.y + leaves.get(k).corridor2.height)) {
                        wal = false;
                        break;
                    }

                    if (leaves.get(k).room != null) if (i >= leaves.get(k).room.x && i < leaves.get(k).room.x + leaves.get(k).room.width && j >= leaves.get(k).room.y && j < leaves.get(k).room.y + leaves.get(k).room.height) {
                        wal = false;
                        if (i > 5 && j > 5) {
                            if (Math.random() < 0.006) rat.add(new Rat(i * 50, j * 50));
                            if (Math.random() < 0.002) shooter.add(new Shooter(i * 50, j * 50));
                        }
                        break;
                    }
//                    System.out.println(leaves.get(k).getRoom().x + " " + leaves.get(k).getRoom().y + " " + leaves.get(k).getRoom().width + " " + leaves.get(k).getRoom().height);
                }
                if (wal) wall.add(new Wall(i * 50, j * 50, 50, 50));
                if (wal) System.out.println(i + " " + j);
                wal = true;
            }
        }
    }

    public void pause() {
        if (pause) {
            timer.start();
            pause = false;
        }
        else {
            timer.stop();
            pause = true;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        int A = KeyEvent.VK_A;
        if (key == KeyEvent.VK_I) {
            mainMenu = false;
            inGame = true;
            settingsMain = false;
            timer.stop();
        }
        if (key == KeyEvent.VK_U) {
            mainMenu = true;
            inGame = false;
            settingsMain = false;
        }
        if (key == KeyEvent.VK_T) {
            player.x = 0;
            player.y = 0;
        }
        if (!gameOver) {

            if (key == KeyEvent.VK_W) {
                moveW = true;
            }
            if (key == A) {
                moveA = true;
            }
            if (key == KeyEvent.VK_S) {
                moveS = true;
            }
            if (key == KeyEvent.VK_D) {
                moveD = true;
            }

            if (key == KeyEvent.VK_SPACE && cooldown <= 0) {
                playerBullet.add(new PlayerBullet(player.x + player.width / 2, player.y + player.height / 2, (int) mouseX + cameraX, (int) mouseY + cameraY));
                cooldown = 10;
            }

          /*  if (key == KeyEvent.VK_SPACE) {
                jump = true;
            }
            if (key == KeyEvent.VK_A) {
                playerSpeedA = playerSpeed * -1;
                moveA = true;
                directionLeft = true;
            }
            if (key == KeyEvent.VK_S) {
                moveS = true;
            }
            if (key == KeyEvent.VK_D) {
                moveD = true;
                playerSpeedD = playerSpeed;
                directionLeft = false;
            }*/
            //     if (key == KeyEvent.VK_SPACE && cooldown > playercooldown) {
            //         shot();
            //         cooldown = 0;
            //     }
            if (key == KeyEvent.VK_C) {
                map = !map;
            }
        }
        if (key == KeyEvent.VK_Q && !gameOver) {
            pause();
        }
        if (key == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) {
            moveW = false;
        }
        if (key == KeyEvent.VK_A) {
            moveA = false;
        }
        if (key == KeyEvent.VK_S) {
            moveS = false;
        }
        if (key == KeyEvent.VK_D) {
            moveD = false;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {

        if (mainMenu && button1.click(mouseX, mouseY)) {
            mainMenu = false;
            inGame = true;
        }

        if (mainMenu && mouseX > 1000 && mouseX < 1192 && mouseY > 700 && mouseY < 808) {
            mainMenu = false;
            settingsMain = true;
        }

        if (settingsMain && mouseX > 1000 && mouseX < 1192 && mouseY > 500 && mouseY < 608) {
            settingsMain = false;
            mainMenu = true;
        }

        if (mainMenu && mouseX > 700 && mouseX < 892 && mouseY > 700 && mouseY < 808) {
            System.exit(0);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public void level() {
        Leaf rootLeaf = new Leaf(0, 0, 200, 200);
        leaves.add(rootLeaf);

        while (!allSplit) {
            allSplit = true;
            for (int i = 0; i < leaves.size(); i++) {
                //    System.out.println(leaves.get(i).x + " " + leaves.get(i).y);
//                System.out.println(leaves.get(i).canSplit());
                //   leaves.get(i).split();
                if (leaves.get(i).canSplit()) {
                    leaves.get(i).split();
                    leaves.add(leaves.get(i).leftChild);
                    leaves.add(leaves.get(i).rightChild);
                    allSplit = false;
                }
            }
        }

        rootLeaf.createRooms();
    }
}