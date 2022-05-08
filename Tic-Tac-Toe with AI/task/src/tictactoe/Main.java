package tictactoe;

import java.util.Scanner;

public class Main {
    static StringBuilder cells = new StringBuilder("         ");
    static Player player1, player2;
    static String resultCount, beginning;
    static String[] params;
    static int x = 0, y = 0;
    static boolean switchXO = true;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Input command: ");
            beginning = scanner.nextLine();

            if (beginning.equals("exit"))
                break;

            params = beginning.split(" ");

            if ((params.length == 3 && beginning.contains("start"))
                    && (beginning.contains("user")
                    || (beginning.contains("easy") || beginning.contains("medium") || beginning.contains("hard")))) {
                player1 = playerCreation(params[1], 'X');
                player2 = playerCreation(params[2], 'O');
                fieldShow();
                game();
                break;
            } else {
                System.out.println("Bad parameters!");
            }
        }
    }

    public static void game() {
        do {
            if (switchXO) {
                player1.turn();
                switchXO = false;
            } else {
                player2.turn();
                switchXO = true;
            }

            fieldShow();
            resultCount = resultCount(cells);

        } while (resultCount == null || (!resultCount.equals("Draw") && !resultCount.contains("wins")));

        System.out.println(resultCount);
    }

    public static Player playerCreation(String playerType, char xo) {
        if (playerType.equals("user"))
            return new Human(xo);
        else
            return new AI(playerType, xo);
    }

    public static String resultCount(StringBuilder cells) {
        String result = null;
        int rX = 0, rO = 0, cX = 0, cO = 0, d0, d1;
        int r = 0, c = 0;

        //считаем Х и О в диагоналях
        d0 = cells.charAt(0) + cells.charAt(4) + cells.charAt(8);
        d1 = cells.charAt(2) + cells.charAt(4) + cells.charAt(6);

        //считаем Х и О в строках
        for (int i = 0; i < 7; i += 3) {
            for (int j = i; j < i + 3; j++)
                r += cells.charAt(j);

            if (r == 264) {
                rX++;
                r = 0;
            } else if (r == 237) {
                rO++;
                r = 0;
            } else
                r = 0;
        }

        //считаем Х и О в столбцах
        for (int i = 0; i < 3; i++) {
            for (int j = i; j < i + 7; j += 3)
                c += cells.charAt(j);

            if (c == 264) {
                cX++;
                c = 0;
            } else if (c == 237) {
                cO++;
                c = 0;
            } else
                c = 0;
        }

        //считаем итог
        if ((d0 != 264 && d1 != 264 && d0 != 237 && d1 != 237)
                && rX == 0 && rO == 0 && cX == 0 && cO == 0 &&
                !cells.toString().contains(" "))
            result = "Draw";
        else if (d0 == 237 || d1 == 237 || rO == 1 || cO == 1)
            result = "O wins";
        else if (d0 == 264 || d1 == 264 || rX == 1 || cX == 1)
            result = "X wins";

        return result;
    }

    public static void fieldShow() {
        System.out.println("---------");
        System.out.println("| " + cells.charAt(0) + " " + cells.charAt(1) + " " + cells.charAt(2) + " |");
        System.out.println("| " + cells.charAt(3) + " " + cells.charAt(4) + " " + cells.charAt(5) + " |");
        System.out.println("| " + cells.charAt(6) + " " + cells.charAt(7) + " " + cells.charAt(8) + " |");
        System.out.println("---------");
    }
}