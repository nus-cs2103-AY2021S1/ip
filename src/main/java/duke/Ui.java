package main.java.duke;

import java.util.Scanner;

public class Ui {
    private static final String BORDER = "____________________________________________________________";
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

    public void showError(String message) {
        System.out.println(message);
    }

    public void showLine() {
        System.out.println(BORDER);
    }

    public void printMessage(String message) {
        System.out.println(String.format(message));
    }

}
