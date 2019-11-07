package natik;

import java.awt.*;

public class BodyPart {
    private int x;
    private int y;
    private int size;

    public BodyPart(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // cuz segment of size 1 contains TILE_SIZE pixels
    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x * size, y * size, size, size);
    }
}
