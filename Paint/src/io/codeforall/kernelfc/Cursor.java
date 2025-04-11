package io.codeforall.kernelfc;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;

public class Cursor implements KeyboardHandler {

    private Keyboard keyboard;
    private Rectangle cursor;
    private HashSet<String> paintedCell;
    private HashMap<String, Rectangle> paintedRectangles;
    private Color currentColor;

    public Cursor() {
        this.keyboard = new Keyboard(this);
        this.cursor = new Rectangle(10, 10, 50, 50);
        this.paintedCell = new HashSet<>();
        this.paintedRectangles = new HashMap<>();
        this.currentColor = Color.PINK;
        cursor.setColor(currentColor);
        cursor.fill();
        createKeyboardEvent();
    }

    public void createKeyboardEvent() {
        KeyboardEvent keyboardEventSpace = new KeyboardEvent();
        keyboardEventSpace.setKey(KeyboardEvent.KEY_SPACE);
        keyboardEventSpace.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(keyboardEventSpace);

        KeyboardEvent keyboardEventRight = new KeyboardEvent();
        keyboardEventRight.setKey(KeyboardEvent.KEY_RIGHT);
        keyboardEventRight.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(keyboardEventRight);

        KeyboardEvent keyboardEventLeft = new KeyboardEvent();
        keyboardEventLeft.setKey(KeyboardEvent.KEY_LEFT);
        keyboardEventLeft.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(keyboardEventLeft);

        KeyboardEvent keyboardEventUp = new KeyboardEvent();
        keyboardEventUp.setKey(KeyboardEvent.KEY_UP);
        keyboardEventUp.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(keyboardEventUp);

        KeyboardEvent keyboardEventDown = new KeyboardEvent();
        keyboardEventDown.setKey(KeyboardEvent.KEY_DOWN);
        keyboardEventDown.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(keyboardEventDown);

        KeyboardEvent keyboardEventC = new KeyboardEvent();
        keyboardEventC.setKey(KeyboardEvent.KEY_C);
        keyboardEventC.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(keyboardEventC);

        KeyboardEvent keyboardEventL = new KeyboardEvent();
        keyboardEventL.setKey(KeyboardEvent.KEY_L);
        keyboardEventL.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(keyboardEventL);

        KeyboardEvent keyboardEventS = new KeyboardEvent();
        keyboardEventS.setKey(KeyboardEvent.KEY_S);
        keyboardEventS.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(keyboardEventS);

        KeyboardEvent keyboardEvent1 = new KeyboardEvent();
        keyboardEvent1.setKey(KeyboardEvent.KEY_1);
        keyboardEvent1.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(keyboardEvent1);

        KeyboardEvent keyboardEvent2 = new KeyboardEvent();
        keyboardEvent2.setKey(KeyboardEvent.KEY_2);
        keyboardEvent2.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(keyboardEvent2);

        KeyboardEvent keyboardEvent3 = new KeyboardEvent();
        keyboardEvent3.setKey(KeyboardEvent.KEY_3);
        keyboardEvent3.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(keyboardEvent3);

        KeyboardEvent keyboardEvent4 = new KeyboardEvent();
        keyboardEvent4.setKey(KeyboardEvent.KEY_4);
        keyboardEvent4.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(keyboardEvent4);

        KeyboardEvent keyboardEvent5 = new KeyboardEvent();
        keyboardEvent5.setKey(KeyboardEvent.KEY_5);
        keyboardEvent5.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(keyboardEvent5);
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        int newX = cursor.getX();
        int newY = cursor.getY();

        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_LEFT:
                if (newX > Grid.getX(0)) {
                    cursor.translate(-50, 0);
                    updateCursorColor();
                }
                break;
            case KeyboardEvent.KEY_RIGHT:
                if (newX < Grid.getX(Grid.COLS - 1)) {
                    cursor.translate(50, 0);
                    updateCursorColor();
                }
                break;
            case KeyboardEvent.KEY_UP:
                if (newY > Grid.getY(0)) {
                    cursor.translate(0, -50);
                    updateCursorColor();
                }
                break;
            case KeyboardEvent.KEY_DOWN:
                if (newY < Grid.getY(Grid.ROWS - 1)) {
                    cursor.translate(0, 50);
                    updateCursorColor();
                }
                break;
            case KeyboardEvent.KEY_SPACE:
                changeColor(newX, newY);
                break;
            case KeyboardEvent.KEY_C:
                clear();
                break;
            case KeyboardEvent.KEY_S:
                save();
                break;
            case KeyboardEvent.KEY_L:
                load();
                break;
            case KeyboardEvent.KEY_1:
                currentColor = Color.BLUE;
                updateCursorColor();
                break;
            case KeyboardEvent.KEY_2:
                currentColor = Color.BLACK;
                updateCursorColor();
                break;
            case KeyboardEvent.KEY_3:
                currentColor = Color.YELLOW;
                updateCursorColor();
                break;
            case KeyboardEvent.KEY_4:
                currentColor = Color.RED;
                updateCursorColor();
                break;
            case KeyboardEvent.KEY_5:
                currentColor = Color.PINK;
                updateCursorColor();
                break;
        }
    }

    private void updateCursorColor() {
        cursor.setColor(currentColor);
        cursor.fill();
        bringCursorToFront();
    }

    private void bringCursorToFront() {
        cursor.delete();
        cursor.fill();
    }

    private void changeColor(int x, int y) {
        String positionKey = x + "-" + y;

        if (paintedCell.contains(positionKey)) {

            Rectangle cell = paintedRectangles.get(positionKey);
            if (!cell.getColor().equals(currentColor)) {
                cell.setColor(currentColor);
                cell.fill();
                paintedCell.add(positionKey);
            } else {

                unpaintCell(x, y);
                paintedCell.remove(positionKey);
            }
        } else {

            paintCell(x, y);
            paintedCell.add(positionKey);
        }
    }

    private void paintCell(int x, int y) {
        String positionKey = x + "-" + y;
        Rectangle paintedCell = paintedRectangles.get(positionKey);

        if (paintedCell == null) {
            paintedCell = new Rectangle(x, y, 50, 50);
            paintedCell.setColor(currentColor);
            paintedCell.fill();
            paintedRectangles.put(positionKey, paintedCell);
        } else {
            paintedCell.setColor(currentColor);
            paintedCell.fill();
        }
    }

    private void unpaintCell(int x, int y) {
        String positionKey = x + "-" + y;
        Rectangle cell = paintedRectangles.get(positionKey);

        if (cell != null) {
            cell.delete();
            cell = new Rectangle(x, y, 50, 50);
            cell.setColor(Color.BLACK);
            cell.draw();
            paintedRectangles.put(positionKey, cell);
        }
    }

    private void clear() {
        for (String key : paintedCell) {
            String[] parts = key.split("-");
            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);
            unpaintCell(x, y);
        }
        paintedCell.clear();
        paintedRectangles.clear();
    }

    private void save() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("drawing.txt"))) {
            for (String key : paintedCell) {
                writer.write(key + "," + colorToString(paintedRectangles.get(key).getColor()));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void load() {
        clear();
        try (BufferedReader reader = new BufferedReader(new FileReader("drawing.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String[] coordinates = parts[0].split("-");
                int x = Integer.parseInt(coordinates[0]);
                int y = Integer.parseInt(coordinates[1]);
                Color color = stringToColor(parts[1]);
                currentColor = color;
                paintCell(x, y);
                paintedCell.add(parts[0]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String colorToString(Color color) {
        if (color == Color.BLUE) {
            return "BLUE";
        } else if (color == Color.BLACK) {
            return "BLACK";
        } else if (color == Color.YELLOW) {
            return "YELLOW";
        } else if (color == Color.RED) {
            return "RED";
        } else if (color == Color.PINK) {
            return "PINK";
        }
        return "BLACK";
    }

    private Color stringToColor(String colorStr) {
        switch (colorStr) {
            case "BLUE":
                return Color.BLUE;
            case "BLACK":
                return Color.BLACK;
            case "YELLOW":
                return Color.YELLOW;
            case "RED":
                return Color.RED;
            case "PINK":
                return Color.PINK;
            default:
                return Color.BLACK;
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
    }
}