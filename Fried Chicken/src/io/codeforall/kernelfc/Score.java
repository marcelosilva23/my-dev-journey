package io.codeforall.kernelfc;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;

public class Score {
    public static int score = 0;
    public static Text scoreImg;
    public static int minDistanceBetweenPipes = 100;

    public Score() {
        scoreImg = new Text(50, 50, "0");
        scoreImg.grow(30, 40);
        scoreImg.setColor(Color.WHITE);
    }

    public static void drawScore() {
        scoreImg.setText(score + "");
        scoreImg.draw();
    }

    // add score
    public static void add(int ammount) {
        score += ammount;
        int temp = GameHandler.newPipeTrigger + (ammount * GameHandler.dificultyMultiplier);
        if (temp < minDistanceBetweenPipes) {
            GameHandler.newPipeTrigger = temp;
            System.out.println("temp" + temp);
        }
    }

    public void setScore(int score) {
        this.score = score;
    }

}
