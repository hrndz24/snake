package natik;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable, KeyListener {

    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;
    private static final int TILE_SIZE = 25;
    private static final int DELAY = 300;

    //head coordinates
    private int x, y;
    private ArrayList<BodyPart> snake;
    private boolean right = true, left, up, down;

    private Random random = new Random();
    private Food food;

    private boolean running;
    private Thread thread;

    public GamePanel() {
        setFocusable(true);//to press keys
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        thread = new Thread(this);
        thread.start();
        running = true;
        snake = new ArrayList<>();
        x = random.nextInt(WIDTH / TILE_SIZE / 2);
        y = random.nextInt(HEIGHT / TILE_SIZE / 2);
        food = new Food(random.nextInt(WIDTH / TILE_SIZE), random.nextInt(WIDTH / TILE_SIZE), TILE_SIZE);
        addKeyListener(this);
    }

    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.DARK_GRAY);
        for (int i = 0; i < WIDTH / TILE_SIZE; i++) {
            g.drawLine(i * TILE_SIZE, 0, i * TILE_SIZE, HEIGHT);
        }

        for (int i = 0; i < HEIGHT / TILE_SIZE; i++) {
            g.drawLine(0, i * TILE_SIZE, WIDTH, i * TILE_SIZE);
        }

        for (int i = 0; i < snake.size(); i++) {
            if (i == snake.size() - 1) {
                snake.get(i).drawHead(g);
            } else {
                snake.get(i).draw(g);
            }
        }
        food.draw(g);
    }

    private void tick() throws InterruptedException {

        boolean collision = true; //possibility of collision with itself
        if (snake.size() == 0) {
            snake.add(new BodyPart(x, y, TILE_SIZE));
        }
        thread.sleep(DELAY);
        if (right) {
            x++;
        }
        if (left) {
            x--;
        }
        if (down) {
            y++;
        }
        if (up) {
            y--;
        }
        // it doesn't move the snake, it adds a new part and removes the last
        snake.add(new BodyPart(x, y, TILE_SIZE));
        snake.remove(0);

        if (x == food.getX() && y == food.getY()) {
            locateFood();
            BodyPart b = new BodyPart(snake.get(snake.size() - 1).getX(), snake.get(snake.size() - 1).getY(), TILE_SIZE);
            snake.add(0, b);
            collision = false; // so that not to stop when it gets the food
        }

        //collision with itself
        for (int i = 0; i < snake.size(); i++) {
            if (x == snake.get(i).getX() && y == snake.get(i).getY()) {
                if (collision && (i != snake.size() - 1)) {
                    stop();
                }
            }
        }

        if (x < 0) {
            x = WIDTH / TILE_SIZE;
        }
        if (x > WIDTH / TILE_SIZE) {
            x = -1;
        }
        if (y < 0) {
            y = HEIGHT / TILE_SIZE;
        }
        if (y > HEIGHT / TILE_SIZE) {
            y = -1;
        }
        /** in case of collision with the boards
         if (x < 0 || x > WIDTH / TILE_SIZE - 1 || y < 0 || y > HEIGHT / TILE_SIZE - 1) {
         stop();
         }
         */
        if (snake.size() == WIDTH * HEIGHT / (TILE_SIZE * TILE_SIZE)) {
            stop();
        }
    }

    //so that the apple isn't located inside the snake
    public void locateFood() {
        food = new Food(random.nextInt(WIDTH / TILE_SIZE), random.nextInt(WIDTH / TILE_SIZE), TILE_SIZE);
        for (BodyPart b : snake) {
            if (b.getX() == food.getX() && b.getY() == food.getY()) {
                locateFood();
            }
        }
    }

    @Override
    public void run() {
        while (running) {
            try {
                tick();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            repaint();
        }
    }

    private void stop() {
        running = false;
        try {
            JFrame frame = new JFrame();
            frame.setSize(400, 150);
            JLabel label = new JLabel();
            if (snake.size() == WIDTH * HEIGHT / (TILE_SIZE * TILE_SIZE)) {
                label.setText("CONGRATULATIONS");
            } else {
                label.setText("GAME OVER");
            }
            label.setHorizontalAlignment(JLabel.CENTER);
            frame.setLocation(580, 250);
            frame.getContentPane().setBackground(Color.YELLOW);
            frame.getContentPane().add(label);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT && !left) {
            right = true;
            up = false;
            down = false;
        }
        if (key == KeyEvent.VK_LEFT && !right) {
            left = true;
            up = false;
            down = false;
        }
        if (key == KeyEvent.VK_UP && !down) {
            right = false;
            up = true;
            left = false;
        }
        if (key == KeyEvent.VK_DOWN && !up) {
            right = false;
            left = false;
            down = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
