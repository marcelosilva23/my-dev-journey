package io.codeforall.kernelfc;


import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

import java.io.IOException;
import java.util.ArrayList;

public class GameHandler {
    static boolean isGameRunning = true;
    static int screenWidth = 1800;
    static int screenHeight = 1100;
    static int newPipeTrigger = 900;
    static int pipeSpeed = 5;
    static int invocaOTigrão = 900;
    static int topLimit = 0;
    public static int bottomLimit = 875;
    public static int imageThreshHold = 25;
    public static int dificultyMultiplier = 200;

    public static Picture startMenu = new Picture(10, 10, "resources/startmenu.png");
    public static Picture background = new Picture(10, 0, "resources/background.png");
    public static Picture fried = new Picture(475, 355, "resources/youarefried.png");
    public static Picture coolchicken = new Picture(600, 90, "resources/coolchicken.png");

    public static Bird bird = new Bird();
    public static MyLittleKeyboardHandler kbh = new MyLittleKeyboardHandler(bird);
    public static ArrayList<Pipe> arrayPipe = new ArrayList<>();
    public static Score score = new Score();

    static Sounds deadChicken = new Sounds("resources/deadchicken.wav");
    static Sounds gameMusic = new Sounds("resources/gamemusic.wav");
    static Sounds menuMusic = new Sounds("resources/menumusic.wav");
    static Sounds jumpMusic = new Sounds("resources/chickenjump.wav");

    public static void main(String[] args) throws InterruptedException, IOException {

        isGameRunning = false;
        while (!isGameRunning) {
            startMenu.draw();
            menuMusic.playLoop();
        }
        Rectangle rect = new Rectangle(10, 10, screenWidth, screenHeight);
        rect.setColor(Color.DARK_GRAY);
        rect.draw();
        rect.fill();

        arrayPipe.add(PipeFactory.pipeCreator());

        background.draw();
        score.setScore(0);
        Score.drawScore();
        playMusic();

        startMenu.delete();
        startGame();
        rect = new Rectangle(10, 10, screenWidth, screenHeight);
        rect.draw();

        arrayPipe.add(PipeFactory.pipeCreator());

        background.draw();
        score.setScore(0);
        Score.drawScore();
        playMusic();
    }

    public static void StopGame() {
        coolchicken.draw();
        fried.draw();
        isGameRunning = false;
    }

    public static void restart() throws IOException, InterruptedException {
        Thread.sleep(500);

        bird.bird.delete();
        bird = new Bird();
        bird.isJumping = false;
        kbh = new MyLittleKeyboardHandler(bird);

        for (Pipe pipe : arrayPipe) {
            pipe.upPipe.delete();
            pipe.downPipe.delete();
            pipe.pipeScore.delete();
        }

        arrayPipe.clear();
        arrayPipe.add(PipeFactory.pipeCreator());

        fried.delete();
        coolchicken.delete();

        isGameRunning = true;
        bird.newBird();
        Score.score = 0;
        Score.drawScore();
        startGame();
    }

    public static void startGame() throws InterruptedException, IOException {
        menuMusic.stop();
        bird.draw();
        coolchicken.delete();
        boolean restarting = false;
        while (true) {
            if (restarting) {
                restart();
                restarting = false;
            }

            // create and move for each pipe
            // if last pipe in array position X is < 900
            if (arrayPipe.get(arrayPipe.size() - 1).getX() < newPipeTrigger) {
                // create pipe
                arrayPipe.add(PipeFactory.pipeCreator());
            }

            if (arrayPipe.get(0).getX() < -200) {
                // create pipe
                arrayPipe.remove(0);
            }
            // make the bird fall
            bird.fall();

            //check colision
            if (ColisionHandler.isColliding(arrayPipe, bird)) {
                bird.isDead = true;
            }

            for (Pipe pipe : arrayPipe) {
                pipe.move();
                pipe.draw();
            }

            Thread.sleep(20);

            if (bird.isJumping) {
                jumpMusic = new Sounds("resources/chickenjump.wav");
                jumpMusic.play();
                bird.jump();
                bird.isJumping = false;
            }

            while (!isGameRunning) {
                Thread.sleep(50);
                restarting = true;
            }
        }

    }

    public static void playDeadSound() {
        deadChicken = new Sounds("resources/deadchicken.wav");
        deadChicken.play();
    }

    public static void playMusic() {
        gameMusic = new Sounds("resources/gamemusic.wav");
        gameMusic.playLoop();
    }
}

