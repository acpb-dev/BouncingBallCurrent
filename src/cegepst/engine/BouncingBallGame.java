package cegepst.engine;

import java.awt.*;


public class BouncingBallGame extends Game {
    private Ball ball;
    private int score = 0;

    public BouncingBallGame() {
        ball = new Ball(25);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void conclude() {

    }

    @Override
    public void update() {
        ball.update();
        if (ball.hasTouchedBound()) {
            score += 10;
        }
    }

    @Override
    public void draw(Buffer buffer) {
        ball.draw(buffer);
        buffer.drawText("Score: " + score, 10, 10, Color.white);
        buffer.drawText("fps: " + GameTime.getCurrentFps(), 10, 40, Color.white);
        buffer.drawText("Time: " + GameTime.getElapseFormatTime(), 10 , 70, Color.white);
    }
}
