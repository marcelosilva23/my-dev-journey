    package io.codeforall.kernelfc;

    import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
    import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
    import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
    import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

    public class MyLittleKeyboardHandler implements KeyboardHandler {
        private final Bird bird;
        private Keyboard keyboard = new Keyboard(this);

        public MyLittleKeyboardHandler(Bird bird) {
            this.bird = bird;
            createKeyboardEvents();
        }

        public void createKeyboardEvents() {

            KeyboardEvent keyboardEventSpace = new KeyboardEvent();
            keyboardEventSpace.setKey(KeyboardEvent.KEY_SPACE);
            keyboardEventSpace.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            keyboard.addEventListener(keyboardEventSpace);

            KeyboardEvent keyboardEventR = new KeyboardEvent();
            keyboardEventR.setKey(KeyboardEvent.KEY_R);
            keyboardEventR.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            keyboard.addEventListener(keyboardEventR);

        }

        @Override
        public void keyPressed(KeyboardEvent keyboardEvent) {

            switch (keyboardEvent.getKey()) {
                case KeyboardEvent.KEY_SPACE:
                    bird.isJumping = true;
                    break;
                case KeyboardEvent.KEY_R:
                        GameHandler.isGameRunning = true;
                    break;
            }
        }

        @Override
        public void keyReleased(KeyboardEvent keyboardEvent) {

        }


    }
