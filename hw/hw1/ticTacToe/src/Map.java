
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import static java.util.Arrays.deepToString;

public class Map extends JPanel {

    private int panelWidth;
    private int panelHeight;
    private int cellHeight;
    private int cellWidth;
    private int fieldSizeX;
    private int fieldSizeY;

    private static final Random RANDOM = new Random();
    private static final int DOT_PADDING = 5;
    private int gameOverType;
    private static final int STATE_DRAW = 0;
    private static final int STATE_WIN_HUMAN = 1;
    private static final int STATE_WIN_AI = 2;
    private static final String MSG_WIN_HUMAN = "You WIN";
    private static final String MSG_WIN_AI = "AI win";
    private static final String MSG_WIN_DRAW = "DRAW";
    private final int HUMAN_DOT = 1;
    private final int AI_DOT = 2;
    private final int EMPTY_DOT = 0;
    private char [][] field;
    private boolean isGameOver;
    private boolean isInitialized;

    Map(int fieldSizeX, int fieldSizeY) {

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                update(e);
            }
        });
        isInitialized = false;
        this.fieldSizeX = fieldSizeX;
        this.fieldSizeY = fieldSizeY;
    }

    private void initMap(int fieldSizeX, int fieldSizeY) {

        field = new char[fieldSizeY][fieldSizeX];
        for (int i = 0; i < fieldSizeY; i++){
            for (int j = 0; j < fieldSizeX; j++){
                field[i][j] = EMPTY_DOT;
            }
        }
        System.out.println(deepToString(field));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }

    private void render(Graphics g) {
        if (!isInitialized) return;

        panelWidth = getWidth();
        panelHeight = getHeight();
        cellHeight = panelHeight / fieldSizeX;
        cellWidth = panelWidth / fieldSizeY;

        g.setColor(Color.BLACK);

        for (int h = 0; h < fieldSizeX; h++) {
            int y = h * cellHeight;
            g.drawLine(0, y, panelWidth, y);
        }

        for (int w = 0; w < fieldSizeY; w++) {
            int x = w * cellWidth;
            g.drawLine(x, 0, x, panelHeight);
        }

        for (int y = 0; y < fieldSizeY; y++){
            for (int x = 0; x < fieldSizeX; x++){
                if (field[y][x] == EMPTY_DOT) continue;

                if (field[y][x] == HUMAN_DOT) {

                    g.setColor(Color.BLUE);
                    g.fillOval(x * cellWidth + DOT_PADDING,
                            y * cellHeight + DOT_PADDING,
                            cellWidth - DOT_PADDING * 2,
                            cellHeight - DOT_PADDING *2);

                }else if (field[y][x] == AI_DOT) {

                    g.setColor(new Color(0xff0000));
                    g.fillOval(x * cellWidth + DOT_PADDING,
                            y * cellHeight + DOT_PADDING,
                            cellWidth - DOT_PADDING * 2,
                            cellHeight - DOT_PADDING *2);

                }else
                    throw new RuntimeException("Unexpected value " + field[y][x] +
                            " in cell: x= " + x + " y=" + y);
            }
        }
        if (isGameOver) showMessageGameOver(g);
    }

    private boolean isValidCell(int x, int y){
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }

    private boolean isEmptyCell(int x, int y){
        return field[y][x] == EMPTY_DOT;
    }

    private void aiTurn() {

        if (turnAIWinCell()) return;
        if (turnHumanWinCell()) return;

        int x, y;
        do {
            x = RANDOM.nextInt(fieldSizeX);
            y = RANDOM.nextInt(fieldSizeY);
        } while (!isEmptyCell(x, y));
        field[y][x] = AI_DOT;

    }

    private boolean turnHumanWinCell() {
        for (int i = 0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++) {
                if (isEmptyCell(j, i)) {
                    field[i][j] = HUMAN_DOT;
                    if (checkWin((char) HUMAN_DOT)) {
                        field[i][j] = AI_DOT;
                        return true;
                    }
                    field[i][j] = EMPTY_DOT;
                }
            }
        }
        return false;
    }

    private boolean turnAIWinCell() {
        for (int i = 0; i < fieldSizeY; i++){
            for (int j = 0; j < fieldSizeX; j++){
                if (isEmptyCell(j,i)){
                    field[i][j] = AI_DOT;
                    if (checkWin((char) AI_DOT)) return true;
                    field[i][j] = EMPTY_DOT;
                }
            }
        }
        return false;
    }

    private void update(MouseEvent e) {

        if (isGameOver || !isInitialized) return;

        int cellX = e.getX() / cellWidth;
        int cellY = e.getY() / cellHeight;
        repaint();

        if (!isValidCell(cellX, cellY) || !isEmptyCell(cellX, cellY)) return;
        field[cellY][cellX] = HUMAN_DOT;


        if (checkEndGame(HUMAN_DOT, STATE_WIN_HUMAN)) return;

        aiTurn();
        repaint();
        checkEndGame(AI_DOT, STATE_WIN_AI);
    }

    private boolean checkEndGame(int dot, int gameOverType) {
        if (checkWin((char) dot)){

            this.gameOverType = gameOverType;
            isGameOver = true;
            repaint();
            return true;
        }
        if (isMapFull()){

            this.gameOverType = STATE_DRAW;
            isGameOver = true;
            repaint();
            return true;
        }
        return false;
    }

    void startNewGame(boolean mode, int fSzX, int fSzY, int wLen){

        System.out.printf("Mode: %b;\nSize: x=%d, y=%d;\nWin Length: %d", mode, fSzX, fSzY, wLen);

        fieldSizeX = fSzX;
        fieldSizeY = fSzY;
        initMap(fieldSizeX, fieldSizeY);
        isGameOver = false;
        isInitialized = true;
        repaint();
    }

    public boolean checkWin(char player) {
        int size = field.length;

        // Check rows
        for (char[] chars : field) {
            boolean win = true;
            for (int j = 0; j < size; j++) {
                if (chars[j] != player) {
                    win = false;
                    break;
                }
            }
            if (win) {
                return true;
            }
        }

        // Check columns
        for (int j = 0; j < size; j++) {
            boolean win = true;
            for (char[] chars : field) {
                if (chars[j] != player) {
                    win = false;
                    break;
                }
            }
            if (win) {
                return true;
            }
        }

        // Check diagonals
        boolean win = true;
        for (int i = 0; i < size; i++) {
            if (field[i][i] != player) {
                win = false;
                break;
            }
        }
        if (win) {
            return true;
        }

        win = true;
        for (int i = 0; i < size; i++) {
            if (field[i][size - i - 1] != player) {
                win = false;
                break;
            }
        }
        return win;

        // No win
    }
    private boolean isMapFull() {
        for (int i =0; i < fieldSizeY; i++) {
            for (int j = 0; j < fieldSizeX; j++){
                if (field[i][j] == EMPTY_DOT) return false;
            }
        }
        return true;
    }
    private void showMessageGameOver(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 200, getWidth(), 70);
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Times new roman", Font.BOLD, 48));

        switch (gameOverType){
            case STATE_DRAW -> g.drawString(MSG_WIN_DRAW, 180, getHeight() / 2);
            case STATE_WIN_AI -> g.drawString(MSG_WIN_AI, 180, getHeight() / 2);
            case STATE_WIN_HUMAN -> g.drawString(MSG_WIN_HUMAN, 180, getHeight() / 2);
            default -> throw new RuntimeException("Unexpected gameOver state: " + gameOverType);
        }
    }
}
