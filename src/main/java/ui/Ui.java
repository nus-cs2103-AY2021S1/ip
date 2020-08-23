package ui;

import java.util.Scanner;

public class Ui {

    private final Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public static void printCommand(String textToAppend) {
        System.out.println(textToAppend);
    }

    public String readCommand() {
        return sc.nextLine();
    }
}