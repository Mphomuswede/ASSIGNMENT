/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author RC_Student_lab
 */
import bird.Bird;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class FlappyBirdGame extends JPanel implements ActionListener {
    private Bird bird; // Fixed class name from 'bird' to 'Bird'
    private ArrayList<Pipe> pipes;
    private Timer timer;
    private int score;
    private boolean gameOver;

    public FlappyBirdGame() {
        this.bird = new Bird(100, 300, 20, 20);
        this.pipes = new ArrayList<>();
        this.timer = new Timer(20, this);
        this.score = 0;
        this.gameOver = false;
        this.setPreferredSize(new Dimension(800, 600));
        this.setBackground(Color.CYAN);

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    if (gameOver) {
                        resetGame();
                    } else {
                        bird.jump();
                    }
                }
            }
        });
        this.setFocusable(true);
        timer.start();
    }

    private void resetGame() {
        bird = new Bird(100, 300, 20, 20);
        pipes.clear();
        score = 0;
        gameOver = false;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        bird.render(g);
        for (Pipe pipe : pipes) {
            pipe.render(g);
        }

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 30));
        g.drawString("Score: " + score, 10, 30);

        if (gameOver) {
            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.drawString("Game Over!", 200, 300);
        }
    }

    
    public void actionPerformed(ActionEvent e) {
        if (gameOver) {
            return;
        }

        bird.tick();
        ArrayList<Pipe> pipesToRemove = new ArrayList<>();
        for (Pipe pipe : pipes) {
            pipe.tick();
            if (pipe.isOffScreen()) {
                pipesToRemove.add(pipe);
            }
            if (pipe.getBoundsTop().intersects(bird.getBounds()) || pipe.getBoundsBottom().intersects(bird.getBounds())) {
                gameOver = true;
            }
        }
        pipes.removeAll(pipesToRemove);

        if (pipes.isEmpty() || pipes.get(pipes.size() - 1).getX() < 400) {
            addPipe();
        }

        if (bird.getBounds().getMaxY() > getHeight()) {
            gameOver = true;
        }

        repaint();
    }

    private void addPipe() {
        Random rand = new Random();
        int pipeHeight = rand.nextInt(300) + 100; 
        pipes.add(new Pipe(getWidth(), pipeHeight));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Flappy Bird");
        FlappyBirdGame game = new FlappyBirdGame();
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
