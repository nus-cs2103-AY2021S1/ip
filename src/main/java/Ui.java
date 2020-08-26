package main.java;

import java.util.Scanner;

public class Ui {
    private final String BORDER = "____________________________________________________________";
    private final String LOGO = " ____        ____\n"
            + "|  _ \\  ___ |  _ \\\n"
            + "| | | |/ _ \\| | | |\n"
            + "| |_| || __/| |_| |\n"
            + "|____/ \\___||____/\n";
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Hello I am\n" + LOGO + "\n" + "Feed me some stuff! :3\n");
    }

    public String readCommand() {

        String command = sc.nextLine();
        return command;

    }

    public void showLoadingError() {
        System.out.println("Error loading file!");
    }

    public void printInBorder(String message) {
        System.out.println(String.format("%s\n%s\n%s", BORDER, message, BORDER));
    }

}
