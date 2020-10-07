package cegepst.engine;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameWindow {
    private JFrame jFrame;
    private static  final int SLEEP = 15;
    private long before;
    private JPanel panel;
    private Ball ball;
    private int score = 0;
    private boolean playing = true;
    private BufferedImage bufferedImage;
    private Graphics2D buffer;

    public GameWindow() {
        initializeFrame();
        Panel();
    }

    public void start() {
        jFrame.setVisible(true);
        updateSyncTime();
        while(playing) {
            bufferedImage = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
            buffer = bufferedImage.createGraphics();
            buffer.setRenderingHints(getOptimalRenderingHints());

            update();
            drawOnBuffer();
            drawOnScreen();
            sleep();
            updateSyncTime();
        }
    }

    private RenderingHints getOptimalRenderingHints() {
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        return rh;
    }

    private void sleep() {
        try {
            Thread.sleep(getSleepTime());
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private long getSleepTime() {
        long sleep = SLEEP - (System.currentTimeMillis() - before);
        if (sleep < 0) {
            sleep = 4;
        }
        return sleep;
    }

    private void updateSyncTime() {
        before = System.currentTimeMillis();
    }

    private void Panel() {
        panel = new JPanel();
        panel.setBackground(Color.blue);
        panel.setFocusable(true);
        panel.setDoubleBuffered(true);
        jFrame.add(panel);
        ball = new Ball(25);
    }

    private void initializeFrame() {
        jFrame = new JFrame();
        jFrame.setSize(800, 600);
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);
        jFrame.setTitle("Bouncing Ball Game");
        //setUndecorated(true); no top bar
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void update() {
        ball.update();
        if (ball.hasTouchedBound()) {
            score += 10;
        }
    }

    private void drawOnBuffer() {
        ball.draw(buffer);
        buffer.setPaint(Color.white);
        buffer.drawString("Score: " + score, 10, 20);
    }

    private void drawOnScreen() {
        Graphics2D graphics2D = (Graphics2D) panel.getGraphics();
        graphics2D.drawImage(bufferedImage, 0, 0, panel);
        Toolkit.getDefaultToolkit().sync();
        graphics2D.dispose();
    }
}
