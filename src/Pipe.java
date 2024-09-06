/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author RC_Student_lab
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Pipe {
    private int x, width, height, gap;
    private final int PIPE_WIDTH = 50;
    private final int GAP_SIZE = 150;
    private final int SPEED = 2;

    public Pipe(int x, int height) {
        this.x = x;
        this.height = height;
        this.width = PIPE_WIDTH;
        this.gap = GAP_SIZE;
    }

    public void tick() {
        x -= SPEED;
    }

    public void render(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x, 0, width, height);
        g.fillRect(x, height + gap, width, 600 - height - gap);
    }

    public Rectangle getBoundsTop() {
        return new Rectangle(x, 0, width, height);
    }

    public Rectangle getBoundsBottom() {
        return new Rectangle(x, height + gap, width, 600 - height - gap);
    }

    public boolean isOffScreen() {
        return x + width < 0;
    }

    public int getX() {
        return x;
    }
}
