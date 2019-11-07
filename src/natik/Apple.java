package natik;

import java.awt.*;
import java.util.Random;

public class Apple {
    private int x;
    private int y;
    private int size;
    private Random random = new Random();
    private Color color;

    public Apple(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
        color = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
        if (color.equals(Color.BLACK)) {
            color = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x * size, y * size, size, size);
    }
}
