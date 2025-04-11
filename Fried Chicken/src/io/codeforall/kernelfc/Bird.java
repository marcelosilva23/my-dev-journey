package io.codeforall.kernelfc;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Bird {
    // handles gravity acceleratiion (to be implemented)
    public int acceleration;
    public boolean isDead = false;
    public Picture bird;
    public final int jumpHeight = 12;
    public final int GRAVITY = 100;
    public int startingPosition = 100;
    public int X = startingPosition;
    public int Y = 100;
    public boolean isJumping = false;

    public Bird() {
        bird = new Picture(startingPosition, Y, "resources/bird.png");
        GameHandler.invocaOTigrão = 8001;
    }

    public void newBird() {
        bird = null;
        bird = new Picture(startingPosition, 100, "resources/bird.png");
        Y = 100;
        acceleration = 0;
    }

    //do move, if falling not jumping, if jumping not falling
    public void fall() {
        if (acceleration < GRAVITY) {
            acceleration++;
        }
        bird.translate(0, acceleration);
        Y = bird.getY();
    }

    // bird jumping logic
    public void jump() {
        acceleration = -jumpHeight;
    }

    //getters
    public int getWidth() {
        return bird.getWidth();
    }

    public int getHeight() {
        return bird.getHeight();
    }


    public void draw() {
        bird.draw();
    }

}



