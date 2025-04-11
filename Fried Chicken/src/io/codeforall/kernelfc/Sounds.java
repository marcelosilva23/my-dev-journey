package io.codeforall.kernelfc;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sounds {

    private Clip clip;

    public Sounds(String filePath) {
        try {
            File soundFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void playLoop() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        }
    }

    public void stop() {
        if (clip != null) {
            clip.stop();
        }

    }
    public void play(){
        if(clip !=null) clip.start();
    }
    public Sounds restartMusic;
    public Sounds menuMusic;
    public Sounds gameMusic;
    public Sounds deadChicken;


}
