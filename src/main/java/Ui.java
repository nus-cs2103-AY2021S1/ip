package main.java;

import java.util.Scanner;

public class Ui {
    private Scanner sc = new Scanner(System.in);

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
    }

    public void showLoadingError() {
        System.out.println("Failed to load from saved data\n");
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showLine() {
        System.out.println("\t____________________________________________________________");
    }

    public void showError(String err) {
        System.out.printf("\t%s\n", err);
    }

    public void showMessage(String message) {
        System.out.print(message);
    }
}
