package natik;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Snake");

        GamePanel gamePanel = new GamePanel();
        frame.getContentPane().add(gamePanel);

        frame.setLocation(500, 160);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
