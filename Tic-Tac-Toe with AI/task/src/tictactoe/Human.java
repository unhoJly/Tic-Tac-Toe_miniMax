package tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Human extends Player {
    Scanner scanner = new Scanner(System.in);
    int cell;

    public Human(char xo){
        this.xo = xo;
    }

    @Override
    public void turn() {
        while (true) {
            System.out.print("Enter the coordinates: ");

            //ставим Х или О по указанным координатам, проверяем ввод, обрабатываем невалидный ввод
            try {
                Main.x = scanner.nextInt();
                Main.y = scanner.nextInt();
                cell = (Main.x - 1) * 3 + (Main.y - 1);
                if (Main.x < 1 || Main.x > 3 || Main.y < 1 || Main.y > 3) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    Main.x = 0;
                    Main.y = 0;
                } else if (Main.cells.charAt(cell) != ' ') {
                    System.out.println("This cell is occupied! Choose another one!");
                    Main.x = 0;
                    Main.y = 0;
                } else {
                    Main.cells.setCharAt(cell, xo);
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("You should enter numbers!");
                scanner = new Scanner(System.in);
            }
        }
    }
}