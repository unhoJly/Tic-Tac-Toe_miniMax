package tictactoe;

import java.util.ArrayList;
import java.util.Random;

public class AI extends Player {
    ArrayList<Integer> easyX = new ArrayList<>();
    ArrayList<Integer> mediumX = new ArrayList<>();
    ArrayList<Integer> easyY = new ArrayList<>();
    ArrayList<Integer> mediumY = new ArrayList<>();
    Random random = new Random();
    String level;
    long xoOccurrencesCounter, enemyXOOccurrencesCounter;
    char enemyXO;

    public AI(String level, char xo) {
        this.level = level;
        this.xo = xo;

        if (xo == 'X')
            enemyXO = 'O';
        else
            enemyXO = 'X';
    }

    @Override
    public void turn() {
        System.out.println("Making move level \"" + level + "\"");

        if (level.equals("hard"))
            moveHard();

        if (!rowsScanner() && !level.equals("hard"))
            if (!columnsScanner())
                if (!diagonalScanner()) {
                    switch (level) {
                        case "easy":
                            if (!easyX.isEmpty())
                                Main.cells.setCharAt(easyX.get(random.nextInt(easyX.size())), xo);
                            else if (easyX.isEmpty() && !easyY.isEmpty())
                                Main.cells.setCharAt(easyY.get(random.nextInt(easyY.size())), xo);
                            else if (easyX.isEmpty() && easyY.isEmpty() && !mediumX.isEmpty())
                                Main.cells.setCharAt(mediumX.get(random.nextInt(mediumX.size())), xo);
                            else if (easyX.isEmpty() && mediumX.isEmpty() && easyY.isEmpty() && !mediumY.isEmpty())
                                Main.cells.setCharAt(mediumY.get(random.nextInt(mediumY.size())), xo);
                            break;
                        case "medium":
                            if (!mediumX.isEmpty())
                                Main.cells.setCharAt(mediumX.get(random.nextInt(mediumX.size())), xo);
                            else if (mediumX.isEmpty() && !mediumY.isEmpty())
                                Main.cells.setCharAt(mediumY.get(random.nextInt(mediumY.size())), xo);
                            else if (mediumX.isEmpty() && mediumY.isEmpty() && !easyX.isEmpty())
                                Main.cells.setCharAt(easyX.get(random.nextInt(easyX.size())), xo);
                            else if (mediumX.isEmpty() && mediumY.isEmpty() && easyX.isEmpty() && !easyY.isEmpty())
                                Main.cells.setCharAt(easyY.get(random.nextInt(easyY.size())), xo);
                    }

                    easyX.clear();
                    mediumX.clear();
                    easyY.clear();
                    mediumY.clear();
                }
    }

    private boolean rowsScanner() {
        String str;

        for (int i = 0; i < 7; i += 3) {
            str = Main.cells.substring(i, i + 3);
            xoOccurrencesCounter = str.chars().filter(ch -> ch == xo).count();
            enemyXOOccurrencesCounter = str.chars().filter(ch -> ch == enemyXO).count();

            for (int j = i; j < i + 3; j++) {
                if (Main.cells.charAt(j) == 32 && xoOccurrencesCounter == 2 && level.equals("medium")) {
                    Main.cells.setCharAt(j, xo);
                    Main.resultCount = xo + " wins";
                    return true;
                } else if (!str.contains(" "))
                    break;
                else if (Main.cells.charAt(j) == 32 && xoOccurrencesCounter == 1)
                    mediumX.add(j);
                else if (Main.cells.charAt(j) == 32 && xoOccurrencesCounter == 0)
                    easyX.add(j);

                if (Main.cells.charAt(j) == 32 && enemyXOOccurrencesCounter == 2 && level.equals("medium")) {
                    Main.cells.setCharAt(j, xo);
                    return true;
                }
            }
        }

        return false;
    }

    private boolean columnsScanner() {
        StringBuilder sbAI = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            sbAI.append(Main.cells.charAt(i)).append(Main.cells.charAt(i + 3)).append(Main.cells.charAt(i + 6));
            xoOccurrencesCounter = sbAI.chars().filter(ch -> ch == xo).count();
            enemyXOOccurrencesCounter = sbAI.chars().filter(ch -> ch == enemyXO).count();

            for (int j = i; j < i + 7; j += 3) {
                if (Main.cells.charAt(j) == 32 && xoOccurrencesCounter == 2 && level.equals("medium")) {
                    Main.cells.setCharAt(j, xo);
                    Main.resultCount = xo + " wins";
                    return true;
                } else if (!sbAI.toString().contains(" "))
                    break;
                else if (Main.cells.charAt(j) == 32 && xoOccurrencesCounter == 1)
                    mediumX.add(j);
                else if (Main.cells.charAt(j) == 32 && xoOccurrencesCounter == 0)
                    easyX.add(j);

                if (Main.cells.charAt(j) == 32 && enemyXOOccurrencesCounter == 2 && level.equals("medium")) {
                    Main.cells.setCharAt(j, xo);
                    return true;
                }
            }

            sbAI = new StringBuilder();
        }

        return false;
    }

    private boolean diagonalScanner() {
        StringBuilder d1 = new StringBuilder();
        StringBuilder d2 = new StringBuilder();
        long xoOccurrencesCounter1, enemyXOOccurrencesCounter1, xoOccurrencesCounter2, enemyXOOccurrencesCounter2;

        d1.append(Main.cells.charAt(0)).append(Main.cells.charAt(4)).append(Main.cells.charAt(8));
        xoOccurrencesCounter1 = d1.chars().filter(ch -> ch == xo).count();
        enemyXOOccurrencesCounter1 = d1.chars().filter(ch -> ch == enemyXO).count();

        for (int i = 0; i < 9; i += 4) {
            if (Main.cells.charAt(i) == 32 && xoOccurrencesCounter1 == 2 && level.equals("medium")) {
                Main.cells.setCharAt(i, xo);
                Main.resultCount = xo + " wins";
                return true;
            } else if (!d1.toString().contains(" "))
                break;
            else if (Main.cells.charAt(i) == 32 && xoOccurrencesCounter1 == 1)
                mediumX.add(i);
            else if (Main.cells.charAt(i) == 32 && xoOccurrencesCounter1 == 0)
                easyX.add(i);

            if (Main.cells.charAt(i) == 32 && enemyXOOccurrencesCounter1 == 2 && level.equals("medium")) {
                Main.cells.setCharAt(i, xo);
                return true;
            }
        }

        d2.append(Main.cells.charAt(2)).append(Main.cells.charAt(4)).append(Main.cells.charAt(6));
        xoOccurrencesCounter2 = d2.chars().filter(ch -> ch == xo).count();
        enemyXOOccurrencesCounter2 = d2.chars().filter(ch -> ch == enemyXO).count();

        for (int i = 2; i < 7; i += 2) {
            if (Main.cells.charAt(i) == 32 && xoOccurrencesCounter2 == 2 && level.equals("medium")) {
                Main.cells.setCharAt(i, xo);
                Main.resultCount = xo + " wins";
                return true;
            } else if (!d2.toString().contains(" "))
                break;
            else if (Main.cells.charAt(i) == 32 && xoOccurrencesCounter2 == 1)
                mediumX.add(i);
            else if (Main.cells.charAt(i) == 32 && xoOccurrencesCounter2 == 0)
                easyX.add(i);

            if (Main.cells.charAt(i) == 32 && enemyXOOccurrencesCounter2 == 2 && level.equals("medium")) {
                Main.cells.setCharAt(i, xo);
                return true;
            }
        }

        return false;
    }

    private void moveHard() {
        int score;
        int bestScore = Integer.MIN_VALUE;
        int bestPosition = 0;

        for (int i = 0; i < Main.cells.length(); i++) {
            if (Main.cells.charAt(i) == ' ') {
                StringBuilder cellsHard = new StringBuilder(Main.cells);
                cellsHard.setCharAt(i, xo);
                score = miniMax(cellsHard, false);
                if (score > bestScore) {
                    bestScore = score;
                    bestPosition = i;
                }
            }
        }

        Main.cells.setCharAt(bestPosition, xo);
    }

    private int miniMax(StringBuilder cellsHard, boolean isMaximizing) {
        if ((xo + " wins").equals(Main.resultCount(cellsHard)))
            return 10;
        else if ((enemyXO + " wins").equals(Main.resultCount(cellsHard)))
            return -10;
        else if ("Draw".equals(Main.resultCount(cellsHard)))
            return 0;

        int bestScore = isMaximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        for (int i = 0; i < cellsHard.length(); i++) {
            if (cellsHard.charAt(i) == ' ') {
                StringBuilder board = new StringBuilder(cellsHard);
                board.setCharAt(i, isMaximizing ? xo : enemyXO);
                int score = miniMax(board, !isMaximizing);
                bestScore = isMaximizing ? Math.max(score, bestScore) : Math.min(score, bestScore);
            }
        }

        return bestScore;
    }
}