package cegepst.engine;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class RenderingEngine {
    private JFrame jFrame;
    private JPanel panel;
    private BufferedImage bufferedImage;

    public RenderingEngine() {
        initializeFrame();
        Panel();
    }

    public void start(){
        jFrame.setVisible(true);
    }

    public void stop() {
        jFrame.setVisible(false);
        jFrame.dispose();
    }

    public Buffer getRenderingBuffer() {
        bufferedImage = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.setRenderingHints(getOptimalRenderingHints());
        return new Buffer(graphics);
    }

    private RenderingHints getOptimalRenderingHints() {
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        return rh;
    }

    public void renderBufferOnScreen() {
        Graphics2D graphics2D = (Graphics2D) panel.getGraphics();
        graphics2D.drawImage(bufferedImage, 0, 0, panel);
        Toolkit.getDefaultToolkit().sync();
        graphics2D.dispose();
    }

    private void Panel() {
        panel = new JPanel();
        panel.setBackground(Color.blue);
        panel.setFocusable(true);
        panel.setDoubleBuffered(true);
        jFrame.add(panel);
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
}


