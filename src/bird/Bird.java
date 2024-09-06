/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bird;

/**
 *
 * @author RC_Student_lab
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bird {
    private int x, y, width, height;
    private int velocity;
    private final int GRAVITY = 1;
    private final int JUMP_STRENGTH = -10;

    public Bird(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.velocity = 0;
    }

    public void jump() {
        this.velocity = JUMP_STRENGTH;
    }

    public void tick() {
        velocity += GRAVITY;
        y += velocity;
    }

    public void render(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, width, height);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
