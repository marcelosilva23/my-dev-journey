package io.codeforall.kernelfc;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Pipe {

    int y;
    public Picture upPipe;
    public Picture downPipe;
    // invisible score rectangle
    public Rectangle pipeScore;
    public boolean scored;

    //gap between the top and bottom pipe, score´s height
    public int gap = 500;
    // tracks the groups X position
    public int X = GameHandler.screenWidth - 100;

    // initiate bottom pipe, top pipe and invisible score rectangle
    // Y parameter to be randomized by the pipe factory
    public Pipe(int y) {
        this.y = y;
        upPipe = new Picture(X, y - gap, "resources/toppipe.png");
        downPipe = new Picture(X, y + gap, "resources/bottompipe.png");
        pipeScore = new Rectangle(X, y, 100, gap);
    }

    // moves the pipe group 1px to the left, used for the automove of the pipes
    public void move() {
        upPipe.translate(-GameHandler.pipeSpeed, 0);
        downPipe.translate(-GameHandler.pipeSpeed, 0);
        pipeScore.translate(-GameHandler.pipeSpeed, 0);
        X = upPipe.getX();
    }

    public void setGap(int gap) {
        this.gap = gap;
    }

    public double getX() {
        return X;
    }

    public void draw() {
        upPipe.draw();
        downPipe.draw();
    }
}
