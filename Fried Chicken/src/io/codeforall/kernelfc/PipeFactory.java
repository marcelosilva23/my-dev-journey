package io.codeforall.kernelfc;

public class PipeFactory {
    public static Pipe pipeCreator() {
        int yMax = 500;
        int yMin = -200;
        Pipe pipe = new Pipe((int) (Math.random() * (yMax - yMin)) + yMin);
        return pipe;
    }
}
