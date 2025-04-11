package io.codeforall.kernelfc;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.util.ArrayList;

public class ColisionHandler {
    public static int topLimit = GameHandler.topLimit;
    public static int bottomLimit = GameHandler.bottomLimit;
    public static int imageThreshHold = GameHandler.imageThreshHold;

    // automatic collision detection
    public static boolean checkColision(Picture obstacle, Bird bird) {
        if (
                obstacle.getX() < bird.X + bird.getWidth() - imageThreshHold &&
                        obstacle.getX() + obstacle.getWidth() > bird.X + imageThreshHold &&
                        obstacle.getY() < bird.Y + bird.getHeight() - imageThreshHold &&
                        obstacle.getY() + obstacle.getHeight() > bird.Y + imageThreshHold) {
            return true;
        }
        return false;
    }

    // isColiding return if is colliding ? true : false
    public static boolean isColliding(ArrayList<Pipe> pipes, Bird bird) {
        if (bird.isDead) {
            return true;
        }
        // is hitting the top or bottom Limit?
        if (bird.Y < topLimit || bird.Y > bottomLimit) {
            GameHandler.playDeadSound();
            GameHandler.StopGame();
            return true;
        }

        // for each pipe in pipes
        for (Pipe pipe : pipes) {
            Rectangle score = pipe.pipeScore;
            // if bird is colliding with current pipe
            if (
                    checkColision(pipe.upPipe, bird) || checkColision(pipe.downPipe, bird)
            ) {
                // return true
                GameHandler.playDeadSound();
                GameHandler.StopGame();
                return true;
            } else if (
                // hit score
                    score.getX() < bird.X + bird.getWidth() &&
                            score.getX() + score.getWidth() > bird.X &&
                            score.getY() < bird.Y + bird.getHeight() &&
                            score.getY() + score.getHeight() > bird.Y &&
                            !pipe.scored
            ) {
                Score.add(1);
                Score.drawScore();
                pipe.scored = true;
                return false;
            }
        }
        // if bird is not colliding with any pipe, return false
        return false;
    }
}
