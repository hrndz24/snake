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

    public void drawHead(Graphics g){
        g.setColor(Color.GREEN);
        g.fillRect(x * size, y * size, size, size);
        g.setColor(Color.BLACK);
        g.drawOval(x*size+5, y*size+5, 5, 5);
        g.fillOval(x*size+5, y*size+5, 5, 5);
        g.drawOval(x*size+15, y*size+5, 5, 5);
        g.fillOval(x*size+15, y*size+5, 5, 5);
        g.drawArc(x * size+3, y * size+6, size-6, size-10, 180, 180);
        g.fillArc(x * size+2, y * size+6, size-4, size-9, 180, 180);
    }
}
